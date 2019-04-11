/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        
    }    
    
}
