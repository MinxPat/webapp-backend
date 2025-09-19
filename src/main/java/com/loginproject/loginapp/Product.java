package com.loginproject.loginapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pro_id;

    private String pro_name;     // Product name
    private String category;    // Product category
    private double price;       // Price of product
    private double discount;    //Discount for product
    private int quantity;       // Inventory stock
    private String imagePath;   //path to the image

    public Product() {}

    public Product(String pro_name, String category, double price,double discount, int quantity,String imagePath) {
        this.pro_name = pro_name;
        this.category = category;
        this.price = price;
        this.discount=discount;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    // Getters and setters
    public Long getId() {
        return pro_id;
    }

    public String getName() {
        return pro_name;
    }

    public void setName(String name) {
        this.pro_name = name;
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

    public String getImagePath(){
        return imagePath;
    }

    public void setImagePath(String imagePath){
        this.imagePath=imagePath;
    }
}

