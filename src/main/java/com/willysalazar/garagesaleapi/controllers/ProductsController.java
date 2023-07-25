package com.willysalazar.garagesaleapi.controllers;

import com.willysalazar.garagesaleapi.dtos.ProductsDto;
import com.willysalazar.garagesaleapi.models.ProductsModel;
import com.willysalazar.garagesaleapi.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping
    public ResponseEntity<Object> saveProducts(@RequestBody @Valid ProductsDto productsDto){
        var productsModel = new ProductsModel();
        BeanUtils.copyProperties(productsDto, productsModel);
        productsModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.save(productsModel));
    }

    @GetMapping
    public ResponseEntity<Page<ProductsModel>> getProducts(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.findAll(pageable));
    }

}
