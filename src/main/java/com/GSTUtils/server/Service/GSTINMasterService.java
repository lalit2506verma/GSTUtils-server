package com.GSTUtils.server.Service;

import com.GSTUtils.server.Model.GSTINMaster;
import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.dto.GSTINResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GSTINMasterService {

    // Add new GSTIN
    GSTINResponse createGstinMaster(GSTINMaster gstin);

    // Update GSTIN details
    GSTINResponse updateGstinMaster(String gstinNumber, User newUser);

    // find GSTIN details via GST number
    GSTINMaster findByGstinNumber(String gstinNumber);

    // Delete record
    void deleteGstinMaster(String gstinNumber);

    GSTINResponse findByGstinID(Long GstinID);

    List<GSTINResponse> findAllGstinByUserId(Long userId);
}
