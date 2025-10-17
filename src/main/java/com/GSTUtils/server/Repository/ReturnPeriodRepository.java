package com.GSTUtils.server.Repository;

import com.GSTUtils.server.Helper.Month;
import com.GSTUtils.server.Model.ReturnPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReturnPeriodRepository extends JpaRepository<ReturnPeriod, Long> {

    boolean existsByGstin_GstinNumberAndReturnMonthAndReturnYear(String gstinNumber, Month returnMonth, int returnYear);

    Optional<ReturnPeriod> findByGstin_GstinNumberAndReturnMonthAndReturnYear(String gstinNumber, Month returnMonth, int returnYear);

    // find all by GSTIN number (joining through gstin entity)
    List<ReturnPeriod> findAllByGstin_GstinNumber(String gstinNumber);

}