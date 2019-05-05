package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import org.junit.Test;

import static org.junit.Assert.*;

public class FullHouseTest {

    @Test
    public void parse() {
        assertTrue(new FullHouse().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Three, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseReverse() {
        assertTrue(new FullHouse().parse(
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades)
        ).isPresent());
    }

    @Test
    public void parseUnordered() {
        assertTrue(new FullHouse().parse(
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Four, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseWildcard() {
        assertTrue(new FullHouse().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Three, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseIncorrect() {
        assertFalse(new FullHouse().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Six, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Three, CardSuit.Hearts)
        ).isPresent());
    }
}