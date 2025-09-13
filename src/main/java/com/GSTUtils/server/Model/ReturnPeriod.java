package com.GSTUtils.server.Model;

import com.GSTUtils.server.Helper.FilingFrequency;
import com.GSTUtils.server.Helper.ReturnMonth;
import com.GSTUtils.server.Helper.ReturnStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "return_period")
public class ReturnPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnPeriodID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "return_month", length = 20)
    private ReturnMonth returnMonth;

    @Column(nullable = false, name="return_year", length = 4)
    private int returnYear;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    @Enumerated(EnumType.STRING)
    private FilingFrequency frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gstinID", nullable = false)
    private GSTINMaster gstin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnPeriod")
    @JsonIgnore
    private List<UploadFile> files = new ArrayList<>();

    // contain multiple GSTIN_filing from each ecom platform
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "returnPeriod")
    @JsonIgnore
    private List<GST_Filing> filings = new ArrayList<>();

    // Getters and Setters

    public ReturnPeriod() {
    }

    public Long getReturnPeriodID() {
        return returnPeriodID;
    }

    public void setReturnPeriodID(Long returnPeriodID) {
        this.returnPeriodID = returnPeriodID;
    }

    public ReturnMonth getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(ReturnMonth returnMonth) {
        this.returnMonth = returnMonth;
    }

    public int getReturnYear() {
        return returnYear;
    }

    public void setReturnYear(int returnYear) {
        this.returnYear = returnYear;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(ReturnStatus status) {
        this.status = status;
    }

    public FilingFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(FilingFrequency frequency) {
        this.frequency = frequency;
    }

    public GSTINMaster getGstin() {
        return gstin;
    }

    public void setGstin(GSTINMaster gstin) {
        this.gstin = gstin;
    }

    public List<UploadFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadFile> files) {
        this.files = files;
    }

    public List<GST_Filing> getFilings() {
        return filings;
    }

    public void setFilings(List<GST_Filing> filings) {
        this.filings = filings;
    }
}
