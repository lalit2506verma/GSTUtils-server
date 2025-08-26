package com.GSTUtils.server.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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
}
