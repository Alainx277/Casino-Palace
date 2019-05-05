package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * {@link CardSelector} is used to select cards with specific properties
 * <br/>
 * e.g. cards of suit Hearts except for ten
 */
public class CardSelector {
    private ArrayList<CardSelector> orSelectors = new ArrayList<>();
    private ArrayList<CardSelector> andSelectors = new ArrayList<>();
    private CardSuit requiredSuit = null;
    private CardRank requiredRank = null;
    private boolean inverted = false;
    private ArrayList<Predicate<Card>> cardPredicates = new ArrayList<>();

    public CardSelector ofSuit(CardSuit suit){
        requiredSuit = suit;
        return this;
    }

    public CardSelector ofRank(CardRank rank){
        requiredRank = rank;
        return this;
    }

    public CardSelector or(CardSelector selector){
        orSelectors.add(selector);
        return this;
    }

    public CardSelector and(CardSelector selector){
        andSelectors.add(selector);
        return this;
    }

    public CardSelector not(){
        inverted = true;
        return this;
    }

    public CardSelector matches(Predicate<Card> test){
        cardPredicates.add(test);
        return this;
    }

    /**
     * Checks if a given part passes the selector
     * @param card The card to check
     * @return If the card fulfills the selector
     */
    public boolean check(Card card){
        boolean result = true;

        if (requiredSuit != null && requiredSuit != card.getSuit()){
            result = false;
        }
        else if (requiredRank != null && requiredRank != card.getRank()){
            result = false;
        }
        else if (!cardPredicates.stream().allMatch(x -> x.test(card))){
            result = false;
        }

        return inverted != ((result && andSelectors.stream().allMatch(x -> x.check(card))) || orSelectors.stream().anyMatch(x -> x.check(card)));
    }

    public CardSelector[] multiple(int count){
        CardSelector[] arr = new CardSelector[count];
        for (int i = 0; i < count; i++) {
            arr[i] = this;
        }
        return arr;
    }
}
