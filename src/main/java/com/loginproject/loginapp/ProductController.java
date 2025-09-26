package com.loginproject.loginapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3002")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    // Create a product
    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // collect validation errors
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }

        if (product.getQuantityUnit() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Quantity unit must be one of: kg, g, ml, l");
        }

        Product savedProduct = productRepo.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepo.findAll());
    }

    // Get a single product
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepo.findById(id);
        return product.isPresent() ?
                ResponseEntity.ok(product.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @Valid @RequestBody Product productDetails,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }

        if (productDetails.getQuantityUnit() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Quantity unit must be one of: kg, g, ml, l");
        }

        Optional<Product> existingProduct = productRepo.findById(id);

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProName(productDetails.getProName());
            product.setCategory(productDetails.getCategory());
            product.setPrice(productDetails.getPrice());
            product.setDiscount(productDetails.getDiscount());
            product.setQuantity(productDetails.getQuantity());
            product.setQuantityUnit(productDetails.getQuantityUnit());
            product.setDescription(productDetails.getDescription());
            product.setImagePath(productDetails.getImagePath());

            Product updatedProduct = productRepo.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
}



