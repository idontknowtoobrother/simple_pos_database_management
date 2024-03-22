/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hexademical
 */
public class User {

    private int userId;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String position;
    private double salary;

    public User(int userId, String firstname, String lastname, String email, String role, String position, double salary) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.position = position;
        this.salary = salary;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}
