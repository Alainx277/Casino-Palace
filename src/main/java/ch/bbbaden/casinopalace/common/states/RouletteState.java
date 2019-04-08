package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.roulette.RouletteController;

import java.net.URL;

public class RouletteState extends GameState {
    @Override
    public URL getURL() {
        return RouletteController.class.getResource("Roulette.fxml");
    }
}
