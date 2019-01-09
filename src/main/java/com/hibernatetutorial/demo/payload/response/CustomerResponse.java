package com.hibernatetutorial.demo.payload.response;

import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.CustomerDetail;
import com.hibernatetutorial.demo.payload.request.global.ApiDataRequest;

public class CustomerResponse {

    private Long customerId;

    private String name;

    private String email;

    private CustomerDetail customerDetail;

    public CustomerResponse() {

    }

    public CustomerResponse(Long customerId, String name, String email, CustomerDetail customerDetail) {
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

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }
}
