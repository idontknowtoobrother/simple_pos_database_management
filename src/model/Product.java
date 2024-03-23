/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hexademical
 */
public class Product {

    private int productId;
    private String name;
    private String description;
    private double price;
    private int totalAvailable;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deleteAt;

    // Constructors
    public Product() {
    }

    public Product(int productId, String name, String description, double price, int totalAvailable, Timestamp createdAt, Timestamp updatedAt, Timestamp deleteAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.totalAvailable = totalAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleteAt = deleteAt;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(int totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(createdAt.getTime()));
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedAtLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(updatedAt.getTime()));
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isRemovedFromStore() {
        return deleteAt != null;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public String getDeleteAtLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(deleteAt.getTime()));
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    // toString method
    @Override
    public String toString() {
        return "Product{"
                + "productId=" + productId
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", totalAvailable=" + totalAvailable
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + '}';
    }
}
