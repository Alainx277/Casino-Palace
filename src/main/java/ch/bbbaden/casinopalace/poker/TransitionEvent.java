package ch.bbbaden.casinopalace.poker;

public class TransitionEvent<T, R extends AbstractState<T>> {
    private StateMachine<T, R> machine;
    private R oldState;
    private R newState;

    public TransitionEvent(StateMachine<T, R> machine, R oldState, R newState) {
        this.machine = machine;
        this.oldState = oldState;
        this.newState = newState;
    }

    public StateMachine<T, R> getMachine() {
        return machine;
    }

    public R getOldState() {
        return oldState;
    }

    public R getNewState() {
        return newState;
    }
}
