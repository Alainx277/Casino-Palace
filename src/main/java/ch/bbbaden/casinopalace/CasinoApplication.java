package ch.bbbaden.casinopalace;

import ch.bbbaden.casinopalace.common.*;
import ch.bbbaden.casinopalace.common.states.LoginState;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CasinoApplication extends Application {
    public void start(Stage primaryStage) throws Exception {
        try {
            StateManager manager = new StateManager(new Casino(new UserFactory(), new DatabaseStorage("localhost:3306", "casino", "root", "")), new SceneCreator());
            manager.transition(new LoginState());
        } catch (Exception e) {
            // TODO: Notify user (critical error)
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            System.exit(1);
        }
    }
}
