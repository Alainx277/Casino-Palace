package ch.bbbaden.casinopalace.poker.game;

import ch.bbbaden.casinopalace.poker.StateMachine;

public class EndState extends PokerState {
    @Override
    public boolean isEnd(StateMachine<Poker, PokerState> machine) {
        return true;
    }
}
