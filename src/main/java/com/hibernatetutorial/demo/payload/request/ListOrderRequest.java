package com.hibernatetutorial.demo.payload.request;

public class ListOrderRequest {

    private String idProductItem;
    private Integer quantity;

    public ListOrderRequest() {
    }

    public String getIdProductItem() {
        return idProductItem;
    }

    public void setIdProductItem(String idProductItem) {
        this.idProductItem = idProductItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
