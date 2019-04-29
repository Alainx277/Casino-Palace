/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import javafx.scene.image.ImageView;

/**
 *
 * @author doemu
 */
public class StandBy implements BJState {

    @Override
    public void handleHit(BlackJack bj) {
        System.out.println(bj.getBet()); //0
        if (bj.getBet() > 0) {
            bj.setState((BJState) new Hit());
            //give poinetur and croupier a card
            ImageView poineturView = new ImageView();
            ImageView croupierView = new ImageView();
            poineturView.setImage(bj.play().getImage());
            croupierView.setImage(bj.play().getImage());
            poineturView.setFitWidth(100);
            poineturView.setFitHeight(150);
            croupierView.setFitHeight(150);
            croupierView.setFitWidth(100);
            bj.getHand1().setLayoutX(100);
            bj.getCroupier().setLayoutX(100);

            bj.getHand1().getChildren().add(poineturView);
            bj.getCroupier().getChildren().add(croupierView);
        }
    }

    @Override
    public void handleStand(BlackJack bj) {
        throw new UnsupportedOperationException("Shouldnt work");
    }

    @Override
    public void handleStandby(BlackJack bj) {
        throw new UnsupportedOperationException("Shouldnt work");
    }

    @Override
    public void handleDouble(BlackJack bj) {
        throw new UnsupportedOperationException("Shouldnt work");
    }

    @Override
    public void handleInsurance(BlackJack bj) {
        throw new UnsupportedOperationException("Shouldnt work");
    }

    @Override
    public void handleSplit(BlackJack bj) {
        throw new UnsupportedOperationException("Shoudlnt work");
    }

}
