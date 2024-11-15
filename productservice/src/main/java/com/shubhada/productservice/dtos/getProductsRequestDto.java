package com.shubhada.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getProductsRequestDto {
    private int numberOfResults;
    private int offset;

}
