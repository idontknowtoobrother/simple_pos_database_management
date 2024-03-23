/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import data.GlobalData;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        mainMenuBar.setVisible(false);

//        try {
//            File fontStyle = new File("src/main/resources/fonts/NotoSansThai-Regular.ttf");
//            Font noto = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(18f);
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(noto);
//        } catch (FontFormatException ex) {
//            Logger.getLogger(DatabaseApplication.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DatabaseApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
        jlThaiTitle.setFont(new Font("Noto Sans Thai", Font.PLAIN, 18));

        // สร้าง instance ของ SelectionRow
        SelectionRow customRenderer = new SelectionRow();

        // กำหนด CustomTableCellRenderer เป็น renderer ของทุกคอลัมน์ใน JTable
        for (int i = 0; i < productJTable.getColumnCount(); i++) {
            productJTable.setDefaultRenderer(productJTable.getColumnClass(i), customRenderer);
        }

        JTableHeader productTableHeader = productJTable.getTableHeader();

        productTableHeader.setFont(new Font("Noto Sans Thai", Font.PLAIN, 13));

        setScreen(Enums.Menu.Login.getValue());

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
        if (searchTextField.getText().equals("ข้อมูลการค้นหา...")) {
            return;
        }
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
        jLabel6 = new javax.swing.JLabel();
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
        mainMenuBar = new javax.swing.JMenuBar();
        userInfoMenu = new javax.swing.JMenu();
        userInfoMenuItem = new javax.swing.JMenuItem();
        logoutApplication = new javax.swing.JMenuItem();
        managementMenu = new javax.swing.JMenu();
        gotoStockManagement = new javax.swing.JMenuItem();
        gotoEmployeeManagement = new javax.swing.JMenuItem();
        gotoEmployeeManagement1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEMO POS Database Management (Only Stocks Product)");
        setBackground(new java.awt.Color(26, 35, 39));
        setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        setIconImages(null);
        setLocation(new java.awt.Point(50, 50));
        setName("application"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(800, 622));
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
        panelLogin.setMaximumSize(new java.awt.Dimension(800, 700));
        panelLogin.setMinimumSize(new java.awt.Dimension(800, 700));
        panelLogin.setPreferredSize(new java.awt.Dimension(800, 700));

        jlEngTitle.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jlEngTitle.setForeground(new java.awt.Color(255, 255, 255));
        jlEngTitle.setText("SIMPLE POS DATABASE MANAGEMENT");

        jlThaiTitle.setFont(new java.awt.Font("Noto Sans Thai", 0, 18)); // NOI18N
        jlThaiTitle.setForeground(new java.awt.Color(201, 209, 217));
        jlThaiTitle.setText("จัดการฐานข้อมูลร้านขายของทั่วไป");

        jlLicense.setFont(new java.awt.Font("Noto Sans Thai", 0, 10)); // NOI18N
        jlLicense.setForeground(new java.awt.Color(201, 209, 217));
        jlLicense.setText("Develop by 62160246, 62160242 BUU Students");

        jlThaiTitle1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jlThaiTitle1.setForeground(new java.awt.Color(255, 255, 255));
        jlThaiTitle1.setText("Email");

        emailTextField.setBackground(new java.awt.Color(26, 35, 39));
        emailTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 15)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(201, 209, 217));
        emailTextField.setText("กรุณาใส่อีเมล...");
        emailTextField.setActionCommand("<Not Set>");
        emailTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(217, 217, 217)));
        emailTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        emailTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        emailTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTextFieldFocusLost(evt);
            }
        });
        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });

        jlThaiTitle2.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jlThaiTitle2.setForeground(new java.awt.Color(255, 255, 255));
        jlThaiTitle2.setText("Password");

        passwordTextField.setBackground(new java.awt.Color(26, 35, 39));
        passwordTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 15)); // NOI18N
        passwordTextField.setForeground(new java.awt.Color(201, 209, 217));
        passwordTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(217, 217, 217)));

        loginButton.setBackground(new java.awt.Color(61, 127, 161));
        loginButton.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/logo68x68.png"))); // NOI18N

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlEngTitle)
                            .addComponent(jlThaiTitle)))
                    .addComponent(jlThaiTitle1)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlThaiTitle2)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginButton))
                .addGap(252, 252, 252))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlLicense)
                .addGap(14, 14, 14))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jlEngTitle)
                        .addGap(0, 0, 0)
                        .addComponent(jlThaiTitle)))
                .addGap(120, 120, 120)
                .addComponent(jlThaiTitle1)
                .addGap(0, 0, 0)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jlThaiTitle2)
                .addGap(0, 0, 0)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(jlLicense)
                .addContainerGap())
        );

        tpWindows.addTab("Login", panelLogin);

        panelStockManagement.setBackground(new java.awt.Color(26, 35, 39));
        panelStockManagement.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(26, 35, 39));
        jScrollPane1.setForeground(new java.awt.Color(26, 35, 39));
        jScrollPane1.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N

        productJTable.setFont(new java.awt.Font("Noto Sans Thai", 0, 13)); // NOI18N
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
        productJTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        productJTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        productJTable.setRowHeight(35);
        productJTable.setSelectionBackground(new java.awt.Color(61, 127, 161));
        productJTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        productJTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        productJTable.getTableHeader().setReorderingAllowed(false);
        productJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(productJTable);
        if (productJTable.getColumnModel().getColumnCount() > 0) {
            productJTable.getColumnModel().getColumn(0).setMinWidth(80);
            productJTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            productJTable.getColumnModel().getColumn(0).setMaxWidth(80);
            productJTable.getColumnModel().getColumn(3).setMinWidth(80);
            productJTable.getColumnModel().getColumn(3).setPreferredWidth(80);
            productJTable.getColumnModel().getColumn(3).setMaxWidth(80);
            productJTable.getColumnModel().getColumn(4).setMinWidth(60);
            productJTable.getColumnModel().getColumn(4).setPreferredWidth(60);
            productJTable.getColumnModel().getColumn(4).setMaxWidth(60);
        }

        panelStockManagement.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 800, 318));

        updateStockManagementItemButton.setBackground(new java.awt.Color(61, 127, 161));
        updateStockManagementItemButton.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        updateStockManagementItemButton.setForeground(new java.awt.Color(255, 255, 255));
        updateStockManagementItemButton.setText("อัพเดท");
        updateStockManagementItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockManagementItemButtonActionPerformed(evt);
            }
        });
        panelStockManagement.add(updateStockManagementItemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 586, -1, -1));

        addStockItemDataButton.setBackground(new java.awt.Color(61, 127, 161));
        addStockItemDataButton.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        addStockItemDataButton.setForeground(new java.awt.Color(255, 255, 255));
        addStockItemDataButton.setText("เพิ่มข้อมูล");
        addStockItemDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockItemDataButtonActionPerformed(evt);
            }
        });
        panelStockManagement.add(addStockItemDataButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 330, -1, -1));

        deleteStockManagementItemButton.setBackground(new java.awt.Color(240, 92, 92));
        deleteStockManagementItemButton.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        deleteStockManagementItemButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteStockManagementItemButton.setText("ลบข้อมูล");
        deleteStockManagementItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockManagementItemButtonActionPerformed(evt);
            }
        });
        panelStockManagement.add(deleteStockManagementItemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 586, -1, -1));

        searchTextField.setBackground(new java.awt.Color(26, 35, 39));
        searchTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 14)); // NOI18N
        searchTextField.setForeground(new java.awt.Color(201, 209, 217));
        searchTextField.setText("ข้อมูลการค้นหา...");
        searchTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        searchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusLost(evt);
            }
        });
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
        panelStockManagement.add(searchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 331, 327, 29));

        filterTypes.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        filterTypes.setForeground(new java.awt.Color(255, 255, 255));
        filterTypes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Product ID", "Name", "Description", "Price", "Total" }));
        filterTypes.setToolTipText("");
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
        panelStockManagement.add(filterTypes, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 330, -1, -1));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Filter:");
        panelStockManagement.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 333, -1, -1));

        productIdTextInfo.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        productIdTextInfo.setForeground(new java.awt.Color(255, 255, 255));
        productIdTextInfo.setText("Product ID : -");
        panelStockManagement.add(productIdTextInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 376, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name :");
        panelStockManagement.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 405, -1, -1));

        productNameTextField.setBackground(new java.awt.Color(94, 94, 94));
        productNameTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productNameTextField.setForeground(new java.awt.Color(255, 255, 255));
        productNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameTextFieldActionPerformed(evt);
            }
        });
        panelStockManagement.add(productNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 404, 190, -1));

        productPriceTextField.setBackground(new java.awt.Color(94, 94, 94));
        productPriceTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productPriceTextField.setForeground(new java.awt.Color(255, 255, 255));
        productPriceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPriceTextFieldActionPerformed(evt);
            }
        });
        panelStockManagement.add(productPriceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 435, 190, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Price :");
        panelStockManagement.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 436, -1, -1));

        productTotalTextField.setBackground(new java.awt.Color(94, 94, 94));
        productTotalTextField.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productTotalTextField.setForeground(new java.awt.Color(255, 255, 255));
        productTotalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productTotalTextFieldActionPerformed(evt);
            }
        });
        panelStockManagement.add(productTotalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 466, 190, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total :");
        panelStockManagement.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 467, -1, -1));

        createAtTextInfo.setBackground(new java.awt.Color(115, 211, 130));
        createAtTextInfo.setFont(new java.awt.Font("Noto Sans Thai", 0, 13)); // NOI18N
        createAtTextInfo.setForeground(new java.awt.Color(115, 211, 130));
        createAtTextInfo.setText("เพิ่มเข้าระบบเมื่อ : -");
        panelStockManagement.add(createAtTextInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 503, -1, -1));

        updateAtTextInfo.setBackground(new java.awt.Color(255, 255, 255));
        updateAtTextInfo.setFont(new java.awt.Font("Noto Sans Thai", 0, 13)); // NOI18N
        updateAtTextInfo.setForeground(new java.awt.Color(61, 127, 161));
        updateAtTextInfo.setText("อัพเดทล่าสุดเมื่อ : -");
        panelStockManagement.add(updateAtTextInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 529, -1, -1));

        productDescriptionTextArea.setBackground(new java.awt.Color(94, 94, 94));
        productDescriptionTextArea.setColumns(20);
        productDescriptionTextArea.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        productDescriptionTextArea.setForeground(new java.awt.Color(255, 255, 255));
        productDescriptionTextArea.setLineWrap(true);
        productDescriptionTextArea.setRows(5);
        jScrollPane2.setViewportView(productDescriptionTextArea);

        panelStockManagement.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 404, 519, 170));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Description :");
        panelStockManagement.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 376, -1, -1));

        deletedInfoText.setFont(new java.awt.Font("Noto Sans Thai", 0, 17)); // NOI18N
        deletedInfoText.setForeground(new java.awt.Color(240, 92, 92));
        deletedInfoText.setText("ถูกลบออกจากคลังวันที่ -");
        panelStockManagement.add(deletedInfoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 586, -1, -1));

        tpWindows.addTab("Stock Management", panelStockManagement);

        getContentPane().add(tpWindows, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -38, -1, 660));

        mainMenuBar.setBackground(new java.awt.Color(26, 35, 39));
        mainMenuBar.setForeground(new java.awt.Color(255, 255, 255));
        mainMenuBar.setToolTipText("");
        mainMenuBar.setBorderPainted(false);
        mainMenuBar.setFont(new java.awt.Font("Noto Sans Thai", 0, 11)); // NOI18N
        mainMenuBar.setMargin(new java.awt.Insets(0, 0, 0, 50));

        userInfoMenu.setForeground(new java.awt.Color(201, 209, 217));
        userInfoMenu.setText("ผู้ใช้งาน");
        userInfoMenu.setFocusable(false);
        userInfoMenu.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N

        userInfoMenuItem.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        userInfoMenuItem.setText("user_info");
        userInfoMenuItem.setEnabled(false);
        userInfoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userInfoMenuItemActionPerformed(evt);
            }
        });
        userInfoMenu.add(userInfoMenuItem);

        logoutApplication.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        logoutApplication.setText("ออกจากระบบ");
        logoutApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutApplicationActionPerformed(evt);
            }
        });
        userInfoMenu.add(logoutApplication);

        mainMenuBar.add(userInfoMenu);

        managementMenu.setForeground(new java.awt.Color(201, 209, 217));
        managementMenu.setText("จัดการฐานข้อมูล");
        managementMenu.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N

        gotoStockManagement.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        gotoStockManagement.setText("สินค้า");
        gotoStockManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoStockManagementActionPerformed(evt);
            }
        });
        managementMenu.add(gotoStockManagement);

        gotoEmployeeManagement.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        gotoEmployeeManagement.setText("พนักงาน");
        gotoEmployeeManagement.setEnabled(false);
        gotoEmployeeManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoEmployeeManagementActionPerformed(evt);
            }
        });
        managementMenu.add(gotoEmployeeManagement);

        gotoEmployeeManagement1.setFont(new java.awt.Font("Noto Sans Thai", 0, 12)); // NOI18N
        gotoEmployeeManagement1.setText("ลูกค้า");
        gotoEmployeeManagement1.setEnabled(false);
        gotoEmployeeManagement1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoEmployeeManagement1ActionPerformed(evt);
            }
        });
        managementMenu.add(gotoEmployeeManagement1);

        mainMenuBar.add(managementMenu);

        setJMenuBar(mainMenuBar);

        setSize(new java.awt.Dimension(816, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        if (!validateEmailPassword()) {
            if (getEmail().isEmpty() || getEmail().contains("กรุณาใส่อีเมล...")) {
                Notify.showNotify(this, "เกิดข้อผิดพลาด!", "กรุณากรอกอีเมล เช่น foo@gmail.com", 0);
                return;
            }
            if (getPassword().isEmpty()) {
                Notify.showNotify(this, "เกิดข้อผิดพลาด!", "กรุณากรอกรหัสผ่าน", 0);
                return;
            }
            return;
        }
        DatabaseService.getInstance().login(getEmail(), getPassword());
        if (!GlobalData.getInstance().isLogginned()) {
            Notify.showNotify(this, "เกิดข้อผิดพลาด!", "ไม่พบผู้ใช้!", 0);
            return;
        }
        setScreen(Enums.Menu.StockManagement.getValue());
        String fullname = GlobalData.getInstance().getCurrentLoginnedUser().getFirstname() + " " + GlobalData.getInstance().getCurrentLoginnedUser().getLastname();
        String role = GlobalData.getInstance().getCurrentLoginnedUser().getRole();
        String position = GlobalData.getInstance().getCurrentLoginnedUser().getPosition();
        userInfoMenuItem.setText(fullname + "  ( " + role + " | " + position + "  ) ");
        mainMenuBar.setVisible(true);
    }//GEN-LAST:event_loginButtonActionPerformed

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
                refreshJTableProducts();
                filterProducts();
            }
        });
    }


    private void gotoStockManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoStockManagementActionPerformed
        setScreen(Enums.Menu.StockManagement.getValue());
        mainMenuBar.setVisible(true);
    }//GEN-LAST:event_gotoStockManagementActionPerformed

    private void userInfoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userInfoMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userInfoMenuItemActionPerformed

    private void gotoEmployeeManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoEmployeeManagementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gotoEmployeeManagementActionPerformed

    private void gotoEmployeeManagement1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoEmployeeManagement1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gotoEmployeeManagement1ActionPerformed

    private void logoutApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutApplicationActionPerformed
        GlobalData.getInstance().logout();
        setScreen(Enums.Menu.Login.getValue());
        mainMenuBar.setVisible(false);
        emailTextField.setText("");
        passwordTextField.setText("");
    }//GEN-LAST:event_logoutApplicationActionPerformed

    private void emailTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusGained
        if (emailTextField.getText().equals("กรุณาใส่อีเมล...")) {
            emailTextField.setText("");
        }
    }//GEN-LAST:event_emailTextFieldFocusGained

    private void emailTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusLost
        if (emailTextField.getText().equals("")) {
            emailTextField.setText("กรุณาใส่อีเมล...");
        }
    }//GEN-LAST:event_emailTextFieldFocusLost

    private void productTotalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productTotalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productTotalTextFieldActionPerformed

    private void productPriceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPriceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPriceTextFieldActionPerformed

    private void productNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameTextFieldActionPerformed

    private void filterTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTypesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTypesActionPerformed

    private void filterTypesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterTypesItemStateChanged
        refreshJTableProducts();
        searchTextField.setText("ข้อมูลการค้นหา...");
        unSelectStockManagementItem();
    }//GEN-LAST:event_filterTypesItemStateChanged

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped

    }//GEN-LAST:event_searchTextFieldKeyTyped

    private void searchTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyPressed

    }//GEN-LAST:event_searchTextFieldKeyPressed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusLost
        if (searchTextField.getText().equals("")) {
            searchTextField.setText("ข้อมูลการค้นหา...");
            searchTextField.setForeground(new Color(201, 209, 217));
        }
    }//GEN-LAST:event_searchTextFieldFocusLost

    private void searchTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusGained
        if (searchTextField.getText().equals("ข้อมูลการค้นหา...")) {
            searchTextField.setText("");
            searchTextField.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_searchTextFieldFocusGained

    private void deleteStockManagementItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockManagementItemButtonActionPerformed
        // is editing product
        if (GlobalData.getInstance().isEditingProduct()) {
            // validate data type
            Product editedProduct = GlobalData.getInstance().getProductFromProductId(GlobalData.getInstance().getEditingProductId());
            if (Notify.confirmOption(this, "ยืนยันการแก้ไข", "ลบสินค้า " + editedProduct.getName() + " / " + "Product ID: " + editedProduct.getProductId() + " ?")) {
                // update to database
                GlobalData.getInstance().deleteProduct(editedProduct);
                // filter data again
            }
        }

        refreshJTableProducts();
        filterProducts();
        unSelectStockManagementItem();
    }//GEN-LAST:event_deleteStockManagementItemButtonActionPerformed

    private void addStockItemDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockItemDataButtonActionPerformed
        unSelectStockManagementItem();
        openAddNewStockItemProduct();
    }//GEN-LAST:event_addStockItemDataButtonActionPerformed

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
                }
            }
        }
        refreshJTableProducts();
        filterProducts();
        unSelectStockManagementItem();
    }//GEN-LAST:event_updateStockManagementItemButtonActionPerformed

    private void productJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productJTableMouseClicked
        onClickedStockManagementItem();
    }//GEN-LAST:event_productJTableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();
        FontLoader.loadFonts();
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
    private javax.swing.JMenuItem gotoEmployeeManagement;
    private javax.swing.JMenuItem gotoEmployeeManagement1;
    private javax.swing.JMenuItem gotoStockManagement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlEngTitle;
    private javax.swing.JLabel jlLicense;
    private javax.swing.JLabel jlThaiTitle;
    private javax.swing.JLabel jlThaiTitle1;
    private javax.swing.JLabel jlThaiTitle2;
    private javax.swing.JButton loginButton;
    private javax.swing.JMenuItem logoutApplication;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu managementMenu;
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
    private javax.swing.JMenu userInfoMenu;
    private javax.swing.JMenuItem userInfoMenuItem;
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
            createAtTextInfo.setText("เพิ่มเข้าระบบเมื่อ : " + editingProduct.getCreatedAtLabel());
            updateAtTextInfo.setText("อัพเดทล่าสุดเมื่อ : " + editingProduct.getUpdatedAtLabel());
            if (editingProduct.isRemovedFromStore()) {
                deletedInfoText.setText("ลบออกจากคลังวันที่ " + editingProduct.getDeleteAtLabel());
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
        productJTable.repaint(); // อัพเดทการแสดงผลของตาราง
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
        if (email.equals("กรุณาใส่อีเมล...")) {
            return false;
        }
        return !(email.isEmpty() || password.isEmpty());
    }
}
