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
    private ArrayList<Integer> commitmoney = new ArrayList<>();
    private int result;

    public void addChip(int money) {
        betmoney.add(money);
        commitmoney.add(money);
    }

    public int getMoney() {
        result = 0;
        for (int i = 0; i < betmoney.size(); i++) {
            result += betmoney.get(i);
        }
        return result;
    }

    public int getCommitMoney() {
        result = 0;
        for (int i = 0; i < commitmoney.size(); i++) {
            result += commitmoney.get(i);
        }
        return result;
    }

    public void clearBetMoney() {
        betmoney.clear();
    }

    public void clearCommitMoney() {
        commitmoney.clear();
    }
}
