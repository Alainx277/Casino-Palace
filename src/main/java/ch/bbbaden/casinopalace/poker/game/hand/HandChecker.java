package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import ch.bbbaden.casinopalace.poker.game.CardRank;

import java.util.*;
import java.util.function.Predicate;

public class HandChecker {
    private ArrayList<HandChecker> andChecks = new ArrayList<>();
    private ArrayList<ArrayList<CardOption>> ofSameColor = new ArrayList<>();
    private ArrayList<ArrayList<CardOption>> ofSameType = new ArrayList<>();
    private ArrayList<ArrayList<CardOption>> inRow = new ArrayList<>();
    private Boolean allowWildcards = null;
    private ArrayList<CardGroup> groups = new ArrayList<>();

    public HandChecker and(HandChecker check) {
        andChecks.add(check);
        return this;
    }

    public HandChecker wildcards(boolean allow) {
        allowWildcards = allow;
        return this;
    }

    public HandChecker ofSameType(CardOption... cards) {
        ArrayList<CardOption> list = new ArrayList<>(cards.length);
        list.addAll(Arrays.asList(cards));
        ofSameType.add(list);
        return this;
    }

    public HandChecker ofSameColor(CardOption... cards) {
        ArrayList<CardOption> list = new ArrayList<>(cards.length);
        list.addAll(Arrays.asList(cards));
        ofSameColor.add(list);
        return this;
    }

    public HandChecker inRow(CardOption... cards) {
        return inRow(false, cards);
    }

    public HandChecker inRow(boolean ascending, CardOption... cards) {
        ArrayList<CardOption> list = new ArrayList<>(cards.length);
        list.addAll(Arrays.asList(cards));
        Comparator<CardOption> comparator = Comparator.comparing(CardOption::getRequiredType);
        if (ascending){
            comparator = comparator.reversed();
        }
        list.sort(comparator);
        inRow.add(list);
        return this;
    }

    /*public CardGroup group(){
        CardGroup cardGroup = new CardGroup(this);
        groups.add(cardGroup);
        return cardGroup;
    }*/

    public boolean build(Card... cards) {
        ArrayList<Card> cardsLeft = new ArrayList<>(Arrays.asList(cards));
        boolean valid = true;

        // Search cards of same type
        for (ArrayList<CardOption> options : ofSameType) {
            // did we find a match
            boolean found = false;
            for (CardRank cardRank : CardRank.values()) {
                if (matchOptions(cardsLeft, options, x -> x.getRank() == cardRank)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                valid = false;
            }
        }

        if (valid) {
            // Search cards of same color
            for (ArrayList<CardOption> options : ofSameColor) {
                // did we find a match
                boolean found = false;
                // try all colors
                for (CardSuit color : CardSuit.values()) {
                    if (matchOptions(cardsLeft, options, x -> x.getSuit() == color)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    valid = false;
                    break;
                }
            }
        }

        if (valid) {
            // Search cards in a row
            for (ArrayList<CardOption> options : inRow) {
                boolean found = false;
                for (int i = 0; i < CardRank.values().length; i++) {
                    int finalI = i;
                    Predicate<Card> predicate = new Predicate<Card>() {
                        int offset = finalI;

                        @Override
                        public boolean test(Card card) {
                            if (offset >= CardRank.values().length){
                                return false;
                            }
                            boolean b = CardRank.values()[offset] == card.getRank();
                            offset++;
                            return b;
                        }
                    };

                    if (matchOptions(cardsLeft, options, predicate))
                    {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    valid = false;
                    break;
                }
            }
        }

        return valid && andChecks.stream().allMatch(x -> x.build(cards));
    }

    private boolean matchOptions(List<Card> cards, List<CardOption> options, Predicate<Card> condition) {
        boolean matchedOptions = true;
        ArrayList<Card> modCards = new ArrayList<>(cards);
        // check every option
        for (CardOption option : options) {
            // override settings
            if (allowWildcards != null) {
                option.wildcards(allowWildcards);
            }

            // have we found a card we can use
            Card foundCard = null;
            for (Card card : modCards) {
                // check if the card satisfies the option
                if (option.build(card) && (condition == null || CardOption.isWildcard(card) && option.allowWildcards() || condition.test(card))) {
                    foundCard = card;
                    // with wildcards we just continue searching
                    if (!option.allowWildcards()) {
                        break;
                    }
                }
            }
            if (foundCard == null) {
                matchedOptions = false;
                break;
            } else {
                modCards.remove(foundCard);
            }
        }

        if (matchedOptions) {
            cards.removeIf(x -> !modCards.contains(x));
        }
        return matchedOptions;
    }
}
