package com.GSTUtils.server.Controller;

import com.GSTUtils.server.Exception.ReturnNotFoundException;
import com.GSTUtils.server.Helper.Month;
import com.GSTUtils.server.Model.ReturnPeriod;
import com.GSTUtils.server.Service.ReturnPeriodService;
import com.GSTUtils.server.dto.ReturnPeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnPeriodController {

    private static final Logger logger = LoggerFactory.getLogger(ReturnPeriodController.class);

    @Autowired
    private ReturnPeriodService returnPeriodService;

    @PostMapping("/")
    public ResponseEntity<?> createReturn(@RequestBody ReturnPeriodDTO returnPeriodDTO){
        try {
            // NO GST_FILING SET WILL BE CREATED
            ReturnPeriod rtn = this.returnPeriodService.createReturn(returnPeriodDTO);

            if(rtn == null){
                throw new RuntimeException("Return can not be created due to internal server error");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(rtn);
        }
        catch(Exception e){
            logger.error("Something went wrong", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Return can not be created");
        }
    }

    @PutMapping("/{returnId}/")
    public ResponseEntity<?> updateReturn(@RequestBody ReturnPeriodDTO returnPeriodDTO, @PathVariable long returnId){

        try{
            ReturnPeriod period = this.returnPeriodService.updateReturn(returnPeriodDTO, returnId);
            ReturnPeriodDTO newReturnDTO = new ReturnPeriodDTO(period);
            return ResponseEntity.ok().body(newReturnDTO);

        }
        catch(ReturnNotFoundException e){
            logger.error("Return Not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Return Can not be deleted");
        }
        catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/gstinNumber/{gstinNumber}/month/{rtnMonth}/year/{rtnYear}/")
    public ResponseEntity<?> deleteReturn(@PathVariable("gstinNumber") String gstinNumber,
                                          @PathVariable("rtnMonth") String returnMonth,
                                          @PathVariable("rtnYear") int returnYear ){

        try{
            this.returnPeriodService.deleteReturn(gstinNumber, returnMonth, returnYear);
            System.out.println("Return Successfully deleted");
            return ResponseEntity.ok().body("return successfully deleted");
        }
        catch(ReturnNotFoundException e){
            logger.error("Return Not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Return Can not be deleted");
        }
        catch (Exception e){
            logger.error("Something went wrong", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the Return");
        }
    }

    // FOR ADMIN
    // RETURN RETURNPERIOD IS PRESENT IN THE DB
    // OTHERWISE CALL CREATERETURN METHOD
    @GetMapping("/gstinNumber/{gstinNumber}/month/{rtnMonth}/year/{rtnYear}/")
    public ResponseEntity<?> getReturn(@PathVariable("gstinNumber") String gstinNumber,
                                          @PathVariable("rtnMonth") String returnMonth,
                                          @PathVariable("rtnYear") int returnYear ) {

        try{
            ReturnPeriod rtn = this.returnPeriodService.getReturn(gstinNumber, returnMonth, returnYear);

            // CONVERTING INTO FRONTEND FORMAT DTO
            ReturnPeriodDTO returnDTO = new ReturnPeriodDTO(rtn);

            return ResponseEntity.ok().body(returnDTO);
        }
        catch (ReturnNotFoundException e){
            logger.error("Return Not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Return does not exist");
        }
        catch (Exception e){
            logger.error("Something went wrong", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch the return");
        }
    }

    @GetMapping("/gstinNumber/{gstinNumber}/")
    public ResponseEntity<?> getAllReturnByGstinNumber(@PathVariable String gstinNumber){
        try {
            List<ReturnPeriod> rtns = this.returnPeriodService.getAllReturnByGstin(gstinNumber);

            List<ReturnPeriodDTO> returns = new ArrayList<>();
            for(ReturnPeriod p : rtns){
                returns.add(new ReturnPeriodDTO(p));
            }

            return ResponseEntity.ok().body(returns);
        }
        catch (ReturnNotFoundException e){
            logger.error("Return Not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Returns does not exist");
        }
        catch (Exception e){
            logger.error("Something went wrong", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch the return");
        }
    }

}
