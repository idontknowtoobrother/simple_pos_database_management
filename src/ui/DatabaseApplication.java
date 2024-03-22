/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import data.GlobalData;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.Enums;
import model.Product;
import utils.DatabaseService;
import utils.Notify;

/**
 *
 * @author hexademical
 */
public class DatabaseApplication extends javax.swing.JFrame {

    /**
     * Creates new form DatabaseApplication
     */
    public DatabaseApplication() {
        initComponents();
        setScreen(Enums.Menu.StockManagement.getValue());

        productJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterProducts();
                unSelectStockManagementItem();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterProducts();
                unSelectStockManagementItem();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public void filterProducts() {
        String searchQueryString = searchTextField.getText().toLowerCase().trim();
        String filterType = filterTypes.getSelectedItem().toString().toLowerCase();
        System.out.println("searchQueryString->" + filterType);
        System.out.println("filterType->" + searchQueryString);

        DatabaseService.getInstance().refreshProducts();
        List<Product> products = GlobalData.getInstance().getPrducts();
        DefaultTableModel model = (DefaultTableModel) productJTable.getModel();
        model.setRowCount(0);

        for (Product product : products) {
            boolean shouldAddProduct = false;

            // แปลงข้อมูลในตารางเป็นตัวอักษรทั้งหมดเพื่อทำให้กรองข้อมูลได้ถูกต้อง
            String productId = String.valueOf(product.getProductId()).toLowerCase();
            String productName = product.getName().toLowerCase();
            String description = product.getDescription().toLowerCase();
            String price = String.valueOf(product.getPrice()).toLowerCase();
            String total = String.valueOf(product.getTotalAvailable()).toLowerCase();
            String createdAt = product.getCreatedAt().toString().toLowerCase();

            if (filterType.equals("all")) {
                // กรณีกรองทุกอย่าง
                if (productId.contains(searchQueryString) || productName.contains(searchQueryString)
                        || description.contains(searchQueryString) || price.contains(searchQueryString)
                        || total.contains(searchQueryString) || createdAt.contains(searchQueryString)) {
                    shouldAddProduct = true;
                }
            } else if (filterType.equals("product id")) {
                // กรณีกรองตาม Product ID
                if (productId.contains(searchQueryString)) {
                    shouldAddProduct = true;
                }
            } else if (filterType.equals("name")) {
                // กรณีกรองตามชื่อสินค้า
                if (productName.contains(searchQueryString)) {
                    shouldAddProduct = true;
                }
            } else if (filterType.equals("description")) {
                // กรณีกรองตามคำอธิบายสินค้า
                if (description.contains(searchQueryString)) {
                    shouldAddProduct = true;
                }
            } else if (filterType.equals("price")) {
                // กรณีกรองตามราคา
                if (price.contains(searchQueryString)) {
                    shouldAddProduct = true;
                }
            } else if (filterType.equals("total")) {
                // กรณีกรองตามจำนวนสินค้าทั้งหมด
                if (total.contains(searchQueryString)) {
                    shouldAddProduct = true;
                }
            } else if (filterType.equals("create at")) {
                // กรณีกรองตามวันที่สร้าง
                if (createdAt.contains(searchQueryString)) {
                    shouldAddProduct = true;
                }
            }

            // หากต้องการเพิ่มสินค้าลงในตาราง
            if (shouldAddProduct) {
                Object[] rowData = {product.getProductId(), product.getName(), product.getDescription(), product.getPrice(), product.getTotalAvailable(), product.getCreatedAt(), product.getUpdatedAt()};
                model.addRow(rowData);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpWindows = new javax.swing.JTabbedPane();
        panelLogin = new javax.swing.JPanel();
        jlEngTitle = new javax.swing.JLabel();
        jlThaiTitle = new javax.swing.JLabel();
        jlLicense = new javax.swing.JLabel();
        jlThaiTitle1 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jlThaiTitle2 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        panelStockManagement = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productJTable = new javax.swing.JTable();
        updateStockManagementItemButton = new javax.swing.JButton();
        addStockItemDataButton = new javax.swing.JButton();
        deleteStockManagementItemButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        filterTypes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        productIdTextInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        productNameTextField = new javax.swing.JTextField();
        productPriceTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        productTotalTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        createAtTextInfo = new javax.swing.JLabel();
        updateAtTextInfo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        productDescriptionTextArea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        deletedInfoText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple POS Database Management");
        setBackground(new java.awt.Color(26, 35, 39));
        setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        setIconImages(null);
        setLocation(new java.awt.Point(50, 50));
        setName("application"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tpWindows.setBackground(new java.awt.Color(26, 35, 39));
        tpWindows.setForeground(new java.awt.Color(255, 255, 255));
        tpWindows.setToolTipText("");
        tpWindows.setFocusable(false);
        tpWindows.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        tpWindows.setMaximumSize(new java.awt.Dimension(800, 600));
        tpWindows.setMinimumSize(new java.awt.Dimension(800, 600));
        tpWindows.setPreferredSize(new java.awt.Dimension(800, 600));

        panelLogin.setBackground(new java.awt.Color(26, 35, 39));
        panelLogin.setForeground(new java.awt.Color(255, 255, 255));
        panelLogin.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        panelLogin.setMaximumSize(new java.awt.Dimension(800, 600));
        panelLogin.setMinimumSize(new java.awt.Dimension(800, 600));
        panelLogin.setPreferredSize(new java.awt.Dimension(800, 600));

        jlEngTitle.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jlEngTitle.setForeground(new java.awt.Color(255, 255, 255));
        jlEngTitle.setText("SIMPLE POS DATABASE MANAGEMENT");

        jlThaiTitle.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        jlThaiTitle.setForeground(new java.awt.Color(201, 209, 217));
        jlThaiTitle.setText("จัดการฐานข้อมูลร้านขายของทั่วไป");

        jlLicense.setFont(new java.awt.Font("Noto Sans Thai", 0, 10)); // NOI18N
        jlLicense.setForeground(new java.awt.Color(201, 209, 217));
        jlLicense.setText("Develop by 62160246, 62160242 BUU Students");

        jlThaiTitle1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jlThaiTitle1.setForeground(new java.awt.Color(255, 255, 255));
        jlThaiTitle1.setText("Email");

        emailTextField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        emailTextField.setActionCommand("<Not Set>");
        emailTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        emailTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        emailTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });

        jlThaiTitle2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jlThaiTitle2.setForeground(new java.awt.Color(255, 255, 255));
        jlThaiTitle2.setText("Password");

        passwordTextField.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        loginButton.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlLicense)
                .addContainerGap())
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginButton)
                    .addComponent(jlThaiTitle2)
                    .addComponent(jlThaiTitle1)
                    .addComponent(jlEngTitle)
                    .addComponent(jlThaiTitle)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(332, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlEngTitle)
                .addGap(0, 0, 0)
                .addComponent(jlThaiTitle)
                .addGap(93, 93, 93)
                .addComponent(jlThaiTitle1)
                .addGap(0, 0, 0)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlThaiTitle2)
                .addGap(0, 0, 0)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                .addComponent(jlLicense)
                .addContainerGap())
        );

        tpWindows.addTab("Login", panelLogin);

        productJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Name", "Description", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(productJTable);

        updateStockManagementItemButton.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        updateStockManagementItemButton.setText("อัพเดท");
        updateStockManagementItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockManagementItemButtonActionPerformed(evt);
            }
        });

        addStockItemDataButton.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        addStockItemDataButton.setText("เพิ่มข้อมูล");
        addStockItemDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockItemDataButtonActionPerformed(evt);
            }
        });

        deleteStockManagementItemButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteStockManagementItemButton.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        deleteStockManagementItemButton.setText("ลบข้อมูล");
        deleteStockManagementItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockManagementItemButtonActionPerformed(evt);
            }
        });

        searchTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        filterTypes.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        filterTypes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Product ID", "Name", "Description", "Price", "Total", "Create At" }));
        filterTypes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filterTypesItemStateChanged(evt);
            }
        });
        filterTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTypesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("Filter:");

        productIdTextInfo.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        productIdTextInfo.setText("Product ID : -");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("Name :");

        productNameTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameTextFieldActionPerformed(evt);
            }
        });

        productPriceTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productPriceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPriceTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Price :");

        productTotalTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productTotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productTotalTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Total :");

        createAtTextInfo.setFont(new java.awt.Font("Noto Sans Thai", 0, 13)); // NOI18N
        createAtTextInfo.setText("เพิ่มเข้าระบบเมื่อ : -");

        updateAtTextInfo.setFont(new java.awt.Font("Noto Sans Thai", 0, 13)); // NOI18N
        updateAtTextInfo.setText("อัพเดทล่าสุดเมื่อ : -");

        productDescriptionTextArea.setColumns(20);
        productDescriptionTextArea.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productDescriptionTextArea.setRows(5);
        jScrollPane2.setViewportView(productDescriptionTextArea);

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("Description :");

        deletedInfoText.setFont(new java.awt.Font("Noto Sans Thai", 1, 18)); // NOI18N
        deletedInfoText.setForeground(new java.awt.Color(204, 0, 0));
        deletedInfoText.setText("สินค้าถูกลบออกจากคลังแล้วเมื่อ : -");

        javax.swing.GroupLayout panelStockManagementLayout = new javax.swing.GroupLayout(panelStockManagement);
        panelStockManagement.setLayout(panelStockManagementLayout);
        panelStockManagementLayout.setHorizontalGroup(
            panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panelStockManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStockManagementLayout.createSequentialGroup()
                        .addComponent(addStockItemDataButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelStockManagementLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelStockManagementLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(createAtTextInfo)
                                    .addGroup(panelStockManagementLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(productTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(updateAtTextInfo)
                                    .addComponent(deletedInfoText)))
                            .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelStockManagementLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(productPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelStockManagementLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(productIdTextInfo))))
                        .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelStockManagementLayout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                                    .addGroup(panelStockManagementLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStockManagementLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateStockManagementItemButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteStockManagementItemButton)))))
                .addContainerGap())
        );
        panelStockManagementLayout.setVerticalGroup(
            panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStockManagementLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(addStockItemDataButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(productIdTextInfo)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStockManagementLayout.createSequentialGroup()
                        .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(productPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(productTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(createAtTextInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateAtTextInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletedInfoText)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(panelStockManagementLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelStockManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteStockManagementItemButton)
                            .addComponent(updateStockManagementItemButton))))
                .addContainerGap())
        );

        tpWindows.addTab("Stock Management", panelStockManagement);

        getContentPane().add(tpWindows, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -38, -1, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        if (!validateEmailPassword()) {
            Notify.showNotify(this, "Something's went wrong!", "Please input email and password!", 0);
            return;
        }
        DatabaseService.getInstance().login(getEmail(), getPassword());
        if (!GlobalData.getInstance().isLogginned()) {
            Notify.showNotify(this, "Something's went wrong!", "Not found user!", 0);
        }
        setScreen(Enums.Menu.StockManagement.getValue());
    }//GEN-LAST:event_loginButtonActionPerformed

    private void updateStockManagementItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockManagementItemButtonActionPerformed
        // is editing product
        if (GlobalData.getInstance().isEditingProduct()) {
            // validate data type
            if (validateUpdateStockItemData()) {
                Product editedProduct = GlobalData.getInstance().getProductFromProductId(GlobalData.getInstance().getEditingProductId());
                if (Notify.confirmOption(this, "ยืนยันการแก้ไข", "อัพเดทข้อมูลสินค้า " + editedProduct.getName() + " / " + "Product ID: " + editedProduct.getProductId() + " ?")) {
                    editedProduct.setName(productNameTextField.getText());
                    editedProduct.setDescription(productDescriptionTextArea.getText());
                    editedProduct.setPrice(Double.parseDouble(productPriceTextField.getText()));
                    editedProduct.setTotalAvailable(Integer.parseInt(productTotalTextField.getText()));
                    // update to database
                    GlobalData.getInstance().updateProductById(editedProduct);
                    // filter data again
                    filterProducts();
                }
            }
        }
    }//GEN-LAST:event_updateStockManagementItemButtonActionPerformed

    public boolean validateUpdateStockItemData() {
        try {
            int productId = GlobalData.getInstance().getEditingProductId();
            String productName = productNameTextField.getText();
            String productDescription = productDescriptionTextArea.getText();
            double productPrice = Double.parseDouble(productPriceTextField.getText());
            int productTotal = Integer.parseInt(productTotalTextField.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void openAddNewStockItemProduct() {
        filterTypes.setEnabled(false);
        addStockItemDataButton.setEnabled(false);
        searchTextField.setEnabled(false);
        productJTable.clearSelection();
        productJTable.setEnabled(false);

        AddNewProduct frame = new AddNewProduct();
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // This code block will be executed when the AddNewProduct frame is closed
                // You can re-enable any disabled components here
                filterTypes.setEnabled(true);
                addStockItemDataButton.setEnabled(true);
                searchTextField.setEnabled(true);
                productJTable.setEnabled(true);
                filterProducts();
            }
        });
    }


    private void addStockItemDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockItemDataButtonActionPerformed
        unSelectStockManagementItem();
        openAddNewStockItemProduct();

    }//GEN-LAST:event_addStockItemDataButtonActionPerformed

    private void deleteStockManagementItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockManagementItemButtonActionPerformed
        // is editing product
        if (GlobalData.getInstance().isEditingProduct()) {
            // validate data type
            Product editedProduct = GlobalData.getInstance().getProductFromProductId(GlobalData.getInstance().getEditingProductId());
            if (Notify.confirmOption(this, "ยืนยันการแก้ไข", "ลบสินค้า " + editedProduct.getName() + " / " + "Product ID: " + editedProduct.getProductId() + " ?")) {
                // update to database
                GlobalData.getInstance().deleteProduct(editedProduct);
                // filter data again
                filterProducts();
            }
        }
    }//GEN-LAST:event_deleteStockManagementItemButtonActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void filterTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTypesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTypesActionPerformed

    private void productNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameTextFieldActionPerformed

    private void productPriceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPriceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPriceTextFieldActionPerformed

    private void productTotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productTotalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productTotalTextFieldActionPerformed

    private void productJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productJTableMouseClicked
        onClickedStockManagementItem();
    }//GEN-LAST:event_productJTableMouseClicked

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
    }//GEN-LAST:event_searchTextFieldKeyTyped

    private void searchTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyPressed

    }//GEN-LAST:event_searchTextFieldKeyPressed

    private void filterTypesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterTypesItemStateChanged
        refreshJTableProducts();
        searchTextField.setText("");
        unSelectStockManagementItem();
    }//GEN-LAST:event_filterTypesItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatabaseApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatabaseApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatabaseApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatabaseApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatabaseApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStockItemDataButton;
    private javax.swing.JLabel createAtTextInfo;
    private javax.swing.JButton deleteStockManagementItemButton;
    private javax.swing.JLabel deletedInfoText;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JComboBox<String> filterTypes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlEngTitle;
    private javax.swing.JLabel jlLicense;
    private javax.swing.JLabel jlThaiTitle;
    private javax.swing.JLabel jlThaiTitle1;
    private javax.swing.JLabel jlThaiTitle2;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelStockManagement;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JTextArea productDescriptionTextArea;
    private javax.swing.JLabel productIdTextInfo;
    private javax.swing.JTable productJTable;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JTextField productPriceTextField;
    private javax.swing.JTextField productTotalTextField;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTabbedPane tpWindows;
    private javax.swing.JLabel updateAtTextInfo;
    private javax.swing.JButton updateStockManagementItemButton;
    // End of variables declaration//GEN-END:variables

    private void onClickedStockManagementItem() {
        int selectedRow = productJTable.getSelectedRow();
        if (selectedRow != -1) { // ถ้ามีการเลือกรายการ
            // ดึงข้อมูลจากตาราง

            int productId = (int) productJTable.getValueAt(selectedRow, 0);
            Product editingProduct = GlobalData.getInstance().getProductFromProductId(productId);
            if (editingProduct == null) {
                return;
            }

            String productName = editingProduct.getName();
            String productDescription = editingProduct.getDescription();
            double productPrice = editingProduct.getPrice();
            int productTotal = editingProduct.getTotalAvailable();
            GlobalData.getInstance().setEditingProductId(editingProduct.getProductId());

            // ตั้งค่าข้อมูลในฟอร์มข้อมูลสินค้า
            productIdTextInfo.setText("Product ID : " + String.valueOf(productId));
            productNameTextField.setText(productName);
            productDescriptionTextArea.setText(productDescription);
            productPriceTextField.setText(Double.toString(productPrice));
            productTotalTextField.setText(String.valueOf(productTotal));
            createAtTextInfo.setText("เพิ่มเข้าระบบเมื่อ : " + editingProduct.getCreatedAt());
            updateAtTextInfo.setText("อัพเดทล่าสุดเมื่อ : " + editingProduct.getUpdatedAt());
            if (editingProduct.isRemovedFromStore()) {
                deletedInfoText.setText("สินค้าถูกลบออกจากคลังแล้วเมื่อ : " + editingProduct.getDeleteAt());
                deletedInfoText.setVisible(true);
                disableEditingStckManageMentItem();
                return;
            }
            enableEditingStockManagementItem();
        }
    }

    private void enableEditingStockManagementItem() {
        productNameTextField.setEnabled(true);
        productNameTextField.setEditable(true);

        productPriceTextField.setEnabled(true);
        productPriceTextField.setEditable(true);

        productTotalTextField.setEnabled(true);
        productTotalTextField.setEditable(true);

        productDescriptionTextArea.setEnabled(true);
        productDescriptionTextArea.setEditable(true);

        updateStockManagementItemButton.setEnabled(true);

        deleteStockManagementItemButton.setEnabled(true);

        deletedInfoText.setVisible(false);
    }

    private void disableEditingStckManageMentItem() {
        productNameTextField.setEnabled(false);
        productNameTextField.setEditable(false);

        productPriceTextField.setEnabled(false);
        productPriceTextField.setEditable(false);

        productTotalTextField.setEnabled(false);
        productTotalTextField.setEditable(false);

        productDescriptionTextArea.setEnabled(false);
        productDescriptionTextArea.setEditable(false);

        updateStockManagementItemButton.setEnabled(false);
        deleteStockManagementItemButton.setEnabled(false);
    }

    private void unSelectStockManagementItem() {
        GlobalData.getInstance().clearEditingProductId();
        productIdTextInfo.setText("Product ID : -");
        productNameTextField.setText("");

        productPriceTextField.setText("");

        productTotalTextField.setText("");

        productDescriptionTextArea.setText("");

        createAtTextInfo.setText("เพิ่มเข้าระบบเมื่อ : -");
        updateAtTextInfo.setText("อัพเดทล่าสุดเมื่อ : -");
        disableEditingStckManageMentItem();
        deletedInfoText.setVisible(false);

    }

    private void refreshJTableProducts() {
        DatabaseService.getInstance().refreshProducts();
        DefaultTableModel model = (DefaultTableModel) productJTable.getModel();
        model.setRowCount(0);
        for (Product product : GlobalData.getInstance().getPrducts()) {
            Object[] rowData = {product.getProductId(), product.getName(), product.getDescription(), product.getPrice(), product.getTotalAvailable(), product.getCreatedAt(), product.getUpdatedAt()};
            model.addRow(rowData);
        }
    }

    private void onOpennedScreenStockManagement() {
        unSelectStockManagementItem();
        refreshJTableProducts();
    }

    private void setScreen(int panelEnum) {

        tpWindows.setSelectedIndex(panelEnum);
        if (panelEnum == Enums.Menu.StockManagement.getValue()) {
            onOpennedScreenStockManagement();
        }
    }

    private String getEmail() {
        return emailTextField.getText();
    }

    private String getPassword() {
        char[] passwordChars = passwordTextField.getPassword();
        String password = new String(passwordChars);
        return password;
    }

    public boolean validateEmailPassword() {
        String email = getEmail();
        String password = getPassword();
        return !(email.isEmpty() || password.isEmpty());
    }
}
