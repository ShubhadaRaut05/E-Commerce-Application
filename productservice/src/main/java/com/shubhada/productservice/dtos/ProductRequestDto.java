package com.shubhada.productservice.dtos;

import com.shubhada.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private double price;
    private  String description;
    private String image;
    private Category category;
}
