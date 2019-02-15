package com.hibernatetutorial.demo.payload.request;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {

    private Long idCustomer;
    private List<ListOrderRequest>  listOrder = new ArrayList<>();

    public OrderRequest() {
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public List<ListOrderRequest> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<ListOrderRequest> listOrder) {
        this.listOrder = listOrder;
    }
}
