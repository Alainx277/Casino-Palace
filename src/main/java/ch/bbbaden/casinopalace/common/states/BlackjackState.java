package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.blackjack.BlackjackUbersichtsController;

import java.net.URL;

public class BlackjackState extends GameState {
    @Override
    public URL getURL() {
        return BlackjackUbersichtsController.class.getResource("BlackjackUbersichtsseite.fxml");
    }
}
