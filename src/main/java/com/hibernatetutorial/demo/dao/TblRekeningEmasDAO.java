package com.hibernatetutorial.demo.dao;


import com.hibernatetutorial.demo.entity.TblRekeningEmas;
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
public class TblRekeningEmasDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public TblRekeningEmas findByNorek(String noRek) {
        Session session = getSessionFactory();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TblRekeningEmas> query = criteriaBuilder.createQuery(TblRekeningEmas.class);
        Root<TblRekeningEmas> rekeningEmasRoot = query.from(TblRekeningEmas.class);
        query.select(rekeningEmasRoot).where(criteriaBuilder.equal(rekeningEmasRoot.get("norek"), noRek));
        Query<TblRekeningEmas> rekeningEmasQuery = session.createQuery(query);
        if (rekeningEmasQuery.getResultList().isEmpty()) {
            return null;
        }
        return rekeningEmasQuery.getSingleResult();
    }
}
