package com.shubhada.productservice.inheritanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Getter
@Setter
@Entity(name = "tbc_instructor")
public class Instructor extends User {
    private boolean isHandsome;
}
