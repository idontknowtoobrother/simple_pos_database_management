/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author hexademical
 */
public class Notify {
    
    // show notify
    public static void showNotify(Component component, String title, String description, int enumType) {
        JLabel jlDescription = createLabelText(description);
        JOptionPane.showMessageDialog(component, jlDescription, "", enumType);
    }

    public static boolean confirmOption(Component component, String title, String description) {
        JLabel jlDescription = createLabelText(description);
        int confirmed = JOptionPane.showConfirmDialog(component, jlDescription, "", JOptionPane.YES_NO_OPTION);
        return confirmed == JOptionPane.YES_OPTION;
    }
    
    // create label text because we have to using font support thai
    // so we can use createLableText for it and using with another component or frame
    public static JLabel createLabelText(String msg) {
        JLabel label = new JLabel(msg);
        label.setFont(new Font("Noto Sans Thai", Font.PLAIN, 14));
        return label;
    }
}
