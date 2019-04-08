package ch.bbbaden.casinopalace;

import ch.bbbaden.casinopalace.common.Casino;
import ch.bbbaden.casinopalace.common.DatabaseStorage;
import ch.bbbaden.casinopalace.common.SceneCreator;
import ch.bbbaden.casinopalace.common.StateManager;
import ch.bbbaden.casinopalace.common.states.LoginState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class CasinoApplication extends Application {
    public void start(Stage primaryStage) throws Exception {
        StateManager manager = new StateManager(new Casino(new DatabaseStorage()), new SceneCreator());
        manager.transition(new LoginState());
    }
}
