package ch.bbbaden.casinopalace.poker.game;

import java.util.Objects;
import java.util.Random;

public class Card {
    private CardRank rank;
    private CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public static Card random(){
        Random random = new Random();
        CardRank[] cardRanks = CardRank.values();
        CardRank type = cardRanks[random.nextInt(cardRanks.length)];
        CardSuit[] cardSuits = CardSuit.values();
        CardSuit color = cardSuits[random.nextInt(cardSuits.length)];

        return new Card(type, color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {

        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return getRank() + " of " + getSuit();
    }

    /**
     * Returns a card in it's short string representation
     * <ul>
     *     <li>Ace of Spades -> <b>AS<b/></li>
     *     <li>Ten of Heartss -> <b>10H</b></li>
     *     <li>Five of Clubs -> <b>5C</b></li>
     * </ul>
     * @return The string in short form
     */
    public String toShortString(){
        return getRank().toShortString() + getSuit().toShortString();
    }
}
