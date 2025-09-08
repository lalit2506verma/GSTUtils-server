package com.GSTUtils.server.Service.Impl;

import com.GSTUtils.server.Exception.GSTINNotFoundException;
import com.GSTUtils.server.Model.GSTINMaster;
import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.Repository.GSTINMasterRepository;
import com.GSTUtils.server.Service.GSTINMasterService;
import com.GSTUtils.server.dto.GSTINResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class GSTINMasterServiceImpl implements GSTINMasterService {

    @Autowired
    private GSTINMasterRepository  gstinMasterRepository;

    @Override
    public GSTINResponse createGstinMaster(GSTINMaster gstin) {
        // Validate gstin already exist or not
        String gstinNumber = gstin.getGstinNumber();

        if(gstinNumber.length() != 15){
            System.out.println("Invalid GSTIN number provided");
            return null;
        }

        GSTINMaster gstinMaster = findByGstinNumber(gstinNumber);

        if(gstinMaster == null){
            // Good to save the GSTIN in the database
            GSTINMaster temp = this.gstinMasterRepository.save(gstin);
            return new GSTINResponse(temp);

        }
        else{
            return null;
        }
    }

    /*
    This method update the relation of the user and GSTIN info ONLY
    1 GSTIN -> 1 User
     */
    @Override
    public GSTINResponse updateGstinMaster_UserId(String gstinNumber, User newUser) {

        GSTINMaster gstinMaster = findByGstinNumber(gstinNumber);

        // GSTIN not found in the database
        if(gstinMaster == null){
            throw new GSTINNotFoundException("GSTIN Not found");
        }

        // Update the GSTIN Master
        gstinMaster.setUser(newUser);
        gstinMaster.setUpdatedAt(LocalDate.now());

        GSTINMaster gstin = this.gstinMasterRepository.save(gstinMaster);

        return new GSTINResponse(gstin);

    }

    @Override
    public GSTINMaster findByGstinNumber(String gstinNumber) {

        return this.gstinMasterRepository.findBygstinNumber(gstinNumber);
    }

    @Override
    public void deleteGstinMaster(String gstinNumber) {
        // validate GSTIN Number exist for not
        GSTINMaster gstinMaster = findByGstinNumber(gstinNumber);

        if(gstinMaster == null){
            throw new GSTINNotFoundException("GSTIN Not found");
        }

        // GSTIN Exists
        this.gstinMasterRepository.deleteById(gstinMaster.getGstinID());
    }

    @Override
    public GSTINResponse findByGstinID(Long gstinID) {

        GSTINMaster gstin  = this.gstinMasterRepository.getReferenceById(gstinID);
        return new GSTINResponse(gstin);
    }

    @Override
    public List<GSTINResponse> findAllGstinByUserId(Long userId) {

        List<GSTINMaster> gstinList = this.gstinMasterRepository.findByUser_UserID(userId);
        List<GSTINResponse> dtoList = new ArrayList<>();

        // converting GSTMaster list into GSTResponse list
        for(GSTINMaster gstin: gstinList){
            dtoList.add(new GSTINResponse(gstin));
        }

        return dtoList;
    }

    @Override
    public boolean updateGstinMaster(String gstinNumber) {
        // check GSTIN number exists
        GSTINMaster gstin = findByGstinNumber(gstinNumber);
        if(gstin == null){
            System.out.println("Gstin didn't updated");
            return false;
        }

        // gstin Exist
        if(!gstin.getUpdatedAt().isEqual(LocalDate.now())){
            // if updatedDate and current date is not same  then only call database to same the GSTINMaster
            gstin.setUpdatedAt(LocalDate.now());
            this.gstinMasterRepository.save(gstin);
        }

        return true;

    }
}
