package com.shubhada.productservice.services;

import com.shubhada.productservice.dtos.CategoryDTO;
import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.exceptions.NotFoundException;
import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import com.shubhada.productservice.repositories.CategoryRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Primary
public class SelfCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;


    @Autowired
    public SelfCategoryService( CategoryRepository categoryRepository){

        this.categoryRepository=categoryRepository;
    }
    @Override
    public Object[] getAllCategories() {

        Object result[]=categoryRepository.getCategories();

        return result;
    }

    @Override
    public List<ProductResponseDTO> getProductsInCategory(String category) throws NotFoundException {
        Category cat=categoryRepository.getCategoryByName(category).orElseThrow(()->new NotFoundException("Category does not found with name: "+category));
        List<Object[]> prod=categoryRepository.findByCategory_Name(category);
        List<ProductResponseDTO> newProduct=new ArrayList<>();
        for(Object[] pr:prod){
            ProductResponseDTO pro=new ProductResponseDTO();
            pro.setId((Long)pr[0]);
            pro.setTitle((String) pr[1]);
            pro.setPrice((double)pr[2]);
            pro.setDescription((String)pr[3]);
            pro.setImage((String)pr[4]);
            pro.setCategory((String)pr[5]);
            newProduct.add(pro);
        }
        return newProduct;
    }


}
