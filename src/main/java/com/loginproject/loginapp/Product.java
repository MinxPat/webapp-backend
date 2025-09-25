package com.loginproject.loginapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long pro_id;

    @NotNull(message = "Product name cannot be null")
    @Column(name = "pro_name", nullable = false)
    private String proName;   // product name

    @NotNull(message = "Category cannot be null")
    @Column(nullable = false)
    private String category;  // product category

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price cannot be negative")
    @Column(nullable = false)
    private double price;     // price of a product

    @Min(value = 0, message = "Discount cannot be negative")
    @Column(nullable = false, columnDefinition = "double default 0")
    private double discount = 0.0;  // default = 0

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private int quantity;     // quantity value

    @NotNull(message = "Quantity unit must be provided")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuantityUnit quantityUnit; //  only kg, g, ml, l

    @Lob
    private String description;   // product description 

    private String imagePath;    // link for the image

    // Constructors
    public Product() {}

    public Product(String proName, String category, double price, double discount,
                   int quantity, QuantityUnit quantityUnit,
                   String description, String imagePath) {
        this.proName = proName;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public Long getPro_id() {
        return pro_id;
    }
    public void setPro_id(Long pro_id) {
        this.pro_id = pro_id;
    }

    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }
    public void setQuantityUnit(QuantityUnit quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

enum QuantityUnit {
    kg, g, ml, l
}




