package com.GSTUtils.server.Helper;

import java.awt.print.Printable;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	public final String UPLOAD_DIR = "C:\\Users\\III S I\\Project\\server\\src\\main\\resources\\static\\uploadExcelFile";
	
	public boolean uploadFile(MultipartFile multipartFile) {
		
		boolean flag = false;
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			
			byte[] fileData = new byte[fileStream.available()];
			
			// read file Data
			fileStream.read(fileData);
			
			// Write data in directory file
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + "\\" + multipartFile.getOriginalFilename());
			fos.write(fileData);
			
			fos.close();
			
			flag = true;
		}
		catch( Exception e){
			e.getStackTrace();
		}
		
		System.out.println("File uploaded successfully");
		
		return flag;
	}

}
