/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.menu;

import ch.bbbaden.casinopalace.roulette.game.Datenbank;
import ch.bbbaden.casinopalace.roulette.game.RouletteGameController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class Roulette_MenuController implements Initializable {

    private Thread thread;
    @FXML
    private ImageView rouletteimage;
    @FXML
    private Button button;
    @FXML
    private ImageView titleimage;
    @FXML
    private Label kontostand;
    private final Datenbank db = new Datenbank();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Images
        Image roulette = new Image("/images/roulette_1.png");
        rouletteimage.setImage(roulette);
        Image title = new Image("/images/roulette.png");
        titleimage.setImage(title);
        //Rotate Image
        thread = new Thread() {
            public void run() {
                do {
                    rouletteimage.setRotate(rouletteimage.getRotate() + 1);
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Roulette_MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } while (true);

            }
        };
        thread.setDaemon(true);
        thread.start();
        kontostand.setText(""+db.getKonto());
    }

    @FXML
    private void clickStart(ActionEvent event) throws IOException {
        //Thread stop
        thread.stop();
        //Start Roulette
        Parent root = FXMLLoader.load(RouletteGameController.class.getResource("RouletteGame.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Roulette");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //Schliessen von Fenster
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
