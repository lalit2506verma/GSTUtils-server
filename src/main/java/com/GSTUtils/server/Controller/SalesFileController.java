package com.GSTUtils.server.Controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.GSTUtils.server.Helper.FileUploadHelper;
import com.GSTUtils.server.Service.FastApiExcelService;

@RestController
@RequestMapping("/SalesReport")
public class SalesFileController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@Autowired
	private FastApiExcelService fastApiExcelService;
	
	private static final List<String> ALLOWED_FILE_TYPE = Arrays.asList(
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // xlsx
				"application/vnd.ms-excel", 										 // xls
				"text/csv" 															 // csv
			);
	
	@PostMapping("/upload-file")
	public ResponseEntity<?> handlefileUpload(@RequestParam("sales_file") MultipartFile salesFile, @RequestParam("return_sales_file") MultipartFile returnSalesFile){
		
		String salesFileName = salesFile.getOriginalFilename();
		String salesContentType = salesFile.getContentType();
		
		String returnFileName = returnSalesFile.getOriginalFilename();
		String returnContentType = returnSalesFile.getContentType();
		
		try {
			
			// Validation
			if(salesFileName == null || salesContentType == null || ALLOWED_FILE_TYPE.stream().noneMatch(salesContentType::equalsIgnoreCase)) {
				return ResponseEntity.badRequest().body("Invalid Sales File type. Please upload excel file");
			}
			
			if(returnFileName == null || returnContentType == null || ALLOWED_FILE_TYPE.stream().noneMatch(returnContentType::equalsIgnoreCase)) {
				return ResponseEntity.badRequest().body("Invalid Return Sales File type. Please upload excel file");
			}
			
			System.out.println("Validation Completed");
			
			if(fileUploadHelper.uploadFile(salesFile) && fileUploadHelper.uploadFile(returnSalesFile)) {
				// File is uploaded successfully in the server location
				
				byte[] processedFile = fastApiExcelService.processFile(salesFile, returnSalesFile);
				
				HttpHeaders headers= new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDisposition(ContentDisposition.attachment().filename("processed_" + salesFileName).build());
				
				return new ResponseEntity<>(processedFile, headers, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		
		
	}

}
