package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.common.StateManager;

public abstract class GameState extends State {
    @Override
    public void handleCasino(StateManager stateManager) throws Exception {
        stateManager.transition(new CasinoState());
    }
}
