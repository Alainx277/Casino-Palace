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
import javafx.scene.control.Button;
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
    @FXML
    private ImageView fiftychipimage;
    @FXML
    private ImageView hundredchipimage;
    @FXML
    private ImageView twohundredchipimage;
    @FXML
    private ImageView fivehundredchipimage;
    @FXML
    private GridPane roultable;
    @FXML
    private Button btn3;
    @FXML
    private Button btn6;
    @FXML
    private Button btn9;
    @FXML
    private Button btn12;
    @FXML
    private Button btn15;
    @FXML
    private Button btn18;
    @FXML
    private Button btn21;
    @FXML
    private Button btn24;
    @FXML
    private Button btn27;
    @FXML
    private Button btn30;
    @FXML
    private Button btn33;
    @FXML
    private Button btn36;
    @FXML
    private Button btw1row;
    @FXML
    private Button btn2;
    @FXML
    private Button btn5;
    @FXML
    private Button btn8;
    @FXML
    private Button btn11;
    @FXML
    private Button btn14;
    @FXML
    private Button btn17;
    @FXML
    private Button btn20;
    @FXML
    private Button btn23;
    @FXML
    private Button btn26;
    @FXML
    private Button btn29;
    @FXML
    private Button btn32;
    @FXML
    private Button btn35;
    @FXML
    private Button btn2row;
    @FXML
    private Button btn4;
    @FXML
    private Button btn7;
    @FXML
    private Button btn10;
    @FXML
    private Button btn13;
    @FXML
    private Button btn16;
    @FXML
    private Button btn19;
    @FXML
    private Button btn22;
    @FXML
    private Button btn25;
    @FXML
    private Button btn28;
    @FXML
    private Button btn31;
    @FXML
    private Button btn3row;
    @FXML
    private Button btn34;
    @FXML
    private Label lb3and6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Images
        
        //Icons
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
        //Chip1
        Image onechip = new Image("/images/chips/chip1.png");
        onechipimage.setImage(onechip);
        //Chip10
        Image tenchip = new Image("/images/chips/chip10.png");
        tenchipimage.setImage(tenchip);
        //Chip50
        Image fiftychip = new Image("/images/chips/chip50.png");
        fiftychipimage.setImage(fiftychip);
        //Chip100
        Image hundredchip = new Image("/images/chips/chip100.png");
        hundredchipimage.setImage(hundredchip);
        //Chip250
        Image twohundredchip = new Image("/images/chips/chip250.png");
        twohundredchipimage.setImage(twohundredchip);
        //Chip500
        Image fivehundredchip = new Image("/images/chips/chip500.png");
        fivehundredchipimage.setImage(fivehundredchip);
        
        //GridPane
        /*
        HashMap<String, Field> table = new HashMap<String, Field>();
        table.put("0", new Field("0", "green"));
        table.put("00", new Field("00", "green"));*/
        roultable.add(new Label(), 0, 1);
        roultable.add(new Label(), 1, 0);
        roultable.add(new Label(), 1, 1);
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
