package com.shubhada.productservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shubhada.productservice.repositories.Queries;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    //@ManyToOne(fetch = FetchType.LAZY)
  //  @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
    //@JoinColumn(name="category_id",insertable = false,updatable = false)
    private Category category;
    private String imageUrl;
}