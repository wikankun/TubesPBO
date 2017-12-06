/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class Main {
    private Database db;
    private DefaultTableModel model;
    
    public Main(JTable tbData) {
        model = (DefaultTableModel) tbData.getModel();
        this.db = new Database();
        db.connect();
        populate();
    }

    public Main() {
        this.db = new Database();
        db.connect();
    }
    
    public boolean loginAdmin(String username, String password){
        Admin a = new Admin(username, password);
        if (db.loginAdmin(a)){
            return true;
        } else {
            return false;
        }
    }
    
    public void populate() {
        ListKendaraan lk = new ListKendaraan();
        lk = db.loadListKendaraan();
        model.setRowCount(0);
        for (int i = 0; i < lk.getList().size(); i++) {
            String nopol = lk.getList().get(i).getNopol();
            String jam = String.valueOf(lk.getList().get(i).getJamMasuk());
            String menit = String.valueOf(lk.getList().get(i).getMenitMasuk());
            if (menit.length()<2){
                menit = '0' + menit;
            }
            String jamMasuk = jam + ":" + menit;
            if (lk.getList().get(i) instanceof RodaDua) {
                model.insertRow(model.getRowCount(), new Object[]{nopol, "Roda 2", jamMasuk});
            } else {
                model.insertRow(model.getRowCount(), new Object[]{nopol, "Roda 4", jamMasuk});
            }
        }
    }
    
    public void masuk(JRadioButton rb_2, String nopol, String jamMasuk, String menitMasuk) {
        Kendaraan k;
        if (rb_2.isSelected()) {
            model.insertRow(model.getRowCount(), new Object[]{nopol, "Roda 2", jamMasuk + ":" + menitMasuk});
            k = new RodaDua(nopol, Integer.parseInt(jamMasuk), Integer.parseInt(menitMasuk), 0, 0);
        } else {
            model.insertRow(model.getRowCount(), new Object[]{nopol, "Roda 4", jamMasuk + ":" + menitMasuk});
            k = new RodaEmpat(nopol, Integer.parseInt(jamMasuk), Integer.parseInt(menitMasuk), 0, 0);
        }
        db.addKendaraan(k);
    }
    
    public String hitung(JTable tbData, String nopol, String jamKeluar, String menitKeluar) {
        Kendaraan k = db.loadKendaraan(nopol);
        if (k != null) {
            db.deleteKendaraan(nopol);

            k.setJamKeluar(Integer.parseInt(jamKeluar));
            k.setMenitKeluar(Integer.parseInt(menitKeluar));
            Transaksi t = new Transaksi(k);
            return String.valueOf(t.getTarif(k));
        } else {
            JOptionPane.showMessageDialog(null, "No table selected", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public Admin searchNik(String nik) {
        Admin a = db.getNik(nik);
        if (a!=null){
           return a;
        }
        else {
            return null;
        }
    }
    
    public void updateAdmin(Admin a){
        db.updateAdmin(a.getNik(), a.getPassword());
    }
    
    public void createAdmin(Admin a){
        db.signUpAdmin(a);
    }
}
