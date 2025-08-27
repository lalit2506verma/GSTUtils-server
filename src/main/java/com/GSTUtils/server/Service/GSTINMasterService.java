package com.GSTUtils.server.Service;

import com.GSTUtils.server.Model.GSTINMaster;
import org.springframework.stereotype.Service;

@Service
public interface GSTINMasterService {

    // Add new GSTIN
    boolean createGstinMaster(GSTINMaster gstin);

    // Update GSTIN details
    GSTINMaster updateGstinMaster(GSTINMaster gstin, String gstinNumber);

    // find GSTIN details via GST number
    GSTINMaster findByGstinNumber(String gstinNumber);

    // Delete record
    void deleteGstinMaster(String gstinNumber);

    GSTINMaster findByGstinID(Long GstinID);
}
