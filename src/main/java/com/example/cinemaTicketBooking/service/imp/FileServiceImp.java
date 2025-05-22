package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImp implements FileService {
    @Value("${file.upload}")
    private String root;

    @Override
    public void saveFile(MultipartFile file) {
        try {
            Path path = Paths.get(file.getOriginalFilename());
            if(!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            System.out.println("error at saveFileService "+e.getMessage());
        }
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path path = Paths.get(root).resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists()){
                return resource;
            }
        }catch (Exception e){
            System.out.println("error at loadFileService "+e.getMessage());
        }
        return null;
    }
}
