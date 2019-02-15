package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblRasioTarif;
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
public class TblRasioTarifDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public List<TblRasioTarif> findAll() {
        try{
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TblRasioTarif> query = criteriaBuilder.createQuery(TblRasioTarif.class);
            Root<TblRasioTarif> tarifRoot = query.from(TblRasioTarif.class);
            query.select(tarifRoot);
            query.orderBy(criteriaBuilder.desc(tarifRoot.get("tglBerlaku")));
            Query<TblRasioTarif> tarifQuery = session.createQuery(query);
            return tarifQuery.getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
