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

    @Override
    public void handleHit(BlackJack bj) {
        /*
        todo
        when line is over due and stuffed we gotta make the cards smaller @ 6 max 21 Aces
         */
        if (miniState == 0) {
            if (bj.getWorthpointeur() < goal) {
                ImageView nextcard = new ImageView();

                HBox hand1 = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("hand1")).findAny().get();

                Image image = null;
                Button deck1 = new Button("Deck 1");
                Karte k;
                do {
                    k = bj.play();
                    image = k.getImage();
                } while (checkImage(image) == false);

                bj.setCardAmountPointeur(bj.getCardAmountpointeur() + 1);
                bj.setWorthpointeur(bj.getWorthpointeur() + k.getCount());
                nextcard.setImage(image);

                nextcard.setFitWidth(80);
                nextcard.setFitHeight(120);

                hand1.getChildren().add(nextcard);

                ArrayList<Image> cardsInHandForDeck1 = new ArrayList();

                if (bj.getCardAmountpointeur() == 7) {
                    for (int i = 0; i < hand1.getChildren().size(); i++) {
                        ImageView img = (ImageView) hand1.getChildren().get(i);

                        cardsInHandForDeck1.add(img.getImage());
                        System.out.println("Size matters: " + cardsInHandForDeck1.size());
                    }

                    bj.getAp().getChildren().add(deck1);
                    deck1.setLayoutY(470);
                    deck1.setLayoutX(400);

                } else if (bj.getCardAmountpointeur() == 8) {
                    hand1.getChildren().removeAll(hand1.getChildren()); //clears for transition
                } else if (bj.getCardAmountpointeur() > 7 && bj.getCardAmountpointeur() < 14) {
                    for (int i = 0; i < hand1.getChildren().size(); i++) {
                        ImageView img = (ImageView) hand1.getChildren().get(i);
                        if (i < 7) { //prolly mistaken here
                            cardsInHandForDeck2[i] = img.getImage();
                        }

                    }
                    Button deck2 = new Button("Deck 2");
                    bj.getAp().getChildren().add(deck2);
                    deck2.setLayoutY(470);
                    deck2.setLayoutX(470);

                    deck2.setOnAction((ActionEvent event) -> {
                        hand1.getChildren().removeAll(hand1.getChildren());
                        if (bj.getCardAmountpointeur() == 14) {
                            miniState = 1;
                        } else {
                            miniState = 0;
                        }

                        for (Image img : cardsInHandForDeck2) {
                            if (img != null) {
                                ImageView imgView = new ImageView();
                                imgView.setFitHeight(120);
                                imgView.setFitWidth(80);
                                imgView.setImage(img);
                                hand1.getChildren().add(imgView);
                            }

                        }
                        System.out.println("Clicked.");
                    });
                } else if (bj.getCardAmountpointeur() == 14) {
                    hand1.getChildren().removeAll(hand1.getChildren());
                } else if (bj.getCardAmountpointeur() > 14 & bj.getCardAmountpointeur() < 21) {
                    for (int i = 0; i < hand1.getChildren().size(); i++) {
                        ImageView img = (ImageView) hand1.getChildren().get(i);
                        if (i < 7) { //prolly mistaken here
                            cardsInHandForDeck3[i] = img.getImage();
                        }

                    }
                    Button deck3 = new Button("Deck 3");
                    bj.getAp().getChildren().add(deck3);
                    deck3.setLayoutX(540);
                    deck3.setLayoutY(470);
                    deck3.setOnAction((ActionEvent event) -> {
                        hand1.getChildren().removeAll(hand1.getChildren());
                        if (bj.getCardAmountpointeur() == 21) {
                            miniState = 1;
                        } else {
                            miniState = 0;
                        }

                        for (Image img : cardsInHandForDeck3) {
                            if (img != null) {
                                ImageView imgView = new ImageView();
                                imgView.setFitHeight(120);
                                imgView.setFitWidth(80);
                                imgView.setImage(img);
                                hand1.getChildren().add(imgView);
                            }

                        }
                        System.out.println("Clicked.");
                    });

                } else if (bj.getCardAmountpointeur() == 21) {
                    miniState = 1;
                }
                deck1.setOnAction((ActionEvent event) -> {
                    if (cardsInHandForDeck2[0] != null) {
                        hand1.getChildren().removeAll(hand1.getChildren());  //clears current deck
                        for (Image img : cardsInHandForDeck1) {
                            ImageView imgView = new ImageView(img);
                            imgView.setFitHeight(120);
                            imgView.setFitWidth(80);
                            hand1.getChildren().add(imgView);
                        }
                        miniState = 1;
                    } else {
                        System.out.println("SHAIT");
                    }
                });
            } else if (bj.getWorthpointeur() > 21) {
                System.out.println("LOST");
            }
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
        //make pointeur incapable of Hitting or basically anything else
    }

    @Override
    public void handleStandby(BlackJack bj) {
        //If won, lost or 
    }

    @Override
    public void handleDouble(BlackJack bj) {

        //if hed like he may Double
    }

    @Override
    public void handleInsurance(BlackJack bj) {
        //When the Croupier has an Ace one can make another bet for insuring agianst his BlackJack
    }

    @Override
    public void handleSplit(BlackJack bj) {
        //Two of the same makes the Pointeur see a chance of splitting his cards
    }

}
