package com.willysalazar.garagesaleapi.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal discountPercentage;
    @NotNull
    private int stock;
    @NotBlank
    private String brand;
    @NotBlank
    private String category;
}
