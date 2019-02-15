package com.hibernatetutorial.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Indexed
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long customerId;
    @Field(index = Index.YES,analyze = Analyze.YES,store = Store.NO)
    private String name;

    @Email
    @Column(name = "email", unique = true)
    @Field(index = Index.YES,analyze = Analyze.YES,store = Store.NO)
    private String email;

    @Column(nullable = true, name = "customer_det_id")
    @Field(index = Index.YES,analyze = Analyze.YES,store = Store.NO)
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("{").append("customerId").append(":").append(this.getCustomerId()).append(",")
        .append("name").append(":").append(this.getName()).append(",")
        .append("email").append(":").append(this.getEmail()).append("}").toString();
    }
}
