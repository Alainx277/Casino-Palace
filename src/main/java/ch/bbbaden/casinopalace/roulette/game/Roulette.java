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
    private ArrayList<Integer> moneyreceived = new ArrayList<>();
    private ArrayList<Integer> won = new ArrayList<>();
    private ArrayList<Integer> loss = new ArrayList<>();
    private ArrayList<Integer> allwon = new ArrayList<>();
    private ArrayList<Integer> allloss = new ArrayList<>();
    private int winmoney;

    public void createTable() {
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

    public String getColorFromField(String field) {
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
            if (field.equals("0") || field.equals("00") || field.equals("2to1 1st") || field.equals("2to1 2nd")
                    || field.equals("2to1 3rd") || field.equals("1st12") || field.equals("2nd12") || field.equals("3rd12")
                    || field.equals("Gerade") || field.equals("Ungerade") || field.equals("1-18 Niedrig")
                    || field.equals("19-36 Hoch")) {
                color = "green";
            } else if (field.equals("SCHWARZ")) {
                color = "black";
            } else if (field.equals("ROT")) {
                color = "red";
            }
        }
        return color;
    }

    public Integer winStraightUpBets(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (fieldinput.isEmpty() == false) {
            for (Field field : fieldinput.keySet()) {
                if (field.getText().equals(drawnum.getText())) {
                    numbers.add(fieldinput.get(field) * 36);
                    won.add(fieldinput.get(field) * 35);
                }
            }
            for (int i = 0; i < numbers.size(); i++) {
                result += numbers.get(i);
            }
        }
        return result;
    }

    public Integer winSplitBets(HashMap<int[], Integer> rowcolumninput, Field drawnum) {
        int result = 0;
        boolean isWinner = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {
            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 2) {
                    for (int i : hashmap.getKey()) {
                        if (i == Integer.parseInt(drawnum.getText())) {
                            isWinner = true;
                            break;
                        } else {
                            isWinner = false;
                        }
                    }
                    if (isWinner == true) {
                        numbers.add(hashmap.getValue() * 9);
                        won.add(hashmap.getValue() * 8);
                    }
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }

        return result;
    }

    public Integer winStreetBets(HashMap<int[], Integer> rowcolumninput, Field drawnum) {
        int result = 0;
        boolean isWinner = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {

            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 3) {

                    for (int i : hashmap.getKey()) {
                        if (i == drawnum.getNumber()) {
                            isWinner = true;
                            break;
                        } else {
                            isWinner = false;
                        }
                    }
                    if (isWinner == true) {
                        numbers.add(hashmap.getValue() * 12);
                        won.add(hashmap.getValue() * 11);
                    }
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }

        return result;
    }

    public Integer winCornerBets(HashMap<int[], Integer> rowcolumninput, Field drawnum) {
        int result = 0;
        boolean isWinner = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {

            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 4) {
                    for (int i : hashmap.getKey()) {
                        if (i == Integer.parseInt(drawnum.getText())) {
                            isWinner = true;
                            break;
                        } else {
                            isWinner = false;
                        }
                    }
                    if (isWinner == true) {
                        numbers.add(hashmap.getValue() * 9);
                        won.add(hashmap.getValue() * 8);
                    }
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }

        return result;
    }

    public Integer winFiveNumberBets(HashMap<int[], Integer> rowcolumninput, Field drawnum) {
        int result = 0;
        boolean isWinner = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {

            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 5) {
                    for (int i : hashmap.getKey()) {
                        if (i == Integer.parseInt(drawnum.getText())) {
                            isWinner = true;
                            break;
                        } else {
                            isWinner = false;
                        }
                    }
                    if (isWinner == true) {
                        numbers.add(hashmap.getValue() * 7);
                        won.add(hashmap.getValue() * 6);
                    }
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }

        return result;
    }

    public int winRedOrBlack(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("SCHWARZ")) {
                if (field.getColour().equals(drawnum.getColour())) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
            }
            if (field.getText().equals("ROT")) {
                if (field.getColour().equals(drawnum.getColour())) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    public int winOddOrEven(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("Gerade")) {
                if (drawnum.getNumber() % 2 == 0) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
            }
            if (field.getText().equals("Ungerade")) {
                if (drawnum.getNumber() % 2 == 1) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    public int winNiedrigOrHoch(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("1-18 Niedrig")) {
                if (drawnum.getNumber() > 0 && drawnum.getNumber() < 18) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
            }
            if (field.getText().equals("19-36 Hoch")) {
                if (drawnum.getNumber() > 18 && drawnum.getNumber() < 36) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    public int winColumn(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        int number = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("2to1 1st")) {
                number = 1;
                for (int i = 0; i < 12; i++) {
                    if (drawnum.getNumber() == number) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                    number += 3;
                }
            }
            if (field.getText().equals("2to1 2nd")) {
                number = 2;
                for (int i = 0; i < 12; i++) {
                    if (drawnum.getNumber() == number) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                    number += 3;
                }
            }
            if (field.getText().equals("2to1 3rd")) {
                number = 3;
                for (int i = 0; i < 12; i++) {
                    if (drawnum.getNumber() == number) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                    number += 3;
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    public int winDozen(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("1st12")) {
                for (int i = 1; i < 13; i++) {
                    if (drawnum.getNumber() == i) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                }
            }
            if (field.getText().equals("2nd12")) {
                for (int i = 13; i < 25; i++) {
                    if (drawnum.getNumber() == i) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                }
            }
            if (field.getText().equals("3rd12")) {
                for (int i = 25; i < 37; i++) {
                    if (drawnum.getNumber() == i) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                }
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        return result;
    }

    public void setReceivedMoney(HashMap<Field, Integer> fieldinput, HashMap<int[], Integer> rowcolumninput, Field drawnum) {
        int result = 0;
        winmoney = 0;
        result = winRedOrBlack(fieldinput, drawnum)
                + winOddOrEven(fieldinput, drawnum)
                + winNiedrigOrHoch(fieldinput, drawnum)
                + winColumn(fieldinput, drawnum)
                + winDozen(fieldinput, drawnum)
                + winStraightUpBets(fieldinput, drawnum)
                + winSplitBets(rowcolumninput, drawnum)
                + winStreetBets(rowcolumninput, drawnum)
                + winCornerBets(rowcolumninput, drawnum)
                + winFiveNumberBets(rowcolumninput, drawnum);
        winmoney = result;
    }

    public int getReceivedMoney() {
        return winmoney;
    }

    public int getWonMoney() {
        int result = 0;
        for (int i = 0; i < won.size(); i++) {
            result += won.get(i);
        }
        allwon.add(result);
        won.clear();
        return result;
    }

    public int getAllWonMoney() {
        int result = 0;
        for (int i = 0; i < allwon.size(); i++) {
            result += allwon.get(i);
        }
        return result;
    }
}
