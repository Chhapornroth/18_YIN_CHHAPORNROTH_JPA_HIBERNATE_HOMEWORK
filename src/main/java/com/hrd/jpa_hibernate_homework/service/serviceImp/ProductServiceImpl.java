package com.hrd.jpa_hibernate_homework.service.serviceImp;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import com.hrd.jpa_hibernate_homework.repository.ProductRepository;
import com.hrd.jpa_hibernate_homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(ProductRequest req) {
        Product newProduct = Product.builder()
                .name(req.name())
                .price(req.price())
                .quantity(req.quantity()).build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.remove(id);
    }
}
