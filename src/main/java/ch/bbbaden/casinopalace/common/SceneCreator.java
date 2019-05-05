package ch.bbbaden.casinopalace.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

import javafx.stage.StageStyle;
import javafx.util.Callback;

public class SceneCreator {
    private Stage currentStage = null;

    public Controller createScene(URL url) throws IOException {
        return createScene(url, null);
    }

    public Controller createScene(URL url, Consumer<FXMLLoader> fxmlLoaderConsumer) throws IOException {
        Stage stage = new Stage();
        currentStage = stage;
        stage.setTitle("Casino-Palace");
        FXMLLoader loader = new FXMLLoader(url);

        if (fxmlLoaderConsumer != null){
            fxmlLoaderConsumer.accept(loader);
        }

        Parent root = loader.load();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        return (Controller) loader.getController();
    }

    public Stage getCurrentStage() {
        return currentStage;
    }
}
