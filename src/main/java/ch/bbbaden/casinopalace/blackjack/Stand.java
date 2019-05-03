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
            hbox.getChildren().remove(1);
            for (int i = 0; i < 21; i++) {

                counter++;
                if (bj.getCardAmountCroupier() == 1) {
                    Karte k = bj.takeCard();
                    bj.handleNewCard(true, k);

                    ImageView croupierView = new ImageView(k.getImage());
                    croupierView.setFitWidth(80);
                    croupierView.setFitHeight(120);

                    hbox.getChildren().add(croupierView);
                } else {
                    if (bj.getWorthCroupier() > 16) {
                        System.out.println("16");
                    } else {
                        Karte k = bj.takeCard();
                        bj.handleNewCard(true, k);

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
        HBox hbox = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("croupier")).findAny().get();
        
        if()

        ImageView imgView = new ImageView();

        System.out.println("LAWL");
        Karte k = bj.takeCard();
        bj.handleNewCard(true, k);
        imgView.setImage(k.getImage());
        hbox.getChildren().remove(1);
        imgView.setFitWidth(80);
        imgView.setFitHeight(120);
        hbox.getChildren().add(imgView);
        if (bj.getWorthCroupier() == 21) {

            System.out.println("BLACK JACK BUT INSURED");
            //pay out

        }
    }

    @Override
    public void handleSplit(BlackJack bj) {
        throw new UnsupportedOperationException("Shouldnt work");
    }

}
