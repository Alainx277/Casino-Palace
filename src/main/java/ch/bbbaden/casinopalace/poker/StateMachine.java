package ch.bbbaden.casinopalace.poker;

import ch.bbbaden.casinopalace.poker.game.DrawnState;

import java.util.ArrayList;

public class StateMachine<T, R> {
    private final T owner;
    private R state;
    private ArrayList<EventHandler<TransitionEvent<T, R>>> transitionEventHandlers = new ArrayList<>();

    public StateMachine(T owner) {
        this.owner = owner;
    }

    public void transition(R state){
        R oldState = this.state;
        this.state = state;
        transitionEventHandlers.forEach(x -> x.handle(new TransitionEvent<>(this, oldState, state)));
    }

    public R getState() {
        return state;
    }

    public T getOwner() {
        return owner;
    }

    public void addTransitionListener(EventHandler<TransitionEvent<T, R>> handler){
        transitionEventHandlers.add(handler);
    }

    public boolean removeTransitionListener(Object o) {
        return transitionEventHandlers.remove(o);
    }
}
