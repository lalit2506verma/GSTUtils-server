package com.GSTUtils.server.Model;

import com.GSTUtils.server.Helper.Frequency;
import com.GSTUtils.server.Helper.Month;
import com.GSTUtils.server.Helper.ReturnStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "return_period",
        uniqueConstraints = @UniqueConstraint(columnNames = {"gstin_number", "return_month", "return_year"})
)
public class ReturnPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnPeriodID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "return_month", length = 20)
    private Month returnMonth;

    @Column(nullable = false, name="return_year", length = 4)
    private int returnYear;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gstin_number", referencedColumnName = "gstinNumber", nullable = false)
    @JsonIgnore
    private GSTINMaster gstin;

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

    public Month getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(Month returnMonth) {
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

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public GSTINMaster getGstin() {
        return gstin;
    }

    public void setGstin(GSTINMaster gstin) {
        this.gstin = gstin;
    }

    public List<GST_Filing> getFilings() {
        return filings;
    }

    public void setFilings(List<GST_Filing> filings) {
        this.filings = filings;
    }
}
