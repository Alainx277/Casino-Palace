/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author gabri
 */
public class Roulette {

    private ArrayList<Field> table = new ArrayList<>();
    private String outputtext;
    private String outputcolour;
    private int numdrawn;

    public void createTable() {
        //Collection
        table.add(new Field("00", "green"));
        table.add(new Field("0", "green"));
        for (int i = 1; i < 37; i++) {
            if (i % 2 == 0) {
                table.add(new Field("" + i, "black"));
            } else {
                table.add(new Field("" + i, "red"));
            }
        }
        //Die Felder werden herausgegeben
        for (int i = 0; i < table.size(); i++) {
            System.out.println(table.get(i).getText() + " " + table.get(i).getColour());
        }
    }

    public ArrayList<Field> getTable() {
        return table;
    }

    public void drawNumber() {
        Random random = new Random();
        numdrawn = random.nextInt(table.size());
    }

    public String getNumberDrawnAsText() {
        outputtext = table.get(numdrawn).getText();
        return outputtext;
    }

    public String getNumberDrawnAsColour() {
        outputcolour = table.get(numdrawn).getColour();
        return outputcolour;
    }

}
