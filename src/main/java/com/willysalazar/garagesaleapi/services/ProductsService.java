package com.willysalazar.garagesaleapi.services;

import com.willysalazar.garagesaleapi.models.ProductsModel;
import com.willysalazar.garagesaleapi.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ProductsService {

    @Autowired
    ProductsRepository parkingSpotRepository;

    @Transactional
    public ProductsModel save(ProductsModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public Page<ProductsModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ProductsModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

}
