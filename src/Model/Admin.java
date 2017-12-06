/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PC
 */
public class Admin{
    private String username;
    private String password;
    private String nik;
    private String nama;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Admin(String username, String password, String nik, String nama) {
        this.username = username;
        this.password = password;
        this.nik = nik;
        this.nama = nama;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }
    
}
