/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author gabri
 */
public class Roulette {

    private final ArrayList<Field> table = new ArrayList<>();
    private String outputtext;
    private String outputcolour;
    private int numdrawn;
    private ArrayList<String> fields = new ArrayList<>();
    private ArrayList<Integer> won = new ArrayList<>();
    private ArrayList<Integer> loss = new ArrayList<>();

    public void createTable() {
        //Collection
        table.add(new Field("00", "green"));
        table.add(new Field("0", "green"));
        for (int i = 1; i < 11; i++) {
            if (i % 2 == 0) {
                table.add(new Field("" + i, "black"));
            } else {
                table.add(new Field("" + i, "red"));
            }
        }
        for (int i = 11; i < 19; i++) {
            if (i % 2 == 0) {
                table.add(new Field("" + i, "red"));
            } else {
                table.add(new Field("" + i, "black"));
            }
        }
        for (int i = 19; i < 29; i++) {
            if (i % 2 == 0) {
                table.add(new Field("" + i, "black"));
            } else {
                table.add(new Field("" + i, "red"));
            }
        }
        for (int i = 29; i < 37; i++) {
            if (i % 2 == 0) {
                table.add(new Field("" + i, "red"));
            } else {
                table.add(new Field("" + i, "black"));
            }
        }
        //Die Felder werden herausgegeben
        /*
        for (int i = 0; i < table.size(); i++) {
            System.out.println(table.get(i).getText() + " " + table.get(i).getColour());
        }*/
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

    public String findColorFromSelectedField(String field) {
        String color = "";

        try {
            int number = Integer.parseInt(field);
            if (number > 0 && number < 11) {
                if (Integer.parseInt(field) % 2 == 0) {
                    color = "black";
                } else {
                    color = "red";
                }
            }
            if (number > 10 && number < 19) {
                if (Integer.parseInt(field) % 2 == 0) {
                    color = "red";
                } else {
                    color = "black";
                }
            }
            if (number > 18 && number < 29) {
                if (Integer.parseInt(field) % 2 == 0) {
                    color = "black";
                } else {
                    color = "red";
                }
            }
            if (number > 28 && number < 37) {
                if (Integer.parseInt(field) % 2 == 0) {
                    color = "red";
                } else {
                    color = "black";
                }
            }
        } catch (Exception e) {
            if (field.equals("0") || field.equals("00") || field.equals("2to1")
                    || field.equals("1st12") || field.equals("2nd12") || field.equals("3rd12")
                    || field.equals("Gerade") || field.equals("Ungerade") || field.equals(" 1-18 Niedrig")
                    || field.equals("19-36 Hoch")) {
                color = "green";
            } else if (field.equals("SCHWARZ")) {
                color = "black";
            } else if (field.equals("ROT")) {
                color = "red";
            }
        }
        System.out.println(color);
        return color;
    }

    public Integer winStraightUpBets(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals(drawnum.getText())) {
                numbers.add(fieldinput.get(field) * 36);
            } else {
                loss.add(fieldinput.get(field));
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    public Integer winSplitBets(HashMap<int[], Integer> rowcolumninput, Field drawnum) {

        int result = 0;
        int num = 0;
        ArrayList<Integer> numbers = new ArrayList<>();

        for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
            if (hashmap.getKey().length > 2) {
                //Do Nothing
            } else {
                for (int i : hashmap.getKey()) {
                    if (i == Integer.parseInt(drawnum.getText())) {
                        System.out.println(hashmap.getKey().toString());
                        numbers.add(hashmap.getValue() * 18);
                    }
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }

        return result;
    }
    public Integer winStreetBets(){
        return null;
    }

    public void playRoulette(HashMap<Field, Integer> fieldinput, HashMap<int[], Integer> rowcolumninput, Field drawnum) {
        winStraightUpBets(fieldinput, drawnum);
    }
}
