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

    private String text;
    private String colour;
    private int number;

    public Field(String text, String colour) {
        this.text = text;
        this.colour = colour;
    }

    public String getText() {
        return text;
    }

    public int getNumber() {
        if (text.equals("00")) {
            number = 100;
        } else {
            number = Integer.parseInt(this.text);
        }
        return number;
    }

    public String getColour() {
        return colour;
    }
}
