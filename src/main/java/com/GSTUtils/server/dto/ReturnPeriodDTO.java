package com.GSTUtils.server.dto;

import com.GSTUtils.server.Model.ReturnPeriod;

public class ReturnPeriodDTO {

    private String gstinNumber;
    private String returnMonth;
    private int returnYear;
    private String frequency;

    public ReturnPeriodDTO(String gstinNumber, String returnMonth, int returnYear, String frequency) {
        this.gstinNumber = gstinNumber;
        this.returnMonth = returnMonth;
        this.returnYear = returnYear;
        this.frequency = frequency;
    }

    public ReturnPeriodDTO(ReturnPeriod returnPeriod){
        this.gstinNumber = returnPeriod.getGstin().getGstinNumber();
        this.returnMonth = returnPeriod.getReturnMonth().toString();
        this.returnYear = returnPeriod.getReturnYear();
        this.frequency = String.valueOf(returnPeriod.getFrequency());
    }

    public ReturnPeriodDTO() {
    }

    public String getGstinNumber() {
        return gstinNumber;
    }

    public void setGstinNumber(String gstinNumber) {
        this.gstinNumber = gstinNumber;
    }

    public String getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(String returnMonth) {
        this.returnMonth = returnMonth;
    }

    public int getReturnYear() {
        return returnYear;
    }

    public void setReturnYear(int returnYear) {
        this.returnYear = returnYear;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
