package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.BankAccount;
import com.hibernatetutorial.demo.model.BankAccountInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class BankAccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public BankAccountDAO() {
    }

    public BankAccount findById(Long id) {
        Session session = getSession();
        return session.get(BankAccount.class, id);
    }

    public List<BankAccount> listBankAccounts(){
        String sql = "Select e from "+BankAccount.class.getName()+" e";
        Session session = this.sessionFactory.getCurrentSession();
        Query<BankAccount> query = session.createQuery(sql,BankAccount.class);
        return query.getResultList();
    }

    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "Select new " + BankAccountInfo.class.getName() + "(e.id , e.fullName, e.balance)" +
                " from" + BankAccount.class.getName() + " e";
        Session session = getSession();
        Query<BankAccountInfo> query = session.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }

}
