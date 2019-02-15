package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblRekeningNasabah;
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
public class TblRekeningNasabahDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public TblRekeningNasabah findByNorek(String noRek) {
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblRekeningNasabah> query = criteriaBuilder.createQuery(TblRekeningNasabah.class);
        Root<TblRekeningNasabah> rekeningNasabahRoot = query.from(TblRekeningNasabah.class);
        query.select(rekeningNasabahRoot).where(criteriaBuilder.equal(rekeningNasabahRoot.get("norek"), noRek));
        Query<TblRekeningNasabah> rekeningNasabahQuery = session.createQuery(query);
        if (rekeningNasabahQuery.getResultList().isEmpty()) {
            return null;
        }
        return rekeningNasabahQuery.getSingleResult();
    }
}
