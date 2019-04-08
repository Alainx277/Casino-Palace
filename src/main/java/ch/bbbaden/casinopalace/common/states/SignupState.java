package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.common.StateManager;
import ch.bbbaden.casinopalace.view.SignupController;

import java.net.URL;

public class SignupState extends State {
    @Override
    public URL getURL() {
        return SignupController.class.getResource("Signup.fxml");
    }

    @Override
    public void handleLogin(StateManager stateManager) throws Exception {
        stateManager.transition(new LoginState());
    }
}
