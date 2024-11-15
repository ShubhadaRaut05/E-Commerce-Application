package com.shubhada.productservice.services;

import com.shubhada.productservice.dtos.GenericProductDto;
import com.shubhada.productservice.dtos.UserDto;
import com.shubhada.productservice.exceptions.NotFoundException;
import com.shubhada.productservice.models.Product;
import com.shubhada.productservice.repositories.CategoryRepository;
import com.shubhada.productservice.repositories.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private RestTemplate restTemplate;


    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository,RestTemplate restTemplate){
        this.productRepository=productRepository;
       this.categoryRepository=categoryRepository;
       this.restTemplate=restTemplate;

    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }
    //10,30
    public Page<Product> getProducts(int numberOfProducts,int offset){
        //productRepository.executeQuery("select * from products limit numberOfProducts offset offset")

        Page<Product> products=productRepository.findAll(
                PageRequest.of((offset/numberOfProducts),numberOfProducts,
                        Sort.by("price").descending()
                                .and(
                                Sort.by("title").ascending()
                                )

        )

        );
        //order by price desc, title asc
        return products;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        System.out.println("In product service");

       // RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<UserDto> userDto = restTemplate.getForEntity(
                "http://localhost:9002/users/1",
                //"http://userservice/users/1",
                UserDto.class);

        /*Optional<Product> productOptional= Optional.ofNullable(Optional.ofNullable(productRepository.findProductById(productId))
                .orElseThrow(() -> new NotFoundException("Does not found product with id: " + productId)));
        return Optional.of(productRepository.findProductById(productId));*/
        return Optional.of(new Product());
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        System.out.println("In product service");
     //   RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<UserDto> userDto = restTemplate.getForEntity(
                //"http://localhost:9002/users/1",
                "http://userservice/users/1",
                UserDto.class);

        // Product product = ProductRepository.getProductByID(id);
        //  if (product.getStatus().equals(PRIVATE)) {
        //      if (userIdTryingToAccess.equals(product.getCreatorId())) {
        //        return product;
        //      }
        //       return null;
        //  }
        //
        // return product;
        return new GenericProductDto();
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws NotFoundException {

        Optional<Product> productOptional= Optional.ofNullable(Optional.ofNullable(productRepository.findProductById(productId))
                .orElseThrow(() -> new NotFoundException("Does not found product with id: " + productId)));
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) throws NotFoundException {

        Optional<Product> productOptional= Optional.ofNullable(Optional.ofNullable(productRepository.findProductById(productId))
                .orElseThrow(() -> new NotFoundException("Does not found product with id: " + productId)));
        return productRepository.save(product);
    }
    @Override
    public Optional<Product> deleteProduct(Long productId) throws NotFoundException {
        Optional<Product> productOptional= Optional.ofNullable(Optional.ofNullable(productRepository.findProductById(productId))
                .orElseThrow(() -> new NotFoundException("Does not found product with id: " + productId)));
        Product product=productRepository.findProductById(productId);
     productRepository.deleteById(productId);
      return Optional.ofNullable(product);
       // return null;
    }
}
