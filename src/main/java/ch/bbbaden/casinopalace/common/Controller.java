package ch.bbbaden.casinopalace.common;

public abstract class Controller {
    private StateManager stateManager;

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }
}
