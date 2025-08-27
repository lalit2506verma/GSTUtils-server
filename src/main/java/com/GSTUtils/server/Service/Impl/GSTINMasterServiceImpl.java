package com.GSTUtils.server.Service.Impl;

import com.GSTUtils.server.Model.GSTINMaster;
import com.GSTUtils.server.Repository.GSTINMasterRepository;
import com.GSTUtils.server.Service.GSTINMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GSTINMasterServiceImpl implements GSTINMasterService {

    @Autowired
    private GSTINMasterRepository  gstinMasterRepository;

    @Override
    public boolean createGstinMaster(GSTINMaster gstin) {
        // Validate gstin already exist or not
        String gstinNumber = gstin.getGstinNumber();

        if(gstinNumber.length() != 15){
            System.out.println("Invalid GSTIN number provided");
            return false;
        }

        GSTINMaster gstinMaster = findByGstinNumber(gstinNumber);

        if(gstinMaster == null){
            // Good to save the GSTIN in the database
            this.gstinMasterRepository.save(gstin);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public GSTINMaster updateGstinMaster(GSTINMaster gstin, String gstinNumber) {
        return null;
    }

    @Override
    public GSTINMaster findByGstinNumber(String gstinNumber) {

        return this.gstinMasterRepository.findBygstinNumber(gstinNumber);
    }

    @Override
    public void deleteGstinMaster(String gstinNumber) {

    }

    @Override
    public GSTINMaster findByGstinID(Long GstinID) {
        return null;
    }
}
