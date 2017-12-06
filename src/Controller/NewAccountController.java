/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.Admin;
import Model.Main;
import View.NewAccount;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author PC
 */
public class NewAccountController implements ActionListener {
    private Main model;
    private NewAccount view;
    private Database db = new Database();
    
    public NewAccountController() {
        model = new Main();
        view = new NewAccount();
        view.setVisible(true);
        view.addActionListener(this);
        db.connect();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnSubmitSignUp())){
            try {
                if(view.getTfNIKSignUp().getText().equals("")||view.getTfNameSignUp().getText().equals("")||view.getTfUsernameSignUP().getText().equals("")||
                    view.getTfPasswordSignUp().toString().equals("")||view.getTfVerifyPassSignUp().getText().equals("")){
                        JOptionPane.showMessageDialog(view, "All data must be filled");
                } else {
                    String username = view.getTfUsernameSignUP().getText();
                    String password = view.getTfPasswordSignUp().getText();
                    String nik = view.getTfNIKSignUp().getText();
                    String nama = view.getTfNameSignUp().getText();
                    Admin a = new Admin(username, password, nik, nama);
                    model.createAdmin(a);
                    JOptionPane.showMessageDialog(view, "Signed Up");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
            view.setTfNIKSignUp("");
            view.setTfNameSignUp("");
            view.setTfPasswordSignUp("");
            view.setTfUsernameSignUp("");
            view.setTfVerifyPassSignUp("");
        } else if (source.equals(view.getBtnBack())){
            view.setVisible(false);
            new LoginController();
            view.dispose();
        }
    }
    
}
