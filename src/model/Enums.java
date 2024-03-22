/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hexademical
 */
public class Enums {

    public static enum Menu {
        Login,
        Dashboard,
        StockManagement,
        CustomerManagement,
        EmployeeManagement;

        public int getValue() {
            return this.ordinal();
        }
    }
}
