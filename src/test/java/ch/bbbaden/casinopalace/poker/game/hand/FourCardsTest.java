package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class FourCardsTest {

    @Test
    public void parseFour() {
        assertTrue(new FourCards().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Four, CardSuit.Diamonds),
                new Card(CardRank.Three, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseFourWildcard() {
        assertTrue(new FourCards().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Four, CardSuit.Diamonds),
                new Card(CardRank.Three, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseFourIncorrect() {
        assertFalse(new FourCards().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Three, CardSuit.Hearts)
        ).isPresent());
    }
}