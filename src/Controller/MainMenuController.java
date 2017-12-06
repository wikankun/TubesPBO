/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.Main;
import View.MainMenu;
import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class MainMenuController implements ActionListener {
    private Main model;
    private MainMenu view;
    private Database db = new Database();

    public MainMenuController(String username) {
        view = new MainMenu(username);
        model = new Main(view.getTbDataKendaraan());
        view.setVisible(true);
        view.addActionListener(this);
        db.connect();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        try {
            if (source.equals(view.getBtnTambah())){
                String masuk = view.getTfJamMasuk().getText();
                String[] jamMasuk = masuk.split(":");
                model.masuk(view.getRbRoda2(), view.getTfNopolMasuk().getText(), jamMasuk[0], jamMasuk[1]);
                model.populate();
                    
                view.setTfJamMasuk("");
                view.setTfNopolMasuk("");
            } else if (source.equals(view.getBtnHitung())){
                String nopol = view.getTfNopolKeluar().getText();
                String keluar = view.getTfJamKeluar().getText();
                String[] jamKeluar = keluar.split(":");
                view.setTfKeluar(model.hitung(view.getTbDataKendaraan(), nopol, jamKeluar[0], jamKeluar[1]));
                model.populate();
                
                view.setTfJamKeluar("");
                view.setTfNopolKeluar("");
            } else if (source.equals(view.getToLogin())){
                new LoginController();
                view.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
    
}
