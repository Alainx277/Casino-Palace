package ch.bbbaden.casinopalace.poker.game;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
    private ArrayList<Card> cards = new ArrayList<>();

    public CardStack() {
        fillStack();
    }

    public Card pop(){
        if (cards.size() == 0){
            fillStack();
        }

        Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return card;
    }

    public void fillStack(){
        cards.clear();

        for (CardSuit color : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                cards.add(new Card(cardRank, color));
            }
        }

        Collections.shuffle(cards);
    }
}
