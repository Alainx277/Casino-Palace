/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author doemu
 */
public class Double implements BJState {

    private int hits = 0;

    @Override
    public void handleHit(BlackJack bj) {
        if (hits == 0) {
            ImageView nextcard = new ImageView();

            HBox hand1 = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("hand1")).findAny().get();
            if (bj.getCompleteStack().isEmpty()) {
                //Shuffling
                bj.addCards();
            }
            Karte k = bj.takeCard();
            if (bj.getWorthpointeur() > 10 && k.getNumber() == 14) {
                k.setCount(1);
            }
            bj.handleNewCard(1, k);
            nextcard.setImage(k.getImage());

            nextcard.setFitWidth(80);
            nextcard.setFitHeight(120);

            hand1.getChildren().add(nextcard);
            hits++;
            bj.setState((BJState) new Stand());
        } else {
        }
    }

    @Override
    public void handleStand(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleStandby(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleDouble(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleInsurance(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleSplit(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
