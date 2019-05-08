package ch.bbbaden.casinopalace.poker.game;

import ch.bbbaden.casinopalace.poker.AbstractState;
import ch.bbbaden.casinopalace.poker.StateMachine;

import java.util.Random;

public class HeldState extends PokerState {
    @Override
    public boolean canGamble(StateMachine<Poker, PokerState> machine) {
        return machine.getOwner().isWon();
    }

    @Override
    public Card handleGamble(StateMachine<Poker, PokerState> machine) {
        // Generate new cards and flip one
        Poker poker = machine.getOwner();
        poker.getCards().clear();
        for (int i = 0; i < poker.getNumberOfCards(); i++) {
            poker.getCards().add(poker.getStack().pop());
        }

        Card flippedCard = poker.getCards().get(new Random().nextInt(poker.getNumberOfCards()));
        machine.transition(new GambleState(flippedCard));
        return flippedCard;
    }

    @Override
    public boolean canEnd(StateMachine<Poker, PokerState> machine) {
        return true;
    }

    @Override
    public void handleEnd(StateMachine<Poker, PokerState> machine) {
        machine.transition(new EndState());
    }
}
