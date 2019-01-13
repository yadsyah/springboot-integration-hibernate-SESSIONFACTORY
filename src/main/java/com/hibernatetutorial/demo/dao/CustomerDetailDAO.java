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
@Transactional
public class CustomerDetailDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDetailDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public List<CustomerDetail> findAllCustomerDetails(){
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerDetail> query = criteriaBuilder.createQuery(CustomerDetail.class);
        Root<CustomerDetail> customerDetailRoot = query.from(CustomerDetail.class);
        query.select(customerDetailRoot);
        Query<CustomerDetail> customerDetailQuery = session.createQuery(query);
        return customerDetailQuery.setMaxResults(100).getResultList();
    }

    public CustomerDetail findById(Long id){
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerDetail> query = criteriaBuilder.createQuery(CustomerDetail.class);
        Root<CustomerDetail> customerDetailRoot = query.from(CustomerDetail.class);
        query.select(customerDetailRoot).where(criteriaBuilder.equal(customerDetailRoot.get("customerDetailId"),id));
        Query<CustomerDetail> customerDetailQuery = session.createQuery(query);
        return customerDetailQuery.getSingleResult();
    }

    public void persistCustomerDetail(CustomerDetail customerDetail){
        Session session = getSessionFactory();
        session.persist(customerDetail);
    }

    public void saveOrUpdateCustomerDetail(CustomerDetail customerDetail){
        Session session = getSessionFactory();
        session.beginTransaction();
        session.saveOrUpdate(customerDetail);
    }

    public void saveCustomerDetail(CustomerDetail customerDetail){
        Session session = getSessionFactory();
        session.beginTransaction();
        session.save(customerDetail);
    }

}
