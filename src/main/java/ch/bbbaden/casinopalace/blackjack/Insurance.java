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
public class Insurance implements BJState {

    @Override
    public void handleHit(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        HBox hbox = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("croupier")).findAny().get();

        ImageView imgView = new ImageView();

        Karte k = bj.takeCard();
        if (bj.getWorthCroupier() > 10 && k.getNumber() == 14) {
            k.setCount(1);
        }
        bj.handleNewCard(0, k);
        System.out.println(bj.getcoC().size());

        hbox.getChildren().remove(1);
        imgView.setImage(k.getImage());

        imgView.setFitWidth(80);
        imgView.setFitHeight(120);
        hbox.getChildren().add(imgView);
        if (bj.getWorthCroupier() == 21) {

            System.out.println("BLACK JACK BUT INSURED");
            System.out.println("Ihr Gewinn: " + bj.getInsuranceBet() + bj.getInsuranceBet() * 3 / 2);
            //pay off

        }else{
            System.out.println("USELESS INSURANCE");
            System.out.println("Your Loss : " + bj.getInsuranceBet());
        }
        bj.setState((BJState) new Stand());

    }

    @Override
    public void handleSplit(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
