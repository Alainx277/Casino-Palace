package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import org.junit.Test;

import static org.junit.Assert.*;

public class FourDeucesTest {

    @Test
    public void parse() {
        assertTrue(new FourDeuces().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Four, CardSuit.Diamonds),
                new Card(CardRank.Nine, CardSuit.Hearts)
        ).isPresent());
    }

    @Test
    public void parseWithWildcardIncorrect() {
        assertFalse(new FourDeuces().parse(
                new Card(CardRank.Four, CardSuit.Hearts),
                new Card(CardRank.Four, CardSuit.Clubs),
                new Card(CardRank.Four, CardSuit.Spades),
                new Card(CardRank.Two, CardSuit.Diamonds),
                new Card(CardRank.Nine, CardSuit.Hearts)
        ).isPresent());
    }
}