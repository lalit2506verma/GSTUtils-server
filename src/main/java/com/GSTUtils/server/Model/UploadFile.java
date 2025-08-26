package com.GSTUtils.server.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "upload_file")
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileID;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "upload_time")
    private LocalDateTime uploadedTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "platformID", nullable = false)
    private Platform platform;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "returnID", nullable = false)
    private ReturnPeriod returnPeriod;

    private boolean processed_flag;

    public UploadFile() {
    }

    public UploadFile(Long fileID, String fileName, String filePath, LocalDateTime uploadedTime, Platform platform, ReturnPeriod returnPeriod, boolean processed_flag) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedTime = uploadedTime;
        this.platform = platform;
        this.returnPeriod = returnPeriod;
        this.processed_flag = processed_flag;
    }

    public Long getFileID() {
        return fileID;
    }

    public void setFileID(Long fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(LocalDateTime uploadedTime) {
        this.uploadedTime = uploadedTime;
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

    public boolean isProcessed_flag() {
        return processed_flag;
    }

    public void setProcessed_flag(boolean processed_flag) {
        this.processed_flag = processed_flag;
    }
}
