/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.yatzy;

import java.util.Random;

/**
 *
 * @author denni
 */
public class Dice {
      Random rnd = new Random();
    private int wert;
    private boolean keep;

    public Dice() {
      
        this.wert = rnd.nextInt(6)+1;
    }

    public int getWert() {
        return wert;
    }


    public boolean getKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }
    
    
}
