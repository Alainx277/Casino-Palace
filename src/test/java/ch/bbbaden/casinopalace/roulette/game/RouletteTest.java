/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gabri
 */
public class RouletteTest {

    public RouletteTest() {
    }

    @org.junit.Test
    public void testCreateTable() {
    }

    @org.junit.Test
    public void testGetTable() {
    }

    @org.junit.Test
    public void testDrawNumber() {
    }

    @org.junit.Test
    public void testGetNumberDrawnAsText() {
    }

    @org.junit.Test
    public void testGetNumberDrawnAsColour() {
    }

    @org.junit.Test
    public void testFindColorFromSelectedField() {
    }

    @org.junit.Test
    public void testPlayRoulette() {

    }

    @Test
    public void testWinStraightUpBets() {
        //Arrange
        HashMap<Field, Integer> fields = new HashMap<>();
        fields.put(new Field("11", "black"), 100);
        fields.put(new Field("11", "black"), 50);
        fields.put(new Field("1", "red"), 100);
        Field field = new Field("11", "black");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winStraightUpBets(fields, field);
        //Assert
        assertEquals(5400, result);
    }

    @Test
    public void testWinSplitBets() {

        //Arrange
        HashMap<int[], Integer> labels = new HashMap<>();
        int[] first = {6, 9};
        int[] second = {2, 5, 1, 4};
        int[] third = {2, 5};
        int[] fourth = {6, 9, 5, 8};
        int[] fifth = {9, 12};
        labels.put(first, 100);
        labels.put(second, 100);
        labels.put(third, 100);
        labels.put(fourth, 100);
        labels.put(fifth, 50);
        Field field = new Field("9", "red");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winSplitBets(labels, field);
        //Assert
        assertEquals(1350, result);
    }

    @Test
    public void testWinStreetBets() {

        //Arrange
        HashMap<int[], Integer> labels = new HashMap<>();
        int[] first = {6, 9};
        int[] second = {2, 5, 1, 4};
        int[] third = {2, 100, 1};
        int[] fourth = {6, 9, 5, 8};
        int[] fifth = {9, 12, 8, 11};
        labels.put(first, 100);
        labels.put(second, 100);
        labels.put(third, 100);
        labels.put(fourth, 100);
        labels.put(fifth, 50);
        Field field = new Field("00", "green");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winStreetBets(labels, field);
        //Assert
        assertEquals(1200, result);
    }

    @Test
    public void testWinCornerBets() {

        //Arrange
        HashMap<int[], Integer> labels = new HashMap<>();
        int[] first = {6, 9};
        int[] second = {2, 5, 1, 4};
        int[] third = {2, 5};
        int[] fourth = {6, 9, 5, 8};
        int[] fifth = {9, 12, 8, 11};
        labels.put(first, 100);
        labels.put(second, 100);
        labels.put(third, 100);
        labels.put(fourth, 100);
        labels.put(fifth, 50);
        Field field = new Field("9", "red");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winCornerBets(labels, field);
        //Assert
        assertEquals(1350, result);
    }

    @Test
    public void testGetColorFromField() {
    }

    @Test
    public void testWinRedOrBlack() {
    }

    @Test
    public void testSetWinMoney() {
    }

    @Test
    public void testGetWinMoney() {
    }

    @Test
    public void testSetLostMoney() {
    }

    @Test
    public void testGetLostMoney() {
    }

    @Test
    public void testGetAllWinMoney() {
    }

    @Test
    public void testGetAllLostMoney() {
    }

    @Test
    public void testWinOddOrEven() {
        //Arrange
        HashMap<Field, Integer> fields = new HashMap<>();
        fields.put(new Field("11", "black"), 100);
        fields.put(new Field("10", "black"), 50);
        fields.put(new Field("Ungerade", "green"), 100);
        fields.put(new Field("Ungerade", "green"), 50);
        fields.put(new Field("Gerade", "green"), 100);
        fields.put(new Field("Gerade", "green"), 50);
        Field field = new Field("2", "black");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winOddOrEven(fields, field);
        //Assert
        assertEquals(300, result);
    }

    @Test
    public void testWinNiedrigOrHoch() {
        //Arrange
        HashMap<Field, Integer> fields = new HashMap<>();
        fields.put(new Field("11", "black"), 100);
        fields.put(new Field("10", "black"), 50);
        fields.put(new Field("1-18 Niedrig", "green"), 100);
        fields.put(new Field("1-18 Niedrig", "green"), 50);
        fields.put(new Field("19-36 Hoch", "green"), 100);
        fields.put(new Field("19-36 Hoch", "green"), 50);
        Field field = new Field("2", "black");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winNiedrigOrHoch(fields, field);
        //Assert
        assertEquals(300, result);
    }

    @Test
    public void testSetReceivedMoney() {
    }

    @Test
    public void testGetReceivedMoney() {
    }

    @Test
    public void testGetWonMoney() {

    }

    @Test
    public void testGetAllWonMoney() {
    }

    @Test
    public void testWinColumn() {
        //Arrange
        //Fields
        HashMap<Field, Integer> fields = new HashMap<>();
        fields.put(new Field("0", "green"), 1);
        fields.put(new Field("3rd12", "green"), 100);
        fields.put(new Field("2nd12", "green"), 100);
        fields.put(new Field("Ungerade", "green"), 100);
        fields.put(new Field("ROT", "red"), 100);
        fields.put(new Field("19-36 Hoch", "green"), 100);
        fields.put(new Field("2to1 1st", "green"), 100);
        fields.put(new Field("2to1 2nd", "green"), 250);
        //Labels
        HashMap<int[], Integer> labels = new HashMap<>();
        labels.put(new int[]{3, 2, 100}, 500);
        labels.put(new int[]{3, 2, 100, 0, 1}, 500);
        labels.put(new int[]{6, 9}, 10);
        labels.put(new int[]{5, 8, 4, 7}, 50);
        labels.put(new int[]{11, 10}, 10);
        labels.put(new int[]{12, 15, 11, 14}, 50);
        labels.put(new int[]{18, 21, 17, 20}, 50);
        labels.put(new int[]{19, 20}, 10);
        labels.put(new int[]{27, 24}, 10);
        labels.put(new int[]{22, 25}, 10);
        labels.put(new int[]{30, 33, 29, 32}, 50);
        //Drawn Number
        Field field = new Field("33", "black");
        Roulette roulette = new Roulette();
        //Act
        roulette.setReceivedMoney(fields, labels, field);
        int result = roulette.getWonMoney();
        //Assert
        assertEquals(800, result);
    }

    @Test
    public void testWinDozen() {
        //Arrange
        HashMap<Field, Integer> fields = new HashMap<>();
        fields.put(new Field("1st12", "green"), 100);
        fields.put(new Field("2nd12", "green"), 50);
        fields.put(new Field("3rd12", "green"), 100);
        fields.put(new Field("3rd12", "green"), 100);
        Field field = new Field("34", "red");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winDozen(fields, field);
        //Assert
        assertEquals(600, result);
    }

    @Test
    public void testWinFiveNumberBets() {
        //Arrange
        HashMap<int[], Integer> labels = new HashMap<>();
        int[] first = {6, 9};
        int[] second = {2, 5, 1, 4};
        int[] third = {2, 100, 1};
        int[] fourth = {0, 00, 1, 2, 3};
        int[] fifth = {0, 00, 1, 2, 3};
        labels.put(first, 100);
        labels.put(second, 100);
        labels.put(third, 100);
        labels.put(fourth, 100);
        labels.put(fifth, 50);
        Field field = new Field("00", "green");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winFiveNumberBets(labels, field);
        //Assert
        assertEquals(1050, result);
    }

}
