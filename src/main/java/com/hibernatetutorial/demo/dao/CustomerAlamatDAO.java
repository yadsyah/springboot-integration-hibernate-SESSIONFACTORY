package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.CustomerAlamat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
public class CustomerAlamatDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public void saveCustomer(CustomerAlamat customerAlamat) {
        try {
            Session session = getSessionFactory();
            session.save(customerAlamat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CustomerAlamat> findOneIdCustomerAlamatNotPK(Long id) {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CustomerAlamat> query = criteriaBuilder.createQuery(CustomerAlamat.class);
            Root<CustomerAlamat> customerAlamatRoot = query.from(CustomerAlamat.class);
            query.select(customerAlamatRoot).where(criteriaBuilder.equal(customerAlamatRoot.get("customerId"), id));
            Query<CustomerAlamat> q = session.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CustomerAlamat> findAllCustomerAlamat() {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CustomerAlamat> query = criteriaBuilder.createQuery(CustomerAlamat.class);
            Root<CustomerAlamat> customerAlamatRoot = query.from(CustomerAlamat.class);
            query.select(customerAlamatRoot);
            Query<CustomerAlamat> customerAlamatQuery = session.createQuery(query);
            return customerAlamatQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void persistCustomerAlamat(CustomerAlamat customerAlamat){
        try{
            Session session = getSessionFactory();
            session.save(customerAlamat);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
