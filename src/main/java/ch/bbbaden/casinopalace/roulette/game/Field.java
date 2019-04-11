/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

/**
 *
 * @author gabri
 */
public class Field {
    private String colour;
    private String number;

    public Field(String number, String colour) {
        this.number = number;
        this.colour = colour;
    }

    public String getNumber() {
        return number;
    }

    public String getColour() {
        return colour;
    }
    
    
}
