package br.schoolsystem.schoolsystemweb.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StoreUploadService {
	
	public String storeFile(MultipartFile file) {
		
		Path fileStorageLocation = Paths.get("./uploads")
                .toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(fileStorageLocation);
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
			 Path targetLocation = fileStorageLocation.resolve(fileName);
	         Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
