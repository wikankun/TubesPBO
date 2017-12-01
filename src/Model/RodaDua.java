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
public class RodaDua extends Kendaraan{
    private int roda = 2;

    public RodaDua(String nopol,  int jammasuk, int menitmasuk, int jamkeluar, int menitkeluar) {
        super(nopol,  jammasuk, menitmasuk, jamkeluar, menitkeluar);
    }
}
