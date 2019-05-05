package ch.bbbaden.casinopalace.poker;

public abstract class AbstractState<T> {
    public void handleEnter(StateMachine<T, AbstractState<T>> machine){}
}
