package com.shubhada.productservice.dtos;


import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String title;
    private double price;
    private String category;
    private  String description;
    private String image;

    public static ProductResponseDTO from(Product product){
        ProductResponseDTO dto=new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setImage(product.getImageUrl());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory().getName());
        dto.setDescription(product.getDescription());
        return dto;

    }
}
