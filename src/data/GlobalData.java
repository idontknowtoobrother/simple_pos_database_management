/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import model.User;

public class GlobalData {

    private static GlobalData instance = null;
    private User currentLoginnedUser = null;

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

    public void setCurrentLoginnedUser(User user) {
        if (currentLoginnedUser == null) {
            currentLoginnedUser = user;
        } else {
            System.out.println("Current logged in user is already set.");
        }
    }
}
