package com.shubhada.productservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;
   // @OneToMany(fetch = FetchType.LAZY,mappedBy = "category", cascade = {CascadeType.REMOVE})
   // @Fetch(FetchMode.SUBSELECT)
   // @BatchSize(size = 1)
    //@PersistenceContext(type = PersistenceContextType.EXTENDED)
    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "category")
    private List<Product> products;
}
