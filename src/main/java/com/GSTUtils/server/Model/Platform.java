package com.GSTUtils.server.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "platform")
    @JsonIgnore
    private List<UploadFile> files = new ArrayList<>();

    public Platform() {
    }

    public Platform(Long platformID, String platformName) {
        this.platformID = platformID;
        this.platformName = platformName;
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

    public List<UploadFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadFile> files) {
        this.files = files;
    }
}
