package ch.bbbaden.casinopalace.poker.game;

import ch.bbbaden.casinopalace.poker.StateMachine;
import ch.bbbaden.casinopalace.poker.game.hand.Hand;

import java.util.ArrayList;
import java.util.Optional;

public class DrawnState extends PokerState {

    @Override
    public boolean canHold(StateMachine<Poker, PokerState> machine) {
        return true;
    }

    @Override
    public void handleHold(StateMachine<Poker, PokerState> machine, Card[] heldCards) {
        Poker poker = machine.getOwner();

        // Switch out all cards that aren't held
        ArrayList<Card> cards = poker.getCards();
        for (int i = 0; i < cards.size(); i++) {
            boolean hold = false;
            for (Card heldCard : heldCards) {
                if (heldCard.equals(cards.get(i))){
                    hold = true;
                    break;
                }
            }

            if (!hold){
                cards.set(i, poker.getStack().pop());
            }
        }

        // Get current hand held
        Optional<Hand> hand = poker.getCurrentHand();
        // Set status
        poker.setWon(hand.isPresent());

        machine.transition(new EndState());
    }
}
