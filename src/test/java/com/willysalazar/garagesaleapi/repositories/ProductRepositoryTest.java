package com.willysalazar.garagesaleapi.repositories;

import com.willysalazar.garagesaleapi.dtos.ProductsDto;
import com.willysalazar.garagesaleapi.models.ProductsModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductsRepository productsRepository;

    @BeforeEach
    void setup(){
        productsRepository.deleteAll();
    }

    @Test
    void shouldBeSaveProduct(){
        var productsModel = new ProductsModel();
        productsModel.setTitle("Webcam Logitech C922 Pro Full HD 1080p");
        productsModel.setDescription("Webcam Logitech C922 Pro Full HD 1080p");
        productsModel.setPrice(BigDecimal.valueOf(439.00));
        productsModel.setDiscountPercentage(BigDecimal.valueOf(10.00));
        productsModel.setRegistrationDate(LocalDateTime.now());
        productsModel.setStock(1);
        productsModel.setBrand("Logitech");
        productsModel.setCategory("It Acessories");

        productsRepository.save(productsModel);

        var result = productsRepository.findAll();
        assertThat(result).contains(productsModel);



    }
}
