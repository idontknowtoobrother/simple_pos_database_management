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
import java.sql.CallableStatement;
import java.sql.ResultSet;

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
