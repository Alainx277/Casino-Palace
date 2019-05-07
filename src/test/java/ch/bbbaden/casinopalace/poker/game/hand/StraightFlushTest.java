package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import org.junit.Test;

import static org.junit.Assert.*;

public class StraightFlushTest {

    @Test
    public void parseFromKing() {
        assertTrue(new StraightFlush().parse(
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts),
                new Card(CardRank.Ten, CardSuit.Hearts),
                new Card(CardRank.Nine, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseFromTen() {
        assertTrue(new StraightFlush().parse(
                new Card(CardRank.Ten, CardSuit.Hearts),
                new Card(CardRank.Nine, CardSuit.Hearts),
                new Card(CardRank.Eight, CardSuit.Hearts),
                new Card(CardRank.Seven, CardSuit.Hearts),
                new Card(CardRank.Six, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseIncorrectFromKing() {
        assertFalse(new StraightFlush().parse(
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts),
                new Card(CardRank.Ten, CardSuit.Hearts),
                new Card(CardRank.Eight, CardSuit.Hearts)
        ).isPresent());
    }
}