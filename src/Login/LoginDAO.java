/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author rafai
 */
public class LoginDAO {
    // Method untuk memvalidasi login admin
    public static boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Return true jika ada hasil
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false jika terjadi error
        }
    }
    
        // Metode untuk menyimpan username dan nomor meja ke database
    public static boolean saveLogin(String username, String NoMeja) {
        String query = "INSERT INTO Customer (username, no_meja, login_time) VALUES (?, ?, NOW())"; // Menyimpan data dengan waktu login

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username); // Set parameter username
            stmt.setString(2, NoMeja);  // Set parameter nomor meja

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Jika ada baris yang berhasil dimasukkan, return true

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Jika terjadi kesalahan, return false
        }
    }
    
        // Metode untuk menambahkan pelanggan
    public static boolean tambahPelanggan(String pelangganId, String username, int noMeja) {
        String query = "INSERT INTO pelanggan (pelanggan_id, nama_pelanggan, no_meja) VALUES (?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, pelangganId); // Pastikan ID pelanggan yang valid
            stmt.setString(2, username);
            stmt.setInt(3, noMeja);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Berhasil jika ada baris yang dimasukkan
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Jika terjadi kesalahan
    }
    
    
    // Metode untuk memeriksa ketersediaan meja
    public static boolean isMejaTersedia(int noMeja) {
        String query = "SELECT status FROM meja WHERE no_meja = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, noMeja);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int status = rs.getInt("status");
                    return status == 0; // Meja tersedia jika status = 0
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Jika terjadi kesalahan atau meja tidak ditemukan
    }
    
        public static String generatePelangganId() {
        String query = "SELECT IFNULL(MAX(CAST(SUBSTRING(pelanggan_id, 3) AS UNSIGNED)), 0) + 1 AS next_id FROM pelanggan";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int nextId = rs.getInt("next_id");
                return String.format("PE%03d", nextId); // Format ke PE001, PE002, dst.
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Jika terjadi kesalahan
    }
}
