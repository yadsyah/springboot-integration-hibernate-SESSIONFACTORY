package com.hibernatetutorial.demo.controller;

import com.hibernatetutorial.demo.payload.request.CustomerAlamatRequest;
import com.hibernatetutorial.demo.payload.request.CustomerRequest;
import com.hibernatetutorial.demo.constant.UtilityConstant;
import com.hibernatetutorial.demo.dao.BankAccountDAO;
import com.hibernatetutorial.demo.dao.CustomerDAO;
import com.hibernatetutorial.demo.entity.BankAccount;
import com.hibernatetutorial.demo.payload.response.CustomerResponse;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;
import javax.xml.crypto.Data;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/banks")
    public ResponseEntity<?> getAllAccountBank() {
        List<BankAccount> bankAccountInfoList = bankAccountDAO.listBankAccounts();
        return ResponseEntity.ok(bankAccountInfoList);
    }

    @GetMapping("/banksCB")
    public ResponseEntity<?> getAllAccountBanksCB() {
        List<BankAccount> bankAccounts = bankAccountDAO.listBankAccountsCB();
        return ResponseEntity.ok(bankAccounts);
    }

    @PostMapping(value = "/bank", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAccountBank(@RequestBody BankAccount bankAccount) {
        bankAccountDAO.saveBankAccount(bankAccount);
        HashMap<String, Object> context = new HashMap<>();
        context.put("Success", true);
        context.put("Data", "Success Save");
        return ResponseEntity.ok(context);
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<?> getOneAccount(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(bankAccountDAO.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("NotFound", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bankCB/{id}")
    public ResponseEntity<?> getOneAccountCB(@PathVariable("id") Long id) {
        HashMap<String, Object> context = new HashMap<>();
        try {
            BankAccount account = bankAccountDAO.getOneBankAccount(id);
            if (account != null) {
                context.put("Success", true);
                context.put("Data", account);
                return ResponseEntity.ok(account);
            }
        } catch (Exception e) {
            context.put("Success", false);
            context.put("Data", e.getMessage());
        }
        return new ResponseEntity(context, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCustomerAndDetail(@Valid @RequestBody CustomerRequest customer) {
        try {
            DataApiResponse response = customerService.saveCustomerAndDetail(customer);
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            }
            return new ResponseEntity(new DataApiResponse(false, response.getData().toString()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new DataApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/customersCB", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCustomers() {
        try {
            DataApiResponse customerResponses = customerService.getAllCustomerResponse();
            if (customerResponses.isSuccess()) {
                return ResponseEntity.ok(new DataApiResponse(true, customerResponses.getData()));
            }
            return new ResponseEntity(new DataApiResponse(true, customerResponses.getData()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(new DataApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/customerCB/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOneCustomerCB(@PathVariable("customerId") Long id) {
        Class methodName = new Object() {
        }.getClass();
        try {
            LOGGER.info(UtilityConstant.INFO_SERVICE.START_CONTROLLER.getDescription(methodName));
            DataApiResponse customerResponse = customerService.getOneCustomerAndDetailResponse(id);
            if (customerResponse.isSuccess()) {
                LOGGER.info(UtilityConstant.INFO_SERVICE.END_CONTOLLER.getDescription(methodName));
                return ResponseEntity.ok(new DataApiResponse(true, customerResponse.getData()));
            }
            LOGGER.info(UtilityConstant.INFO_SERVICE.END_CONTOLLER.getDescription(methodName));
            return new ResponseEntity(new DataApiResponse(false, customerResponse.getData()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(UtilityConstant.INFO_SERVICE.END_CONTOLLER.getDescription(methodName));
            return new ResponseEntity(new DataApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/add/alamat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAlamatByCustomer(@RequestBody @Valid CustomerAlamatRequest customerAlamatRequest) {
        try {
            DataApiResponse apiResponse = customerService.addAlamatByCustomerId(customerAlamatRequest);
            if (apiResponse.isSuccess()) {
                return ResponseEntity.ok(apiResponse);
            }
            return new ResponseEntity(new DataApiResponse(false, apiResponse.getData()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new DataApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/checkemail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkExistingEmail(@PathVariable("email") String email) {
        LinkedHashMap<String, Object> context = new LinkedHashMap<>();
        DataApiResponse apiResponse = customerService.checkEmailExisting(email);
        if (apiResponse.isSuccess()) {
            context.put("existingEmail", apiResponse.getData());
            return ResponseEntity.ok(context);
        }
        return new ResponseEntity(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/search/{customerName}/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchCustomer(@PathVariable(value = "customerName",required = true)String customerName){
        DataApiResponse apiResponse = customerService.searchCustomerByCustomerName(customerName);
        return ResponseEntity.ok(apiResponse);
    }
}
