package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblParam;
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

@Transactional(value = "sfTX")
@Repository
public class TblParamDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TblParamDAO.class);

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public List<TblParam> findAll() {
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblParam> query = criteriaBuilder.createQuery(TblParam.class);
        Root<TblParam> tblParamRoot = query.from(TblParam.class);
        query.select(tblParamRoot);
        Query<TblParam> tblParamQuery = session.createQuery(query);
        if (tblParamQuery.getResultList().isEmpty()) {
            return null;
        }
        return tblParamQuery.getResultList();
    }

    public TblParam findByKey(String key) {
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblParam> query = criteriaBuilder.createQuery(TblParam.class);
        Root<TblParam> paramRoot = query.from(TblParam.class);
        query.select(paramRoot).where(criteriaBuilder.equal(paramRoot.get("key"), key));
        Query<TblParam> paramQuery = session.createQuery(query);
        if (paramQuery.getResultList().isEmpty()) {
            return null;
        }
        return paramQuery.getSingleResult();
    }

}
