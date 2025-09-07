package com.GSTUtils.server.Repository;

import com.GSTUtils.server.Model.GSTINMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GSTINMasterRepository extends JpaRepository<GSTINMaster, Long> {

    GSTINMaster findBygstinNumber(String gstinNumber);

    // Fetch List<GSTINMaster> based on specific UserID
    List<GSTINMaster> findByUser_UserID(Long userId);
}
