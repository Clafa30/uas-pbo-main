/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataDAO;

import Customer.HalamanUtama;
import Login.DbConnection;
import PanelBottom.PanelRincian1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author rafai
 */
public class DataDAO {
    private HalamanUtama halamanUtama;

    public DataDAO(HalamanUtama halamanUtama) {
        this.halamanUtama = halamanUtama;
    }

public void addToKeranjang(String menuId, int jumlah, double harga) {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "INSERT INTO keranjang (menu_id, nama_menu, harga, jumlah, total) "
                     + "SELECT menu_id, nama_menu, harga, ?, ? FROM menu WHERE menu_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, jumlah);
        pstmt.setDouble(2, harga * jumlah);
        pstmt.setString(3, menuId);
        pstmt.executeUpdate();
        System.out.println("Item berhasil ditambahkan ke keranjang!");

        // Segera refresh data setelah menambah item ke keranjang
        loadDataFromKeranjang();  // Memuat ulang data dari keranjang ke PanelRincian1
        refreshTable();  // Memastikan tabel di PanelRincian1 diperbarui
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void loadDataFromKeranjang() {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "SELECT * FROM keranjang";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel tableModel = halamanUtama.getPanelRincian1().getTableModel();
        Map<String, Integer> rowMap = new HashMap<>(); // Untuk melacak row index berdasarkan menu_id

        // Membangun peta menu_id -> row index dari tabel
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String menuId = (String) tableModel.getValueAt(i, 0); // Ambil menu_id dari kolom 0
            rowMap.put(menuId, i);
        }

        // Proses hasil query
        while (rs.next()) {
            String menuId = rs.getString("menu_id");
            String namaMenu = rs.getString("nama_menu");
            double harga = rs.getDouble("harga");
            int jumlah = rs.getInt("jumlah");
            double total = rs.getDouble("total");

            if (rowMap.containsKey(menuId)) {
                // Jika menu_id sudah ada di tabel, update data
                int rowIndex = rowMap.get(menuId);
                tableModel.setValueAt(jumlah, rowIndex, 3); // Update kolom jumlah
                tableModel.setValueAt(total, rowIndex, 4);  // Update kolom total
            } else {
                // Jika menu_id belum ada, tambahkan baris baru
                tableModel.addRow(new Object[]{menuId, namaMenu, harga, jumlah, total});
            }
        }

        tableModel.fireTableDataChanged(); // Refresh tabel setelah semua data dimasukkan
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Memperbarui tabel setelah data dimasukkan
private void refreshTable() {
    PanelRincian1.getInstance().refreshTable();
}

public boolean updateKeranjang(String menuId, int jumlah) {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "UPDATE keranjang SET jumlah = ? WHERE menu_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, jumlah);
        pstmt.setString(2, menuId);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean removeFromKeranjang(String menuId) {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "DELETE FROM keranjang WHERE menu_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, menuId);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
