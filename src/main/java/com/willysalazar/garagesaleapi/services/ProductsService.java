package com.willysalazar.garagesaleapi.services;

import com.willysalazar.garagesaleapi.models.ProductsModel;
import com.willysalazar.garagesaleapi.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductsService {

    @Autowired
    ProductsRepository parkingSpotRepository;

    @Transactional
    public ProductsModel save(ProductsModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }
}
