package com.hibernatetutorial.demo.controller;

import com.hibernatetutorial.demo.dao.BankAccountDAO;
import com.hibernatetutorial.demo.entity.BankAccount;
import com.hibernatetutorial.demo.model.BankAccountInfo;
import com.hibernatetutorial.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @GetMapping("/banks")
    public ResponseEntity<?> getAllAccountBank(){
        List<BankAccount> bankAccountInfoList = bankAccountDAO.listBankAccounts();
        return ResponseEntity.ok(bankAccountInfoList);
    }
}
