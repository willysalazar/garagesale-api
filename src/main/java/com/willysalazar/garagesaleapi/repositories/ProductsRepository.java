package com.willysalazar.garagesaleapi.repositories;

import com.willysalazar.garagesaleapi.models.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, UUID> {

}
