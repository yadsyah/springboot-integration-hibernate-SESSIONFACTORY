package com.hibernatetutorial.demo.service;

import com.hibernatetutorial.demo.dao.BankAccountDAO;
import com.hibernatetutorial.demo.entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    BankAccountDAO bankAccountDAO;

    public List<BankAccount> getAllBankAccount(){
       return bankAccountDAO.listBankAccounts();
    }
}
