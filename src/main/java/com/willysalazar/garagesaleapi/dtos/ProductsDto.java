package com.willysalazar.garagesaleapi.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductsDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private BigDecimal discountPercentage;
    @NotBlank
    private int stock;
    @NotBlank
    private String brand;
    @NotBlank
    private String category;
}
