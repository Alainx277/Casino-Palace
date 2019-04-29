/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class BetMoney {

    private ArrayList<Integer> betmoney = new ArrayList<>();
    private int result;

    public ArrayList<Integer> getBetmoney() {
        return betmoney;
    }

    public void addChip(int money) {
        betmoney.add(money);
    }

    public void clearBetMoney() {
        betmoney.clear();
    }

    public int getMoney() {
        //Here I'm here!!!!!
        result = 0;
        for (int i = 0; i < betmoney.size(); i++) {
            result += betmoney.get(i);
        }
        return result;
    }
}
