package com.GSTUtils.server.Model;

import com.GSTUtils.server.Helper.ReturnMonth;
import com.GSTUtils.server.Helper.ReturnStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "return_period")
public class ReturnPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "return_month", length = 20)
    private ReturnMonth returnMonth;

    @Column(nullable = false, name="return_year", columnDefinition = "YEAR(4)")
    private int year;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gstinID", nullable = false)
    private GSTINMaster gstin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "returnPeriod")
    @JsonIgnore
    private List<UploadFile> files = new ArrayList<>();

    public ReturnPeriod() {
    }

    public Long getReturnID() {
        return returnID;
    }

    public void setReturnID(Long returnID) {
        this.returnID = returnID;
    }

    public ReturnMonth getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(ReturnMonth returnMonth) {
        this.returnMonth = returnMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(ReturnStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
}
