package com.hibernatetutorial.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long customerId;

    private String name;

    @Email
    @Column(name = "email",unique = true)
    private String email;

    @Column(nullable = true,name = "customer_det_id")
    private Long customerDetailId;

    public Long getCustomerDetailId() {
        return customerDetailId;
    }

    public void setCustomerDetailId(Long customerDetailId) {
        this.customerDetailId = customerDetailId;
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
}
