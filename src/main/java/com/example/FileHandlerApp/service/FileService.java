package com.example.FileHandlerApp.service;

import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

import com.example.FileHandlerApp.model.FileData;

public interface FileService {

	public String uploadFile(MultipartFile doc) throws IOException;

	public List<FileData> getAllFilesData();

	public Resource loadFile(String fileName);

	public FileData searchFile(FileData fileData);

}
