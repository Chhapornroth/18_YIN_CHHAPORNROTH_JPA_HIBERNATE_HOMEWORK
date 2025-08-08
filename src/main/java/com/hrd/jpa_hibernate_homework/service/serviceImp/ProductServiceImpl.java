package com.hrd.jpa_hibernate_homework.service.serviceImp;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.exception.NotFoundException;
import com.hrd.jpa_hibernate_homework.model.base.PaginationResponseAPI;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import com.hrd.jpa_hibernate_homework.repository.ProductRepository;
import com.hrd.jpa_hibernate_homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public PaginationResponseAPI<Product> getAllProducts(Integer page, Integer size) {
        return productRepository.findAll(page, size);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }

    @Override
    public Product updateProductById(Long id, ProductRequest req) {
        return productRepository.save(id, req);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.remove(id);
    }

    @Override
    public List<Product> getLowStockProducts(Integer quantity) {
        return productRepository.getLowStockProducts(quantity);
    }
}
