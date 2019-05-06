package ch.bbbaden.casinopalace.poker.game;

import ch.bbbaden.casinopalace.poker.AbstractState;
import ch.bbbaden.casinopalace.poker.StateMachine;

public abstract class PokerState extends AbstractState<Poker> {

    public boolean canDraw(StateMachine<Poker, PokerState> machine){
        return false;
    }

    public void handleDraw(StateMachine<Poker, PokerState> machine){
    }

    public boolean canHold(StateMachine<Poker, PokerState> machine){
        return false;
    }


    public void handleHold(StateMachine<Poker, PokerState> machine, Card... heldCards){

    }

    public boolean canBet(StateMachine<Poker, PokerState> machine){
        return false;
    }
}
