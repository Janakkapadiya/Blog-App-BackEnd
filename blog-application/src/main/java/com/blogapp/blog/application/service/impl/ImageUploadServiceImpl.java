package com.blogapp.blog.application.service.impl;

import com.blogapp.blog.application.service.ImageUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageUploadServiceImpl implements ImageUploadService{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name =  file.getOriginalFilename();
        String filePath = path + File.separator+name;
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream loadFileAsResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath);
    }
}
