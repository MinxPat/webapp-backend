package com.loginproject.loginapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3002")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    // Create a product 
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(
            @RequestParam("proName") String proName,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam("quantityUnit") String quantityUnit,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "discount", required = false, defaultValue = "0.0") double discount,
            @RequestParam("imagePath") String imagePath) {

       
        Product product = new Product();
        product.setProName(proName);
        product.setCategory(category);
        product.setPrice(price);
        product.setQuantity(quantity);

        // Convert String -> Enum safely
        try {
            product.setQuantityUnit(QuantityUnit.valueOf(quantityUnit));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid quantityUnit. Allowed values: kg, g, ml, l");
        }

        product.setDescription(description);
        product.setDiscount(discount);
        product.setImagePath(imagePath);

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
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestParam("proName") String proName,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam("quantityUnit") String quantityUnit,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "discount", required = false, defaultValue = "0.0") double discount,
            @RequestParam("imagePath") String imagePath) {

        Optional<Product> existingProduct = productRepo.findById(id);
        if (existingProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product product = existingProduct.get();
        product.setProName(proName);
        product.setCategory(category);
        product.setPrice(price);
        product.setQuantity(quantity);

        // Convert String -> Enum safely
        try {
            product.setQuantityUnit(QuantityUnit.valueOf(quantityUnit));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid quantityUnit. Allowed values: kg, g, ml, l");
        }

        product.setDescription(description);
        product.setDiscount(discount);
        product.setImagePath(imagePath);

        Product updatedProduct = productRepo.save(product);
        return ResponseEntity.ok(updatedProduct);
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
