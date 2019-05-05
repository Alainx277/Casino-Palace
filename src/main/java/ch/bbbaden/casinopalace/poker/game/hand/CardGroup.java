package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.Permutation;
import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;

import java.util.*;
import java.util.stream.Collectors;

public class CardGroup {
    private ArrayList<CardSelector> selectors = new ArrayList<>();
    private ArrayList<CardGroup> orGroups = new ArrayList<>();
    private ArrayList<CardGroup> andGroups = new ArrayList<>();
    private boolean sameSuit = false;
    private boolean sameRank = false;
    private boolean sequential = false;
    private boolean wildcards = false;

    public CardGroup add(CardSelector selector){
        selectors.add(selector);
        return this;
    }

    public CardGroup add(CardSelector... selectors){
        for (CardSelector selector : selectors) {
            add(selector);
        }
        return this;
    }

    public CardGroup ofSameSuit(){
        sameSuit = true;
        return this;
    }

    public CardGroup ofSameRank(){
        sameRank = true;
        return this;
    }

    public CardGroup sequential(){
        sequential = true;
        return this;
    }

    public CardGroup wildcards(){
        wildcards = true;
        return this;
    }

    public CardGroup or(){
        CardGroup g = new CardGroup();
        orGroups.add(g);
        return g;
    }

    public CardGroup or(CardGroup group){
        orGroups.add(group);
        return this;
    }

    public CardGroup and(){
        CardGroup g = new CardGroup();
        andGroups.add(g);
        return g;
    }

    public CardGroup and(CardGroup group){
        andGroups.add(group);
        return this;
    }

    /**
     * Finds the first occurence of cards matching this group
     * @param cards The cards to search in
     * @return The first occurence of cards matching the conditions or an empty optional
     */
    public Optional<List<Card>> find(Card... cards){
        ArrayList<ArrayList<Card>> viableCards = new ArrayList<>();

        // Match base selectors
        for (CardSelector selector : selectors) {
            ArrayList<Card> selectedCards = new ArrayList<>();
            for (Card card : cards) {
                if (selector.check(card)){
                    selectedCards.add(card);
                }
            }
            viableCards.add(selectedCards);
        }

        // A card is missing -> no way to find it
        for (ArrayList<Card> viableCard : viableCards) {
            if (viableCard.size() == 0) {
                return Optional.empty();
            }
        }

        // Work through all possible combinations
        int[] indices = new int[viableCards.size()];

        int currentIndex = indices.length - 1;
        outerProcess: while (true) {

            ArrayList<Card> permutation = new ArrayList<>(viableCards.size());
            for (int i = 0; i < viableCards.size(); i++) {
                permutation.add(viableCards.get(i).get(indices[i]));
            }


            boolean passes = true;
            for (int i = 0; i < permutation.size(); i++) {
                for (int j = 0; j < permutation.size(); j++) {
                    if (i == j){
                        continue;
                    }
                    if (permutation.get(i).equals(permutation.get(j))){
                        passes = false;
                        break;
                    }
                }
                if (!passes){
                    break;
                }
            }

            if (sameSuit && passes){
                passes = permutation.stream().allMatch(x -> x.getSuit() == permutation.get(0).getSuit()) && passes;
            }

            if (sameRank && passes){
                CardRank rank = permutation.stream().filter(x -> !Wildcard.is(x.getRank())).findFirst().orElse(permutation.get(0)).getRank();
                passes = permutation.stream().allMatch(x -> x.getRank() == rank || (wildcards && Wildcard.is(x.getRank()))) && passes;
            }

            if (sequential && passes) {
                boolean foundAll = false;
                for (int base = CardRank.values().length - 1; base >= 0; base--) {
                    // Iterate from base offset downwards
                    boolean foundNext = true;
                    boolean[] columnsUsed = new boolean[viableCards.size()];
                    for (int i = base; i > base - viableCards.size(); i--) {
                        if (i < 0){
                            foundNext = false;
                            break;
                        }

                        CardRank rank = CardRank.values()[i];
                        boolean found = false;
                        for (int i1 = 0; i1 < permutation.size(); i1++) {
                            if (columnsUsed[i1]){
                                continue;
                            }
                            Card x = permutation.get(i1);
                            if (x.getRank() == rank) {
                                columnsUsed[i1] = true;
                                found = true;
                                break;
                            }
                        }

                        if (!found && wildcards){
                            for (int i1 = 0; i1 < permutation.size(); i1++) {
                                if (columnsUsed[i1]){
                                    continue;
                                }
                                Card x = permutation.get(i1);
                                if (Wildcard.is(x.getRank())) {
                                    found = true;
                                    columnsUsed[i1] = true;
                                    break;
                                }
                            }
                        }

                        if (!found){
                            foundNext = false;
                            break;
                        }
                    }
                    if (foundNext){
                        foundAll = true;
                        break;
                    }
                }
                passes = foundAll && passes;
            }

            if (passes){

                ArrayList<Card> cardResult = new ArrayList<>(permutation);

                for (CardGroup andGroup : andGroups) {
                    Card[] remainingCards = new Card[cards.length - cardResult.size()];
                    int i = 0;
                    for (Card card : cards) {
                        if (!cardResult.contains(card)){
                            remainingCards[i++] = card;
                        }
                    }
                    Optional<List<Card>> newFoundCards = andGroup.find(remainingCards);
                    if (newFoundCards.isPresent()){
                        cardResult.addAll(newFoundCards.get());
                    }
                    else{
                        passes = false;
                        break;
                    }
                }

                // All andGroups matched

                return Optional.of(cardResult);

            }

            // Try orGroups
            for (CardGroup orGroup : orGroups) {
                Optional<List<Card>> orCards = orGroup.find(cards);
                if (orCards.isPresent()){
                    return orCards;
                }
            }

            while (true) {
                // Increase current index
                indices[currentIndex]++;
                // If index too big, set itself and everything right of it to 0 and move left
                if (indices[currentIndex] >= viableCards.get(currentIndex).size()) {
                    for (int j = currentIndex; j < indices.length; j++) {
                        indices[j] = 0;
                    }
                    currentIndex--;
                } else {
                    // If index is allowed, move as far right as possible and process next
                    // combination
                    while (currentIndex < indices.length - 1) {
                        currentIndex++;
                    }
                    break;
                }
                // If we cannot move left anymore, we're finished
                if (currentIndex == -1) {
                    break outerProcess;
                }
            }
        }

        return Optional.empty();
    }

    private void enforceSelector(ArrayList<ArrayList<Card>> viableCards, CardSelector selector) {
        enforceSelector(viableCards, selector, selector);
    }

    private void enforceSelector(ArrayList<ArrayList<Card>> viableCards, CardSelector selector, CardSelector deleteSelector){
        boolean viable = true;
        ArrayList<Card> cardsUsed = new ArrayList<>();

        boolean[] columnsScanned = new boolean[viableCards.size()];
        viableCards.stream().min(Comparator.comparingInt(ArrayList::size));
        for (ArrayList<Card> column : viableCards) {
            Optional<Card> first = column.stream().filter(x -> selector.check(x) && !cardsUsed.contains(x)).findFirst();
            if (first.isPresent()) {
                cardsUsed.add(first.get());
            }
            else {
                viable = false;
                break;
            }
        }

        if (!viable){
            // Remove all cards that match the selector
            for (ArrayList<Card> column : viableCards) {
                column.removeIf(deleteSelector::check);
            }
        }
    }

}
