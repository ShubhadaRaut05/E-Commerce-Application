package com.shubhada.productservice.services;

import com.shubhada.productservice.dtos.AddNewProductRequestDTO;
import com.shubhada.productservice.dtos.UpdateRequestDto;
import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import com.shubhada.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Utils {
    private CategoryRepository categoryRepository;

    public Utils(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    public Product productDtoToProduct(UpdateRequestDto productDTO,Long productId){

        Product product=new Product();
        product.setId(productId);
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImage());
        product.setDescription(productDTO.getDescription());

         Optional<Category> categoryOptional=categoryRepository.getCategoryByName(productDTO.getCategory());

        if(categoryOptional.isEmpty()){
            Category category=new Category();
            category.setName(productDTO.getCategory());
            product.setCategory(category);
        }
        else {
            product.setCategory(categoryOptional.get());
        }

        return product;

    }

    public Product addDtoToProduct(AddNewProductRequestDTO productDTO){

        Product product=new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImage());
        product.setDescription(productDTO.getDescription());

        Optional<Category> categoryOptional=categoryRepository.getCategoryByName(productDTO.getCategory());

        if(categoryOptional.isEmpty()){
            Category category=new Category();
            category.setName(productDTO.getCategory());
            product.setCategory(category);
        }
        else {
            product.setCategory(categoryOptional.get());
        }

        return product;

    }
}
