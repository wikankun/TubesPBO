/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Main;
import View.ForgotPass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author PC
 */
public class ForgotPassController implements ActionListener {
    private Main model;
    private ForgotPass view;
            
    public ForgotPassController() {
        this.model = model;
        view = new ForgotPass();
        view.setVisible(true);
        view.addActionListener(this);
    }        
            
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
