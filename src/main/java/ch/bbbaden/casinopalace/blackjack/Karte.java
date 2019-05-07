/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import javafx.scene.image.Image;

/**
 *
 * @author doemu
 */
public class Karte {

    private int count;
    private Symbol symbol;
    private Image image;
    private int number;

    public Karte(int count, int number, Symbol symbol, Image image) {
        this.count = count;
        this.symbol = symbol;
        this.image = image;
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Image getImage() {
        return image;
    }

}
