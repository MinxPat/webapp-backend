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
    private String proName;   //product name

    private String category;  //product category
    private double price;     //price of a product
    private double discount;  //Discount for product
    private int quantity;     //quantity available
    private String description;   //product description
    private String imagePath;    // link for the image

    public Product() {}

    public Product(String proName, String category, double price,double discount, int quantity,String description,String imagePath) {
        this.proName = proName;
        this.category = category;
        this.price = price;
        this.discount=discount;
        this.quantity = quantity;
        this.description=description;
        this.imagePath = imagePath;
    }

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

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }

    public String getImagePath(){ 
        return imagePath; 
    }
    public void setImagePath(String imagePath){ 
        this.imagePath=imagePath; 
    }
}


