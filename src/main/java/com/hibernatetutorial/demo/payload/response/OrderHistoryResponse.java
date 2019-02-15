package com.hibernatetutorial.demo.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderHistoryResponse {
    @JsonIgnore
    private long idHistoryOrderPK;
    @JsonIgnore
    private String idHistoryOrder;
    private String idProductItem;
    private String nameProductItem;
    private String totalPrice;
    private String priceProduct;
    private Long quantity;

    public OrderHistoryResponse() {
    }

    public long getIdHistoryOrderPK() {
        return idHistoryOrderPK;
    }

    public void setIdHistoryOrderPK(long idHistoryOrderPK) {
        this.idHistoryOrderPK = idHistoryOrderPK;
    }

    public String getIdHistoryOrder() {
        return idHistoryOrder;
    }

    public void setIdHistoryOrder(String idHistoryOrder) {
        this.idHistoryOrder = idHistoryOrder;
    }

    public String getIdProductItem() {
        return idProductItem;
    }

    public void setIdProductItem(String idProductItem) {
        this.idProductItem = idProductItem;
    }

    public String getNameProductItem() {
        return nameProductItem;
    }

    public void setNameProductItem(String nameProductItem) {
        this.nameProductItem = nameProductItem;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }
}
