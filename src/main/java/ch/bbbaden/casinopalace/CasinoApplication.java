package ch.bbbaden.casinopalace;

import ch.bbbaden.casinopalace.common.*;
import ch.bbbaden.casinopalace.common.states.LoginState;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CasinoApplication extends Application {
    public void start(Stage primaryStage) throws Exception {
        try {
            StateManager manager = new StateManager(new Casino(new UserFactory(), new DatabaseStorage("remotemysql.com:3306", "gAbRqMsvI0", "gAbRqMsvI0", "vCy9DcZ3AY")), new SceneCreator());
            manager.transition(new LoginState());
        } catch (Exception e) {
            // Notify user (critical error)
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            new Alert(Alert.AlertType.ERROR, "Ein schwerwiegender Fehler wurde ausgelöst und das Programm muss beendet werden.\n" +
                    "Bitte melden Sie den Fehler unter https://github.com/Alainx277/Casino-Palace/issues/new \n\n" +
                    e.toString(), ButtonType.CLOSE).showAndWait();
            System.exit(1);
        }
    }
}
