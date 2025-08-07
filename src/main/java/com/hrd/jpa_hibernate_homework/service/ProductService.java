package com.hrd.jpa_hibernate_homework.service;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;

public interface ProductService {
    Product saveProduct(ProductRequest request);

    Product getProductById(Long id);

    void deleteProductById(Long id);
}
