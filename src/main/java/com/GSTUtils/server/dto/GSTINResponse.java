package com.GSTUtils.server.dto;

import com.GSTUtils.server.Model.GSTINMaster;

import java.time.LocalDateTime;

public class GSTINResponse {
    private Long gstinID;
    private String gstinNumber;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String lastUsedReturnPeriod;
    private Long userId;

    public GSTINResponse(GSTINMaster gstin) {
        this.gstinID = gstin.getGstinID();
        this.gstinNumber = gstin.getGstinNumber();
        this.state = gstin.getState();
        this.createdAt = gstin.getCreatedAt();
        this.updatedAt = gstin.getUpdatedAt();
        this.lastUsedReturnPeriod = gstin.getLastUsedReturnPeriod();
        this.userId = gstin.getUser().getUserID(); // extract userID only
    }

    public Long getGstinID() {
        return gstinID;
    }

    public void setGstinID(Long gstinID) {
        this.gstinID = gstinID;
    }

    public String getGstinNumber() {
        return gstinNumber;
    }

    public void setGstinNumber(String gstinNumber) {
        this.gstinNumber = gstinNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastUsedReturnPeriod() {
        return lastUsedReturnPeriod;
    }

    public void setLastUsedReturnPeriod(String lastUsedReturnPeriod) {
        this.lastUsedReturnPeriod = lastUsedReturnPeriod;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}