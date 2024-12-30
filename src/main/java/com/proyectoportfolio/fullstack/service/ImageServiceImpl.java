package com.proyectoportfolio.fullstack.service;

import com.proyectoportfolio.fullstack.entity.Image;
import com.proyectoportfolio.fullstack.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService{

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {

        Map uploadResult = cloudinaryService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        String imageId = (String) uploadResult.get("public_id");
        Image image = new Image(file.getOriginalFilename(), imageUrl, imageId);

        return imageRepository.save(image);
    }

    @Override
    @Transactional
    public void deleteImage(Image image) throws IOException {
        try {
            imageRepository.deleteById(image.getId());
            cloudinaryService.delete(image.getImageCloudI());
            System.out.println("Image with ID " + image.getId() + " deleted successfully.");
        } catch (Exception e) {
            System.err.println("Error deleting image: " + e.getMessage());
            throw e;
        }

    }
}
