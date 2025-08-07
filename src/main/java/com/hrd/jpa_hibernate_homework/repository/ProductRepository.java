package com.hrd.jpa_hibernate_homework.repository;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public Product save(ProductRequest req){
        Product newProduct = Product.builder().name(req.name()).price(req.price()).quantity(req.quantity()).build();
        entityManager.persist(newProduct);
        return newProduct;
    }
}
