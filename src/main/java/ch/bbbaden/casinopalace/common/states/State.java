package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.common.StateManager;

import java.net.URL;

public abstract class State {
    public void handleCasino(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handleLogin(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handleSignUp(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handleStatistik(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handleAdmin(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handlePoker(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handleYatzy(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handleBlackjack(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public void handleRoulette(StateManager stateManager) throws Exception{
        throw new UnsupportedOperationException();
    }

    public abstract URL getURL();
}
