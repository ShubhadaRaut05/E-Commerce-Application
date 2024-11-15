package com.shubhada.productservice.dtos;

import com.shubhada.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ProductDTO  implements Serializable {
    private Long id;
    private String title;
    private double price;
    private  String description;
    private String imageUrl;
    private Category category;
}
