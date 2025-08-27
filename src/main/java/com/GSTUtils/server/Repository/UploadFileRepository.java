package com.GSTUtils.server.Repository;

import com.GSTUtils.server.Model.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
