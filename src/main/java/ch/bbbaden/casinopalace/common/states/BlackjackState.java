package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.blackjack.BlackjackController;

import java.net.URL;

public class BlackjackState extends GameState {
    @Override
    public URL getURL() {
        return BlackjackController.class.getResource("Blackjack.fxml");
    }
}
