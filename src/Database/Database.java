/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Model.*;
import java.sql.*;
import java.sql.*;
import java.util.*;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.*;
/**
 *
 * @author PC
 */
public class Database {
    private String server="jdbc:mysql://localhost/3306/parkiran";
    private String dbUser ="root";
    private String dbPasswd="";
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
            statement.execute(query);
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
            String query1 = "select nik from admin where nik = '"
                    + a.getNik() + "'";
            statement.execute(query1);
            if (rs.next()) {
                String query2 = "insert into admin (username, password, nama, nik) values"
                    + "('" + a.getUsername() + "', "
                    + "'" + a.getPassword() + "', "
                    + "'" + a.getNama() + "', "
                    + "'" + a.getNik() + "')";
                statement.execute(query2);
            }
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
            System.out.println(k.getMenitMasuk());
            String query = "insert into kendaraan(nopol, jenis, jammasuk, menitmasuk) values"
                    + "('" + k.getNopol() + "', "
                    + "'" + jenis + "',"
                    + "'" + k.getJamMasuk()+ "',"
                    + "'" + k.getMenitMasuk()+ "',"
                    + "'" + k.getJamKeluar()+ "',"
                    + "'" + k.getMenitKeluar()+ "')";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Problem when adding vehicle");
        }
    }
    
    public void updateKendaraan(Kendaraan k){ // digunakan untuk penarikan dan setoran uang
        try {
            String query = "update kendaraan set jamkeluar = '" + k.getJamKeluar()
                    + "' , menitkeluar = '" + k.getMenitKeluar() + "' where nopol = '" + k.getNopol() + "'";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat setor uang");
        }
    }
    
    public Kendaraan loadKendaraan(String nopol){
        try {
            System.out.println(nopol);
            String query = "select * from kendaraan where nopol='" + nopol + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String jenis = rs.getString(2);
                Kendaraan k;
                if (jenis.equals("roda2")){
                    k = new RodaDua(rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                    return k;
                } else if (jenis.equals("roda4")){
                    k = new RodaEmpat(rs.getString(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                    return k;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat load kendaraan");
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
            throw new IllegalArgumentException("terjadi kesalahan saat load kendaraan");
        }
    }
    
    public void deleteKendaraan(String nopol){
        try {
            String query = "delete from kendaraan where nopol='" + nopol+ "'";
            statement.execute(query);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("terjadi kesalahan saat delete");
        }
    }
    
}
