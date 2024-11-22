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
        System.out.println("Item ditambahkan ke keranjang!");

        // Memperbarui tabel setelah data ditambahkan
        refreshTable();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void loadDataFromKeranjang() {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "SELECT * FROM keranjang";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        // Ambil tabel model dari PanelRincian1
        DefaultTableModel tableModel = HalamanUtama.getInstance().getPanelRincian1().getTableModel();
        tableModel.setRowCount(0);  // Hapus semua baris sebelumnya

        while (rs.next()) {
            tableModel.addRow(new Object[]{
                rs.getString("menu_id"),
                rs.getString("nama_menu"),
                rs.getDouble("harga"),
                rs.getInt("jumlah"),
                rs.getDouble("total")
            });
        }
        tableModel.fireTableDataChanged();  // Notify tabel untuk refresh
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Memperbarui tabel setelah data dimasukkan
private void refreshTable() {
    PanelRincian1.getInstance().refreshTable();
}

public void updateKeranjang(String menuId, int jumlah) {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "UPDATE keranjang SET jumlah = ?, total = harga * ? WHERE menu_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, jumlah);
        pstmt.setInt(2, jumlah);
        pstmt.setString(3, menuId);
        pstmt.executeUpdate();
        System.out.println("Keranjang diperbarui untuk menu: " + menuId);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void removeFromKeranjang(String menuId) {
    try (Connection conn = DbConnection.getConnection()) {
        String query = "DELETE FROM keranjang WHERE menu_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, menuId);
        pstmt.executeUpdate();
        System.out.println("Item dihapus dari keranjang: " + menuId);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
