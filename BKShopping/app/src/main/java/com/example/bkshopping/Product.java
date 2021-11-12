package com.example.bkshopping;

import java.util.Date;

public class Product {
    private int id;
    private String productName;
    private int quantity;
    private  float price;
    private Date purchase_date;

    public Product(int id, String productName, int quantity, float price, Date purchase_date) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.purchase_date = purchase_date;
    }

    public Product(String productName, int quantity, float price, Date purchase_date) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.purchase_date = purchase_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }
}
