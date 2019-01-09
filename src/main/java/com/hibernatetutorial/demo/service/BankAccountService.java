package com.hibernatetutorial.demo.service;

import com.hibernatetutorial.demo.dao.BankAccountDAO;
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
public class BankAccountService {
    @Autowired
    BankAccountDAO bankAccountDAO;

    public List<BankAccount> getAllBankAccount(){
       return bankAccountDAO.listBankAccounts();
    }
}
