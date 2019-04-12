/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import ch.bbbaden.casinopalace.roulette.menu.Roulette_MenuController;
import static java.awt.Color.red;
import java.awt.Paint;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
    @FXML
    private Button btn1;
    @FXML
    private Label lb2and3and5and6;
    @FXML
    private Label lb2and3;
    @FXML
    private Label lb2and5;
    @FXML
    private Label lb2and5and1and4;
    @FXML
    private Label lb1and2;
    @FXML
    private Label lb1and4;
    @FXML
    private Label lb6and9;
    @FXML
    private Label lb6and9and5and8;
    @FXML
    private Label lb6and5;
    @FXML
    private Label lb5and8;
    @FXML
    private Label lb5and8and4and7;
    @FXML
    private Label lb5and4;
    @FXML
    private Label lb4and7;
    @FXML
    private Label lb9and12;
    @FXML
    private Label lb9and12and8and11;
    @FXML
    private Label lb9and8;
    @FXML
    private Label lb8and11;
    @FXML
    private Label lb8and11and7and10;
    @FXML
    private Label lb8and7;
    @FXML
    private Label lb7and10;
    @FXML
    private Label lb12and15;
    @FXML
    private Label lb12and15and11and14;
    @FXML
    private Label lb12and11;
    @FXML
    private Label lb11and14;
    @FXML
    private Label lb11and14and10and13;
    @FXML
    private Label lb11and10;
    @FXML
    private Label lb10and13;
    @FXML
    private Label lb15and18;
    @FXML
    private Label lb15and18and14and17;
    @FXML
    private Label lb15and14;
    @FXML
    private Label lb14and17;
    @FXML
    private Label lb14and17and13and16;
    @FXML
    private Label lb14and13;
    @FXML
    private Label lb13and16;
    @FXML
    private Label lb18and21;
    @FXML
    private Label lb18and21and17and20;
    @FXML
    private Label lb18and17;
    @FXML
    private Label lb17and20;
    @FXML
    private Label lb17and20and16and19;
    @FXML
    private Label lb17and16;
    @FXML
    private Label lb16and19;
    @FXML
    private Label lb21and24;
    @FXML
    private Label lb21and24and20and23;
    @FXML
    private Label lb21and20;
    @FXML
    private Label lb20and23;
    @FXML
    private Label lb20and23and19and22;
    @FXML
    private Label lb20and19;
    @FXML
    private Label lb19and22;
    @FXML
    private Label lb24and27;
    @FXML
    private Label lb24and27and23and26;
    @FXML
    private Label lb24and23;
    @FXML
    private Label lb23and26;
    @FXML
    private Label lb23and26and22and25;
    @FXML
    private Label lb23and22;
    @FXML
    private Label lb22and25;
    @FXML
    private Label lb27and30;
    @FXML
    private Label lb27and30and26and29;
    @FXML
    private Label lb27and26;
    @FXML
    private Label lb26and29;
    @FXML
    private Label lb26and29and25and28;
    @FXML
    private Label lb26and25;
    @FXML
    private Label lb25and28;
    @FXML
    private Label lb30and33;
    @FXML
    private Label lb30and33and29and32;
    @FXML
    private Label lb30and29;
    @FXML
    private Label lb29and32;
    @FXML
    private Label lb29and32and28and31;
    @FXML
    private Label lb29and28;
    @FXML
    private Label lb28and31;
    @FXML
    private Label lb33and36;
    @FXML
    private Label lb33and36and32and35;
    @FXML
    private Label lb33and32;
    @FXML
    private Label lb32and35;
    @FXML
    private Label lb32and35and31and34;
    @FXML
    private Label lb32and31;
    @FXML
    private Label lb31and34;
    @FXML
    private Label lb36and35;
    @FXML
    private Label lb35and34;
    @FXML
    private Label shownum;

    private ArrayList<Field> table = new ArrayList<>();

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

        //Hashmap
        /*
        HashMap<String, Field> table = new HashMap<String, Field>();
        table.put("00",new Field("00","green"));
        table.put("0",new Field("0","green"));
        table.put("0",new Field("0","green"));*/
        table.add(new Field("00", "green"));
        for (int i = 0; i < 37; i++) {
            if (i % 2 == 0) {
                table.add(new Field("" + i, "black"));
            } else {
                table.add(new Field("" + i, "red"));
            }
        }

        for (int i = 0; i < table.size(); i++) {
            System.out.println(table.get(i).getText() + " " + table.get(i).getColour());
        }

    }

    @FXML
    private void clickSpin(ActionEvent event) throws InterruptedException {
        //Rotate Roulette
        RotateTransition imageRotate = new RotateTransition(seconds(1), rouletteimage);
        imageRotate.setByAngle(360);
        imageRotate.play();
        //show the Random Number
        showNumber();
    }

    private void showNumber() throws InterruptedException {
        Random random = new Random();
        int num = random.nextInt(table.size());
        String outputtext = table.get(num).getText();
        String outputcolour = table.get(num).getColour();
        shownum.setVisible(true);
        shownum.setText(outputtext);
        shownum.setStyle("-fx-background-color: " + outputcolour + ";");
    }

}
