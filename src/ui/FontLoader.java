/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author hexademical
 */
public class FontLoader {

    public static void loadFonts() {
        try {
            Font notoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/NotoSansThai-Regular.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(notoFont);
            System.out.println(notoFont);

            Font poppinsFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Poppins-Regular.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(poppinsFont);
            System.out.println("REGISTERD FONT!!!");
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
