package com.willysalazar.garagesaleapi.services;

import com.willysalazar.garagesaleapi.dtos.ProductsDto;
import com.willysalazar.garagesaleapi.models.ProductsModel;
import com.willysalazar.garagesaleapi.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Transactional
    public ProductsModel save(ProductsDto productsDto) {
        var productsModel = new ProductsModel();
        BeanUtils.copyProperties(productsDto, productsModel);
        productsModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return productsRepository.save(productsModel);
    }

    public Page<ProductsModel> findAll(Pageable pageable) {
        return productsRepository.findAll(pageable);
    }

    public Optional<ProductsModel> findById(UUID id) {
        return productsRepository.findById(id);
    }

    @Transactional
    public void delete(ProductsModel productsModel) {
        productsRepository.delete(productsModel);
    }

    public ProductsModel updateAndSave(UUID id, ProductsDto productsDto) {
        ProductsModel productsModelUpdated = validateProduct(id);
        BeanUtils.copyProperties(productsDto, productsModelUpdated, "id");
        return productsRepository.save(productsModelUpdated);
    }

    private ProductsModel validateProduct(UUID uuid){
        Optional<ProductsModel> productsModelOptional = productsRepository.findById(uuid);
        if(!productsModelOptional.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return productsModelOptional.get();
    }

}
