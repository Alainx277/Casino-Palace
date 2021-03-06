/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author doemu
 */
public class Split implements BJState {

    private HBox hand1;
    private HBox newHand2;
    private Label lblNewBet;

    @Override
    public void handleHit(BlackJack bj) {
        if (bj.getSplitPointeur() >= 21 || bj.getSplitPointeur1() >= 21) {
            newHand2.setVisible(false);
            bj.setState((BJState) new Stand());
        } else {
            for (int i = 0; i < 2; i++) {
                ImageView imgView = new ImageView();
                Image img = null;
                if (bj.getCompleteStack().isEmpty()) {
                    //Shuffling
                    bj.addCards();
                }
                Karte k = bj.takeCard();
                img = k.getImage();
                switch (i) {
                    case 0:
                        if (bj.getSplitPointeur() > 10 && k.getNumber() == 14) {
                            k.setCount(1);
                        }
                        imgView.setImage(img);
                        imgView.setFitHeight(100);
                        imgView.setFitWidth(65);
                        hand1.getChildren().add(imgView);
                        bj.handleNewCard(2, k);
                        break;
                    case 1:
                        if (bj.getSplitPointeur1() > 10 && k.getNumber() == 14) {
                            k.setCount(1);
                        }
                        imgView.setImage(img);
                        imgView.setFitHeight(100);
                        imgView.setFitWidth(65);
                        newHand2.getChildren().add(imgView);
                        bj.handleNewCard(3, k);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
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
        //split
        hand1 = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("hand1")).findAny().get();
        Label lbl = (Label) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("betLbl")).findAny().get();
        lblNewBet = (Label) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("lblNewBet")).findAny().get();
        ImageView imgViewChips = (ImageView) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("betImgView")).findAny().get();
        newHand2 = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("newHand2")).findAny().get();
        ImageView chipsView = (ImageView) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("chipsView")).findAny().get();
        //getting Image of second card

        ImageView secondCardView = (ImageView) hand1.getChildren().get(1);
        ImageView firstCardView = (ImageView) hand1.getChildren().get(0);
        firstCardView.setFitWidth(65);
        firstCardView.setFitHeight(100);
        secondCardView.setFitWidth(65);
        secondCardView.setFitHeight(100);

        //clear hand1
        hand1.getChildren().clear();
        newHand2.getChildren().clear();

        //add to hand
        hand1.getChildren().add(firstCardView);
        newHand2.getChildren().add(secondCardView);

        //adjust Hboxes
        hand1.setMaxWidth(200);
        newHand2.setMaxWidth(200);
        newHand2.setSpacing(20);
        newHand2.setLayoutY(hand1.getLayoutY());
        newHand2.setLayoutX(450);

        //new Bet
        lbl.setText(Integer.toString(bj.getBet() / 2));
        lblNewBet.setText(Integer.toString(bj.getBet() / 2));
        lblNewBet.setVisible(true);
        chipsView.setVisible(true);

        //Adjust betLabels
        chipsView.setImage(new Image("/images/chips.png"));
        chipsView.setFitWidth(40);
        chipsView.setFitHeight(40);
        imgViewChips.setFitHeight(40);
        imgViewChips.setFitWidth(40);
        chipsView.setLayoutX(hand1.getLayoutX());
        chipsView.setLayoutY(lbl.getLayoutY());
        imgViewChips.setLayoutX(newHand2.getLayoutX());
        imgViewChips.setLayoutY(lbl.getLayoutY());

        lbl.setLayoutX(chipsView.getLayoutX() + 70);
        lbl.setLayoutY(lbl.getLayoutY() + 10);
        lblNewBet.setLayoutX(imgViewChips.getLayoutX() + 70);
        lblNewBet.setLayoutY(lbl.getLayoutY());

        //Add to splitWorth
        bj.setSplitPointeur(bj.getcoP().get(0).getCount());
        bj.setSplitPointeur1(bj.getcoP().get(1).getCount());

    }

}
