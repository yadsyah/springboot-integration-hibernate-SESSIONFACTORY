package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.CustomerDetail;
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
import java.util.List;

@Repository
@Transactional(value = "sfTX")
public class CustomerDetailDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDetailDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public List<CustomerDetail> findAllCustomerDetails() {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CustomerDetail> query = criteriaBuilder.createQuery(CustomerDetail.class);
            Root<CustomerDetail> customerDetailRoot = query.from(CustomerDetail.class);
            query.select(customerDetailRoot);
            Query<CustomerDetail> customerDetailQuery = session.createQuery(query);
            if (customerDetailQuery.getResultList().isEmpty()) {
                return null;
            }
            return customerDetailQuery.setMaxResults(100).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerDetail findByIdCustomerDetail(Long customerDetail) {
        try {
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CustomerDetail> query = criteriaBuilder.createQuery(CustomerDetail.class);
            Root<CustomerDetail> customerDetailRoot = query.from(CustomerDetail.class);
            query.select(customerDetailRoot).where(criteriaBuilder.equal(customerDetailRoot.get("customerDetailId"), customerDetail));
            Query<CustomerDetail> customerDetailQuery = session.createQuery(query);
            if (!customerDetailQuery.getResultList().isEmpty()) {
                return customerDetailQuery.getSingleResult();
            }
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            return null;
        } catch (Exception e) {
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            e.printStackTrace();
            return null;
        }
    }

    public void persistCustomerDetail(CustomerDetail customerDetail) {
        try {
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            Session session = getSessionFactory();
            session.persist(customerDetail);
        } catch (Exception e) {
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            e.printStackTrace();
        }
    }

    public void saveOrUpdateCustomerDetail(CustomerDetail customerDetail) {
        try {
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            Session session = getSessionFactory();
            session.saveOrUpdate(customerDetail);
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
        } catch (Exception e) {
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            e.printStackTrace();
        }
    }

    public void saveCustomerDetail(CustomerDetail customerDetail) {
        try {
            Session session = getSessionFactory();
            session.save(customerDetail);
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
        } catch (Exception e) {
            LOGGER.info("Session isOpen : {}",sessionFactory.isOpen());
            e.printStackTrace();
        }
    }
    public void saveOrUpdate(CustomerDetail customerDetail){
        try{
            Session session = getSessionFactory();
            session.saveOrUpdate(customerDetail);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
