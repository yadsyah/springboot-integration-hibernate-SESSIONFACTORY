package com.hibernatetutorial.demo.entity.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {
        "createdAt","updatedAt","createdBy","updatedBy"
},allowGetters = true)
public abstract class AuditTrail implements Serializable {

    @JsonIgnore
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Instant createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @JsonIgnore
    @CreatedBy
    @Column(nullable = true)
    private String createdBy;
    
    @JsonIgnore
    @LastModifiedBy
    @Column(nullable = true)
    private String updatedBy;

    @JsonIgnore
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
