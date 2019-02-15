package com.hibernatetutorial.demo.dao;


import com.hibernatetutorial.demo.entity.TblHistoryOrder;
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

@Transactional(value = "sfTX")
@Repository
public class TblHistoryOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public TblHistoryOrder findById(Long idHistoryOrder) {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblHistoryOrder.class);
            Root<TblHistoryOrder> historyOrderRoot = criteriaQuery.from(TblHistoryOrder.class);
            criteriaQuery.select(historyOrderRoot).where(criteriaBuilder.equal(historyOrderRoot.get("idHistoryOrder"), idHistoryOrder));
            Query<TblHistoryOrder> orderQuery = session.createQuery(criteriaQuery);
            if (orderQuery.getResultList().isEmpty()) {
                return null;
            }
            return orderQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TblHistoryOrder> findByIdHistoryIn(Long idHistoryOrder) {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblHistoryOrder.class);
            Root<TblHistoryOrder> historyOrderRoot = criteriaQuery.from(TblHistoryOrder.class);
            criteriaQuery.select(historyOrderRoot).where(criteriaBuilder.equal(historyOrderRoot.get("idHistoryOrder"), idHistoryOrder));
            Query<TblHistoryOrder> orderQuery = session.createQuery(criteriaQuery);
            if (orderQuery.getResultList().isEmpty()) {
                return null;
            }
            return orderQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TblHistoryOrder> findAll() {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblHistoryOrder.class);
            Root<TblHistoryOrder> historyOrderRoot = criteriaQuery.from(TblHistoryOrder.class);
            criteriaQuery.select(historyOrderRoot);
            Query<TblHistoryOrder> historyOrderQuery = session.createQuery(criteriaQuery);
            if (historyOrderQuery.getResultList().isEmpty()) {
                return null;
            }
            return historyOrderQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void persistAndSave(TblHistoryOrder tblHistoryOrder) {
        try {
            Session session = getSessionFactory();
            session.persist(tblHistoryOrder);
            session.save(tblHistoryOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void persist(TblHistoryOrder tblHistoryOrder) {
        try {
            Session session = getSessionFactory();
            session.persist(tblHistoryOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(TblHistoryOrder tblHistoryOrder) {
        try {
            Session session = getSessionFactory();
            session.save(tblHistoryOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flush(){
        try{
            Session session = getSessionFactory();
            session.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}