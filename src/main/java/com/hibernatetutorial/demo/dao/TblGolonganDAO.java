package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblGolongan;
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
public class TblGolonganDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory(){
        return sessionFactory.getCurrentSession();
    }

    public List<TblGolongan> findAll(){
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblGolongan> query = criteriaBuilder.createQuery(TblGolongan.class);
        Root<TblGolongan> tblGolonganRoot = query.from(TblGolongan.class);
        query.select(tblGolonganRoot);
        query.orderBy(criteriaBuilder.asc(tblGolonganRoot.get("kodeGolongan")));
        Query<TblGolongan> golonganQuery = session.createQuery(query);
        return golonganQuery.getResultList();
    }

}
