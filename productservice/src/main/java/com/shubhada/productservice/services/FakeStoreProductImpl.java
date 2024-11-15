package com.shubhada.productservice.services;

import com.shubhada.productservice.Clients.fakestoreapi.FakeStoreClient;
import com.shubhada.productservice.Clients.fakestoreapi.FakeStoreProductDTO;
import com.shubhada.productservice.dtos.GenericProductDto;
import com.shubhada.productservice.dtos.ProductDTO;
import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.exceptions.NotFoundException;
import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
//@Primary
public class FakeStoreProductImpl implements ProductService{
   private RestTemplateBuilder restTemplateBuilder;
   private FakeStoreClient fakeStoreClient;
   private Map<Long,Object> fakeStoreProducts=new HashMap<>();
   private RedisTemplate<Long,Object> redisTemplate;
   public FakeStoreProductImpl(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreClient,RedisTemplate<Long,Object> redisTemplate){
       this.restTemplateBuilder=restTemplateBuilder;
       this.fakeStoreClient=fakeStoreClient;
       this.redisTemplate=redisTemplate;
   }
 private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDTO productDTO){
     Product product=new Product();
     product.setId(productDTO.getId());
     product.setTitle(productDTO.getTitle());
     product.setPrice(productDTO.getPrice());
     product.setDescription(productDTO.getDescription());

     Category category=new Category();
     category.setName(productDTO.getCategory());
     product.setCategory(category);
     product.setImageUrl(productDTO.getImage());
     return product;

 }
    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDTO fakeStoreProductDto) {

        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }
   private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate=restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
       RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return((ResponseEntity)restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables));
    }

    @Override
    public Page<Product> getProducts(int numberOfProducts, int offset) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
      /* RestTemplate restTemplate=restTemplateBuilder.build();
      ResponseEntity<FakeStoreProductDTO[]> l= restTemplate.getForEntity(
               "https://fakestoreapi.com/products",
              FakeStoreProductDTO[].class

       );*/
        List<FakeStoreProductDTO> fakeStoreProductDTOS=fakeStoreClient.getAllProducts();
      List<Product> answer=new ArrayList<>();
      /*  for(FakeStoreProductDTO productDTO:l.getBody())*/
      for(FakeStoreProductDTO productDTO:fakeStoreProductDTOS)
      {

        //convert to product object
          answer.add(convertFakeStoreProductDtoToProduct(productDTO));

      }
        return answer;
    }
    /*
    Return a product object with all the details of the fetched product. The id of category will be null
    but the name of category is correct.
     */
    @Override
    public Optional<Product> getSingleProduct(Long productId) {
       //{url,returnType,parameter_in_url}
        FakeStoreProductDTO fakeStoreProductDTO=(FakeStoreProductDTO) redisTemplate.opsForHash().get(productId,"PRODUCTS");
        //redis caching
        if(fakeStoreProductDTO!=null){
            return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDTO));
        }
        //caching
       /* if(fakeStoreProducts.containsKey(productId)){
            return Optional.of(convertFakeStoreProductDtoToProduct((FakeStoreProductDTO) fakeStoreProducts.get(productId)));
        }*/

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDTO.class,
                productId);
        /*if(response.getStatusCode().is2xxSuccessful()){

        }else{}*/
        FakeStoreProductDTO productDTO=response.getBody();
        //put on hashmap
        //fakeStoreProducts.put(productId,productDTO);
        redisTemplate.opsForHash().put(productId,"PRODUCTS",productDTO);
        if(productDTO==null){
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductDtoToProduct(productDTO));
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        System.out.println("In product service");
        return convertFakeStoreProductIntoGenericProduct(fakeStoreClient.getProductById(id));
    }

    @Override
    public Product addNewProduct(Product product) {
       /* RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,//accepts parameters
                FakeStoreProductDTO.class//response
        );*/

        FakeStoreProductDTO productDTO=fakeStoreClient.addNewProduct(product);
        //convert into Product object

        return convertFakeStoreProductDtoToProduct(productDTO);
    }

    //Patch for Object API
    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDTO fakeStoreProductDTOResponse=fakeStoreClient.updateProduct(productId,product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDTOResponse);
      /*RestTemplate restTemplate=restTemplateBuilder.build();*/
       /* RestTemplate restTemplate=restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();*/
      /*FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
      fakeStoreProductDTO.setDescription(product.getDescription());
      fakeStoreProductDTO.setImage(product.getImageUrl());
      fakeStoreProductDTO.setPrice(product.getPrice());
      fakeStoreProductDTO.setTitle(product.getTitle());
      fakeStoreProductDTO.setCategory(product.getCategory().getName());*/
    /*ResponseEntity<FakeStoreProductDTO> response=  requestForEntity(
        HttpMethod.PATCH,
              "https://fakestoreapi.com/products/{id}",
              fakeStoreProductDTO,//requsetBody
            FakeStoreProductDTO.class,
              productId

      );*/
     /* FakeStoreProductDTO fakeStoreProductDTOResponse=restTemplate.patchForObject(
              "https://fakestoreapi.com/products/{id}",
              fakeStoreProductDTO,
              FakeStoreProductDTO.class,
              productId
      );*/
       /* return convertFakeStoreProductDtoToProduct(response.getBody());*/

    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDTO fakeStoreProductDTOResponse=fakeStoreClient.replaceProduct(productId,product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDTOResponse);
       /* RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        ResponseEntity<FakeStoreProductDTO> res=requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDTO,
                FakeStoreProductDTO.class,
                productId

        );*/

    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {

        FakeStoreProductDTO fakeStoreProductDTOResponse = fakeStoreClient.deleteProduct(productId);
        return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDTOResponse));
    }
}
