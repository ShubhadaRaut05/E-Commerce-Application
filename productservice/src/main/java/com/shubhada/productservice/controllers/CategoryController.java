package com.shubhada.productservice.controllers;

import com.shubhada.productservice.dtos.CategoryDTO;
import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.exceptions.NotFoundException;
import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import com.shubhada.productservice.repositories.CategoryRepository;
import com.shubhada.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/categories")

public class CategoryController {

    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryController( CategoryService categoryService,CategoryRepository categoryRepository){

        this.categoryService=categoryService;
        this.categoryRepository=categoryRepository;

    }
    @GetMapping("")
    public Object[] getAllCategories(){
        return categoryService.getAllCategories();

        //return "Getting All Categories";
    }
    @GetMapping("/{category}")
    public List<ProductResponseDTO> getProductsInCategory(@PathVariable("category") String category) throws NotFoundException {
        return categoryService.getProductsInCategory(category);
        //return "Getting Products In Category is: "+category;
    }
}
