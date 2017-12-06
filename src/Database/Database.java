/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Model.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.*;
/**
 *
 * @author PC
 */
public class Database {
    private String server = "jdbc:mysql://localhost:3306/sistem_parkir";
    private String dbUser = "root";
    private String dbPasswd = "";
    private Statement statement = null;
    private Connection connection = null;
    private ResultSet rs = null;
    
    public void connect(){
        try{
            connection = DriverManager.getConnection(server, dbUser, dbPasswd);
            statement =  connection.createStatement();
        }catch(SQLException ex){
            System.out.println("Connection problem occured");
        }
    }
    
    public boolean loginAdmin(Admin a){
        try {
            String query = "select * from admin where username ='"
                    + a.getUsername() + "' and password = '" + a.getPassword() + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Username or password doesn't exist");
        }
    
    }
    
    public void signUpAdmin(Admin a) {
        try {
            String query = "insert into admin (username, password, nik, nama) values"
                + "('" + a.getUsername() + "', "
                + "'" + a.getPassword() + "', "
                + "'" + a.getNik() + "', "
                + "'" + a.getNama() + "')";
            statement.executeUpdate(query);
        } catch (Exception e) {
            throw new IllegalArgumentException("NIK doesn't exist");
        }
    }
    
    public void addKendaraan(Kendaraan k) {
        try {
            String jenis = null;
            if (k instanceof RodaDua){
                jenis = "roda2";
            } else if (k instanceof RodaEmpat){
                jenis = "roda4";
            }
            String query = "insert into kendaraan (nopol, jenis, jammasuk, menitmasuk, jamkeluar, menitkeluar) values "
                    + "('" + k.getNopol() + "', "
                    + "'" + jenis + "', "
                    + k.getJamMasuk() + ", "
                    + k.getMenitMasuk() + ", "
                    + 0 + ", "
                    + 0 + ")";
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Problem when adding vehicle");
        }
    }
    
    public void updateKendaraan(Kendaraan k){
        try {
            String query = "update kendaraan set jamkeluar = " + k.getJamKeluar()
                    + " , menitkeluar = " + k.getMenitKeluar() + " where nopol = '" + k.getNopol() + "'";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Problem while updating data");
        }
    }
    
    public Kendaraan loadKendaraan(String nopol){
        try {
            System.out.println(nopol);
            String query = "select * from kendaraan where nopol='" + nopol + "'";
            ResultSet rs = statement.executeQuery(query);
            Kendaraan k = null;
            if (rs.next()) {
                String jenis = rs.getString(2);
                if (jenis.equals("roda2")){
                    k = new RodaDua(rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                } else if (jenis.equals("roda4")){
                    k = new RodaEmpat(rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                }
            }
            return k;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Problem while loading data");
        }
    }
    
    public ListKendaraan loadListKendaraan(){
        try {
            ListKendaraan lk = new ListKendaraan();
            String query = "select * from kendaraan";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Kendaraan k = null;
                String jenis = rs.getString(2);
                if (jenis.equals("roda2")){
                    k = new RodaDua(rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                    System.out.println("Load" + k.getMenitMasuk());
                } else if (jenis.equals("roda4")){
                    k = new RodaEmpat(rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                }
                lk.addKendaraan(k);
            }
            return lk;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Problem while loading list data");
        }
    }
    
    public void deleteKendaraan(String nopol){
        try {
            String query = "delete from kendaraan where nopol='" + nopol+ "'";
            statement.execute(query);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Problem while deleting data");
        }
    }
    
    public Admin getNik(String nik){
        try {
            Admin a = null;
            String query = "select * from admin where nik = '" + nik + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()){
                a = new Admin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Problem while getting data");
        }
    }
    
    public void updateAdmin(String nik, String password){
        try {
            String query = "update admin set password = '" + password +
                "' where nik = '" + nik + "'";
            statement.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Problem while updating data");
        }
    }
}
