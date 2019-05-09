package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.common.StateManager;
import ch.bbbaden.casinopalace.view.CasinoController;

import java.net.URL;

public class CasinoState extends State {
    @Override
    public URL getURL() {
        return CasinoController.class.getResource("Casino.fxml");
    }

    @Override
    public void handleLogin(StateManager stateManager) throws Exception {
        stateManager.transition(new LoginState());
    }

    @Override
    public void handlePoker(StateManager stateManager) throws Exception {
        stateManager.transition(new PokerState());
    }

    @Override
    public void handleYatzy(StateManager stateManager) throws Exception {
        stateManager.transition(new YatzyState());
    }

    @Override
    public void handleBlackjack(StateManager stateManager) throws Exception {
        stateManager.transition(new BlackjackState());
    }

    @Override
    public void handleRoulette(StateManager stateManager) throws Exception {
        stateManager.transition(new RouletteState());
    }

    @Override
    public void handleStatistik(StateManager stateManager) throws Exception {
        stateManager.transition(new StatistikState());
    }
}
