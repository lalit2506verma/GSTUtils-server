package com.GSTUtils.server.Model;

import com.GSTUtils.server.Helper.FileType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "gst_filing_files")
public class GST_Filing_Files {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filingID")
    private GST_Filing gstFilings;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private FileType fileType;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String originalFileName;

    private LocalDate uploadedAt;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public GST_Filing getGstFilings() {
        return gstFilings;
    }

    public void setGstFilings(GST_Filing gstFilings) {
        this.gstFilings = gstFilings;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public LocalDate getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDate uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
