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
public class StandBy implements BJState {

    @Override
    public void handleHit(BlackJack bj) {
        if (bj.getBet() > 0) {

            HBox hbox = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("croupier")).findAny().get();
            HBox hbox1 = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("hand1")).findAny().get();

            hbox.setSpacing(20);
            hbox1.setLayoutY(340);
            hbox1.setLayoutX(100);
            hbox.setLayoutX(100);

            ImageView poineturView = new ImageView();
            ImageView croupierView = new ImageView();
            ImageView pointeurTwo = new ImageView();
            ImageView croupierTwo = new ImageView();

            for (int i = 0; i < 3; i++) {
                Image image = null;
                Karte k = null;
                do {
                    k = bj.play();
                    image = k.getImage();

                } while (checkImage(image) == false);
                switch (i) {
                    case 0:
                        bj.setCardAmountPointeur(bj.getCardAmountpointeur() + 1);
                        bj.setcoP(k);
                        bj.setWorthpointeur(bj.getWorthpointeur() + k.getCount());
                        poineturView.setImage(image);
                        break;
                    case 1:
                        bj.setCardAmountPointeur(bj.getCardAmountpointeur() + 1);
                        bj.setcoP(k);
                        bj.setWorthpointeur(bj.getWorthpointeur() + k.getCount());
                        pointeurTwo.setImage(image);
                        break;
                    case 2:
                        bj.setCardAmountCroupier(bj.getCardAmountCroupier() + 1);
                        bj.setWorthCroupier(k.getCount());
                        bj.setcoC(k);
                        croupierView.setImage(image);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            Image hiddenCard = new Image("/images/cards/background.png");
            System.out.println("WorthPointeur: " + bj.getWorthpointeur());
            croupierTwo.setImage(hiddenCard);

            poineturView.setFitWidth(80);
            poineturView.setFitHeight(120);
            pointeurTwo.setFitWidth(80);
            pointeurTwo.setFitHeight(120);
            croupierView.setFitHeight(120);
            croupierView.setFitWidth(80);
            croupierTwo.setFitWidth(80);
            croupierTwo.setFitHeight(120);

            hbox1.getChildren().add(poineturView);
            hbox1.getChildren().add(pointeurTwo);
            hbox.getChildren().add(croupierView);
            hbox.getChildren().add(croupierTwo);

            bj.setState((BJState) new Hit());
        }
    }

    private boolean checkImage(Image image) {
        boolean b = false;
        if (image != null) {
            b = true;
        }
        return b;
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
    public void handleInsurance(BlackJack bj) {
        throw new UnsupportedOperationException("Shouldnt work");
    }

    @Override
    public void handleSplit(BlackJack bj) {
        throw new UnsupportedOperationException("Shoudlnt work");
    }

    @Override
    public void handleDouble(BlackJack bj) {
        throw new UnsupportedOperationException("Shoudlnt work");
    }

}
