/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.roulette.game.Datenbank;
import ch.bbbaden.casinopalace.roulette.game.RouletteGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RouletteController extends Controller implements Initializable {

    private Thread thread;
    @FXML
    private ImageView rouletteimage;
    @FXML
    private Button button;
    @FXML
    private ImageView titleimage;
    @FXML
    private Label kontostand;
    private Datenbank db;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new Datenbank(getStateManager().getCasino());

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
                        Logger.getLogger(RouletteController.class.getName()).log(Level.SEVERE, null, ex);
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
        getStateManager().switchStage(RouletteGameController.class.getResource("RouletteGame.fxml"));
        getStateManager().getSceneCreator().getCurrentStage().setOnCloseRequest(event1 -> {
            try {
                getStateManager().getState().handleCasino(getStateManager());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
