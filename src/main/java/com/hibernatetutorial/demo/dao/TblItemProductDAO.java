package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblItemProduct;
import com.hibernatetutorial.demo.entity.TblProduct;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.List;

@Repository
@Transactional(value = "sfTX")
public class TblItemProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    public void persistAndSave(TblItemProduct tblItemProduct) {
        try {
            Session session = getSessionFactory();
            tblItemProduct.setCreatedAt(Instant.now());
            tblItemProduct.setUpdatedAt(Instant.now());
            session.save(tblItemProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TblItemProduct> findAllPaging(int firstResult, int maxResults) {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblItemProduct.class);
            Root<TblItemProduct> itemProductRoot = criteriaQuery.from(TblItemProduct.class);
            criteriaQuery.select(itemProductRoot);
            Query<TblItemProduct> productQuery = session.createQuery(criteriaQuery);
            if (productQuery.getResultList().isEmpty()) {
                return null;
            }
            productQuery.setFirstResult(firstResult);
            productQuery.setMaxResults(maxResults);
            return productQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TblItemProduct findById(String idItemProduct) {
        try {
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblItemProduct.class);
            Root<TblItemProduct> itemProductRoot = criteriaQuery.from(TblItemProduct.class);
            criteriaQuery.select(itemProductRoot).where(criteriaBuilder.equal(itemProductRoot.get("idItem"), idItemProduct));
            Query<TblItemProduct> query = session.createQuery(criteriaQuery);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateProduct(int quantity,String idProductItem){
        try{
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<TblItemProduct> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(TblItemProduct.class);
            Root rootItemProduct = criteriaUpdate.from(TblItemProduct.class);
            criteriaUpdate.set("stockProduct",quantity);
            criteriaUpdate.where(criteriaBuilder.equal(rootItemProduct.get("idItem"),idProductItem));
            session.createQuery(criteriaUpdate).executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveOrUpdate(TblItemProduct tblItemProduct){
        try{
            Session session = getSessionFactory();
            session.saveOrUpdate(tblItemProduct);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
