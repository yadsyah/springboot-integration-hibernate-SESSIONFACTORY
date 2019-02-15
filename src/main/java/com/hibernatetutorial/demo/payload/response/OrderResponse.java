package com.hibernatetutorial.demo.payload.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse implements Serializable {

    private String idOrder;
    private String idHistoryOrder;
    private String totalOrder;
    private String namaCustomer;
    private List<OrderHistoryResponse> orderHistory = new ArrayList<>();



    public OrderResponse() {
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdHistoryOrder() {
        return idHistoryOrder;
    }

    public void setIdHistoryOrder(String idHistoryOrder) {
        this.idHistoryOrder = idHistoryOrder;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public List<OrderHistoryResponse> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<OrderHistoryResponse> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
