package com.hrd.jpa_hibernate_homework.controller;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.model.base.BaseController;
import com.hrd.jpa_hibernate_homework.model.base.ResponseAPI;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import com.hrd.jpa_hibernate_homework.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController extends BaseController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product!!!")
    public ResponseEntity<ResponseAPI<Product>> saveProduct(@RequestBody ProductRequest request) {
        return response(ResponseAPI.<Product>builder()
                .message("Create a new product successfully!!!")
                .status(HttpStatus.CREATED)
                .payload(productService.saveProduct(request)).build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public ResponseEntity<ResponseAPI<Product>> getProductById(@PathVariable Long id) {
        return response(ResponseAPI.<Product>builder()
                .message("Get product by id successfully!!!")
                .status(HttpStatus.OK)
                .payload(productService.getProductById(id)).build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by id", description = "Deletes a product by its ID. Returns HTTP 200 if the product is successfully deleted.")
    public ResponseEntity<ResponseAPI<Object>> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return response(ResponseAPI.builder()
                .message("Delete product by id successfully!!!")
                .status(HttpStatus.OK).build());
    }
}

