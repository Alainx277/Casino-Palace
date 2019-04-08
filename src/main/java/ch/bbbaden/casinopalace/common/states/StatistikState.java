package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.common.StateManager;
import ch.bbbaden.casinopalace.view.StatistikController;

import java.net.URL;

public class StatistikState extends State {
    @Override
    public URL getURL() {
        return StatistikController.class.getResource("Statistik.fxml");
    }

    @Override
    public void handleLogin(StateManager stateManager) throws Exception {
        stateManager.transition(new LoginState());
    }
}
