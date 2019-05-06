package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiveCardsTest {

    @Test
    public void parseFourOfThreeAndWildcard() {
        assertTrue(new FiveCards().parse(new Card[]{
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Three, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Two, CardSuit.Diamonds)
        }).isPresent());
    }

    @Test
    public void parseThreeOfThreeAndTwoWildcards() {
        assertTrue(new FiveCards().parse(new Card[]{
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Two, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Two, CardSuit.Diamonds)
        }).isPresent());
    }

    @Test
    public void parseFourOfThreeAndIncorrect() {
        assertFalse(new FiveCards().parse(new Card[]{
                new Card(CardRank.Three, CardSuit.Hearts),
                new Card(CardRank.Three, CardSuit.Clubs),
                new Card(CardRank.Three, CardSuit.Spades),
                new Card(CardRank.Three, CardSuit.Diamonds),
                new Card(CardRank.Four, CardSuit.Diamonds)
        }).isPresent());
    }
}