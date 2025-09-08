package com.GSTUtils.server.dto;

import com.GSTUtils.server.Model.GSTINMaster;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GSTINResponse {
    private String gstinNumber;
    private String state;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public GSTINResponse(GSTINMaster gstin) {
        this.gstinNumber = gstin.getGstinNumber();
        this.state = gstin.getState();
        this.createdAt = gstin.getCreatedAt();
        this.updatedAt = gstin.getUpdatedAt();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}