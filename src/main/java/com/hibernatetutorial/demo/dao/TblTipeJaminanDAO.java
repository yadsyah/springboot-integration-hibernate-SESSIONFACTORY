package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblTipeJaminan;
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
public class TblTipeJaminanDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory(){
        return sessionFactory.getCurrentSession();
    }

    public List<TblTipeJaminan> findAll(){
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblTipeJaminan> query = criteriaBuilder.createQuery(TblTipeJaminan.class);
        Root<TblTipeJaminan> tipeJaminanRoot = query.from(TblTipeJaminan.class);
        query.select(tipeJaminanRoot);
        Query<TblTipeJaminan> tipeJaminanQuery = session.createQuery(query);
        return tipeJaminanQuery.getResultList();
    }
}
