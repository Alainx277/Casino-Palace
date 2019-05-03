/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import java.util.ArrayList;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author doemu
 */
public class BlackJack {

    private BJState state;

    private int bet;

    private final int cardStack = 52;

    private final int stacks = 6;

    private ArrayList<Karte> completeStack = new ArrayList();

    private ArrayList<Karte> coP = new ArrayList();

    private ArrayList<Karte> coC = new ArrayList();

    private Image[] imagecards = new Image[cardStack];

    private BJState currentState = (BJState) new StandBy();

    private int cardAmountPointeur = 0;
    private int cardAmountCroupier = 0;
    private int worthCroupier = 0;
    private int worthpointeur = 0;

    @FXML
    private AnchorPane ap;

    public BlackJack(AnchorPane ap) {
        //add cards
        this.ap = ap;
        addImages();
        addCards();

    }

    public ArrayList<Karte> getcoP() {
        return coP;
    }

    public void setcoP(Karte cardsOfPointeur) {
        coP.add(cardsOfPointeur);
    }

    public ArrayList<Karte> getcoC() {
        return coC;
    }

    public void setcoC(Karte cardsOfCroupier) {
        coC.add(cardsOfCroupier);
    }

    public AnchorPane getAp() {
        return ap;
    }

    public BJState requestState() {
        return currentState;
    }

    public void setState(BJState state) {
        currentState = state;
    }

    public int getBet() {
        return bet;
    }

    public Karte play() {
        Karte retVal = null;
        Random r = new Random();
        int i = r.nextInt(completeStack.size());
        retVal = completeStack.get(i);
        completeStack.remove(i);
        return retVal;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getWorthCroupier() {
        return worthCroupier;
    }

    public void setWorthCroupier(int worthCroupier) {
        this.worthCroupier = worthCroupier;
    }

    public void setCardAmountPointeur(int cardAmount) {
        this.cardAmountPointeur = cardAmount;
    }

    public int getCardAmountpointeur() {
        return cardAmountPointeur;
    }

    public int getCardAmountCroupier() {
        return cardAmountCroupier;
    }

    public void setCardAmountCroupier(int cardAmountCroupier) {
        this.cardAmountCroupier = cardAmountCroupier;
    }

    public int getWorthpointeur() {
        return worthpointeur;
    }

    public void setWorthpointeur(int worthpointeur) {
        this.worthpointeur = worthpointeur;
    }

    private void addImages() {
        for (int i = 0; i < cardStack; i++) {
            switch (i) {
                case 0:
                    imagecards[i] = new Image("/images/cards/2H.png");
                    break;
                case 1:
                    imagecards[i] = new Image("/images/cards/3H.png");
                    break;
                case 2:
                    imagecards[i] = new Image("/images/cards/4H.png");
                    break;
                case 3:
                    imagecards[i] = new Image("/images/cards/5H.png");
                    break;
                case 4:
                    imagecards[i] = new Image("/images/cards/6H.png");
                    break;
                case 5:
                    imagecards[i] = new Image("/images/cards/7H.png");
                    break;
                case 6:
                    imagecards[i] = new Image("/images/cards/8H.png");
                    break;
                case 7:
                    imagecards[i] = new Image("/images/cards/9H.png");
                    break;
                case 8:
                    imagecards[i] = new Image("/images/cards/10H.png");
                    break;
                case 9:
                    imagecards[i] = new Image("/images/cards/JH.png");
                    break;
                case 10:
                    imagecards[i] = new Image("/images/cards/QH.png");
                    break;
                case 11:
                    imagecards[i] = new Image("/images/cards/KH.png");
                    break;
                case 12:
                    imagecards[i] = new Image("/images/cards/AH.png");
                    break;
                case 13:
                    imagecards[i] = new Image("/images/cards/2D.png");
                    break;
                case 14:
                    imagecards[i] = new Image("/images/cards/3D.png");
                    break;
                case 15:
                    imagecards[i] = new Image("/images/cards/4D.png");
                    break;
                case 16:
                    imagecards[i] = new Image("/images/cards/5D.png");
                    break;
                case 17:
                    imagecards[i] = new Image("/images/cards/6D.png");
                    break;
                case 18:
                    imagecards[i] = new Image("/images/cards/7D.png");
                    break;
                case 19:
                    imagecards[i] = new Image("/images/cards/8D.png");
                    break;
                case 20:
                    imagecards[i] = new Image("/images/cards/9D.png");
                    break;
                case 21:
                    imagecards[i] = new Image("/images/cards/10D.png");
                    break;
                case 22:
                    imagecards[i] = new Image("/images/cards/JD.png");
                    break;
                case 23:
                    imagecards[i] = new Image("/images/cards/QD.png");
                    break;
                case 24:
                    imagecards[i] = new Image("/images/cards/KD.png");
                    break;
                case 25:
                    imagecards[i] = new Image("/images/cards/AD.png");
                    break;
                case 26:
                    imagecards[i] = new Image("/images/cards/2C.png");
                    break;
                case 27:
                    imagecards[i] = new Image("/images/cards/3C.png");
                    break;
                case 28:
                    imagecards[i] = new Image("/images/cards/4C.png");
                    break;
                case 29:
                    imagecards[i] = new Image("/images/cards/5C.png");
                    break;
                case 30:
                    imagecards[i] = new Image("/images/cards/6C.png");
                    break;
                case 31:
                    imagecards[i] = new Image("/images/cards/7C.png");
                    break;
                case 32:
                    imagecards[i] = new Image("/images/cards/8C.png");
                    break;
                case 33:
                    imagecards[i] = new Image("/images/cards/9C.png");
                    break;
                case 34:
                    imagecards[i] = new Image("/images/cards/10C.png");
                    break;
                case 35:
                    imagecards[i] = new Image("/images/cards/JC.png");
                    break;
                case 36:
                    imagecards[i] = new Image("/images/cards/QC.png");
                    break;
                case 37:
                    imagecards[i] = new Image("/images/cards/KC.png");
                    break;
                case 38:
                    imagecards[i] = new Image("/images/cards/AC.png");
                    break;
                case 39:
                    imagecards[i] = new Image("/images/cards/2S.png");
                    break;
                case 40:
                    imagecards[i] = new Image("/images/cards/3S.png");
                    break;
                case 41:
                    imagecards[i] = new Image("/images/cards/4S.png");
                    break;
                case 42:
                    imagecards[i] = new Image("/images/cards/5S.png");
                    break;
                case 43:
                    imagecards[i] = new Image("/images/cards/6S.png");
                    break;
                case 44:
                    imagecards[i] = new Image("/images/cards/7S.png");
                    break;
                case 45:
                    imagecards[i] = new Image("/images/cards/8S.png");
                    break;
                case 46:
                    imagecards[i] = new Image("/images/cards/9S.png");
                    break;
                case 47:
                    imagecards[i] = new Image("/images/cards/10S.png");
                    break;
                case 48:
                    imagecards[i] = new Image("/images/cards/JS.png");
                    break;
                case 49:
                    imagecards[i] = new Image("/images/cards/QS.png");
                    break;
                case 50:
                    imagecards[i] = new Image("/images/cards/KS.png");
                    break;
                case 51:
                    imagecards[i] = new Image("/images/cards/AS.png");
                    break;
                default:
                    System.out.println("Not implemented");
                    throw new AssertionError();
            }
        }
    }

    private void addCards() {
        for (int i = 0; i < stacks; i++) {
            for (int j = 0; j < cardStack; j++) {
                Karte k;
                switch (j) {
                    case 0:
                        k = new Karte(2, 2, Symbol.Heart, imagecards[j]);
                        break;
                    case 1:
                        k = new Karte(3, 3, Symbol.Heart, imagecards[j]);
                        break;
                    case 2:
                        k = new Karte(4, 4, Symbol.Heart, imagecards[j]);
                        break;
                    case 3:
                        k = new Karte(5, 5, Symbol.Heart, imagecards[j]);
                        break;
                    case 4:
                        k = new Karte(6, 6, Symbol.Heart, imagecards[j]);
                        break;
                    case 5:
                        k = new Karte(7, 7, Symbol.Heart, imagecards[j]);
                        break;
                    case 6:
                        k = new Karte(8, 8, Symbol.Heart, imagecards[j]);
                        break;
                    case 7:
                        k = new Karte(9, 9, Symbol.Heart, imagecards[j]);
                        break;
                    case 8:
                        k = new Karte(10, 10, Symbol.Heart, imagecards[j]);
                        break;
                    case 9:
                        k = new Karte(10, 11, Symbol.Heart, imagecards[j]);
                        break;
                    case 10:
                        k = new Karte(10, 12, Symbol.Heart, imagecards[j]);
                        break;
                    case 11:
                        k = new Karte(10, 13, Symbol.Heart, imagecards[j]);
                        break;
                    case 12:
                        k = new Karte(11, 14, Symbol.Heart, imagecards[j]);
                        break;
                    case 13:
                        k = new Karte(2, 2, Symbol.Diamond, imagecards[j]);
                        break;
                    case 14:
                        k = new Karte(3, 3, Symbol.Diamond, imagecards[j]);
                        break;
                    case 15:
                        k = new Karte(4, 4, Symbol.Diamond, imagecards[j]);
                        break;
                    case 16:
                        k = new Karte(5, 5, Symbol.Diamond, imagecards[j]);
                        break;
                    case 17:
                        k = new Karte(6, 6, Symbol.Diamond, imagecards[j]);
                        break;
                    case 18:
                        k = new Karte(7, 7, Symbol.Diamond, imagecards[j]);
                        break;
                    case 19:
                        k = new Karte(8, 8, Symbol.Diamond, imagecards[j]);
                        break;
                    case 20:
                        k = new Karte(9, 9, Symbol.Diamond, imagecards[j]);
                        break;
                    case 21:
                        k = new Karte(10, 10, Symbol.Diamond, imagecards[j]);
                        break;
                    case 22:
                        k = new Karte(10, 11, Symbol.Diamond, imagecards[j]);
                        break;
                    case 23:
                        k = new Karte(10, 12, Symbol.Diamond, imagecards[j]);
                        break;
                    case 24:
                        k = new Karte(10, 13, Symbol.Diamond, imagecards[j]);
                        break;
                    case 25:
                        k = new Karte(11, 14, Symbol.Diamond, imagecards[j]);
                        break;
                    case 26:
                        k = new Karte(2, 2, Symbol.Club, imagecards[j]);
                        break;
                    case 27:
                        k = new Karte(3, 3, Symbol.Club, imagecards[j]);
                        break;
                    case 28:
                        k = new Karte(4, 4, Symbol.Club, imagecards[j]);
                        break;
                    case 29:
                        k = new Karte(5, 5, Symbol.Club, imagecards[j]);
                        break;
                    case 30:
                        k = new Karte(6, 6, Symbol.Club, imagecards[j]);
                        break;
                    case 31:
                        k = new Karte(7, 7, Symbol.Club, imagecards[j]);
                        break;
                    case 32:
                        k = new Karte(8, 8, Symbol.Club, imagecards[j]);
                        break;
                    case 33:
                        k = new Karte(9, 9, Symbol.Club, imagecards[j]);
                        break;
                    case 34:
                        k = new Karte(10, 10, Symbol.Club, imagecards[j]);
                        break;
                    case 35:
                        k = new Karte(10, 11, Symbol.Club, imagecards[j]);
                        break;
                    case 36:
                        k = new Karte(10, 12, Symbol.Club, imagecards[j]);
                        break;
                    case 37:
                        k = new Karte(10, 13, Symbol.Club, imagecards[j]);
                        break;
                    case 38:
                        k = new Karte(11, 14, Symbol.Club, imagecards[j]);
                        break;
                    case 39:
                        k = new Karte(2, 2, Symbol.Spade, imagecards[j]);
                        break;
                    case 40:
                        k = new Karte(3, 3, Symbol.Spade, imagecards[j]);
                        break;
                    case 41:
                        k = new Karte(4, 4, Symbol.Spade, imagecards[j]);
                        break;
                    case 42:
                        k = new Karte(5, 5, Symbol.Spade, imagecards[j]);
                        break;
                    case 43:
                        k = new Karte(6, 6, Symbol.Spade, imagecards[j]);
                        break;
                    case 44:
                        k = new Karte(7, 7, Symbol.Spade, imagecards[j]);
                        break;
                    case 45:
                        k = new Karte(8, 8, Symbol.Spade, imagecards[j]);
                        break;
                    case 46:
                        k = new Karte(9, 9, Symbol.Spade, imagecards[j]);
                        break;
                    case 47:
                        k = new Karte(10, 10, Symbol.Spade, imagecards[j]);
                        break;
                    case 48:
                        k = new Karte(10, 11, Symbol.Spade, imagecards[j]);
                        break;
                    case 49:
                        k = new Karte(10, 12, Symbol.Spade, imagecards[j]);
                        break;
                    case 50:
                        k = new Karte(10, 13, Symbol.Spade, imagecards[j]);
                        break;
                    case 51:
                        k = new Karte(11, 14, Symbol.Spade, imagecards[j]);
                        break;
                    default:
                        System.out.println("Not implemented");
                        throw new AssertionError();
                }
                completeStack.add(k);

            }

        }
    }
}
