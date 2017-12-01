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
    private ArrayList<Kendaraan> listRodaDua = new ArrayList<Kendaraan>();
    private ArrayList<Kendaraan> listRodaEmpat = new ArrayList<Kendaraan>();
    
    public void addKendaraan(Kendaraan k) {
        if(k instanceof RodaDua){
            listRodaDua.add(k);
        }else if(k instanceof RodaEmpat){
            listRodaEmpat.add(k);
        }else{
            System.out.println("Selain kendaraan roda dua atau empat, tidak diperbolehkan untuk parkir");
        }
    }
    
    public Kendaraan popKendaraan(String nopol, int roda) {
        Kendaraan k = null;
        if(roda == 2){
            for(Kendaraan x: listRodaDua){
                if(x.getNopol().equals(nopol)){
                    k = x;
                    listRodaDua.remove(x);
                    break;
                }
            }
        }else if(roda == 4){
            for(Kendaraan x: listRodaEmpat){
                if(x.getNopol().equals(nopol)){
                    k = x;
                    listRodaEmpat.remove(x);
                    break;
                }
            }
        }
        return k;
    }
    
    public ArrayList<Kendaraan> getList(int roda) {
        ArrayList<Kendaraan> listK = new ArrayList<Kendaraan>();
        if(roda == 2){
            listK =  listRodaDua;
        }else if(roda == 4){
            listK =  listRodaEmpat;
        }
        return listK;
    }
    
    public void getAllKendaraan() {
        System.out.println("Kendaraan Roda Dua:");
        for(Kendaraan k: listRodaDua){
            System.out.println(k.getNopol());
        }
        System.out.println("Kendaraan Roda Empat:");
        for(Kendaraan k: listRodaEmpat){
            System.out.println(k.getNopol());
        }
    }
}
