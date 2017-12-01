/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PC
 */
public abstract class Kendaraan {
    private String nopol;
    private int jamMasuk;
    private int menitMasuk;
    private int jamKeluar;
    private int menitKeluar;

    public String getNopol() {
        return nopol;
    }

    public Kendaraan(String nopol, int jamMasuk, int menitMasuk, int jamKeluar, int menitKeluar) {
        this.nopol = nopol;
        this.jamMasuk = jamMasuk;
        this.menitMasuk = menitMasuk;
        this.jamKeluar = jamKeluar;
        this.menitKeluar = menitKeluar;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public void setJamMasuk(int jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public void setMenitMasuk(int menitMasuk) {
        this.menitMasuk = menitMasuk;
    }

    public void setJamKeluar(int jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public void setMenitKeluar(int menitKeluar) {
        this.menitKeluar = menitKeluar;
    }

    public int getJamMasuk() {
        return jamMasuk;
    }

    public int getMenitMasuk() {
        return menitMasuk;
    }

    public int getJamKeluar() {
        return jamKeluar;
    }

    public int getMenitKeluar() {
        return menitKeluar;
    }
    
}
