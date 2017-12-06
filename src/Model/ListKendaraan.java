/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.*;

/**
 *
 * @author PC
 */
public class ListKendaraan {
    private ArrayList<Kendaraan> listKendaraan = new ArrayList<Kendaraan>();
    
    public void addKendaraan(Kendaraan k) {
        listKendaraan.add(k);
    }
    
    public Kendaraan popKendaraan(String nopol) {
        Kendaraan k = null;
        for(Kendaraan x: listKendaraan){
            if(x.getNopol().equals(nopol)){
                k = x;
                listKendaraan.remove(x);
            }
        }
        return k;
    }
    
    public ArrayList<Kendaraan> getList() {
        return listKendaraan;
    }
    
}
