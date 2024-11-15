package com.shubhada.productservice.dtos;

import com.shubhada.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDTO {
  private Product product;
}
