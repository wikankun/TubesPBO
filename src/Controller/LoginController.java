/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Main;
import View.Login;
import View.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class LoginController implements ActionListener {
    private Main model;
    private Login view;

    public LoginController() {
        model = new Main();
        view = new Login();
        view.setVisible(true);
        view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        try {
            if (source.equals(view.getbtnLogin())){
                if (view.gettfUsername().getText().equals("")||view.gettfPass().getText().equals("")){
                    JOptionPane.showMessageDialog(null, "All data must be field");
                } else {
                    String username=view.gettfUsername().getText();
                    String password=view.gettfPass().getText();
                    if(model.loginAdmin(username,password)){
                        view.setVisible(false);
                        new MainMenu(model);
                        view.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Password/Username");
                    }
                    view.settfUsername("");
                    view.settfPass("");
                }
                if (model.loginAdmin(view.gettfUsername().getText(), view.gettfPass().getText())){
                    new Main();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal Login", "Gagal Login", JOptionPane.ERROR_MESSAGE);
                }
            } else if (source.equals(view.getbtnSignup())){
                view.setVisible(false);
                new ControllerNewAccount(model);
                view.dispose();
            } else if (source.equals(view.getbtnHere())){
                view.setVisible(false);
                new ControllerForgotPass(model);
                view.dispose();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
    
}
