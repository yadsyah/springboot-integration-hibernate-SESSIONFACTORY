package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_order")
public class TblOrder extends AuditTrail implements Serializable {

    @Id
    private String idOrder;
    @NotNull
    private String idHistoryOrder;
    @NotNull
    private Long customerId;
    @NotNull
    private BigDecimal totalOrder;

    public TblOrder() {
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
    }
}
