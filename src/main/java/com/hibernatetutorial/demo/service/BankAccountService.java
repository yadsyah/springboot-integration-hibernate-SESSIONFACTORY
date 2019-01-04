package com.hibernatetutorial.demo.service;

import com.hibernatetutorial.demo.entity.BankAccount;
import com.hibernatetutorial.demo.model.BankAccountInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankAccountService {

    @Autowired
    private SessionFactory sessionFactory;

    public BankAccountService() {

    }

    public BankAccount findById(Long id){
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(BankAccount.class,id);
    }

    public List<BankAccount> listBankAccounts(){
        String sql = "Select e from "+BankAccount.class.getName()+" e";
        Session session = this.sessionFactory.getCurrentSession();
        Query<BankAccount> query = session.createQuery(sql,BankAccount.class);
        return query.getResultList();
    }

    public List<BankAccountInfo> listBankAccountInfo(){
        String sql = "Select new "+BankAccountInfo.class.getName()+ "(e.id , e.fullName, e.balance)" +
                " from"+BankAccount.class.getName()+ " e";
        Session session = this.sessionFactory.getCurrentSession();
        Query<BankAccountInfo> query = session.createQuery(sql,BankAccountInfo.class);
        session.close();
        return query.getResultList();


    }
}
