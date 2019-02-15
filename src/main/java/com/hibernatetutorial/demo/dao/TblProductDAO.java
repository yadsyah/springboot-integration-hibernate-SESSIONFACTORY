package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblProduct;
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

@Repository
@Transactional(value = "sfTX")
public class TblProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory(){
       return sessionFactory.getCurrentSession();
    }

    public List<TblProduct> findAll(){
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblProduct> query = criteriaBuilder.createQuery(TblProduct.class);
        Root<TblProduct> productRoot = query.from(TblProduct.class);
        query.select(productRoot);
        Query<TblProduct> productQuery = session.createQuery(query);
        return productQuery.getResultList();
    }

}
