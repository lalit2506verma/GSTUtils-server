package com.GSTUtils.server.Service;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FastApiExcelService {
	
	public byte[] processFile(MultipartFile salesFile, MultipartFile returnSalesFile) throws IOException;

}
