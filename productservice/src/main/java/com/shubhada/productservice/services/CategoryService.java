package com.shubhada.productservice.services;

import com.shubhada.productservice.dtos.CategoryDTO;
import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.exceptions.NotFoundException;
import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

    Object[] getAllCategories();


    List<ProductResponseDTO> getProductsInCategory(String Category) throws NotFoundException;
}
