package com.GSTUtils.server.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gstin_master")
public class GSTINMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gstinID;

    @Column(unique = true, nullable = false)
    private String gstinNumber;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private LocalDate createdAt;

    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gstin")
    @JsonIgnore
    private Set<ReturnPeriod> returns = new HashSet<>();

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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ReturnPeriod> getReturns() {
        return returns;
    }

    public void setReturns(Set<ReturnPeriod> returns) {
        this.returns = returns;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

}
