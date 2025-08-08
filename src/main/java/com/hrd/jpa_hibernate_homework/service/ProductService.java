package com.hrd.jpa_hibernate_homework.service;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.model.base.PaginationResponseAPI;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface ProductService {
    Product saveProduct(@Valid ProductRequest request);

    Product getProductById(@Positive Long id);

    PaginationResponseAPI<Product> getAllProducts(@Positive Integer page, @Positive Integer size);

    List<Product> searchByName(@NotBlank String name);

    Product updateProductById(@Positive Long id, @Valid ProductRequest request);

    void deleteProductById(@Positive Long id);

    List<Product> getLowStockProducts(@Positive Integer quantity);
}