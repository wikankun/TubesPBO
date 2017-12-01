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
public class Transaksi {
    private Kendaraan k;

    public Transaksi(Kendaraan k) {
        this.k = k;
    }
    
    public double getJamDurasi(Kendaraan k) {
        double durasiJam = k.getJamKeluar() - k.getJamMasuk();
        double durasiMenit = k.getMenitKeluar() - k.getMenitMasuk();
        if (durasiMenit > 0) {
            durasiJam = durasiJam + 1;
        }
        return durasiJam;
    }
    
    public double getMenitDurasi(Kendaraan k) {
        int durasiMenit = k.getMenitKeluar() - k.getMenitMasuk();
        if (durasiMenit<0) {
            durasiMenit =+ 60;
        }
        return durasiMenit;
    }
    
    public String getLamaParkir(Kendaraan k) {
        String jam =  String.valueOf(getJamDurasi(k));
        String menit = String.valueOf(getMenitDurasi(k));
        return jam+':'+menit;
    }
    
    public double getTarif(Kendaraan k) {
        if (k instanceof RodaDua) {
            return getJamDurasi(k) * 2000;
        } else if (k instanceof RodaEmpat) {
            return getJamDurasi(k) * 3000;
        } else {
            return 0;
        }
    }
    
}
