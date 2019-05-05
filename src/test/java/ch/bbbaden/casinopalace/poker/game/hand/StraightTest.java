package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import org.junit.Test;

import static org.junit.Assert.*;

public class StraightTest {

    @Test
    public void parseFromTwo() {
        assertTrue(new Straight().parse(
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Five, CardSuit.Diamonds),
                new Card(CardRank.Six, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseFromTwoWildcard() {
        assertTrue(new Straight().parse(
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Seven, CardSuit.Spades),
                new Card(CardRank.Five, CardSuit.Diamonds),
                new Card(CardRank.Six, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseFromTwoIncorrect() {
        assertFalse(new Straight().parse(
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Seven, CardSuit.Diamonds),
                new Card(CardRank.Eight, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseLowAce() {
        assertTrue(new Straight().parse(
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Five, CardSuit.Diamonds)
        ).isPresent());
    }

    @Test
    public void parseLowAceIncorrect() {
        assertFalse(new Straight().parse(
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Seven, CardSuit.Clubs),
                new Card(CardRank.Five, CardSuit.Spades),
                new Card(CardRank.Six, CardSuit.Diamonds),
                new Card(CardRank.Ace, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseHighAce() {
        assertTrue(new Straight().parse(
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Clubs),
                new Card(CardRank.Jack, CardSuit.Spades),
                new Card(CardRank.Ten, CardSuit.Diamonds)
        ).isPresent());
    }

    @Test
    public void parseHighAceIncorrect() {
        assertFalse(new Straight().parse(
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Clubs),
                new Card(CardRank.Ten, CardSuit.Spades),
                new Card(CardRank.Nine, CardSuit.Diamonds)
        ).isPresent());
    }
}