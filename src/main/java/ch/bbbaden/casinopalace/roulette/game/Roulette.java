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
    private int lostmoney;

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
        //System.out.println(color);
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
                }/* else {
                    loss.add(fieldinput.get(field));
                    fieldinput.remove(field);
                }*/
            }
            for (int i = 0; i < numbers.size(); i++) {
                result += numbers.get(i);
            }
        }
        return result;
    }

    public Integer winSplitBets(HashMap<int[], Integer> rowcolumninput, Field drawnum) {
        int result = 0;
        boolean win = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {
            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 2) {
                    for (int i : hashmap.getKey()) {
                        if (i == Integer.parseInt(drawnum.getText())) {
                            win = true;
                            break;
                        } else {
                            win = false;
                        }
                    }
                    if (win == true) {
                        numbers.add(hashmap.getValue() * 9);
                        won.add(hashmap.getValue() * 8);
                    }
                    /*else {
                       loss.add(hashmap.getValue());
                  }*/
                } else {

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
        boolean win = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {

            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 3) {

                    for (int i : hashmap.getKey()) {
                        if (i == drawnum.getNumber()) {
                            win = true;
                            break;
                        } else {
                            win = false;
                        }
                    }
                    if (win == true) {
                        numbers.add(hashmap.getValue() * 12);
                        won.add(hashmap.getValue() * 11);
                    }
                    /*else {
                        loss.add(hashmap.getValue());
                    }*/
                } else {
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
        boolean win = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {

            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 4) {
                    for (int i : hashmap.getKey()) {
                        if (i == Integer.parseInt(drawnum.getText())) {
                            win = true;
                            break;
                        } else {
                            win = false;
                        }
                    }
                    if (win == true) {
                        numbers.add(hashmap.getValue() * 9);
                        won.add(hashmap.getValue() * 8);
                    }
                    /*else {
                        loss.add(hashmap.getValue());
                    }*/
                } else {

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
        boolean win = false;
        ArrayList<Integer> numbers = new ArrayList<>();
        if (rowcolumninput.isEmpty() == false) {

            for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
                if (hashmap.getKey().length == 5) {
                    for (int i : hashmap.getKey()) {
                        if (i == Integer.parseInt(drawnum.getText())) {
                            win = true;
                            break;
                        } else {
                            win = false;
                        }
                    }
                    if (win == true) {
                        numbers.add(hashmap.getValue() * 7);
                        won.add(hashmap.getValue() * 6);
                    }
                    /*else {
                        loss.add(hashmap.getValue());
                    }*/
                } else {

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
        /*boolean win = false;
        boolean colorfound = false;*/
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("SCHWARZ")) {
                if (field.getColour().equals(drawnum.getColour())) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
            if (field.getText().equals("ROT")) {
                if (field.getColour().equals(drawnum.getColour())) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        //System.out.println(result);
        return result;
    }

    public int winOddOrEven(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        /*boolean win = false;
        boolean colorfound = false;*/
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("Gerade")) {
                if (drawnum.getNumber() % 2 == 0) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
            if (field.getText().equals("Ungerade")) {
                if (drawnum.getNumber() % 2 == 1) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        //System.out.println(result);
        return result;
    }

    public int winNiedrigOrHoch(HashMap<Field, Integer> fieldinput, Field drawnum) {
        int result = 0;
        /*boolean win = false;
        boolean colorfound = false;*/
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Field field : fieldinput.keySet()) {
            if (field.getText().equals("1-18 Niedrig")) {
                if (drawnum.getNumber() > 0 && drawnum.getNumber() < 18) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
            if (field.getText().equals("19-36 Hoch")) {
                if (drawnum.getNumber() > 18 && drawnum.getNumber() < 36) {
                    numbers.add(fieldinput.get(field) * 2);
                    won.add(fieldinput.get(field));
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i);
        }
        //System.out.println(result);
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
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
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
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
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
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
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
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
            if (field.getText().equals("2nd12")) {
                for (int i = 13; i < 25; i++) {
                    if (drawnum.getNumber() == i) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
            }
            if (field.getText().equals("3rd12")) {
                for (int i = 25; i < 37; i++) {
                    if (drawnum.getNumber() == i) {
                        numbers.add(fieldinput.get(field) * 3);
                        won.add(fieldinput.get(field) * 2);
                    }
                    //System.out.println("" + i);
                }
                /*else {
                    loss.add(fieldinput.get(field));
                }*/
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
//
//    public int getLostMoney() {
//        int result = 0;
//        for (int i = 0; i < loss.size(); i++) {
//            result += loss.get(i);
//        }
//        allloss.add(result);
//        return result;
//    }

    public int getAllWonMoney() {
        int result = 0;
        for (int i = 0; i < allwon.size(); i++) {
            result += allwon.get(i);
        }
        return result;
    }
//
//    public int getRotation(String text) {
//        int result = 0;
//        switch (text) {
//            case "00":
//                result = 1440;
//                break;
//            case "0":
//                result = 1440;
//                break;
//            case "35":
//                result = 1470;
//                break;
//            case "12":
//                result = 1480;
//                break;
//            case "28":
//                result = 1490;
//                break;
//            case "7":
//                result = 1500;
//                break;
//            case "29":
//                result = 1510;
//                break;
//            case "18":
//                result = 1520;
//                break;
//            case "22":
//                result = 1530;
//                break;
//            case "9":
//                result = 1540;
//                break;
//            case "31":
//                result = 1550;
//                break;
//            case "14":
//                result = 1560;
//                break;
//            case "20":
//                result = 1568;
//                break;
//            case "1":
//                result = 1578;
//                break;
//            case "33":
//                result = 1588;
//                break;
//            case "16":
//                result = 1598;
//                break;
//            case "24":
//                result = 1607;
//                break;
//            case "5":
//                result = 1617;
//                break;
//            case "10":
//                result = 1500;
//                break;
//            case "23":
//                result = 1500;
//                break;
//            case "8":
//                result = 1500;
//                break;
//            case "30":
//                result = 1500;
//                break;
//            case "11":
//                result = 1500;
//                break;
//            case "36":
//                result = 1500;
//                break;
//            case "13":
//                result = 1500;
//                break;
//            case "27":
//                result = 1500;
//                break;
//            case "6":
//                result = 1500;
//                break;
//            case "34":
//                result = 1500;
//                break;
//            case "17":
//                result = 1500;
//                break;
//            case "25":
//                result = 1500;
//                break;
//            case "2":
//                result = 1500;
//                break;
//            case "21":
//                result = 1500;
//                break;
//            case "4":
//                result = 1500;
//                break;
//            case "19":
//                result = 1500;
//                break;
//            case "15":
//                result = 1500;
//                break;
//            case "32":
//                result = 1500;
//                break;
//            default:
//                throw new AssertionError();
//        }
//        return result;
//    }
//
//    public int getAllLostMoney() {
//        int result = 0;
//        for (int i = 0; i < allloss.size(); i++) {
//            result += allloss.get(i);
//        }
//        return result;
//    }
//
//    public void setGivenMoney(HashMap<Field, Integer> fieldinput, HashMap<int[], Integer> rowcolumninput, Field drawnum) {
//        int result = 0;
//        lostmoney = 0;
//        result = loseBets(fieldinput, rowcolumninput, drawnum);
//        lostmoney = result;
//    }
//    public int getGivenMoney(){
//        return lostmoney;
//    }
//    
//    public int loseBets(HashMap<Field, Integer> fieldinput, HashMap<int[], Integer> rowcolumninput, Field drawnum) {
//        HashMap<Field, Integer> fieldoutput = new HashMap<>();
//        HashMap<int[], Integer> rowcolumnoutput = new HashMap<>();
//        fieldoutput = fieldinput;
//        //Colours
//        for (Field field : fieldinput.keySet()) {
//            if (field.getText().equals("SCHWARZ")) {
//                if (field.getColour().equals(drawnum.getColour())) {
//                } else {
//                    fieldoutput.remove(field);
//                }
//            }
//            if (field.getText().equals("ROT")) {
//                if (field.getColour().equals(drawnum.getColour())) {
//                } else {
//                    fieldoutput.remove(field);
//                }
//            }
//        }//Straight Bets
//        fieldinput = fieldoutput;
//        for (Field field : fieldinput.keySet()) {
//            if (field.getText().equals(drawnum.getText())) {
//            } else {
//                fieldoutput.remove(field);
//            }
//        }
//        //Split Bets
//        rowcolumnoutput = rowcolumninput;
//        boolean win = false;
//        for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
//            if (hashmap.getKey().length > 2) {
//                //Do Nothing
//            } else {
//                for (int i : hashmap.getKey()) {
//                    if (i == Integer.parseInt(drawnum.getText())) {
//                        win = true;
//                    } else {
//                        win = false;
//                    }
//                }
//                if (win == true) {
//                } else {
//                    rowcolumnoutput.remove(hashmap.getKey());
//                }
//            }
//        }
//        //Corner Bets
//        win = false;
//        for (HashMap.Entry<int[], Integer> hashmap : rowcolumninput.entrySet()) {
//            if (hashmap.getKey().length > 2) {
//                //Do Nothing
//            } else {
//                for (int i : hashmap.getKey()) {
//                    if (i == Integer.parseInt(drawnum.getText())) {
//                        win = true;
//                    } else {
//                        win = false;
//                    }
//                }
//                if (win == true) {
//                } else {
//                    rowcolumnoutput.remove(hashmap.getKey());
//                }
//            }
//        }
//        int result1 = 0;
//        for (Field field : fieldoutput.keySet()) {
//            result1 += fieldoutput.get(field);
//        }
//        int result2 = 0;
//        for (HashMap.Entry<int[], Integer> hashmap : rowcolumnoutput.entrySet()) {
//            result2 += hashmap.getValue();
//        }
//        int result = result1 + result2;
//        System.out.println(""+result);
//
//        return result;
//    }
}
