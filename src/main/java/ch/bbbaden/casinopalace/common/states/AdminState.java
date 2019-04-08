package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.common.StateManager;
import ch.bbbaden.casinopalace.view.AdminController;

import java.net.URL;

public class AdminState extends State{
    @Override
    public URL getURL() {
        return AdminController.class.getResource("Admin.fxml");
    }

    @Override
    public void handleLogin(StateManager stateManager) throws Exception {
        stateManager.transition(new LoginState());
    }
}
