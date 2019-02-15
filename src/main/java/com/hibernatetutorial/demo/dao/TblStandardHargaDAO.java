package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblStandardHarga;
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
public class TblStandardHargaDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public List<TblStandardHarga> findAll() {
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblStandardHarga> query = criteriaBuilder.createQuery(TblStandardHarga.class);
        Root<TblStandardHarga> standardHargaRoot = query.from(TblStandardHarga.class);
        query.select(standardHargaRoot);
        Query<TblStandardHarga> standardHargaQuery = session.createQuery(query);
        if (standardHargaQuery.getResultList().isEmpty()) {
            return null;
        }
        return standardHargaQuery.getResultList();
    }
}
