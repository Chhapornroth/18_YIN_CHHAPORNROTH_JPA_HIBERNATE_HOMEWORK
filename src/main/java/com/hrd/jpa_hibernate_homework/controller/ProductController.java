package com.hrd.jpa_hibernate_homework.controller;

import com.hrd.jpa_hibernate_homework.entity.Product;
import com.hrd.jpa_hibernate_homework.model.base.BaseController;
import com.hrd.jpa_hibernate_homework.model.base.PaginationResponseAPI;
import com.hrd.jpa_hibernate_homework.model.base.ResponseAPI;
import com.hrd.jpa_hibernate_homework.model.request.ProductRequest;
import com.hrd.jpa_hibernate_homework.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController extends BaseController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Accepts a product request payload and creates a new product. Returns the created product.")
    public ResponseEntity<ResponseAPI<Product>> saveProduct(@Valid @RequestBody ProductRequest request) {
        return response(ResponseAPI.<Product>builder()
                .message("Create a new product successfully!!!")
                .status(HttpStatus.CREATED)
                .payload(productService.saveProduct(request)).build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id", description = "Fetches a product using its unique ID. Returns 404 if not found.")
    public ResponseEntity<ResponseAPI<Product>> getProductById(@PathVariable @Positive Long id) {
        return response(ResponseAPI.<Product>builder()
                .message("Get product by id successfully!!!")
                .status(HttpStatus.OK)
                .payload(productService.getProductById(id)).build());
    }

    @GetMapping
    @Operation(summary = "Get all products (paginated)", description = "Returns a paginated list of all products. Accepts page and size as query parameters.")
    public ResponseEntity<ResponseAPI<PaginationResponseAPI<Product>>> getAllProducts(@RequestParam(defaultValue = "1") @Positive Integer page, @RequestParam(defaultValue = "10") @Positive Integer size) {
        PaginationResponseAPI<Product> list = productService.getAllProducts(page, size);
        return response(ResponseAPI.<PaginationResponseAPI<Product>>builder()
                .message(list.getItems() == null || list.getItems().isEmpty() ? "Product list in the " + page + " page is empty!!!" : "Products fetched successfully!!!")
                .status(HttpStatus.OK)
                .payload(list).build());
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name", description = "Returns a list of products that contain the given name (case-insensitive partial match).")
    public ResponseEntity<ResponseAPI<List<Product>>> searchByName(@RequestParam @NotBlank String name) {
        return response(ResponseAPI.<List<Product>>builder()
                .message("Products fetched successfully!!!")
                .status(HttpStatus.OK)
                .payload(productService.searchByName(name)).build());
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Get low stock products", description = "Returns a list of products with quantity less than the specified threshold.")
    public ResponseEntity<ResponseAPI<List<Product>>> getLowStockProducts(@RequestParam @Positive Integer quantity) {
        return response(ResponseAPI.<List<Product>>builder()
                .message("Products with quantity less than " + quantity + " fetched successfully!!!")
                .status(HttpStatus.OK)
                .payload(productService.getLowStockProducts(quantity)).build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product by id", description = "Deletes a product by its ID. Returns HTTP 200 if the product is successfully deleted.")
    public ResponseEntity<ResponseAPI<Product>> updateProductById(@PathVariable @Positive Long id, @RequestBody @Valid ProductRequest request) {
        return response(ResponseAPI.<Product>builder()
                .message("Update product by id successfully!!!")
                .status(HttpStatus.OK)
                .payload(productService.updateProductById(id, request)).build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by id", description = "Deletes a product by its ID. Returns HTTP 200 if the product is successfully deleted.")
    public ResponseEntity<ResponseAPI<Object>> deleteProductById(@PathVariable @Positive Long id) {
        productService.deleteProductById(id);
        return response(ResponseAPI.builder()
                .message("Delete product by id successfully!!!")
                .status(HttpStatus.OK).build());
    }
}

