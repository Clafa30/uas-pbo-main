/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Customer.HalamanUtama;
import DataDAO.DataDAO;
import PanelBottom.PanelRincian1;
import Login.DbConnection;
import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rolis
 */
public class PanelPromo extends javax.swing.JPanel {
    private int lastMouseY;
    private HalamanUtama halamanUtama;
    private DefaultTableModel tableModel; // Model data untuk tabel
    private JTable jTable; // Variabel untuk tabel
    private PanelRincian1 panelRincian1;
    private DataDAO dataDAO;
    
    /**
     * Creates new form TesPanel
     */
    public PanelPromo(HalamanUtama halamanUtama, PanelRincian1 panelRincian11) { 
        initComponents();  // Inisialisasi komponen GUI
        
        this.halamanUtama = halamanUtama;
        this.panelRincian1 = panelRincian1;
        // Inisialisasi objek DataDAO
        this.dataDAO = new DataDAO(halamanUtama);  // Pemberian parameter halamanUtama (jika diperlukan)
        
        if (panelRincian1 != null) {
            panelRincian1.refreshTable();
        } else {
            System.out.println("PanelRincian1 belum diinisialisasi!");
        }
         
        menu.getVerticalScrollBar().setUnitIncrement(15); // Atur sensitivitas vertikal
        jPanel1.setPreferredSize(new Dimension(menu.getWidth(), 800)); // Contoh tinggi 2000
        jPanel1.revalidate();

                // Tambahkan listener untuk Checkbox dan Spinner setelah initComponents()
        BBCbox.addActionListener(this::BBCboxActionPerformed);
        CBCbox.addActionListener(this::CBCboxActionPerformed);
        FFCbox.addActionListener(this::FFCboxActionPerformed);
        CNACbox.addActionListener(this::CNACboxActionPerformed);
        HDCbox.addActionListener(this::HDCboxActionPerformed);

        spinnerBeefBurg.addChangeListener(e -> {
            int value = (Integer) spinnerBeefBurg.getValue();
            if (value < 0) {
                spinnerBeefBurg.setValue(0);  // Pastikan nilai tidak negatif
            }
            // Pastikan kita memanggil updateCheckBoxFromSpinner() di sini untuk mengupdate status checkbox
            updateCheckBoxFromSpinner(BBCbox, spinnerBeefBurg, "P001");
        });

        // Menambahkan listener untuk spinnerDblBeef
        spinnerChezBurg.addChangeListener(e -> {
            int value = (Integer) spinnerChezBurg.getValue();
            if (value < 0) {
                spinnerChezBurg.setValue(0); // Pastikan nilai tidak negatif
            }
            updateTableFromSpinner("P002", spinnerChezBurg);
            updateCheckBoxFromSpinner(CBCbox, spinnerChezBurg, "P002");  // Memperbarui checkbox sesuai dengan nilai spinner
        });

        // Menambahkan listener untuk spinnerDelxBurg
        SpinFF.addChangeListener(e -> {
            int value = (Integer) SpinFF.getValue();
            if (value < 0) {
                SpinFF.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
            }
            updateTableFromSpinner("P003", SpinFF);
            updateCheckBoxFromSpinner(FFCbox, SpinFF, "P003");  // Memperbarui checkbox sesuai dengan nilai spinner
        });

        // Menambahkan listener untuk spinnerChezzBurg
        SpinnerCNA.addChangeListener(e -> {
            int value = (Integer) SpinnerCNA.getValue();
            if (value < 0) {
                SpinnerCNA.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
            }
            updateTableFromSpinner("P004", SpinnerCNA);
            updateCheckBoxFromSpinner(CNACbox, SpinnerCNA, "P004");  // Memperbarui checkbox sesuai dengan nilai spinner
        });

        // Menambahkan listener untuk spinnerChikBurg
        SpinnerHD.addChangeListener(e -> {
            int value = (Integer) SpinnerHD.getValue();
            if (value < 0) {
                SpinnerHD.setValue(0);  // Atur nilai spinner kembali ke 0 jika nilai kurang dari 0
            }
            updateTableFromSpinner("P005", SpinnerHD);
            updateCheckBoxFromSpinner(HDCbox, SpinnerHD, "P005");  // Memperbarui checkbox sesuai dengan nilai spinner
        });

        // Menetapkan model spinner dengan validasi agar nilai tidak kurang dari 0
        spinnerBeefBurg.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        spinnerChezBurg.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        SpinFF.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        SpinnerCNA.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 
        SpinnerHD.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1)); 

        
        // Akses tableModel dari PanelRincian1 yang ada di HalamanUtama
        PanelRincian1 panelRincian1 = PanelRincian1.getInstance();
            if (panelRincian1 != null) {
                tableModel = panelRincian1.getTableModel();  // Ambil DefaultTableModel dari PanelRincian1
                System.out.println("Model tabel berhasil diakses!");
            } else {
                System.out.println("PanelRincian1 tidak ditemukan di HalamanUtama");

            // Ambil PanelRincian1 dari HalamanUtama
            panelRincian1 = PanelRincian1.getInstance();
            if (panelRincian1 != null) {
                tableModel = panelRincian1.getTableModel();
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

        menu = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        BBCbox = new javax.swing.JCheckBox();
        CBCbox = new javax.swing.JCheckBox();
        FFCbox = new javax.swing.JCheckBox();
        CNACbox = new javax.swing.JCheckBox();
        HDCbox = new javax.swing.JCheckBox();
        SpinnerHD = new javax.swing.JSpinner();
        SpinnerCNA = new javax.swing.JSpinner();
        SpinFF = new javax.swing.JSpinner();
        spinnerChezBurg = new javax.swing.JSpinner();
        spinnerBeefBurg = new javax.swing.JSpinner();

        setLayout(new java.awt.BorderLayout());

        menu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        menu.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageMakanan/burger.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Rp 20.000");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Beef Burger");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel5))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(4, 4, 4))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageMakanan/chesse.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Rp 25.000");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Chezzzz Burger");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(4, 4, 4))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/kentang.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Rp 12.000");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("French Fries");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(4, 4, 4))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageLainnya/icecreamstick.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Rp 7.000");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("ChocoNut Ais");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(4, 4, 4))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FootageMakanan/hotdog.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Rp 17.000");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("HotDog");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(3, 3, 3)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(4, 4, 4))
        );

        BBCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBCboxActionPerformed(evt);
            }
        });

        CBCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBCboxActionPerformed(evt);
            }
        });

        FFCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FFCboxActionPerformed(evt);
            }
        });

        CNACbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CNACboxActionPerformed(evt);
            }
        });

        HDCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HDCboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BBCbox)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerBeefBurg)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(FFCbox)
                                .addGap(18, 18, 18)
                                .addComponent(SpinFF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CNACbox)
                                .addGap(18, 18, 18)
                                .addComponent(SpinnerCNA))
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(CBCbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spinnerChezBurg, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(HDCbox)
                            .addGap(18, 18, 18)
                            .addComponent(SpinnerHD))
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBCbox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BBCbox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinnerChezBurg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerBeefBurg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FFCbox)
                    .addComponent(CNACbox)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SpinnerCNA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SpinFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HDCbox)
                    .addComponent(SpinnerHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        menu.setViewportView(jPanel1);

        add(menu, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        lastMouseY = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
         int deltaY = lastMouseY - evt.getY();
        lastMouseY = evt.getY(); // Perbarui posisi terakhir mouse

        // Dapatkan viewport dari JScrollPane
        JViewport viewport = menu.getViewport();
        Point viewPosition = viewport.getViewPosition();

        // Geser viewport berdasarkan perubahan vertikal (deltaY)
        viewPosition.translate(0, deltaY); // Geser vertikal, tidak horizontal
        menu.getViewport().setViewPosition(viewPosition);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void BBCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBCboxActionPerformed
    if (BBCbox.isSelected()) {
        loadData("P001", spinnerBeefBurg);
    } else {
        removeData("P001");
    }
    // Panggil metode untuk memperbarui tabel di PanelRincian1
    PanelRincian1.getInstance().refreshTable();
    }//GEN-LAST:event_BBCboxActionPerformed

    private void CBCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBCboxActionPerformed
    if (CBCbox.isSelected()) {
        loadData("P002", spinnerChezBurg);
    } else {
        removeData("P002");
    }
    }//GEN-LAST:event_CBCboxActionPerformed

    private void FFCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FFCboxActionPerformed
    if (FFCbox.isSelected()) {
        loadData("P003", SpinFF);
    } else {
        removeData("P003");
    }
    }//GEN-LAST:event_FFCboxActionPerformed

    private void CNACboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CNACboxActionPerformed
    if (CNACbox.isSelected()) {
        loadData("P004", SpinnerCNA);
    } else {
        removeData("P004");
    }
    }//GEN-LAST:event_CNACboxActionPerformed

    private void HDCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HDCboxActionPerformed
    if (HDCbox.isSelected()) {
        loadData("P005", SpinnerHD);
    } else {
        removeData("P005");
    }
    }//GEN-LAST:event_HDCboxActionPerformed
    
private void customizeScrollPanel() {
        menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        menu.getHorizontalScrollBar().setUI(null); // Hapus UI scrollbar horizontal
        menu.getVerticalScrollBar().setUI(null);   // Hapus UI scrollbar vertikal

        // Tambahkan MouseListener ke jPanel1
        jPanel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jPanel1.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });      
    }
    
private void loadData(String menuId, javax.swing.JSpinner spinner) {
        try (Connection conn = DbConnection.getConnection()) {
            String query = "SELECT * FROM menu WHERE menu_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String namaMenu = rs.getString("nama_menu");
                double harga = rs.getDouble("harga");
                int jumlah = (Integer) spinner.getValue();

                DataDAO dataDAO = new DataDAO(halamanUtama);
                dataDAO.addToKeranjang(menuId, jumlah, harga);
                dataDAO.loadDataFromKeranjang();  // Memastikan data di-refresh
                panelRincian1.refreshTable();     // Segarkan tabel
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

private void removeFromDatabase(String menuId) {
    // Menggunakan try-with-resources untuk memastikan koneksi dan statement ditutup secara otomatis
    try (Connection conn = DbConnection.getConnection()) {
        
        // Query SQL untuk menghapus data berdasarkan menuId
        String query = "DELETE FROM keranjang WHERE menu_id = ?";
        
        // Membuat prepared statement
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Menetapkan parameter query
            pstmt.setString(1, menuId);
            
            // Eksekusi update query (karena ini adalah perintah DELETE)
            int rowsAffected = pstmt.executeUpdate();
            
            // Cek apakah ada baris yang terpengaruh
            if (rowsAffected > 0) {
                System.out.println("Data berhasil dihapus untuk Menu ID: " + menuId);
            } else {
                System.out.println("Tidak ada data yang ditemukan untuk Menu ID: " + menuId);
            }
        }
    } catch (SQLException e) {
        // Menangani kesalahan SQL jika terjadi
        System.out.println("Error saat menghapus data dari database: " + e.getMessage());
    }
}
    
private void removeData(String menuId) {
    // Mendapatkan model tabel dari PanelRincian1
    PanelRincian1 panelRincian1 = PanelRincian1.getInstance();
    DefaultTableModel tableModel = panelRincian1.getTableModel();

    // Loop untuk menemukan baris yang sesuai dengan menuId
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        if (tableModel.getValueAt(i, 0).equals(menuId)) { // Cek jika menuId pada kolom pertama
            tableModel.removeRow(i);  // Hapus baris yang sesuai
            break; // Hentikan loop setelah menghapus
        }
    }

    // Segarkan data tabel
    tableModel.fireTableDataChanged();
    System.out.println("Baris dengan menuId: " + menuId + " dihapus dari tabel.");
}

public void updateCheckBoxFromSpinner(JCheckBox checkBox, JSpinner spinner, String menuId) {
    int value = (Integer) spinner.getValue();
    
    System.out.println("Spinner Value: " + value);  // Debugging
    
    if (value >= 1) {
        checkBox.setSelected(true);  // Centang checkbox jika jumlah > 0
        loadData(menuId, spinner);   // Memuat data ke dalam tabel jika jumlah > 0
    } else if (value == 0) {
        checkBox.setSelected(false);  // Uncheck checkbox jika jumlah = 0
        removeFromDatabase(menuId);   // Hapus data dari database
        removeData(menuId);           // Hapus data dari tabel PanelRincian1
    }
}

public void updateTableFromSpinner(String menuId, JSpinner spinner) {
    int value = (Integer) spinner.getValue();

    if (value >= 1) {
        // Update keranjang di database
        dataDAO.updateKeranjang(menuId, value); 
        System.out.println("Jumlah di keranjang diperbarui untuk menu ID: " + menuId);
    } else {
        // Hapus data dari database dan tabel jika jumlah = 0
        dataDAO.removeFromKeranjang(menuId);
        removeData(menuId);  // Hapus baris yang terkait dengan menuId dari tabel
        System.out.println("Item dihapus dari keranjang untuk menu ID: " + menuId);
    }

    // Memuat data ulang keranjang dari database ke tabel (jika perlu)
    dataDAO.loadDataFromKeranjang(); // Jika ingin memuat ulang semua data ke tabel
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox BBCbox;
    private javax.swing.JCheckBox CBCbox;
    private javax.swing.JCheckBox CNACbox;
    private javax.swing.JCheckBox FFCbox;
    private javax.swing.JCheckBox HDCbox;
    private javax.swing.JSpinner SpinFF;
    private javax.swing.JSpinner SpinnerCNA;
    private javax.swing.JSpinner SpinnerHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane menu;
    private javax.swing.JSpinner spinnerBeefBurg;
    private javax.swing.JSpinner spinnerChezBurg;
    // End of variables declaration//GEN-END:variables
}
