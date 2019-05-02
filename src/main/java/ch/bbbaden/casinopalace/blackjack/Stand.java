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
        System.out.println("Stand in class");
        int counter = 0;
        //make croupier pick
        HBox hbox = (HBox) bj.getAp().getChildren().stream().filter(x -> x.getId().equals("croupier")).findAny().get();
        if (counter == 0) {
            for (int i = 0; i < 21; i++) {

                counter++;
                if (bj.getCardAmountCroupier() == 1) {
                    Image image = null;
                    Karte k = null;
                    do {
                        k = bj.play();
                        image = k.getImage();

                    } while (checkImage(image) == false);
                    int newWorth = k.getCount() + bj.getWorthCroupier();
                    if (k.getNumber() == 14) {
                        //when newly drawn card is an ace

                        if (newWorth > 21) {
                            k.setCount(1); //makes Ace count as 1
                        }
                    }
//                    if (bj.getcoC().get(0).getNumber() == 14) {
//                            System.out.println("First card's an ace");
//                            //when first card was an ace
//                            if (newWorth > 21) {
//                                bj.getcoC().get(0).setCount(1); //makes Ace count as 1
//                            }
//                            if(newWorth > 16){
//                                bj.getcoC().get(0).setCount(1); //makes Ace count as 1
//                            }
//                        }
                    bj.setCardAmountCroupier(bj.getCardAmountCroupier() + 1);
                    bj.setWorthCroupier(bj.getWorthCroupier() + k.getCount());
                    bj.setcoC(k);

                    ImageView croupierView = new ImageView(image);
                    croupierView.setFitWidth(80);
                    croupierView.setFitHeight(120);

                    hbox.getChildren().add(croupierView);
                } else {
                    System.out.println("this: " + bj.getWorthCroupier());
                    if (bj.getWorthCroupier() > 16) {
                        boolean broken = false;
                        System.out.println("Reached");
                        System.out.println("Size: " + bj.getcoC().size());
                        for(int e = 0; e < bj.getcoC().size(); e++){
                            System.out.println(bj.getcoC().get(e));
                            System.out.println("Worth Of Card 1: " +bj.getcoC().get(e).getCount());
                        }
                        for (Karte k : bj.getcoC()) {
                            System.out.println("Worth of card: " + k.getCount());
                            if (k.getCount() == 11) {
                                System.out.println("YES");
                                int hypotheticalWorth = bj.getWorthCroupier() - 10;
                                if (hypotheticalWorth <= 21) {
                                    bj.setWorthCroupier(hypotheticalWorth);
                                    k.setCount(1);
                                    System.out.println("Worth Cro: " +bj.getWorthCroupier());
                                    System.out.println("LOL");
                                } else {
                                    System.out.println("TOO BAD");
                                    broken = true;
                                    break;

                                }

                            } else {
                                broken = true;
                                break;
                            }
                        }
                        if (broken) {
                            break;
                        }
                        System.out.println("THIS: " + bj.getWorthCroupier());
                        System.out.println("Stand on 17");

                    } else {
                        System.out.println("Not 16 yet");
                        System.out.println(bj.getWorthCroupier());
                        Image image = null;
                        Karte k = null;
                        do {
                            k = bj.play();
                            image = k.getImage();

                        } while (checkImage(image) == false);

                        bj.setCardAmountCroupier(bj.getCardAmountCroupier() + 1);
                        bj.setWorthCroupier(bj.getWorthCroupier() + k.getCount());
                        bj.setcoC(k);
                        System.out.println("New Size: "+ bj.getcoC().size());
                        System.out.println("Size of Pointeur: " + bj.getcoP().size());

                        ImageView croupierView = new ImageView(image);
                        croupierView.setFitWidth(80);
                        croupierView.setFitHeight(120);

                        hbox.getChildren().add(croupierView);
                        System.out.println("this new: " + bj.getWorthCroupier());
                    }
                }
            }
        }

//        switch (bj.getWorthpointeur()) {
//            case 21:
//
//                break;
//            default:
//                throw new AssertionError();
//        }
    }

    private boolean checkImage(Image image) {
        boolean b = false;
        if (image != null) {
            b = true;
        }
        return b;
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
