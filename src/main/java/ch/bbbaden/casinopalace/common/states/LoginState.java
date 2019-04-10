package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.common.StateManager;
import ch.bbbaden.casinopalace.view.LoginController;

import java.net.URL;

public class LoginState extends State {
    @Override
    public URL getURL() {
        return LoginController.class.getResource("Login.fxml");
    }

    @Override
    public void handleSignUp(StateManager stateManager) throws Exception {
        stateManager.transition(new SignupState());
    }

    @Override
    public void handleCasino(StateManager stateManager) throws Exception {
        stateManager.transition(new CasinoState());
    }
    
    
}
