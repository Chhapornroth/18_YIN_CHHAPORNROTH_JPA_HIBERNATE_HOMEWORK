package com.hrd.jpa_hibernate_homework.repository;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.exception.NotFoundException;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Product save(Product newProduct){
        entityManager.persist(newProduct);
        return newProduct;
    }

    public Product findById(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id)).orElseThrow(() -> new NotFoundException("product with id: " + id));
    }

    public void remove(Long id) {
        Product product = findById(id);
        entityManager.remove(product);
    }


}
