package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.poker.PokerController;

import java.net.URL;

public class PokerState extends GameState {
    @Override
    public URL getURL() {
        return PokerController.class.getResource("Poker.fxml");
    }
}
