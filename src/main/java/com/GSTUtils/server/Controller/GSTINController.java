package com.GSTUtils.server.Controller;

import com.GSTUtils.server.Exception.GSTINNotFoundException;
import com.GSTUtils.server.Helper.AuthUtils;
import com.GSTUtils.server.Model.GSTINMaster;
import com.GSTUtils.server.Model.GenericResponse;
import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.Service.GSTINMasterService;
import com.GSTUtils.server.Service.UserService;
import com.GSTUtils.server.dto.GSTINResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/gstin")
public class GSTINController {

    @Autowired
    private GSTINMasterService gstinMasterService;

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<?> createGstinMaster(@RequestBody GSTINMaster gstinMaster){
        try{
            // Getting the Principal user from SecurityContextHolder
            User principal = AuthUtils.getCurrentUser().getUser();
            gstinMaster.setUser(principal);

            gstinMaster.setCreatedAt(LocalDate.now());
            gstinMaster.setUpdatedAt(LocalDate.now());

            // saving
            GSTINResponse gstin = this.gstinMasterService.createGstinMaster(gstinMaster);

            if(gstin != null){
                return ResponseEntity.ok(new GenericResponse<>("SUCCESS", "GSTIN added", gstin));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GenericResponse<>("FAILURE", "GSTIN can not be added", null));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GenericResponse<>("FAILURE", "Something went wrong: " + ex.getMessage(), null));
        }
    }

    @PutMapping("/{gstin}/user/{userID}")
    public ResponseEntity<?> updateGstinMaster_UserId(@PathVariable("gstin") String gstinNumber, @PathVariable("userID") Long userID){
        // check new user exist or not
        User newUser = this.userService.findByUserId(userID);

        if(newUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GenericResponse<>("FAILURE", "UserID does not exist", null));
        }

        try {
            // User Exist
            GSTINResponse gstin = this.gstinMasterService.updateGstinMaster_UserId(gstinNumber, newUser);

            return ResponseEntity.ok(new GenericResponse<>("SUCCESS", "GSTIN updated", gstin));

        }
        catch (GSTINNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GenericResponse<>("FAILURE", "GSTIN Not found", null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GenericResponse<>("FAILURE", "Something went wrong: " + ex.getMessage(), null));
        }

    }

    @DeleteMapping("/{gstin}")
    public ResponseEntity<?> deleteGstinMaster(@PathVariable("gstin") String gstinNumber){

        try {
            this.gstinMasterService.deleteGstinMaster(gstinNumber);
            return ResponseEntity.ok(new GenericResponse<>("SUCCESS", "GSTIN updated", null));
        }
        catch (GSTINNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GenericResponse<>("FAILURE", "GSTIN Not found", null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GenericResponse<>("FAILURE", "Something went wrong: " + ex.getMessage(), null));
        }
    }

    @GetMapping("/{gstinID}")
    public ResponseEntity<?> findByGstinID(@PathVariable("gstinID") Long gstinID){
        try {
            GSTINResponse gstinResponse = this.gstinMasterService.findByGstinID(gstinID);

            // GSTIN not found
            if(gstinResponse == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new GenericResponse<>("FAILURE", "GSTIN Not found", null));
            }

            return ResponseEntity.ok(new GenericResponse<>("SUCCESS", "GSTIN updated", gstinResponse));
        }
         catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GenericResponse<>("FAILURE", "Something went wrong: " + ex.getMessage(), null));
        }
    }

    @GetMapping("/list/")
    public ResponseEntity<?> listGstinByUserId(){
        System.out.println("GSTINList fetch controller runs");
        try{
            Long principalId = AuthUtils.getCurrentUserId();
            List<GSTINResponse> list = this.gstinMasterService.findAllGstinByUserId(principalId);

            return ResponseEntity.ok(new GenericResponse<>("SUCCESS", "List of all Gstin fetched successfully", list));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{gstinNumber}")
    public ResponseEntity<?> updateGSTINMaster(@PathVariable("gstinNumber") String gstinNumber){
        try{
            boolean isUpdated = this.gstinMasterService.updateGstinMaster(gstinNumber);
            if(isUpdated){
                return ResponseEntity.ok(new GenericResponse<>("SUCCESS", "GSTIN is updated", null));
            }

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(
                    new GenericResponse<>("FAILURE", "GSTIN Updated Date is not Modified", "null")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
