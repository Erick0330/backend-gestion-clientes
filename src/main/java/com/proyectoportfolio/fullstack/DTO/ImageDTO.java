package com.proyectoportfolio.fullstack.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    private Integer id;
    private String name;
    private String imageUrl;
    private String imageId;
}
