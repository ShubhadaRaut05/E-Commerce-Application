package com.shubhada.productservice.repositories;

import com.shubhada.productservice.dtos.ProductResponseDTO;
import com.shubhada.productservice.models.Category;
import com.shubhada.productservice.models.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category save(Category category);
    List<Category> findAllByIdIn(List<Long> ids);

    @Query(value ="select p.id,p.title,p.price,p.description,p.image_url,c.name from product p join category c on p.category_id=c.id where c.name= :category",nativeQuery = true)
    List<Object[]> findByCategory_Name(@Param("category") String category);


    @Query(value="select name from category",nativeQuery = true)
    Object[] getCategories();

    Optional<Category> getCategoryByName(String category);

    // Optional<Category> findByCategoryName(String category);
}
