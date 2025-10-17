package com.GSTUtils.server.Service;

import com.GSTUtils.server.Model.ReturnPeriod;
import com.GSTUtils.server.dto.ReturnPeriodDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReturnPeriodService {

    // CREATE RETURN PERIOD
    ReturnPeriod createReturn(ReturnPeriodDTO returnDTO);

    // UPDATE RETURN PERIOD
    ReturnPeriod updateReturn(ReturnPeriodDTO returnDTO, long returnId);

    // DELETE RETURN PERIOD
    void deleteReturn(String gstinNumber, String returnMonth, int returnYear);

    // FIND ALL RETURN BY GSTIN NUMBER
    List<ReturnPeriod> getAllReturnByGstin(String gstinNumber);

    // FIND RETURN PERIOD BY COMBINATION OF GSTIN, MONTH AND YEAR
    ReturnPeriod getReturn(String gstinNum, String month, int year);

}
