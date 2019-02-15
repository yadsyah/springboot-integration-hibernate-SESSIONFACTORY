package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblOrder;
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
public class TblOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory(){
        return sessionFactory.getCurrentSession();
    }

    public List<TblOrder> findAll(){
        try{
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblOrder.class);
            Root<TblOrder> orderRoot = criteriaQuery.from(TblOrder.class);
            criteriaQuery.select(orderRoot);
            Query<TblOrder> orderQuery = session.createQuery(criteriaQuery);
            if(orderQuery.getResultList().isEmpty()){
                return null;
            }
            return orderQuery.getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public TblOrder findById(Long idOrder){
        try{
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblOrder.class);
            Root<TblOrder> orderRoot = criteriaQuery.from(TblOrder.class);
            criteriaQuery.select(orderRoot).where(criteriaBuilder.equal(orderRoot.get("idOrder"),idOrder));
            Query<TblOrder> orderQuery = session.createQuery(criteriaQuery);
            if(orderQuery.getResultList().isEmpty()){
                return null;
            }
            return orderQuery.getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void persistAndSave(TblOrder tblOrder){
        try{
            Session session = getSessionFactory();
            session.persist(tblOrder);
            session.save(tblOrder);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void persist(TblOrder tblOrder){
        try{
            Session session = getSessionFactory();
            session.persist(tblOrder);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void save(TblOrder tblOrder){
        try{
            Session session = getSessionFactory();
            session.save(tblOrder);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
