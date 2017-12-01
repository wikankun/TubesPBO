/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class Main {
    Database db;
    DefaultTableModel model;
    
    public Main() {
        db = new Database();
        db.connect();
    }
    
    public boolean loginAdmin(String username, String password){
        Admin p = new Admin(username, password);
        if (db.loginAdmin(p)){
            return true;
        } else {
            return false;
        }
    }
}
