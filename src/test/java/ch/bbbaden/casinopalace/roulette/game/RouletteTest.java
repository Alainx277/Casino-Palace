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
    public void testWinOneToThirtySeven() {
        //Arrange
        HashMap<Field,Integer> fields = new HashMap<>();
        fields.put(new Field("11","black"), 100);
        fields.put(new Field("11","black"), 50);
        fields.put(new Field("1","red"), 100);
        Field field = new Field("11","black");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winStraightUpBets(fields, field);
        //Assert
        assertEquals(5400, result);
    }

    @org.junit.Test
    public void testPlayRoulette() {
    }

    @Test
    public void testWinOneToThirtyFive() {
    }

    @Test
    public void testWinOneToSevenTeen() {
        //Arrange
        HashMap<int[],Integer> labels = new HashMap<>();
        int[] first = {6,9};
        int[] second = {2,5,1,4};
        int[] third = {2,5};
        int[] fourth = {6,9,5,8};
        int[] fifth = {9,12};
        labels.put(first, 100);
        labels.put(second, 100);
        labels.put(third, 100);
        labels.put(fourth, 100);
        labels.put(fifth, 50);
        Field field = new Field("9","red");
        Roulette roulette = new Roulette();
        //Act
        int result = roulette.winSplitBets(labels, field);
        //Assert
        assertEquals(2700, result);
    }
    
}
