package ch.bbbaden.casinopalace.common.states;

import ch.bbbaden.casinopalace.yatzy.YatzyController;

import java.net.URL;

public class YatzyState extends GameState{
    @Override
    public URL getURL() {
        return YatzyController.class.getResource("Yatzy.fxml");
    }
}
