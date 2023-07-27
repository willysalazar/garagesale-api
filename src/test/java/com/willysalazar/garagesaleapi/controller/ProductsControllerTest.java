package com.willysalazar.garagesaleapi.controller;

import com.google.gson.Gson;
import com.willysalazar.garagesaleapi.controllers.ProductsController;
import com.willysalazar.garagesaleapi.dtos.ProductsDto;
import com.willysalazar.garagesaleapi.models.ProductsModel;
import com.willysalazar.garagesaleapi.services.ProductsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductsController.class)
public class ProductsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductsService productsService;

    @Test
    @WithMockUser
    void shouldBeCreateProduct_201() throws Exception {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setTitle("Webcam Logitech C922 Pro Full HD 1080p");
        productsDto.setDescription("Webcam Logitech C922 Pro Full HD 1080p");
        productsDto.setPrice(BigDecimal.valueOf(439.00));
        productsDto.setDiscountPercentage(BigDecimal.valueOf(10.00));
        productsDto.setStock(1);
        productsDto.setBrand("Logitech");
        productsDto.setCategory("It Acessories");

        ProductsModel productsModel = new ProductsModel();
        productsModel.setTitle("Webcam Logitech C922 Pro Full HD 1080p");
        productsModel.setDescription("Webcam Logitech C922 Pro Full HD 1080p");
        productsModel.setPrice(BigDecimal.valueOf(439.00));
        productsModel.setDiscountPercentage(BigDecimal.valueOf(10.00));
        productsModel.setStock(1);
        productsModel.setBrand("Logitech");
        productsModel.setCategory("It Acessories");

        String paraJson = new Gson().toJson(productsDto);
        Mockito.when(productsService.save(productsDto)).thenReturn(productsModel);

        mockMvc.perform(post("/products")
                .contentType("application/json").content(paraJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Webcam Logitech C922 Pro Full HD 1080p")));

        verify(productsService).save(productsDto);
     }
}
