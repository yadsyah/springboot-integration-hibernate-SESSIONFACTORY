package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.TblItemProduct;
import com.hibernatetutorial.demo.entity.TblKategoriProduct;
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
public class TblKategoriProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSessionFactory(){
        return sessionFactory.getCurrentSession();
    }

    public void persistAndSave(TblKategoriProduct tblKategoriProduct){
        try{
             Session session = getSessionFactory();
             session.persist(tblKategoriProduct);
             session.save(tblKategoriProduct);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public TblKategoriProduct findById(Long id){
        try{
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblKategoriProduct.class);
            Root<TblKategoriProduct> kategoriProductRoot = criteriaQuery.from(TblKategoriProduct.class);
            criteriaQuery.select(kategoriProductRoot).where(criteriaBuilder.equal(kategoriProductRoot.get("idKategori"),id));
            Query<TblKategoriProduct> productQuery = session.createQuery(criteriaQuery);
            if(productQuery.getResultList().isEmpty()){
                return null;
            }
            return productQuery.getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<TblKategoriProduct> findAll(){
        try{
            Session session = getSessionFactory();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(TblKategoriProduct.class);
            Root<TblKategoriProduct> kategoriProductRoot = criteriaQuery.from(TblKategoriProduct.class);
            criteriaQuery.select(kategoriProductRoot);
            Query<TblKategoriProduct> productQuery = session.createQuery(criteriaQuery);
            if(productQuery.getResultList().isEmpty()){
                return null;
            }
            return productQuery.getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
