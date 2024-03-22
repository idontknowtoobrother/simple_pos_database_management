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
        StockManagement,
        CustomerManagement,
        EmployeeManagement;

        public int getValue() {
            return this.ordinal();
        }
    }
    
    
    public static enum ErrorType {
        PRODUCT_ID_MUST_POSITIVE_NUMBER,
        CONNECTION_DATABASE_TIMEOUT;
        
        public int getValue() {
            return this.ordinal();
        }
    }
}
