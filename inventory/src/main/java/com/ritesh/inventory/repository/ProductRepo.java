package com.ritesh.inventory.repository;

import com.ritesh.inventory.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products, String> {
}
