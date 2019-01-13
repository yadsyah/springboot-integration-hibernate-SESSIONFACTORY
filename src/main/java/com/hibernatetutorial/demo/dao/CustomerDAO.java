package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.CustomerAlamat;
import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.CustomerDetail;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional(value = "sfTX")
public class CustomerDAO {


    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAO.class);
    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public List<Customer> findAllCustomer() {
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        query.select(customerRoot);
        Query<Customer> customerQuery = session.createQuery(query);
        LOGGER.info("Session : {}", session.isOpen());
        return customerQuery.setMaxResults(100).getResultList();
    }

    public Customer findByIdCustomer(Long id) {
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        query.select(customerRoot).where(criteriaBuilder.equal(customerRoot.get("customerId"), id));
        Query<Customer> q = session.createQuery(query);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getSingleResult();
    }

    public List<Object[]> joinCustomerAndDetail() {
        try {
            CriteriaBuilder criteriaBuilder = getSessionFactory().getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
            Root<CustomerDetail> customerDetailRoot = criteriaQuery.from(CustomerDetail.class);
            criteriaQuery.multiselect(customerRoot, customerDetailRoot);
            criteriaQuery.where(criteriaBuilder.equal(customerDetailRoot.get("customerDetailId"), customerRoot.get("customerDetailId")));
            Query<Object[]> query = getSessionFactory().createQuery(criteriaQuery);
            List<Object[]> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object[] findByIdJoinCustomerAndDetail(Long id) {
        try {
            CriteriaBuilder cb = getSessionFactory().getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);
            Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
            Root<CustomerDetail> customerDetailRoot = criteriaQuery.from(CustomerDetail.class);
            Root<CustomerAlamat> customerAlamatRoot = criteriaQuery.from(CustomerAlamat.class);

            criteriaQuery.multiselect(customerRoot, customerDetailRoot, customerAlamatRoot);
            criteriaQuery.where(cb.equal(customerRoot.get("customerId"), id),
                    cb.equal(customerRoot.get("customerDetailId"), customerDetailRoot.get("customerDetailId")),
                    cb.equal(customerAlamatRoot.get("customerId"), customerRoot.get("customerId")));
            Query<Object[]> query = getSessionFactory().createQuery(criteriaQuery);
            Object[] objects = query.getSingleResult();

            return objects;
        } catch (NoResultException e) {
            LOGGER.error("Error : Data Not Found! `{}`", id);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void persistCustomerAndDetail(Customer customer, CustomerDetail customerDetail) {
        try {
            Session session = getSessionFactory();
            session.save(customerDetail);
            session.save(customer);
            LOGGER.info("Session : {}", session.isOpen());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error : {}", e.getMessage());
        }
    }

    public Boolean existByEmail(String email) {
        try {
            CriteriaBuilder cb = getSessionFactory().getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> customerRoot = query.from(Customer.class);
            query.select(customerRoot).where(cb.equal(customerRoot.get("email"), email));
            Query<Customer> q = getSessionFactory().createQuery(query);
            if (q.getResultList().isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Boolean existByNoKTP(String noKTP) {
        try {
            CriteriaBuilder cb = getSessionFactory().getCriteriaBuilder();
            CriteriaQuery<CustomerDetail> query = cb.createQuery(CustomerDetail.class);
            Root<CustomerDetail> customerRoot = query.from(CustomerDetail.class);
            query.select(customerRoot).where(cb.equal(customerRoot.get("noKTP"), noKTP));
            Query<CustomerDetail> q = getSessionFactory().createQuery(query);
            if (q.getResultList().isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Customer> hibernateSearchCustomer(String customerName) {
        List<Customer> customerList = null;
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(getSessionFactory());
            QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Customer.class).get();
            org.apache.lucene.search.Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1)
                    .withPrefixLength(4).onFields("name").matching(customerName).createQuery();
            javax.persistence.Query hbQuery = fullTextSession.createFullTextQuery(luceneQuery, Customer.class);
            customerList = hbQuery.getResultList();
            return customerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Customer> findIdCustomerLikeCustomerName(String customerName) {
        List<Customer> customers = null;
        try {
            Session session = getSessionFactory();
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();
            QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder()
                    .forEntity(Customer.class).get();

            org.apache.lucene.search.Query lucenQuery = qb.keyword().onFields("name")
                    .matching(customerName).createQuery();

            javax.persistence.Query query = fullTextSession.createFullTextQuery(lucenQuery, Customer.class);
            customers = query.getResultList();
            if (!customers.isEmpty() && customers.size() != 0) {
                return customers;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
