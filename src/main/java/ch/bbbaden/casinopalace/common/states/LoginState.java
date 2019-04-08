package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.view.LoginController;

import java.net.URL;

public class LoginState extends State {
    @Override
    public URL getURL() {
        return LoginController.class.getResource("Login.fxml");
    }
}
