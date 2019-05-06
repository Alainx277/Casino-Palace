/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author doemu
 */
public class Stand implements BJState {

    @Override
    public void handleHit(BlackJack bj) {
        throw new UnsupportedOperationException("Shouldnt work");
    }

    @Override
    public void handleStand(BlackJack bj) {
        //Check results and Cash out
        int counter = 0;
        //make croupier pick
        HBox hbox = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("croupier")).findAny().get();
        if (counter == 0) {
            if (bj.getCardAmountCroupier() < 2) {
                hbox.getChildren().remove(1);
            }

            for (int i = 0; i < 21; i++) {

                counter++;
                if (bj.getCardAmountCroupier() == 1) {
                    Karte k = bj.takeCard();
                    if (bj.getWorthCroupier() > 10 && k.getNumber() == 14) {
                        k.setCount(1);
                    }

                    bj.handleNewCard(0, k);

                    ImageView croupierView = new ImageView(k.getImage());
                    croupierView.setFitWidth(80);
                    croupierView.setFitHeight(120);

                    hbox.getChildren().add(croupierView);
                } else {
                    if (bj.getWorthCroupier() > 16) {
                        bj.getOutComeOfFirstSplit();
                        bj.checkOutcome();

                        break;
                    } else {
                        Karte k = bj.takeCard();
                        if (bj.getWorthCroupier() > 10 && k.getNumber() == 14) {
                            k.setCount(1);
                        }
                        bj.handleNewCard(0, k);

                        ImageView croupierView = new ImageView(k.getImage());
                        croupierView.setFitWidth(80);
                        croupierView.setFitHeight(120);

                        hbox.getChildren().add(croupierView);
                    }
                }
            }
        }
    }

    @Override
    public void handleStandby(BlackJack bj) {
        //Gotta be put back to stand by
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
        throw new UnsupportedOperationException("Shouldnt work");
    }

}
