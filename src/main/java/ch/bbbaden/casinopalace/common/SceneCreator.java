package ch.bbbaden.casinopalace.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneCreator {
    private Stage currentStage = null;

    public Controller createScene(URL url) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Casino-Palace");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        currentStage = stage;
        return (Controller) loader.getController();
    }

    public Stage getCurrentStage() {
        return currentStage;
    }
}
