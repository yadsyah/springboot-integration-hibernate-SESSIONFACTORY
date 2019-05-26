package com.hibernatetutorial.demo.payload.request.frontend;


public class CustomerRequest {
    private Long customerId;
    private String name;
    private String email;
    private String noKTP;

    public CustomerRequest() {
    }

    public CustomerRequest(Long customerId, String name, String email, String noKTP) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.noKTP = noKTP;
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

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }
}
