/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import blockchain_assignment.Blockchain_Assignment;
import controller.InventoryController;
import controller.OrderController;
import controller.TransactionController;
import java.security.Key;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Distributor;
import model.HealthcareProvider;
import model.Inventory;
import model.Manufacturer;
import model.Order;
import model.Transaction;
import util.Helper;
import util.RandomSecretKey;
import util.Symmetric;
import util.TransactionCollection;

/**
 *
 * @author vinie
 */
public class HealthcareProviderFrame extends javax.swing.JFrame {
//    private static User user;

//    private 
//    private static String userID;
    private static HealthcareProvider healthcareProvider;
    private static InventoryController inventoryController;
    private static OrderController orderController;
    private static TransactionController transactionController;

    /**
     * Creates new form ManufacturerFrame
     *
     * @param hp
     * @param ic // * @param inventoryController
     * @param oc
     */
    public HealthcareProviderFrame(HealthcareProvider hp, InventoryController ic, OrderController oc, TransactionController tc) {
        initComponents();
        healthcareProvider = hp;
        inventoryController = ic;
        orderController = oc;
        transactionController = tc;

        inventoryController.setFilename("src/Off_Chain/HealthcareProviderInventory");
        inventoryController.readFromFile();
        orderController.setFilename("src/Off_Chain/HealthcareProviderOrder");
        orderController.readFromFile();
        transactionController.setFilename("src/Off_Chain/TransactionPoolDH");
        transactionController.readFromFile();
        
        loadCompanyName();
        loadInventoryTable();
        loadOrderTable();
        loadTransactionTable();
        loadDistributorComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void loadCompanyName() {
        lblName.setText(healthcareProvider.getName()); // Set the company name to the label
    }
    
//    private void loadDistributor(){
//        
//    }
    
     private void loadDistributorComboBox() {
        for (Distributor d : Blockchain_Assignment.distributorController.getAllDistributor()) {
            distributorComboBox.addItem(d.getID());
        }
    }

    private void loadInventoryTable() {
        try {
//            // Clear existing data in the table
            DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
            model.setRowCount(0);

            ArrayList<Inventory> userInventory = inventoryController.getUserInventory(healthcareProvider.getID());
            // If no products created by the logged-in user were found, display "No products found"

            if (userInventory.isEmpty()) {
                model.addRow(new Object[]{"No products found"});
            } else {
                for (Inventory i : userInventory) {
                    model.addRow(new Object[]{i.getID(), i.getUPC(), i.getBatchNumber(), Integer.toString(i.getQuantity())});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while updating product table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        private void loadTransactionTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) transactionTable.getModel();
            model.setRowCount(0);

            ArrayList<Transaction> userTransaction = transactionController.getUserTransaction(healthcareProvider.getID(), "004");

//            Transaction test = transactionController.getTransaction("TX1228871930");
//            System.out.println(test.getTransactionID());
//            System.out.println(test.getTransactionData());
//            System.out.println(test.getSignatureString());
            if (userTransaction.isEmpty()) {
                model.addRow(new Object[]{"No transactions found"});
            } else {
                for (Transaction t : userTransaction) {
                    String[] data = t.getTransactionData().split("\\|");
                    String ID = t.getTransactionID();
                    String sender = data[1];
                    String UPC = data[3];
                    String batchCode = data[4];
                    String quantity = data[5];
                    String price = data[6];
                    model.addRow(new Object[]{ID, sender, UPC, batchCode, quantity, price});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while updating transaction table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void loadOrderTable() {
        try {
//            // Clear existing data in the table
            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            model.setRowCount(0);

            ArrayList<Order> userOrder = orderController.getUserOrder(healthcareProvider.getID());

            // If no products created by the logged-in user were found, display "No products found"
            if (userOrder.isEmpty()) {
                model.addRow(new Object[]{"No products found"});
            } else {
                for (Order o : userOrder) {
                    model.addRow(new Object[]{o.getID(), o.getSellerID(), o.getOrderDate(), o.getOrderPrice(), o.getOrderStatus()});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while updating product table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabBooking = new javax.swing.JTabbedPane();
        pnlBookRoom3 = new javax.swing.JPanel();
        lblRapidCar9 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        transactionTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        distributorComboBox = new javax.swing.JComboBox<>();
        verifyTransactionButton = new javax.swing.JButton();
        selectedTransactionLabelValue = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlBookRoom1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        inventoryTable = new javax.swing.JTable();
        lblRapidCar6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        lblRapidCar8 = new javax.swing.JLabel();
        lblRapidCar2 = new javax.swing.JLabel();
        btnAddProduct1 = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblName1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabBooking.setBackground(new java.awt.Color(0, 204, 204));
        tabBooking.setForeground(new java.awt.Color(255, 255, 255));

        pnlBookRoom3.setBackground(new java.awt.Color(227, 235, 238));

        lblRapidCar9.setBackground(new java.awt.Color(0, 204, 204));
        lblRapidCar9.setFont(new java.awt.Font("Poppins Medium", 1, 24)); // NOI18N
        lblRapidCar9.setForeground(new java.awt.Color(0, 204, 204));
        lblRapidCar9.setText("Verify");

        transactionTable.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        transactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Sender", "UPC", "Batch Number", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        transactionTable.setColumnSelectionAllowed(true);
        transactionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionTableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(transactionTable);

        jLabel1.setText("Selected Transaction");

        verifyTransactionButton.setBackground(new java.awt.Color(0, 204, 204));
        verifyTransactionButton.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        verifyTransactionButton.setForeground(new java.awt.Color(255, 255, 255));
        verifyTransactionButton.setText("Verify");
        verifyTransactionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verifyTransactionButtonMouseClicked(evt);
            }
        });

        selectedTransactionLabelValue.setText("-");

        jLabel3.setText("Selected Manufacturer");

        javax.swing.GroupLayout pnlBookRoom3Layout = new javax.swing.GroupLayout(pnlBookRoom3);
        pnlBookRoom3.setLayout(pnlBookRoom3Layout);
        pnlBookRoom3Layout.setHorizontalGroup(
            pnlBookRoom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlBookRoom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBookRoom3Layout.createSequentialGroup()
                        .addGroup(pnlBookRoom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBookRoom3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(selectedTransactionLabelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(pnlBookRoom3Layout.createSequentialGroup()
                        .addComponent(lblRapidCar9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlBookRoom3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(distributorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(verifyTransactionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        pnlBookRoom3Layout.setVerticalGroup(
            pnlBookRoom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblRapidCar9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlBookRoom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(selectedTransactionLabelValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBookRoom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBookRoom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(distributorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(verifyTransactionButton))
                .addGap(54, 54, 54))
        );

        tabBooking.addTab("Verify", pnlBookRoom3);

        pnlBookRoom1.setBackground(new java.awt.Color(227, 235, 238));

        inventoryTable.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        inventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "UPC", "Batch Number", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inventoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(inventoryTable);

        lblRapidCar6.setBackground(new java.awt.Color(0, 204, 204));
        lblRapidCar6.setFont(new java.awt.Font("Poppins Medium", 1, 24)); // NOI18N
        lblRapidCar6.setForeground(new java.awt.Color(0, 204, 204));
        lblRapidCar6.setText("Inventory");

        javax.swing.GroupLayout pnlBookRoom1Layout = new javax.swing.GroupLayout(pnlBookRoom1);
        pnlBookRoom1.setLayout(pnlBookRoom1Layout);
        pnlBookRoom1Layout.setHorizontalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBookRoom1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRapidCar6)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlBookRoom1Layout.setVerticalGroup(
            pnlBookRoom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBookRoom1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblRapidCar6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        tabBooking.addTab("Inventory", pnlBookRoom1);

        jPanel2.setBackground(new java.awt.Color(227, 235, 238));

        orderTable.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Seller ID", "Order Date", "Price", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(orderTable);

        lblRapidCar8.setBackground(new java.awt.Color(0, 204, 204));
        lblRapidCar8.setFont(new java.awt.Font("Poppins Medium", 1, 24)); // NOI18N
        lblRapidCar8.setForeground(new java.awt.Color(0, 204, 204));
        lblRapidCar8.setText("Order");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRapidCar8))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblRapidCar8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tabBooking.addTab("Order", jPanel2);

        lblRapidCar2.setBackground(new java.awt.Color(0, 204, 204));
        lblRapidCar2.setFont(new java.awt.Font("Poppins Medium", 3, 24)); // NOI18N
        lblRapidCar2.setForeground(new java.awt.Color(0, 204, 204));
        lblRapidCar2.setText("Healthcare Provider");

        btnAddProduct1.setBackground(new java.awt.Color(153, 0, 51));
        btnAddProduct1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnAddProduct1.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct1.setText("Logout");
        btnAddProduct1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddProduct1MouseExited(evt);
            }
        });
        btnAddProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProduct1ActionPerformed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lblName.setText("Company Name");

        lblName1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lblName1.setText("Welcome Back,");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblName1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRapidCar2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addComponent(lblRapidCar2)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnAddProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(lblName1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(tabBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProduct1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddProduct1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddProduct1MouseExited

    private void btnAddProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProduct1ActionPerformed
        this.setVisible(false);
        new LoginMenu(Blockchain_Assignment.manufacturerController, Blockchain_Assignment.distributorController, Blockchain_Assignment.healthcareProviderController).setVisible(true);
    }//GEN-LAST:event_btnAddProduct1ActionPerformed

    private void inventoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryTableMouseClicked
        //        DefaultTableModel tableModel = (DefaultTableModel) inventoryTable.getModel();
        //        int selectedRow = inventoryTable.getSelectedRow();
        //
        //        if (selectedRow >= 0) {
        //            jLabel51.setText((String) tableModel.getValueAt(selectedRow, 0));
        //            String selectedProductId = (String) tableModel.getValueAt(selectedRow, 0);
        //            DefaultTableModel batchTableModel = (DefaultTableModel) tableBatch.getModel();
        //
        //            // Clear existing data in the table
        //            batchTableModel.setRowCount(0);
        //
        //            // Retrieve blockchain data
        //            Blockchain blockchain = Blockchain.getInstance("src/On_Chain/Blockchain.bin");
        //            LinkedList<Block> blockchainData = blockchain.get();
        //
        //            // Flag to track if any batch is found for the selected product ID
        //            boolean batchFound = false;
        //
        //            // Iterate through the blockchain to find batch info
        //            for (Block block : blockchainData) {
        //                TransactionCollection transactionCollection = block.getTransactionCollection();
        //                if (transactionCollection != null) {
        //                    ArrayList<String> transactions = transactionCollection.getTranxList();
        //                    for (String transaction : transactions) {
        //                        String[] transactionInfo = transaction.split("\\|");
        //                        if (transactionInfo.length >= 5 && transactionInfo[1].equals(selectedProductId)) {
        //                            // Batch info found for the selected product ID
        //                            batchFound = true;
        //                            String batchCode = transactionInfo[2];
        //                            String price = transactionInfo[3];
        //                            String dateAdded = transactionInfo[7];
        //                            String productionDate = transactionInfo[5];
        //                            String expiryDate = transactionInfo[6];
        //                            String quantity = transactionInfo[4];
        //
        //                            // Add batch info to the table
        //                            batchTableModel.addRow(new Object[]{batchCode, price, dateAdded, productionDate, expiryDate, quantity});
        //                        }
        //                    }
        //                }
        //            }
        //
        //            // If no batch is found, display a message in the table
        //            if (!batchFound) {
        //                batchTableModel.addRow(new Object[]{"No Batch Found", "", "", "", "", ""});
        //            }
        //        }
    }//GEN-LAST:event_inventoryTableMouseClicked

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_orderTableMouseClicked

    private void transactionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) transactionTable.getModel();
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow >= 0) {
            selectedTransactionLabelValue.setText((String) tableModel.getValueAt(selectedRow, 0));
        }
    }//GEN-LAST:event_transactionTableMouseClicked

    private void verifyTransactionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verifyTransactionButtonMouseClicked
        // TODO add your handling code here:

        try {
            String transactionID = selectedTransactionLabelValue.getText();
            System.out.println(transactionID);
            String selectedDistributorID = (String) distributorComboBox.getSelectedItem();

//            Distributor d = Blockchain_Assignment.manufacturerController.getManufacturer(selectedManufacturerID);
            Distributor d = Blockchain_Assignment.distributorController.getDistributor( selectedDistributorID);
            

            Transaction transaction = transactionController.getTransaction(transactionID);
//            System.out.println(transaction.toString());

            boolean isVerified = Helper.userVerifyTransaction(transaction.toString(), d.getPublicKeyPath());

            if (!isVerified) {
                JOptionPane.showMessageDialog(this, "Signature is not valid");
                return;
            }

            Symmetric symm = new Symmetric();
            Key secretKey = RandomSecretKey.getSecretKey();
            TransactionCollection transactionCollection = new TransactionCollection();
            String encrypted = symm.encrypt(transaction.toString(), secretKey);
            transactionCollection.add(encrypted);
            
            boolean success = Helper.addBlock(transactionCollection);

            if (success) {

                String[] data = transaction.getTransactionData().split("\\|");
                String sender = data[1];
                String UPC = data[3];
                String batchNumber = data[4];
                int quantity = Integer.parseInt(data[5]);
                int price = Integer.parseInt(data[6]);
                String productName = data[7];

                inventoryController.addNewInventory(healthcareProvider.getID(), UPC, batchNumber, quantity, productName);
                orderController.addNewOrder(healthcareProvider.getID(), sender, LocalDate.now(), price, "RECEIVED");

                transactionController.removeTransaction(transactionID);
                loadTransactionTable();
                loadInventoryTable();
//                loadSelectInventoryComboBox();
                loadOrderTable();

                JOptionPane.showMessageDialog(this, "Signature is valid. Transaction added to block");
                JOptionPane.showMessageDialog(this, "Inventory and order updated");
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_verifyTransactionButtonMouseClicked

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
            java.util.logging.Logger.getLogger(HealthcareProviderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HealthcareProviderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HealthcareProviderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HealthcareProviderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new HealthcareProviderFrame(healthcareProvider, inventoryController, orderController, transactionController).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct1;
    private javax.swing.JComboBox<String> distributorComboBox;
    private javax.swing.JTable inventoryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblRapidCar2;
    private javax.swing.JLabel lblRapidCar6;
    private javax.swing.JLabel lblRapidCar8;
    private javax.swing.JLabel lblRapidCar9;
    private javax.swing.JTable orderTable;
    private javax.swing.JPanel pnlBookRoom1;
    private javax.swing.JPanel pnlBookRoom3;
    private javax.swing.JLabel selectedTransactionLabelValue;
    private javax.swing.JTabbedPane tabBooking;
    private javax.swing.JTable transactionTable;
    private javax.swing.JButton verifyTransactionButton;
    // End of variables declaration//GEN-END:variables
}
