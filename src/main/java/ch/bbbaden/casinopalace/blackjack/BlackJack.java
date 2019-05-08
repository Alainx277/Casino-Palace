/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import ch.bbbaden.casinopalace.common.Casino;
import ch.bbbaden.casinopalace.common.User;
import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author doemu
 */
public class BlackJack {

    private int bet;
    private int insuranceBet;
    private int result;
    private int result1;
    private int change;
    private int split = 0;

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
    private int splitPointeur1 = 0;
    private int splitPointeur = 0;

    @FXML
    private AnchorPane ap;
    private final Casino casino;

    public BlackJack(AnchorPane ap, Casino casino) {
        //add cards
        this.ap = ap;
        this.casino = casino;
        addImages();
        addCards();

    }

    public Casino getCasino() {
        return casino;
    }

    public ArrayList<Karte> getCompleteStack() {
        return completeStack;
    }

    public int getResult() {
        return result;
    }

    public ArrayList<Karte> getcoP() {
        return coP;
    }

    public int getChange() {
        return change;
    }

    public int getResult1() {
        return result1;
    }

    public void setcoP(Karte cardsOfPointeur) {
        coP.add(cardsOfPointeur);
    }

    public ArrayList<Karte> getcoC() {
        return coC;
    }

    public int getSplitPointeur1() {
        return splitPointeur1;
    }

    public void setSplitPointeur1(int splitPointeur1) {
        this.splitPointeur1 = splitPointeur1;
    }

    public int getSplitPointeur() {
        return splitPointeur;
    }

    public void setSplitPointeur(int splitPointeur) {
        this.splitPointeur = splitPointeur;
    }

    public void setcoC(Karte cardsOfCroupier) {
        coC.add(cardsOfCroupier);
    }

    public void setInsuranceBet(int insuranceBet) {
        this.insuranceBet = insuranceBet;
    }

    public int getInsuranceBet() {
        return insuranceBet;
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

    public void onMoneyChanged(int worth) {
        User user = casino.getCurrentUser();
        Label chipsLbl = (Label) getAp().getChildren().stream().filter(x -> x.getId().equals("chipsLbl")).findAny().get();
        if (worth < 0) {
            //negative Worth doesnt have to be taken away
        } else {
            user.setChips(user.getChips().add(new BigDecimal(worth)));
        }
        chipsLbl.setText(user.getChips().stripTrailingZeros().toPlainString());
        try {
            casino.updateUser(user);
        } catch (IOException | UserDoesNotExistException e) {
            e.printStackTrace();
        }
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
                    throw new AssertionError();
            }
        }
    }

    public void addCards() {
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
                        throw new AssertionError();
                }
                completeStack.add(k);
            }

        }
    }

    public Karte takeCard() {
        Image image = null;
        Karte k = null;
        do {
            k = play();
            image = k.getImage();
        } while (checkImage(image) == false);

        return k;
    }

    public void handleNewCard(int definition, Karte k) {
        switch (definition) {
            case 0:
                worthCroupier += k.getCount();
                setCardAmountCroupier(getCardAmountCroupier() + 1);
                setcoC(k);
                break;
            case 1:
                worthpointeur += k.getCount();
                setCardAmountPointeur(getCardAmountpointeur() + 1);
                setcoP(k);
                break;
            case 2:
                splitPointeur += k.getCount();
                break;
            case 3:
                splitPointeur1 += k.getCount();
                break;
            default:
                throw new AssertionError();
        }
        if (definition == 0) {

        } else {

        }
    }

    private boolean checkImage(Image image) {
        boolean b = false;
        if (image != null) {
            b = true;
        }
        return b;
    }

    public int getOutComeOfFirstSplit() {
        change = 0;
        result = 0;
        result1 = 0;
        if (getSplitPointeur() != 0) {
            if (getSplitPointeur() == 21) {
                int e = bet / 2;
                change = e * 3 / 2;
                result = 2;
            } else if (getSplitPointeur() > 21) {
                result = 0;
                change -= bet / 2;
            } else if (getSplitPointeur() < 21) {
                if (getWorthCroupier() > 21) {
                    result = 2;
                    change = bet;
                } else {
                    if (getSplitPointeur() > getWorthCroupier()) {
                        result = 2;
                        change = bet;
                    } else if (getSplitPointeur() < getWorthCroupier()) {
                        result = 0;
                        change -= bet / 2;
                    } else {
                        result = 1;
                        change = 0;
                    }
                }
            }
            if (getSplitPointeur1() == 21) {
                int e = bet / 2;
                change += e * 3 / 2;
                result1 = 2;
            } else if (getSplitPointeur1() > 21) {
                result1 = 0;
                change -= bet / 2;
            } else if (getSplitPointeur1() < 21) {
                if (getWorthCroupier() > 21) {
                    result1 = 2;
                    change += bet;
                } else if (getSplitPointeur1() > getWorthCroupier()) {
                    result1 = 2;
                    change += bet;
                } else if (getSplitPointeur1() < getWorthCroupier()) {
                    result1 = 0;
                    change -= bet / 2;
                } else {
                    result1 = 1;
                    change += 0;
                }
            }
        }
        return change;
    }

    public int checkOutcome() {
        if (change == 0) {
            if (getWorthpointeur() == 21) {
                //BlackJack
                bet += bet * 3 / 2;
                result = 2;
                change = bet;
                if (getCardAmountpointeur() > 3) {
                } else if (getCardAmountpointeur() == 3) {
                    //Possibly Triple Seven
                    for (Karte k : getcoP()) {
                        if (k.getCount() != 7) {
                            //Not Triple seven ? Regular BlackJack
                        } else {
                        }
                    }
                } else if (getCardAmountpointeur() == 2) {
                    //Possibly BlackJack
                    if (getcoP().get(0).getCount() == 11) {
                        //possibly a blackjack
                        if (getcoP().get(1).getNumber() == 11) {
                            //BLACKJACK
                        } else {
                            //regular blackJack
                        }
                    } else {
                        //Regular BlackJack
                    }

                }
            } else if (getWorthpointeur() > 21) {
                //Bust
                result = 0;
                change = bet;
            } else if (getWorthpointeur() < 21) {
                //check if greater than croupier
                if (getWorthCroupier() > 21) {
                    //Croupier busted
                    result = 2;
                    change = bet * 2;
                } else {
                    if (getWorthCroupier() > getWorthpointeur()) {
                        //croupier winner
                        result = 0;
                        change = -bet;
                    } else if (getWorthCroupier() == getWorthpointeur()) {
                        //undeceided
                        result = 1;
                        change = bet;
                    } else {
                        //pointeur winner
                        result = 2;
                        change = bet * 2;
                    }
                }
            }
        }
        return change;
    }

    public int outcomeInsurance() {
        if (getWorthCroupier() == 21) {
            //pay off
            insuranceBet += insuranceBet * 3 / 2;

        } else {
            insuranceBet = 0;
        }
        return insuranceBet;
    }

}
