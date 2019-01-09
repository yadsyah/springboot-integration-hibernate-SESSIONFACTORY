package com.hibernatetutorial.demo.dao;

import com.hibernatetutorial.demo.entity.BankAccount;
import com.hibernatetutorial.demo.exception.BankTransactionException;
import com.hibernatetutorial.demo.exception.ResourceNotFoundException;
import com.hibernatetutorial.demo.model.BankAccountInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class BankAccountDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public BankAccountDAO() {
    }

    public BankAccount findById(Long id) throws ResourceNotFoundException {
        Session session = getSession();
        Criteria criteria = session.createCriteria(BankAccount.class).add(Restrictions.eq("id",id));
        BankAccount account = (BankAccount) criteria.uniqueResult();
        if(account==null&&"".equals(account)){
            throw new ResourceNotFoundException("Data Not Found Id '"+id+"'");
        }
        return account;
    }

    public List<BankAccount> listBankAccounts(){
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(BankAccount.class);
        return criteria.list();
    }
    public void saveBankAccount(BankAccount bankAccount){
        Session session = null;
        try{
            session = getSession();
            BankAccount newAccount = new BankAccount();
            newAccount.setBalance(bankAccount.getBalance());
            newAccount.setFullName(bankAccount.getFullName());
            session.save(newAccount);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<BankAccount> listBankAccountsCB() {
        Session session = null;
        session = getSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<BankAccount> query = criteriaBuilder.createQuery(BankAccount.class);
            Root<BankAccount> root = query.from(BankAccount.class);
            query.select(root);
            Query<BankAccount> bankAccountQuery = session.createQuery(query);
            List<BankAccount> bankAccounts = bankAccountQuery.getResultList();
            for (BankAccount account : bankAccounts) {
                LOGGER.info("Fullname : {} | Balance : {}", account.getFullName(),account.getBalance());
            }
            return bankAccounts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BankAccount getOneBankAccount(Long id) throws ResourceNotFoundException {
        try{
            CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
            CriteriaQuery<BankAccount> query = criteriaBuilder.createQuery(BankAccount.class);
            Root<BankAccount> bankAccountRoot = query.from(BankAccount.class);
            query.select(bankAccountRoot).where(criteriaBuilder.equal(bankAccountRoot.get("id"),id));
            Query<BankAccount> q = getSession().createQuery(query);
            return q.getSingleResult();
        } catch (NoResultException e){
            throw new ResourceNotFoundException(id.toString());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "Select new " + BankAccountInfo.class.getName() + "(e.id , e.fullName, e.balance)" +
                " from" + BankAccount.class.getName() + " e";
        Session session = getSession();
        Query<BankAccountInfo> query = session.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }

    public void addAmount(Long id, double amount) throws BankTransactionException, ResourceNotFoundException {
        BankAccount account = this.findById(id);
        if(account==null){
            throw new BankTransactionException("Account not Found "+id);
        }

        double newBalance = account.getBalance() - amount;
        if(account.getBalance()+ amount < 0){
            throw new BankTransactionException("The money in the account '"+id+"' is not enough ("+account.getBalance()+")");
        }
        account.setBalance(newBalance);
    }



}
