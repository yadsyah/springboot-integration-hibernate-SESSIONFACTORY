package com.hibernatetutorial.demo.service;

import com.hibernatetutorial.demo.constant.UtilityConstant;
import com.hibernatetutorial.demo.dao.CustomerDAO;
import com.hibernatetutorial.demo.dao.CustomerDetailDAO;
import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.CustomerDetail;
import com.hibernatetutorial.demo.exception.ParsingDateException;
import com.hibernatetutorial.demo.exception.ResourceNotFoundException;
import com.hibernatetutorial.demo.payload.request.CustomerRequest;
import com.hibernatetutorial.demo.payload.response.CustomerResponse;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    CustomerDetailDAO customerDetailDAO;

    // @Autowired(required=true)
    // CustomerRepositoryJPA customerRepositoryJPA;

    // @Autowired(required=true)
    // CustomerDetailRepository customerDetailRepository;

    public CustomerResponse getOneCustomerResponse(Long id) {
        try {
            Customer customer = customerDAO.findByIdCustomer(id);
            if (customer == null) {
                throw new ResourceNotFoundException(id.toString());
            }
            CustomerResponse customerResponse = new CustomerResponse();
            customer.setCustomerId(customer.getCustomerId());
            customerResponse.setName(customer.getName());
            customerResponse.setEmail(customer.getEmail());
            return customerResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CustomerResponse> getAllCustomerResponse() {
        try {
            List<Object[]> listObject = customerDAO.joinCustomerAndDetail();
            List<CustomerResponse> customerResponses = new ArrayList<>();

            for (Object[] objects : listObject) {
                Customer customer = (Customer) objects[0];
                CustomerDetail customerDetail = (CustomerDetail) objects[1];
                CustomerResponse customerResponse = new CustomerResponse();
                customerResponse.setCustomerId(customer.getCustomerId());
                customerResponse.setName(customer.getName());
                customerResponse.setEmail(customer.getEmail());
                customerResponse.setCustomerDetail(customerDetail != null ? customerDetail : null);
                customerResponses.add(customerResponse);
            }
            return customerResponses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerResponse getOneCustomerAndDetailResponse(Long id) {
        try {
            Object[] objects = customerDAO.findByIdJoinCustomerAndDetail(id);
            Customer customer = (Customer) objects[0];
            CustomerDetail customerDetail = (CustomerDetail) objects[1];
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setCustomerId(customer.getCustomerId());
            customerResponse.setEmail(customer.getEmail());
            customerResponse.setName(customer.getName());
            customerResponse.setCustomerDetail(customerDetail);
            return customerResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataApiResponse saveCustomerAndDetail(CustomerRequest customerRequest) {
        Object methodName = new Object(){}.getClass();
        try {
            if (!UtilityConstant.isDatePattern(customerRequest.getTglLahir())) {
                throw new ParsingDateException(customerRequest.getTglLahir());
            }
            LOGGER.info("existByEmail : {}", customerDAO.existByEmail(customerRequest.getEmail()));
            if (customerDAO.existByEmail(customerRequest.getEmail())) {
                return new DataApiResponse(false, "Exists By Email Customer");
            }
            if (customerDAO.existByNoKTP(customerRequest.getNoKTP().toString())) {
                return new DataApiResponse(false,"Exists By No KTP Customer");
            }
            
            Customer newCustomer = new Customer();
            CustomerDetail customerDetail = new CustomerDetail();
            customerDetail.setAdult(customerRequest.isAdult());
            customerDetail.setJenisKelamin(customerRequest.getJenisKelamin());
            customerDetail.setNoKTP(customerRequest.getNoKTP());
            customerDetail.setTglLahir(dateFormat.parse(customerRequest.getTglLahir()));
            customerDAO.persistCustomer(customerDetail);
            newCustomer.setEmail(customerRequest.getEmail());
            newCustomer.setName(customerRequest.getName());
            newCustomer.setCustomerDetailId(customerDetail.getCustomerDetailId());
            customerDAO.persistCustomerAndDetail(newCustomer, customerDetail);
            return new DataApiResponse(true,"Success saveCustomerAndDetail");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataApiResponse(false,e.getMessage());
        }
    }
}
