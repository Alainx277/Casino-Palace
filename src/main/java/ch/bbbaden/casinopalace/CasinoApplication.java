package ch.bbbaden.casinopalace;

import ch.bbbaden.casinopalace.common.*;
import ch.bbbaden.casinopalace.common.states.LoginState;
import ch.bbbaden.casinopalace.common.states.PokerState;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CasinoApplication extends Application {
    public void start(Stage primaryStage) throws Exception {
        try {
            MockStorage storage = new MockStorage();
            UserFactory userFactory = new UserFactory();
            StateManager manager = new StateManager(new Casino(userFactory, storage), new SceneCreator());
            manager.getCasino().setCurrentUser(userFactory.createUser("Bruno", "mars"));
            manager.transition(new PokerState());
        } catch (Exception e) {
            // TODO: Notify user (critical error)
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            System.exit(1);
        }
    }
}
