package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblTabungan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional(value = "sfTX")
public class TblTabunganDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory(){
        return sessionFactory.getCurrentSession();
    }
    public TblTabungan findById(String noRek){
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblTabungan> query = criteriaBuilder.createQuery(TblTabungan.class);
        Root<TblTabungan> tabunganRoot = query.from(TblTabungan.class);
        query.select(tabunganRoot).where(criteriaBuilder.equal(tabunganRoot.get("norek"),noRek));
        Query<TblTabungan> tabunganQuery = session.createQuery(query);
        if(tabunganQuery.getResultList().isEmpty()){
            return null;
        }
        return tabunganQuery.getSingleResult();
    }
}
