package com.GSTUtils.server.Controller;

import com.GSTUtils.server.Helper.AuthUtils;
import com.GSTUtils.server.Model.GSTINMaster;
import com.GSTUtils.server.Model.GenericResponse;
import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.Repository.GSTINMasterRepository;
import com.GSTUtils.server.Service.GSTINMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/gstin")
public class GSTINController {

    @Autowired
    private GSTINMasterService gstinMasterService;


    @PostMapping("/")
    public ResponseEntity<?> createGstinMaster(@RequestBody GSTINMaster gstinMaster){
        try{
            // Getting the Principal user from SecurityContextHolder
            User principal = AuthUtils.getCurrentUser().getUser();
            gstinMaster.setUser(principal);

            gstinMaster.setCreatedAt(LocalDateTime.now());

            // saving
            if(this.gstinMasterService.createGstinMaster(gstinMaster)){
                return ResponseEntity.ok(new GenericResponse("SUCCESS", "GSTIN added"));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GenericResponse("FAILURE", "GSTIN can not be added"));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GenericResponse("FAILURE", "Something went wrong: " + ex.getMessage()));
        }
    }

    @PostMapping("/{gstin}")
    public ResponseEntity<?> updateGstinMaster(@RequestBody GSTINMaster gstinMaster, @PathVariable String gstinNumber){

        return null;
    }

    @DeleteMapping("/{gstin}")
    public ResponseEntity<?> deleteGstinMaster(@PathVariable("gstin") String gstinNumber){

        return null;
    }

    @GetMapping("/{gstin}")
    public ResponseEntity<?> findByGstinNumber(@PathVariable("gstin") String gstinNumber){

        return null;
    }

    @GetMapping("/id/{gstinID}")
    public ResponseEntity<?> createGstinMaster(@PathVariable Long gstinID){

        return null;
    }


}
