package com.GSTUtils.server.Service.Impl;

import com.GSTUtils.server.Exception.ReturnNotFoundException;
import com.GSTUtils.server.Helper.Frequency;
import com.GSTUtils.server.Helper.Month;
import com.GSTUtils.server.Helper.ReturnStatus;
import com.GSTUtils.server.Model.GSTINMaster;
import com.GSTUtils.server.Model.ReturnPeriod;
import com.GSTUtils.server.Repository.ReturnPeriodRepository;
import com.GSTUtils.server.Service.GSTINMasterService;
import com.GSTUtils.server.Service.ReturnPeriodService;
import com.GSTUtils.server.dto.ReturnPeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReturnPeriodServiceImpl implements ReturnPeriodService {

    @Autowired
    private ReturnPeriodRepository returnRepository;

    @Autowired
    private GSTINMasterService gstinMasterService;

    @Override
    public ReturnPeriod createReturn(ReturnPeriodDTO returnDTO) {

        String gstinNumber = returnDTO.getGstinNumber();
        Month month = Month.valueOf(returnDTO.getReturnMonth());
        int year = returnDTO.getReturnYear();

        // CHECK RETURN IS ALREADY CREATED OR NOT
        if(returnRepository.existsByGstin_GstinNumberAndReturnMonthAndReturnYear(gstinNumber, month, year)){
            // RETURN EXIST SO WILL FOLLOW A DIFFERENT PATH
            return null;
        }

        GSTINMaster gstin = this.gstinMasterService.findByGstinNumber(gstinNumber);

        // RETURN DO NOT EXIST
        // NEED TO CREATE A NEW RETURN
        ReturnPeriod returns = new ReturnPeriod();
        returns.setReturnMonth(month);
        returns.setReturnYear(year);
        returns.setFrequency(Frequency.valueOf(returnDTO.getFrequency()));
        returns.setStatus(ReturnStatus.PROCESSING);
        returns.setGstin(gstin);

        return returnRepository.save(returns);
    }

    @Override
    public ReturnPeriod updateReturn(ReturnPeriodDTO returnDTO, long returnId) {

        Month month = Month.valueOf(returnDTO.getReturnMonth());
        Frequency frequency = Frequency.valueOf(returnDTO.getFrequency());

        // CHECK THE COMBINATION EXIST IN THE DB
        ReturnPeriod rtn = this.returnRepository.findById(returnId)
                .orElseThrow(() -> new ReturnNotFoundException("Return Id not found - " + returnId));

        // RETURN FOUND
        // UPDATE THE FIELDS OTHER THAN GSTIN NUMBER
        rtn.setReturnMonth(month);
        rtn.setReturnYear(returnDTO.getReturnYear());
        rtn.setFrequency(frequency);

        return this.returnRepository.save(rtn);
    }

    @Override
    public void deleteReturn(String gstinNumber, String rtnMonth, int returnYear) {

        Month returnMonth = Month.valueOf(rtnMonth);

        // CHECK THE UNIQUE COMBINATION EXIST IN THE SYSTEM
        ReturnPeriod rtnPeriod = this.returnRepository.findByGstin_GstinNumberAndReturnMonthAndReturnYear(gstinNumber, returnMonth, returnYear)
                .orElseThrow(() -> new ReturnNotFoundException("Return Not found for the combination of " + gstinNumber + " " + rtnMonth + " " + returnYear));

        this.returnRepository.delete(rtnPeriod);
    }

    @Override
    public List<ReturnPeriod> getAllReturnByGstin(String gstinNumber) {

        List<ReturnPeriod> returns = this.returnRepository.findAllByGstin_GstinNumber(gstinNumber);

        //  CHECK IF NO RETURN EXIST IN DB
        if(returns.isEmpty()){
            throw new ReturnNotFoundException("Return Not found for the combination of " + gstinNumber);
        }

        return returns;
    }

    @Override
    public ReturnPeriod getReturn(String gstinNum, String rtnMonth, int rtnYear) {
        Month returnMonth = Month.valueOf(rtnMonth);

        // FETCH THE RETURN FORM DB
        return this.returnRepository.findByGstin_GstinNumberAndReturnMonthAndReturnYear(gstinNum, returnMonth, rtnYear)
                .orElseThrow(() -> new ReturnNotFoundException("Return Not found for the combination of " + gstinNum + " " + rtnMonth + " " + rtnYear));
    }
}
