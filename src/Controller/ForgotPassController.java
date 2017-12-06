/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.Admin;
import Model.Main;
import View.ForgotPass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ForgotPassController implements ActionListener {
    private Main model;
    private ForgotPass view;
    private Database db = new Database();
            
    public ForgotPassController() {
        model = new Main();
        view = new ForgotPass();
        view.setVisible(true);
        view.addActionListener(this);
        db.connect();
    }        
            
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnSubmit())){
            if (view.getTfNIKPass().getText().equals("") ||
                view.getTfPass().getText().equals("") || view.getTfConfirmPass().getText().equals("")){
                JOptionPane.showMessageDialog(view, "Fill data field first");
            } else {
                String nik = view.getTfNIKPass().getText();
                Admin a = model.searchNik(nik);
                if (model.searchNik(nik) != null){
                    String pass = view.getTfPass().getText();
                    String confirmPass = view.getTfConfirmPass().getText();
                    if (pass.equals(confirmPass)){
                        a.setPassword(pass);
                        model.updateAdmin(a);
                        JOptionPane.showMessageDialog(view, "Change password succesful");
                    } else {
                        JOptionPane.showMessageDialog(view, "Confirmed password must be same");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "NIK doesn't exist");
                }
            }
            view.setTfNIKPass("");
            view.setTfPass("");
            view.setTfConfirmPass("");
        } else if (source.equals(view.getBtnBackForgot())){
            new LoginController();
            view.dispose();
        }
    }
}
