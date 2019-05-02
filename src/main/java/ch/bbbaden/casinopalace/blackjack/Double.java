/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        System.out.println("HIT IN DOUBLE");
        if (hits == 0) {
            ImageView nextcard = new ImageView();

            HBox hand1 = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("hand1")).findAny().get();

            Image image = null;
            Karte k;
            do {
                k = bj.play();
                image = k.getImage();
            } while (checkImage(image) == false);

            bj.setcoP(k);

            if (k.getCount() == 11) {
                if (bj.getCardAmountpointeur() > 10) {
                    k.setCount(1);
                }
            }
            bj.setCardAmountPointeur(bj.getCardAmountpointeur() + 1);
            bj.setWorthpointeur(bj.getWorthpointeur() + k.getCount());
            nextcard.setImage(image);

            nextcard.setFitWidth(80);
            nextcard.setFitHeight(120);

            hand1.getChildren().add(nextcard);
            hits++;
            bj.setState((BJState) new Stand());
        } else {
            System.out.println("Shit ain't going to work");
        }
    }

    private boolean checkImage(Image image) {
        boolean b = false;
        if (image != null) {
            b = true;
        } else {
            System.out.println("EXCEPTIONAL IMAGE");
        }
        return b;
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