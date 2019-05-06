package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlushTest {

    @Test
    public void parse() {
        assertTrue(new Flush().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Eight, CardSuit.Hearts),
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseIncorrect() {
        assertFalse(new Flush().parse(
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Eight, CardSuit.Hearts),
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts)
        ).isPresent());
    }
}