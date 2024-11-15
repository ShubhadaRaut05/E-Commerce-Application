package com.shubhada.productservice.controllers;

import com.shubhada.productservice.Clients.authenticationclient.AuthenticationClient;
import com.shubhada.productservice.dtos.*;
import com.shubhada.productservice.exceptions.NotFoundException;
import com.shubhada.productservice.models.Product;
import com.shubhada.productservice.repositories.CategoryRepository;
import com.shubhada.productservice.repositories.products.ProductRepository;
import com.shubhada.productservice.services.ProductService;
import com.shubhada.productservice.services.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    //inversion of control
    private final ProductService productService;
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthenticationClient authenticationClient;
    private final Utils utils;
    public ProductController(ProductService productService,ProductRepository productRepository,Utils utils,AuthenticationClient authenticationClient){

        this.productService=productService;
        this.productRepository=productRepository;
        this.utils=utils;
        this.authenticationClient=authenticationClient;
    }
    public List<ProductResponseDTO> convertToProductResponseDTO(List<Product> products){
        List<ProductResponseDTO> newProducts=new ArrayList<>();
        for(Product pr:products){
            ProductResponseDTO product=new ProductResponseDTO();
            product.setId(pr.getId());
            product.setTitle(pr.getTitle());
            product.setPrice(pr.getPrice());
            product.setCategory(pr.getCategory().getName());
            product.setDescription(pr.getDescription());
            product.setImage(pr.getImageUrl());
            newProducts.add(product);
        }
        return newProducts;
    }
    public ProductResponseDTO convertToProductDTO(Optional<Product> products){
        ProductResponseDTO product=new ProductResponseDTO();
        product.setId(products.get().getId());
        product.setTitle(products.get().getTitle());
        product.setPrice(products.get().getPrice());
        product.setCategory(products.get().getCategory().getName());
        product.setDescription(products.get().getDescription());
        product.setImage(products.get().getImageUrl());
        return product;
    }

    //return all products in paginated way
    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getProducts(@RequestBody getProductsRequestDto request){

        return ResponseEntity.of(Optional.ofNullable(productService.getProducts(
                request.getNumberOfResults(), request.getOffset()
        )));
    }
    //task make only admins be able to access all products
    @GetMapping("")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@Nullable  @RequestHeader("AUTH_TOKEN") String token
                                                                 , @Nullable @RequestHeader("USER_ID") Long userId){
        //check if token exists
//        if(token==null|| userId==null){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        //check if token is valid
      /*  ValidateTokenResponseDto response= authenticationClient.validate(token,userId);
        if(response.getSessionStatus().equals(SessionStatus.INVALID)){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }*/
        //validate token
        //RestTemplate rt=new RestTemplate
        //rt.get("localhost:9090/auth/validate")

        //check if user has permission
       /* boolean isUserAdmin =false;
        for(Role role:response.getUserDto().getRoles()){
            if(role.getName().equals("ADMIN")){
                isUserAdmin=true;
            }
        }*/
       /* if(!isUserAdmin){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }*/
        List<Product> products=new ArrayList<>();
        try {
            products= productService.getAllProducts();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        //return "Getting All Products";
        return new ResponseEntity<>(convertToProductResponseDTO(products),HttpStatus.OK);
    }


        @GetMapping("/{productId}") //GetSingleProductResponseDTO
    public GenericProductDto getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {

            GenericProductDto productDto = productService.getProductById(productId);
            if (productDto == null) {
                throw new NotFoundException("Product Doesn't Exist");
            }

            return productDto;
    }
    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> addNewProduct(@RequestBody AddNewProductRequestDTO product){
        Product products=utils.addDtoToProduct(product);
        ProductResponseDTO newProduct= convertToProductDTO(Optional.ofNullable(productService.addNewProduct(products)));
       /* Product newProduct=new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImage());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());
       newProduct=productRepository.save(newProduct);*/
       ResponseEntity<ProductResponseDTO> response=new ResponseEntity<>(newProduct,HttpStatus.OK);
        return response;
      //  return "Adding New Product with "+productDTO;
    }
    //assignment take requestBody

    @PatchMapping("/{productId}")
    public ProductResponseDTO updateProduct(@PathVariable("productId") Long productId,
                                 @RequestBody UpdateRequestDto productDTO) throws NotFoundException {
        //return "Updating a Product with id: "+productId +" and with data: "+productDTO;
        //convert productDTO object into Product object

        Product newProduct= utils.productDtoToProduct(productDTO,productId);
        return ProductResponseDTO.from(productService.replaceProduct(productId,newProduct));
    }
    @PutMapping("/{productId}")
    public ProductResponseDTO replaceProduct(@PathVariable("productId") Long productId,
                                  @RequestBody UpdateRequestDto productDTO) throws NotFoundException {

        Product newProduct= utils.productDtoToProduct(productDTO,productId);
        return ProductResponseDTO.from(productService.replaceProduct(productId,newProduct));


    }
    @DeleteMapping("/{productId}")
    public Optional<ProductResponseDTO> deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {

        Optional<Product> productOptional=Optional.ofNullable(productService.deleteProduct(productId).orElseThrow(() -> new NotFoundException("Product not found with id: " + productId)));
        return Optional.of(ProductResponseDTO.from(productOptional.get()));
        //return "hello";
       /* return "Deleting a Product with id: "+productId;*/
    }
    /*@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(Exception exception){
     ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
     errorResponseDTO.setErrorMessage(exception.getMessage());
     return new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);
    }*/


}
