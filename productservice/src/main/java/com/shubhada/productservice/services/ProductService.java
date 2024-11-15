package com.shubhada.productservice.services;

import com.shubhada.productservice.dtos.GenericProductDto;
import com.shubhada.productservice.dtos.ProductDTO;
import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.exceptions.NotFoundException;
import com.shubhada.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
  Page<Product> getProducts(int numberOfProducts, int offset);

  List<Product> getAllProducts();


  Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

  GenericProductDto getProductById(Long id) throws NotFoundException;
  //service should not take DTO object
    Product addNewProduct(Product product);

    /*
    product object has only those fields filled which need to be update
    everything else is null
     */
    Product updateProduct( Long productId, Product product) throws NotFoundException;

    Product replaceProduct(Long productId,Product product) throws NotFoundException;

    Optional<Product> deleteProduct( Long productId) throws NotFoundException;
}
