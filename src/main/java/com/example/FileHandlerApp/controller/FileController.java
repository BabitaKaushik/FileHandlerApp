package com.example.FileHandlerApp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileHandlerApp.model.FileData;
import com.example.FileHandlerApp.service.FileService;

@RestController
public class FileController {

	@Autowired
	FileService fileService;

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("doc") MultipartFile doc) {
		FileData fileMetaDataDTO = new FileData();

		System.out.println(doc.getOriginalFilename());
		fileMetaDataDTO.setFileName(doc.getOriginalFilename());
		System.out.println(doc.getSize());
		fileMetaDataDTO.setSize(doc.getSize());
		System.out.println(doc.isEmpty());
		fileMetaDataDTO.setEmpty(doc.isEmpty());
		try {
			fileService.uploadFile(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@GetMapping("/listFiles")
	public List<FileData> listFiles() {
		List<FileData> fileDataList = fileService.getAllFilesData();
		return fileDataList;
	}

	@GetMapping("/getFile/{fileName:.+}")
	public ResponseEntity<FileSystemResource> getFile(@PathVariable String fileName, HttpServletRequest request) {
		// Resource file = fileService.loadFile(fileName);

		String contentType = null;
		FileSystemResource resource = null;
		try {
			File tempFile = new File("c://uploadedFiles/" + fileName);
			resource = new FileSystemResource(tempFile);
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.info("Could not determine file type.");
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Disposition", "attachment; filename=" + fileName);
		responseHeaders.setContentType(MediaType.parseMediaType(contentType));

		ResponseEntity<FileSystemResource> responseEntity = new ResponseEntity<>(resource, responseHeaders,
				HttpStatus.OK);

		return responseEntity;

	}

}
