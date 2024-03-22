/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.User;
import data.GlobalData;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import model.Enums;
import model.Product;

public class DatabaseService {

    private static DatabaseService instance = null;
    private final String URL = "jdbc:mysql://localhost:3306/simple_pos";
    private final String USERNAME = "root";
    private final String PASSWORD = null;

    private Connection connection = null;

    private DatabaseService() {
        // Private constructor to prevent instantiation outside this class
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connect() ? this.connection : null;
    }

    public void login(String email, String password) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // เรียกใช้ stored procedure
                String query = "{CALL loginUser(?, ?)}";
                try (CallableStatement stmt = conn.prepareCall(query)) {
                    stmt.setString(1, email);
                    stmt.setString(2, password);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            int userId = rs.getInt("employee_id");
                            String firstname = rs.getString("firstname");
                            String lastname = rs.getString("lastname");
                            String role = rs.getString("role");
                            String position = rs.getString("position");
                            double salary = rs.getDouble("salary");

                            // Create and return User object
                            User user = new User(userId, firstname, lastname, email, role, position, salary);
                            GlobalData.getInstance().setCurrentLoginnedUser(user);
                        } else {
                            System.out.println("Invalid email or password");
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error executing login procedure: " + e.getMessage());
            } finally {
                close();
            }
        }
    }

    public void refreshProducts() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // เรียกใช้ stored procedure
                String query = "{CALL getProducts()}";
                try (CallableStatement stmt = conn.prepareCall(query)) {
                    try (ResultSet rs = stmt.executeQuery()) {
                        // จัดเก็บข้อมูลสินค้าใน GlobalData
                        List<Product> products = new ArrayList<>();
                        while (rs.next()) {
                            int productId = rs.getInt("product_id");
                            String productName = rs.getString("name");
                            String description = rs.getString("description");
                            double price = rs.getDouble("price");
                            int totalAvailable = rs.getInt("total_available");
                            Date created_at = rs.getDate("created_at");
                            Date updated_at = rs.getDate("updated_at");
                            Date delete_at = rs.getDate("deleted_at");
                            Product product = new Product(productId, productName, description, price, totalAvailable, created_at, updated_at, delete_at);
                            products.add(product);
                        }
                        // กำหนดรายการสินค้าใน GlobalData
                        GlobalData.getInstance().setProducts(products);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error executing stored procedure: " + e.getMessage());
            } finally {
                close();
            }
        }
    }

    public int updateProduct(Product updateProduct) {
        if (updateProduct.getProductId() == -1) {
            return Enums.ErrorType.PRODUCT_ID_MUST_POSITIVE_NUMBER.getValue();
        }

        Connection conn = getConnection();
        if (conn != null) {
            try {
                // เรียกใช้ stored procedure
                String query = "{CALL updateProduct(?, ?, ?, ?, ?)}";
                try (CallableStatement stmt = conn.prepareCall(query)) {
                    stmt.setInt(1, updateProduct.getProductId());
                    stmt.setString(2, updateProduct.getName());
                    stmt.setString(3, updateProduct.getDescription());
                    stmt.setDouble(4, updateProduct.getPrice());
                    stmt.setInt(5, updateProduct.getTotalAvailable());
                    stmt.execute();

                    
                    return 1;
                }
            } catch (SQLException e) {
                System.out.println("Error executing updateProduct procedure: " + e.getMessage());
            } finally {
                close();
            }
        }
        
        return Enums.ErrorType.CONNECTION_DATABASE_TIMEOUT.getValue();
    }
    
    public int addNewProduct(Product newProduct) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // เรียกใช้ stored procedure
                String query = "{CALL addNewProduct(?, ?, ?, ?)}";
                try (CallableStatement stmt = conn.prepareCall(query)) {
                    stmt.setString(1, newProduct.getName());
                    stmt.setString(2, newProduct.getDescription());
                    stmt.setDouble(3, newProduct.getPrice());
                    stmt.setInt(4, newProduct.getTotalAvailable());
                    stmt.execute();
                    
                    return 1;
                }
            } catch (SQLException e) {
                System.out.println("Error executing updateProduct procedure: " + e.getMessage());
            } finally {
                close();
            }
        }
        
        return Enums.ErrorType.CONNECTION_DATABASE_TIMEOUT.getValue();
    }
    
    public int deleteProductById(int productId) {
        if (productId == -1) {
            return Enums.ErrorType.PRODUCT_ID_MUST_POSITIVE_NUMBER.getValue();
        }

        Connection conn = getConnection();
        if (conn != null) {
            try {
                // เรียกใช้ stored procedure
                String query = "{CALL deleteProductById(?)}";
                try (CallableStatement stmt = conn.prepareCall(query)) {
                    stmt.setInt(1, productId);
                    stmt.execute();
                    return 1;
                }
            } catch (SQLException e) {
                System.out.println("Error executing deleteProduct procedure: " + e.getMessage());
            } finally {
                close();
            }
        }
        
        return Enums.ErrorType.CONNECTION_DATABASE_TIMEOUT.getValue();
    }
    
    

    private boolean connect() {
        try {
            if (connection != null && connection.isValid(5)) {
                return true;
            }
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Can't connect to database");
            return false;
        }

        return true;
    }

    private boolean close() {
        if (connection == null) {
            return true;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't connect to database");
            return false;
        }
        return true;
    }
}
