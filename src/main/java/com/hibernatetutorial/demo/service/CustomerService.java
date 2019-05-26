package com.hibernatetutorial.demo.service;

import com.google.gson.Gson;
import com.hibernatetutorial.demo.constant.UtilityConstant;
import com.hibernatetutorial.demo.constant.UtilityConstant.*;
import com.hibernatetutorial.demo.dao.CustomerAlamatDAO;
import com.hibernatetutorial.demo.dao.CustomerDAO;
import com.hibernatetutorial.demo.dao.CustomerDetailDAO;
import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.CustomerAlamat;
import com.hibernatetutorial.demo.entity.CustomerDetail;
import com.hibernatetutorial.demo.entity.TblUser;
import com.hibernatetutorial.demo.exception.NoResultException;
import com.hibernatetutorial.demo.payload.request.CustomerAlamatRequest;
import com.hibernatetutorial.demo.payload.response.CustomerDetailResponse;
import com.hibernatetutorial.demo.payload.response.CustomerRequest;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;

import com.hibernatetutorial.demo.repositoryjpa.CustomerRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    CustomerDetailDAO customerDetailDAO;

    @Autowired
    CustomerAlamatDAO customerAlamatDAO;

    @Autowired
    CustomerRepositoryJPA customerRepositoryJPA;


    public DataApiResponse getOneCustomerResponse(Long id) {
        Class methodName = new Object() {
        }.getClass();
        LOGGER.info(INFO_SERVICE.START_SERVICE.getDescription(methodName));
        try {
            Customer customer = customerDAO.findByIdCustomer(id);
            if (customer == null) {
                LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
                return new DataApiResponse(false, "Data Not Found!");
            }
            CustomerRequest customerRequest = new CustomerRequest();
            customer.setCustomerId(customer.getCustomerId());
            customerRequest.setName(customer.getName());
            customerRequest.setEmail(customer.getEmail());
            LOGGER.info("Data : {}", customerRequest);
            LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(true, customerRequest);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(false, e.getMessage());
        }
    }

    public DataApiResponse getAllCustomerResponse() {
        Class methodName = new Object() {
        }.getClass();
        LOGGER.info(INFO_SERVICE.START_SERVICE.getDescription(methodName));
        try {
            List<Object[]> listObject = customerDAO.joinCustomerAndDetail();
            List<CustomerRequest> customerRespons = new ArrayList<>();

            if (!listObject.isEmpty()&&listObject!=null) {
                for (Object[] objects : listObject) {
                    Customer customer = (Customer) objects[0];
                    CustomerDetail customerDetail = (CustomerDetail) objects[1];
                    CustomerRequest customerRequest = new CustomerRequest();
                    customerRequest.setCustomerId(customer.getCustomerId());
                    customerRequest.setName(customer.getName());
                    customerRequest.setEmail(customer.getEmail());
                    CustomerDetailResponse customerDetailRes = new CustomerDetailResponse();
                    customerDetailRes.setAdult(customerDetail.isAdult());
                    customerDetailRes.setCustomerDetailId(customerDetail.getCustomerDetailId());
                    customerDetailRes.setJenisKelamin(customerDetail.getJenisKelamin());
                    customerDetailRes.setNoKTP(customerDetail.getNoKTP());
                    customerDetailRes.setTglLahir(customerDetail.getTglLahir() != null ? UtilityConstant.dateFormat.format(customerDetail.getTglLahir()) : null);
                    customerRequest.setCustomerDetail(customerDetailRes != null ? customerDetailRes : null);
                    List<CustomerAlamat> customerAlamats = customerAlamatDAO.findOneIdCustomerAlamatNotPK(customer.getCustomerId());
                    if (!customerAlamats.isEmpty()) {
                        customerRequest.setCustomerAlamats(customerAlamats);
                    }
                    customerRespons.add(customerRequest);
                }
                LOGGER.info("Data Size : {}", customerRespons.size());
                LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
                return new DataApiResponse(true, customerRespons);
            }
            LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(true, "Data Not Found!");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(false, e.getMessage());
        }
    }

    public DataApiResponse getOneCustomerAndDetailResponse(Long id) {
        Class methodName = new Object() {
        }.getClass();
        LOGGER.info(INFO_SERVICE.START_SERVICE.getDescription(methodName));
        try {
            Object[] objects = customerDAO.findByIdJoinCustomerAndDetail(id);
            if (objects == null) {
                LOGGER.error(INFO_SERVICE.END_SERVICE.getDescription(methodName));
                return new DataApiResponse(false, "Data Not Found! `" + id + "`");
            }
            LOGGER.info("Data : {}", objects);
            Customer customer = (Customer) objects[0];
            CustomerDetail customerDetail = (CustomerDetail) objects[1];
            CustomerRequest customerRequest = new CustomerRequest();
            customerRequest.setCustomerId(customer.getCustomerId());
            customerRequest.setEmail(customer.getEmail());
            customerRequest.setName(customer.getName());
            CustomerDetailResponse customerDetailRes = new CustomerDetailResponse();
            customerDetailRes.setAdult(customerDetail.isAdult());
            customerDetailRes.setCustomerDetailId(customerDetail.getCustomerDetailId());
            customerDetailRes.setJenisKelamin(customerDetail.getJenisKelamin());
            customerDetailRes.setNoKTP(customerDetail.getNoKTP());
            customerDetailRes.setTglLahir(customerDetail.getTglLahir() != null ? UtilityConstant.dateFormat.format(customerDetail.getTglLahir()) : null);
            customerRequest.setCustomerDetail(customerDetailRes != null ? customerDetailRes : null);
            List<CustomerAlamat> customerAlamats = customerAlamatDAO.findOneIdCustomerAlamatNotPK(customer.getCustomerId());
            if (!customerAlamats.isEmpty()) {
                customerRequest.setCustomerAlamats(customerAlamats);
            }
            LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(true, customerRequest);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(false, e.getMessage());
        }
    }

    public DataApiResponse saveCustomerAndDetail(com.hibernatetutorial.demo.payload.request.CustomerRequest customerRequest) {
        Class methodName = new Object() {}.getClass();
        LOGGER.info(INFO_SERVICE.START_SERVICE.getDescription(methodName));
        try {
            if (!UtilityConstant.isDatePattern(customerRequest.getTglLahir())) {
                LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
                return new DataApiResponse(false, "Parsing Date Error");
            }
            if (customerDAO.existByEmail(customerRequest.getEmail())) {
                LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
                return new DataApiResponse(false, "Exists By Email Customer");
            }
            if (customerDAO.existByNoKTP(customerRequest.getNoKTP())) {
                LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
                return new DataApiResponse(false, "Exists By No KTP Customer");
            }

            Customer newCustomer = new Customer();
            CustomerDetail customerDetail = new CustomerDetail();
            CustomerAlamat customerAlamat = new CustomerAlamat();

            customerDetail.setJenisKelamin(customerRequest.getJenisKelamin());
            customerDetail.setNoKTP(customerRequest.getNoKTP());
            customerDetail.setTglLahir(UtilityConstant.dateFormat.parse(customerRequest.getTglLahir()));
            customerDetail.setAdult(UtilityConstant.calculateAdult(UtilityConstant.dateFormat.parse(customerRequest.getTglLahir())));
            customerDetailDAO.persistCustomerDetail(customerDetail);

            newCustomer.setEmail(customerRequest.getEmail());
            newCustomer.setName(customerRequest.getName());
            newCustomer.setCustomerDetailId(customerDetail.getCustomerDetailId());

            customerDAO.saveCustomerAndDetail(newCustomer, customerDetail);
            customerAlamat.setCustomerId(newCustomer.getCustomerId());
            customerAlamat.setKodePos(customerRequest.getKodePos());
            customerAlamat.setNamaAlamat(customerRequest.getNamaAlamat());
            customerAlamat.setNegara(customerRequest.getNegara());
            customerAlamatDAO.saveCustomerAlamat(customerAlamat);

            LOGGER.info(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            Customer customer = customerDAO.findByIdCustomer(newCustomer.getCustomerId());
            return new DataApiResponse(true, customer,"Save Data!");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error : {}", e.getMessage());
            LOGGER.error(INFO_SERVICE.END_SERVICE.getDescription(methodName));
            return new DataApiResponse(false, e.getMessage());
        }
    }

    public DataApiResponse addAlamatByCustomerId(CustomerAlamatRequest customerAlamat) {
        try {

            Customer customer = customerDAO.findByIdCustomer(customerAlamat.getCustomerId());
            if (customer == null) {
                return new DataApiResponse(false, "Customer Id `" + customerAlamat.getCustomerId() + "` Not Found!");
            }
            CustomerAlamat addCustomerAlamat = new CustomerAlamat();
            addCustomerAlamat.setNegara(customerAlamat.getNegara());
            addCustomerAlamat.setNamaAlamat(customerAlamat.getNamaAlamat());
            addCustomerAlamat.setKodePos(customerAlamat.getKodePos());
            addCustomerAlamat.setCustomerId(customer.getCustomerId());
            customerAlamatDAO.saveCustomerAlamat(addCustomerAlamat);
            return new DataApiResponse(true, "Success Add Alamat by Customer Id " + customerAlamat.getCustomerId());
        } catch (NoResultException e) {
            return new DataApiResponse(false, "Data Not Found " + customerAlamat.getCustomerId());
        } catch (Exception e) {
            e.printStackTrace();
            return new DataApiResponse(false, e.getMessage());
        }
    }


    public DataApiResponse checkEmailExisting(String email) {
        try {
            if (customerRepositoryJPA.existsByEmail(email)) {
                return new DataApiResponse(true, true);
            }
            return new DataApiResponse(true, false);
        } catch (Exception e) {
            e.printStackTrace();
            return new DataApiResponse(false, e.getMessage());
        }

    }

    public DataApiResponse deleteCustomerById(Long customerId) {
        try {
            Customer customer = customerDAO.findByIdCustomer(customerId);
            if (customer != null) {
                customerDAO.deleteCustomer(customer);
                return new DataApiResponse(true, "Success Delete Id `" + customerId + "`");
            }
            return new DataApiResponse(false, "Customer Not Found!");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataApiResponse(false, e.getMessage());
        }
    }

    public DataApiResponse searchCustomerByCustomerName(String customerName) {
        try {
            List<Customer> customerList = customerDAO.hibernateSearchCustomer(customerName);
            if (customerList != null) {
                if (!customerList.isEmpty()) {
                    return new DataApiResponse(true, customerList);
                }
            }
            return new DataApiResponse(false, "Data Not Found");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataApiResponse(false, e.getMessage());
        }
    }

    public DataApiResponse updateCustomerByCustomerId(Long idCustomer, com.hibernatetutorial.demo.payload.request.frontend.CustomerRequest customerRequest){
        Customer customer = customerDAO.findByIdCustomer(idCustomer);
        if(customer!=null){
            CustomerDetail customerDetail = customerDetailDAO.findByIdCustomerDetail(customer.getCustomerDetailId());
            if(customerDetail!=null){
                customer.setCustomerId(idCustomer);
                customer.setName(customerRequest.getName());
                customer.setEmail(customerRequest.getEmail());
                customerDetail.setNoKTP(customerRequest.getNoKTP());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date tempDate;

//                customerDetail.setTglLahir(tempDate);
                customerDAO.update(customer);
                customerDetailDAO.saveOrUpdate(customerDetail);
                return new DataApiResponse(true,"Success Saving",new Gson().toJson(customerRequest));
            }
        }
        return new DataApiResponse(false,"Customer Not Found!");
    }
}
