/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataDAO;

/**
 *
 * @author rafai
 */
public class KeranjangItem {
    private String menuId;
    private String namaMenu;
    private String kategori;
    private int jumlah;

    // Constructor
    public KeranjangItem(String menuId, String namaMenu, String kategori, int jumlah) {
        this.menuId = menuId;
        this.namaMenu = namaMenu;
        this.kategori = kategori;
        this.jumlah = jumlah;
    }

    // Getters dan Setters
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }    
}
