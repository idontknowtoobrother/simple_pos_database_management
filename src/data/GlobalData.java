/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.User;
import utils.DatabaseService;

public class GlobalData {

    private static GlobalData instance = null;
    private User currentLoginnedUser = null;
    private List<Product> products;
    private int currentEditingProductId = -1;

    private GlobalData() {
        // สร้าง constructor เป็น private เพื่อป้องกันการสร้างอ็อบเจกต์จากภายนอกคลาส
    }

    public static GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public User getCurrentLoginnedUser() {
        return currentLoginnedUser;
    }

    public boolean isLogginned() {
        return currentLoginnedUser != null;
    }

    public void logout() {
        currentLoginnedUser = null;
    }

    public void setCurrentLoginnedUser(User user) {
        if (currentLoginnedUser == null) {
            currentLoginnedUser = user;
        } else {
            System.out.println("Current logged in user is already set.");
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getPrducts() {
        return this.products;
    }

    public Product getProductFromProductId(int findProductId) {
        for (Product product : this.products) {
            if (product.getProductId() == findProductId) {
                return product;
            }
        }
        return null;
    }

    public void setEditingProductId(int editingProductId) {
        this.currentEditingProductId = editingProductId;
    }

    public int getEditingProductId() {
        return this.currentEditingProductId;
    }

    public boolean isEditingProduct() {
        return this.currentEditingProductId != -1;
    }

    public void clearEditingProductId() {
        this.currentEditingProductId = -1;
    }

    public void updateProductById(Product editedProduct) {
        // หาสินค้าที่ต้องการอัปเดตโดยใช้ ID ของสินค้า
        if (DatabaseService.getInstance().updateProduct(editedProduct) == 1) {
            for (Product product : products) {
                if (product.getProductId() == editedProduct.getProductId()) {
                    // พบสินค้าที่ต้องการอัปเดต
                    // อัปเดตข้อมูลสินค้าด้วยข้อมูลที่แก้ไขแล้ว
                    product.setName(editedProduct.getName());
                    product.setDescription(editedProduct.getDescription());
                    product.setPrice(editedProduct.getPrice());
                    product.setTotalAvailable(editedProduct.getTotalAvailable());
                    product.setCreatedAt(editedProduct.getCreatedAt());
                    product.setUpdatedAt(editedProduct.getUpdatedAt());
                    break;
                }
            }
        }
    }

    public void deleteProduct(Product delProduct) {
        // หาสินค้าที่ต้องการอัปเดตโดยใช้ ID ของสินค้า
        DatabaseService.getInstance().deleteProductById(delProduct.getProductId());
    }

}
