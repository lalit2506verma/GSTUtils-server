package com.GSTUtils.server.Service.Impl;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.GSTUtils.server.Service.FastApiExcelService;

@Component
public class FastApiExcelServiceImpl implements FastApiExcelService{
	
	private final WebClient webClient;
	
	// Constructor
	public FastApiExcelServiceImpl() {
		
		this.webClient = WebClient.builder()
				.baseUrl("http://localhost:8000")
				.build();
	}

	@Override
	public byte[] processFile(MultipartFile salesFiles, MultipartFile returnSalesFile) throws IOException {
		
		ByteArrayResource salesResource = new ByteArrayResource(salesFiles.getBytes()) {
			
			@Override
			public String getFilename() {
				// TODO Auto-generated method stub
				return salesFiles.getOriginalFilename();
			}
		};
		
		ByteArrayResource returnSalesResource = new ByteArrayResource(returnSalesFile.getBytes()) {
			
			@Override
			public String getFilename() {
				// TODO Auto-generated method stub
				return returnSalesFile.getOriginalFilename();
			}
		};
		
		MultiValueMap<String, Object> formDataMap = new LinkedMultiValueMap<>();
		
		formDataMap.add("sales_file", salesResource);
		formDataMap.add("return_sales_file", returnSalesResource);
		
		return webClient.post()
				.uri("/process/")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(formDataMap))
				.retrieve()
				.bodyToMono(byte[].class)
				.block();
	}

}
