package com.blogapp.blog.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageUploadService{
    String uploadImage(String path, MultipartFile file) throws IOException;
    InputStream loadFileAsResource(String path,String fileName) throws FileNotFoundException;
}
