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
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping
    public ResponseEntity<Object> saveProducts(@RequestBody @Valid ProductsDto productsDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.save(productsDto));
    }

    @GetMapping
    public ResponseEntity<Page<ProductsModel>> getProducts(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneByIdProducts(@PathVariable(value = "id") UUID id){
        Optional<ProductsModel> parkingSpotModelOptional = productsService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductsModel> productsModelOptional = productsService.findById(id);
        if(!productsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productsService.delete(productsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product " + productsModelOptional.get().getTitle() + " deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductsDto parkingSpotDto){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.updateAndSave(id, parkingSpotDto));
    }


}
