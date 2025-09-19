package com.loginproject.loginapp;

import jakarta.persistence.Column;
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
    @Column(name = "pro_id")
    private Long pro_id;

    @Column(name = "pro_name")
    private String proName;

    private String category;
    private double price;
    private double discount;
    private int quantity;
    private String imagePath;

    public Product() {}

    public Product(String proName, String category, double price,double discount, int quantity,String imagePath) {
        this.proName = proName;
        this.category = category;
        this.price = price;
        this.discount=discount;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public Long getPro_id() { return pro_id; }
    public void setPro_id(Long pro_id) { this.pro_id = pro_id; }

    public String getProName() { return proName; }
    public void setProName(String proName) { this.proName = proName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getImagePath(){ return imagePath; }
    public void setImagePath(String imagePath){ this.imagePath=imagePath; }
}


