package com.GSTUtils.server.Repository;

import com.GSTUtils.server.Model.GSTINMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GSTINMasterRepository extends JpaRepository<GSTINMaster, Long> {

    GSTINMaster findBygstinNumber(String gstinNumber);
}
