package com.willysalazar.garagesaleapi.services;

import com.willysalazar.garagesaleapi.models.ProductsModel;
import com.willysalazar.garagesaleapi.repositories.ProductsRepository;
import org.hibernate.annotations.ManyToAny;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.math.BigDecimal;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductsServicesTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductsService productsService;

    @Test
    void shouldbeGetAllProducts(){
        UUID id = UUID.fromString("2fb7fa23-f6be-4a32-aea6-156aa1483004");
        var product = new ProductsModel();
        product.setId(id);
        product.setTitle("Webcam Logitech C922 Pro Full HD 1080p");
        product.setDescription("Webcam Logitech C922 Pro Full HD 1080p");
        product.setPrice(BigDecimal.valueOf(439.00));
        product.setDiscountPercentage(BigDecimal.valueOf(10.00));
        product.setStock(1);
        product.setBrand("Logitech");
        product.setCategory("It Acessories");

        Mockito.when(productsRepository.findById(any()))
                .thenReturn(Optional.ofNullable(product));

        var result = productsService.findById(id);

        assertEquals(result,product);
        verify(productsRepository).findById(id);


    }
}
