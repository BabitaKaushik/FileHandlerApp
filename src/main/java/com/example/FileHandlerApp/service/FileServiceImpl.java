package com.example.FileHandlerApp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileHandlerApp.dao.FileDAO;
import com.example.FileHandlerApp.model.FileData;

@Service
public class FileServiceImpl implements FileService {
	public List<FileData> getAllFilesData() {
		List<FileData> fileData = fileDAO.findAll();

		return fileData;
	}

	@Autowired
	FileDAO fileDAO;

	@Override
	public String uploadFile(MultipartFile doc) throws IOException {
		byte[] buffer = new byte[8 * 1024];
		InputStream input = null;
		try {
			FileData fileData = new FileData();
			fileData.setFileName(doc.getOriginalFilename());
			fileData.setSize(doc.getSize());

			if (doc.getSize() == 0) {
				fileData.setEmpty(false);
			}
			// File newFile = new java.io.File("c://uploadedFiles" +
			// doc.getOriginalFilename());
			input = doc.getInputStream();
			OutputStream output = new FileOutputStream("c://uploadedFiles/" + doc.getOriginalFilename());
			try {
				int bytesRead;
				while ((bytesRead = input.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}
			} finally {
				output.close();
			}
			FileData tempObject = fileDAO.save(fileData);
			System.out.println(tempObject);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			input.close();
		}
		return "";
	}

	@Override
	public Resource loadFile(String fileName) {
		Resource resource = null;
		try {
			resource = new ClassPathResource("c://uploadedFiles/" + fileName);
			/*
			 * // resource = new UrlResource("c://uploadedFiles/" + fileName); File file =
			 * new File("c://uploadedFiles/" +fileName) InputStream is = new
			 * FileInputStream(file);; BufferedReader br = new BufferedReader(new
			 * InputStreamReader(is)); String line; while ((line = br.readLine()) != null) {
			 * System.out.println(line); br.close();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resource;
	}

	@Override
	public FileData searchFile(FileData fileData) {
		// TODO Auto-generated method stub
		return null;
	}
}
