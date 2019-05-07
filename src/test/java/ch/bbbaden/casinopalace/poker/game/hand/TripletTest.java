package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripletTest {

    @Test
    public void parse() {
        assertTrue(new Triplet().parse(
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Three, CardSuit.Spades),
                new Card(CardRank.Eight, CardSuit.Diamonds),
                new Card(CardRank.King, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseWildcard() {
        assertTrue(new Triplet().parse(
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Two, CardSuit.Spades),
                new Card(CardRank.Eight, CardSuit.Diamonds),
                new Card(CardRank.King, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseTwoWildcards() {
        assertTrue(new Triplet().parse(
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Clubs),
                new Card(CardRank.Two, CardSuit.Spades),
                new Card(CardRank.Eight, CardSuit.Diamonds),
                new Card(CardRank.King, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseIncorrect() {
        assertFalse(new Triplet().parse(
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Nine, CardSuit.Clubs),
                new Card(CardRank.Three, CardSuit.Spades),
                new Card(CardRank.Eight, CardSuit.Diamonds),
                new Card(CardRank.King, CardSuit.Hearts)
        ).isPresent());
    }
}