package com.angularspringbootecommerce.backend.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String title;
   // private String description;
    private BigDecimal price;
    private String image;
}
