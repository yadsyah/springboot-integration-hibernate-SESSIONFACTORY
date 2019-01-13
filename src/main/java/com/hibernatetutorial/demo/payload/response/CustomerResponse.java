package com.hibernatetutorial.demo.payload.response;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.CustomerAlamat;
import com.hibernatetutorial.demo.entity.CustomerDetail;
import com.hibernatetutorial.demo.payload.request.global.ApiDataRequest;

import java.util.List;

public class CustomerResponse {

    private Long customerId;

    private String name;

    private String email;

    private CustomerDetailResponse customerDetail;
    private List<CustomerAlamat> customerAlamats;
    public CustomerResponse() {

    }

    public CustomerResponse(Long customerId, String name, String email, CustomerDetailResponse customerDetail) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.customerDetail = customerDetail;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerDetailResponse getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetailResponse customerDetail) {
        this.customerDetail = customerDetail;
    }

    public List<CustomerAlamat> getCustomerAlamats() {
        return customerAlamats;
    }

    public void setCustomerAlamats(List<CustomerAlamat> customerAlamats) {
        this.customerAlamats = customerAlamats;
    }
}
