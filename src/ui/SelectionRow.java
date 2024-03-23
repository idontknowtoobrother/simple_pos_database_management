/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import data.GlobalData;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Product;

/**
 *
 * @author hexademical
 */
public class SelectionRow extends DefaultTableCellRenderer {

    private static final Color REMOVED_COLOR = new Color(165, 62, 62, 150);
    private static final Color OUT_OF_STOCK_COLOR = new Color(255, 184, 0, 100);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // เช็คว่าแถวนี้ไม่มีสินค้าใน store หรือไม่
        if (isProductRemoved(row, table)) {
            c.setBackground(REMOVED_COLOR);
        } else if (isProductOutOfStock(row, table)) {
            c.setBackground(OUT_OF_STOCK_COLOR);
        } else {
            c.setBackground(table.getBackground());
        }

        return c;
    }

    // เช็คว่าแถวนี้ไม่มีสินค้าใน store หรือไม่
    private boolean isProductRemoved(int row, JTable table) {
        //  column 0 เป็น product id ดึงให้หน่อยสิ
        int productId = (int) table.getValueAt(row, 0);
        Product product = GlobalData.getInstance().getProductFromProductId(productId);
        return product.isRemovedFromStore();
    }

    private boolean isProductOutOfStock(int row, JTable table) {
        //  column 0 เป็น product id ดึงให้หน่อยสิ
        int productId = (int) table.getValueAt(row, 0);
        Product product = GlobalData.getInstance().getProductFromProductId(productId);
        return product.getTotalAvailable() <= 0;
    }
}
