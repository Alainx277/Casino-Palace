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

    public Karte(int count, Symbol symbol, Image image) {
        this.count = count;
        this.symbol = symbol;
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Image getImage() {
        return image;
    }

}
