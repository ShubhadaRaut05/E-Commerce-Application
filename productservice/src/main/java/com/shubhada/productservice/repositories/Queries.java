package com.shubhada.productservice.repositories;

public interface Queries {
    String LAAO_PRODUCTS_WITH_ID_QUERY="select title as title from product where id= :id";
    String GET_PRODUCTS_BY_CATEGORY="select * from product p join category c on p.category_id=c.id where c.name= :category";
}
