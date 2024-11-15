package com.shubhada.productservice.repositories.products;

import com.shubhada.productservice.dtos.ProductDTO;
import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.models.Product;
import com.shubhada.productservice.repositories.Queries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
  // Product save(Product product);
   Product findProductById(Long id);

   Page<Product> findAll(Pageable pageable);
   @Query(value = Queries.GET_PRODUCTS_BY_CATEGORY,nativeQuery = true)
    List<Product> findProductByCategory_Name(String category);
    //  List<Product> findByTitleStartingWith(String title);
   //findByTitleLike(title+"%")
   //List<Product> findByTitleLike(String titleLike);


   /* @Query(value = "select title from product where id = :id", nativeQuery = true)
  List<ProductDBDto> laaoProductsWithId(Long id);*/
   /*@Query(value =Queries.LAAO_PRODUCTS_WITH_ID_QUERY,nativeQuery = true)
   List<ProductDBDto> laaoproductsWithId(Long id);

   @Query("select p from Product p where p.id = :id and p.category.name = :categoryName")
   public List<Product> getByIdAndTitle(Long id, String categoryName);*/

}
