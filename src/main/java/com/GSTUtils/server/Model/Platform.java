package com.GSTUtils.server.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ecom_platforms")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long platformID;

    @Column(nullable = false)
    private String platformName;

    private String code;

    private LocalDate createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "platform")
    @JsonIgnore
    private List<GST_Filing> filings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "platform")
    @JsonIgnore
    private List<UploadFile> files = new ArrayList<>();

    public Platform() {
    }

    public Platform(Long platformID, String platformName, String code, LocalDate createdAt) {
        this.platformID = platformID;
        this.platformName = platformName;
        this.code = code;
        this.createdAt = createdAt;
    }

    public Long getPlatformID() {
        return platformID;
    }

    public void setPlatformID(Long platformID) {
        this.platformID = platformID;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
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
