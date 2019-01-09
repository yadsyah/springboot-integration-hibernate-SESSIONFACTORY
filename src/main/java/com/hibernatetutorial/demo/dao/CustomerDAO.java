package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.payload.request.CustomerRequest;
import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.CustomerDetail;
import com.hibernatetutorial.demo.payload.response.CustomerResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
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
        query.select(customerRoot).where(criteriaBuilder.equal(customerRoot.get("id"), id));
        Query<Customer> q = session.createQuery(query);
        return q.getSingleResult();
    }


    public List<Object[]> joinCustomerAndDetail() {
        try {
            CriteriaBuilder criteriaBuilder = getSessionFactory().getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
            Root<CustomerDetail> customerDetailRoot = criteriaQuery.from(CustomerDetail.class);
            criteriaQuery.multiselect(customerRoot, customerDetailRoot);
            criteriaQuery.where(criteriaBuilder.equal(customerRoot.get("customerDetailId"),
                    customerDetailRoot.get("customerDetailId")));
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
            criteriaQuery.multiselect(customerRoot, customerDetailRoot);
            criteriaQuery.where(cb.equal(customerRoot.get("customerId"), id),
                    cb.equal(customerRoot.get("customerDetailId"), customerDetailRoot.get("customerDetailId")));
            Query<Object[]> query = getSessionFactory().createQuery(criteriaQuery);
            Object[] objects = query.getSingleResult();
            LOGGER.info("objects : {}", objects);
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void persistCustomerAndDetail(Customer customer, CustomerDetail customerDetail) {
        try {

            Session session = getSessionFactory();
            session.getTransaction();
            session.save(customerDetail);
            session.save(customer);
            LOGGER.info("Session : {}", session.isOpen());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error : {}", e.getMessage());
        }
    }

    public void persistCustomer(CustomerDetail customerDetail) {
        try {

            Session session = getSessionFactory();
            session.getTransaction();
            session.persist(customerDetail);
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
}
