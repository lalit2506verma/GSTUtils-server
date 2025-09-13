package com.GSTUtils.server.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gst_filing")
public class GST_Filing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long filingID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gstinID")
    private GSTINMaster gstinMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platformID")
    private Platform platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "returnPeriodID")
    private ReturnPeriod returnPeriod;

    private LocalDate createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fileId")
    @JsonIgnore
    private List<GST_Filing_Files> gstFilingsFiles = new ArrayList<>();

    public Long getFilingID() {
        return filingID;
    }

    public void setFilingID(Long filingID) {
        this.filingID = filingID;
    }

    public GSTINMaster getGstinMaster() {
        return gstinMaster;
    }

    public void setGstinMaster(GSTINMaster gstinMaster) {
        this.gstinMaster = gstinMaster;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public ReturnPeriod getReturnPeriod() {
        return returnPeriod;
    }

    public void setReturnPeriod(ReturnPeriod returnPeriod) {
        this.returnPeriod = returnPeriod;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<GST_Filing_Files> getGstFilings() {
        return gstFilingsFiles;
    }

    public void setGstFilings(List<GST_Filing_Files> gstFilings) {
        this.gstFilingsFiles = gstFilings;
    }
}
