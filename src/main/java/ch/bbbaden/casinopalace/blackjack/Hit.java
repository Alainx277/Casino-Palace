/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author doemu
 */
public class Hit implements BJState {

    private Image[] cardsInHandForDeck2 = new Image[7];
    private Image[] cardsInHandForDeck3 = new Image[7];
    private int miniState = 0;
    private int goal = 21;
    private HBox newHand2;
    private HBox newHand3;
    private Button deck2;
    private Button deck3;

    @Override
    public void handleHit(BlackJack bj) {

        if (miniState == 0) {
            if (bj.getWorthpointeur() < goal) {
                ImageView nextcard = new ImageView();

                HBox hand1 = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("hand1")).findAny().get();
                //Draw card 
                Karte k = bj.takeCard();
                // Make count of card
                if (bj.getWorthpointeur() > 10 && k.getNumber() == 14) {
                    k.setCount(1);
                }
                bj.handleNewCard(false, k);
                //Show card on screen
                nextcard.setImage(k.getImage());

                nextcard.setFitWidth(80);
                nextcard.setFitHeight(120);

                if (bj.getCardAmountpointeur() < 8) {
                    hand1.getChildren().add(nextcard);
                }
                if (bj.getCardAmountpointeur() == 8) {
                    //When 7th card is drawn second Hbox has to show and a Button should manage what's shown
                    hand1.setVisible(false);
                    newHand2.setVisible(true);
                    hand1.getChildren().add(nextcard);
                    newHand2 = new HBox();
                    Button deck1 = new Button("Deck 1");
                    deck2 = new Button("Deck 2");
                    newHand2.setSpacing(20);
                    newHand2.setLayoutX(hand1.getLayoutX());
                    newHand2.setLayoutY(hand1.getLayoutY());
                    bj.getAp().getChildren().addAll(newHand2, deck1, deck2);
                    deck1.setLayoutY(470);
                    deck1.setLayoutX(400);
                    //Button action
                    deck1.setOnAction((ActionEvent event) -> {
                        if (newHand2.getChildren() != null) {
                            hand1.setVisible(true);
                            newHand2.setVisible(false);
                            newHand3.setVisible(false);
                            //Activated means one cannon hit
                            miniState = 1;
                        } else {
                            System.out.println("SHAIT");
                        }
                    });

                } else if (bj.getCardAmountpointeur() > 7 && bj.getCardAmountpointeur() < 14) {
                    //Filling the second Hbox
                    newHand2.getChildren().add(nextcard);

                    deck2.setLayoutY(470);
                    deck2.setLayoutX(470);

                    deck2.setOnAction((ActionEvent event) -> {
                        hand1.setVisible(false);
                        newHand2.setVisible(true);
                        newHand3.setVisible(false);
                        if (bj.getCardAmountpointeur() == 14) {
                            miniState = 1;
                        } else {
                            miniState = 0;
                        }

                        System.out.println("Clicked.");
                    });
                } else if (bj.getCardAmountpointeur() == 14) {
                    //prepare for third deck
                    newHand2.setVisible(false);
                    newHand3.setVisible(true);
                    deck3 = new Button("Deck 3");
                    bj.getAp().getChildren().add(deck3);
                    newHand3.setSpacing(20);
                    newHand3.setLayoutX(hand1.getLayoutX());
                    newHand3.setLayoutY(hand1.getLayoutY());
                } else if (bj.getCardAmountpointeur() > 14 & bj.getCardAmountpointeur() < 21) {

                    deck3.setLayoutX(540);
                    deck3.setLayoutY(470);
                    deck3.setOnAction((ActionEvent event) -> {
                        hand1.setVisible(false);
                        newHand2.setVisible(false);
                        newHand3.setVisible(true);
                        if (bj.getCardAmountpointeur() == 21) {
                            miniState = 1;
                        } else {
                            miniState = 0;
                        }
                        System.out.println("Clicked.");
                    });

                } else if (bj.getCardAmountpointeur() == 21) {
                    miniState = 1;
                }
            }
        }
    }

    @Override
    public void handleStand(BlackJack bj) {
        bj.setState((BJState) new Stand());
        System.out.println("STAND");
        //make pointeur incapable of Hitting or basically anything else
    }

    @Override
    public void handleStandby(BlackJack bj) {
        bj.setState((BJState) new StandBy());
        //If won, lost or 
    }

    @Override
    public void handleInsurance(BlackJack bj) {
        bj.setState((BJState) new Insurance());
        //When the Croupier has an Ace one can make another bet for insuring agianst his BlackJack
    }

    @Override
    public void handleSplit(BlackJack bj) {
        bj.setState((BJState) new Split());
        //Two of the same makes the Pointeur see a chance of splitting his cards
    }

    @Override
    public void handleDouble(BlackJack bj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
