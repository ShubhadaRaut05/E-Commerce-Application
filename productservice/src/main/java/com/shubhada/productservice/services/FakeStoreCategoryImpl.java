package com.shubhada.productservice.services;

import com.shubhada.productservice.Clients.fakestoreapi.FakeStoreClient;
import com.shubhada.productservice.dtos.CategoryDTO;
import com.shubhada.productservice.dtos.ProductDTO;
import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
//@Primary
public class FakeStoreCategoryImpl implements CategoryService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    FakeStoreCategoryImpl(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreClient){

        this.restTemplateBuilder=restTemplateBuilder;
        this.fakeStoreClient=fakeStoreClient;
    }
    @Override
    public Object[] getAllCategories() {
        RestTemplate restTemplate=restTemplateBuilder.build();
       ResponseEntity<Object[]> l= restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories",
               Object[].class
        );
       return l.getBody();
    }

    @Override
    public List<ProductResponseDTO> getProductsInCategory(String category) {
       /* RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductDTO[]> response=restTemplate.getForEntity(
                "https://fakestoreapi.com/products/category/{category}",
                ProductDTO[].class,
                category
        );*/
        List<ProductResponseDTO> result= fakeStoreClient.getProductsInCategory(category);
        return result;
    }
}
