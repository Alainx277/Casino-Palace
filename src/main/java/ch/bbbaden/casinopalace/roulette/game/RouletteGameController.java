/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import ch.bbbaden.casinopalace.roulette.menu.Roulette_MenuController;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import static javafx.util.Duration.seconds;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RouletteGameController implements Initializable {

    @FXML
    private ImageView handimage;
    @FXML
    private ImageView dollarimage;
    @FXML
    private ImageView nodollarimage;
    @FXML
    private ImageView kontoimage;
    @FXML
    private ImageView totaldollarimage;
    @FXML
    private ImageView totalnodollarimage;
    @FXML
    private Label einsatz;
    @FXML
    private Label gewinn;
    @FXML
    private Label verlust;
    @FXML
    private Label kontobestand;
    @FXML
    private Label totalgewinn;
    @FXML
    private Label totalverlust;
    @FXML
    private ImageView rouletteimage;
    @FXML
    private ImageView onechipimage;
    @FXML
    private ImageView tenchipimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Images
        //Hand
        Image hand = new Image("/images/icons/hand.png");
        handimage.setImage(hand);
        //Dollar
        Image dollar = new Image("/images/icons/dollar.png");
        dollarimage.setImage(dollar);
        //NoDollar
        Image nodollar = new Image("/images/icons/nodollar.png");
        nodollarimage.setImage(nodollar);
        //Konto
        Image konto = new Image("/images/icons/konto.png");
        kontoimage.setImage(konto);
        //Total Dollar
        Image totaldollar = new Image("/images/icons/totaldollar.png");
        totaldollarimage.setImage(totaldollar);
        //Total NoDollar
        Image totalnodollar = new Image("/images/icons/totalnodollar.png");
        totalnodollarimage.setImage(totalnodollar);
        //Roulette
        Image roulette = new Image("/images/roulette_2.png");
        rouletteimage.setImage(roulette);
        //Chips
        Image onechip = new Image("/images/roulette_2.png");
        onechipimage.setImage(onechip);
        //GridPane
        HashMap<String, Field> table = new HashMap<String, Field>();
        table.put("0", new Field("0", "green"));
        table.put("00", new Field("00", "green"));

    }

    @FXML
    private void clickSpin(ActionEvent event) throws InterruptedException {
        RotateTransition imageRotate = new RotateTransition(seconds(1), rouletteimage);
        imageRotate.setByAngle(360);
        imageRotate.play();/*
        for (int i = 0; i < 100; i++) {
            rouletteimage.setRotate(rouletteimage.getRotate() + 90);
            Thread.sleep(100);
        }/*
        do {
            rouletteimage.setRotate(rouletteimage.getRotate() + 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Roulette_MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);*/
    }

}
