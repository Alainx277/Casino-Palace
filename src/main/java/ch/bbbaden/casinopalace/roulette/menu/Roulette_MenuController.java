/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.menu;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    }

    @FXML
    private void clickStart(ActionEvent event) {
        thread.stop();

    }
}
