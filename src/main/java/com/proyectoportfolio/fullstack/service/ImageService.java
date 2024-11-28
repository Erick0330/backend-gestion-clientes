package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image uploadImage(MultipartFile file) throws IOException;
    void deleteImage(Image image) throws IOException;
}
