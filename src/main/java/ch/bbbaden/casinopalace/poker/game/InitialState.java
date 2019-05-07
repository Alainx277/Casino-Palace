package ch.bbbaden.casinopalace.poker.game;

import ch.bbbaden.casinopalace.poker.AbstractState;
import ch.bbbaden.casinopalace.poker.StateMachine;

import java.util.ArrayList;

public class InitialState extends PokerState {
    @Override
    public void handleEnter(StateMachine<Poker, AbstractState<Poker>> machine) {
        super.handleEnter(machine);
        Poker owner = machine.getOwner();
        owner.getStack().fillStack();
        owner.setWon(null);
        owner.getCards().clear();
    }

    @Override
    public boolean canDraw(StateMachine<Poker, PokerState> machine) {
        return true;
    }

    @Override
    public void handleDraw(StateMachine<Poker, PokerState> machine) {
        Poker poker = machine.getOwner();
        ArrayList<Card> cards = poker.getCards();
        while (cards.size() < poker.getNumberOfCards()){
            cards.add(poker.getStack().pop());
        }

        machine.transition(new DrawnState());
    }

    @Override
    public boolean canBet(StateMachine<Poker, PokerState> machine) {
        return true;
    }
}
