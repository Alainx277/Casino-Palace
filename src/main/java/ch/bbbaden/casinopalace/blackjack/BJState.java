/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

/**
 *
 * @author doemu
 */
public interface BJState {
    
    public void handleHit(BlackJack bj);   
    public void handleStand(BlackJack bj);
    public void handleStandby(BlackJack bj);
    public void handleDouble(BlackJack bj);
    public void handleInsurance(BlackJack bj);
    public void handleSplit(BlackJack bj);
}
