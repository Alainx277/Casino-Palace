/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import ch.bbbaden.casinopalace.roulette.menu.Roulette_MenuController;
import static java.awt.Color.red;
import java.awt.MouseInfo;
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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import static javafx.util.Duration.seconds;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RouletteGameController implements Initializable {

    //My Attributes
    Roulette roulette = new Roulette();
    Datenbank db = new Datenbank();
    BetMoney bm = new BetMoney();
    Chip chip = new Chip();
    Field fl = new Field();

    // <editor-fold defaultstate="collapsed" desc=" Stats ">
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
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Table Buttons ">
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
    private Button btn1;
    @FXML
    private Button btn0;
    @FXML
    private Button btn00;
    @FXML
    private Button btn1st12;
    @FXML
    private Button btn2nd12;
    @FXML
    private Button btn3rd12;
    @FXML
    private Button btnungerade;
    @FXML
    private Button btngerade;
    @FXML
    private Button btnblack;
    @FXML
    private Button btnred;
    @FXML
    private Button btn1to18;
    @FXML
    private Button btn19to36;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Table Labels ">
    @FXML
    private Label lb3and6;
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
    private Label lbmiddle3;
    @FXML
    private Label lbcorner2;
    @FXML
    private Label lbmiddle2;
    @FXML
    private Label lbcorner1;
    @FXML
    private Label lbmiddle1;
    @FXML
    private Label lbcornerdown1;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Buttons Chips ">

    @FXML
    private Button chip1;
    @FXML
    private Button chip10;
    @FXML
    private Button chip50;
    @FXML
    private Button chip100;
    @FXML
    private Button chip250;
    @FXML
    private Button chip500;
    // </editor-fold>
    @FXML
    private Label shownum;
    @FXML
    private GridPane cornerpane;
    @FXML
    private ImageView rouletteimage;
    @FXML
    private GridPane roultable;
    @FXML
    private Button btnspin;
    @FXML
    private Label resultlb;
    private Label chiplb;
    @FXML
    private AnchorPane anchorpane;

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
        Image roulettei = new Image("/images/roulette_2.png");
        rouletteimage.setImage(roulettei);

        //Chips
        roulette.createTable();
        //Konto angeben
        kontobestand.setText("" + db.getKonto());
    }

    @FXML
    private void clickSpin(ActionEvent event) throws InterruptedException {
        //Rotate Roulette
        RotateTransition imageRotate = new RotateTransition(seconds(1), rouletteimage);
        imageRotate.setByAngle(360);
        imageRotate.play();
        //show the Random Number
        shownum.setVisible(true);
        roulette.drawNumber();
        shownum.setText(roulette.getNumberDrawnAsText());
        shownum.setStyle("-fx-background-color: " + roulette.getNumberDrawnAsColour() + ";");
    }

// <editor-fold defaultstate="collapsed" desc=" Click Fields ">
// <editor-fold defaultstate="collapsed" desc=" Click Row 3 ">
    @FXML
    private void clickField3(ActionEvent event) {

    }

    @FXML
    private void clickField6(ActionEvent event) {
    }

    @FXML
    private void clickField9(ActionEvent event) {
    }

    @FXML
    private void clickField12(ActionEvent event) {
    }

    @FXML
    private void clickField15(ActionEvent event) {
    }

    @FXML
    private void clickField18(ActionEvent event) {
    }

    @FXML
    private void clickField21(ActionEvent event) {
    }

    @FXML
    private void clickField24(ActionEvent event) {
    }

    @FXML
    private void clickField27(ActionEvent event) {
    }

    @FXML
    private void clickField30(ActionEvent event) {
    }

    @FXML
    private void clickField33(ActionEvent event) {
    }

    @FXML
    private void clickField36(ActionEvent event) {
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click Row 2 ">

    @FXML
    private void clickField2(ActionEvent event) {

    }

    @FXML
    private void clickField5(ActionEvent event) {

    }

    @FXML
    private void clickField8(ActionEvent event) {

    }

    @FXML
    private void clickField11(ActionEvent event) {

    }

    @FXML
    private void clickField14(ActionEvent event) {

    }

    @FXML
    private void clickField17(ActionEvent event) {

    }

    @FXML
    private void clickField20(ActionEvent event) {

    }

    @FXML
    private void clickField23(ActionEvent event) {

    }

    @FXML
    private void clickField26(ActionEvent event) {

    }

    @FXML
    private void clickField29(ActionEvent event) {

    }

    @FXML
    private void clickField32(ActionEvent event) {

    }

    @FXML
    private void clickField35(ActionEvent event) {

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click Row 1 ">

    @FXML
    private void clickField1(ActionEvent event) {
    }

    @FXML
    private void clickField4(ActionEvent event) {
    }

    @FXML
    private void clickField7(ActionEvent event) {
    }

    @FXML
    private void clickField10(ActionEvent event) {
    }

    @FXML
    private void clickField13(ActionEvent event) {
    }

    @FXML
    private void clickField16(ActionEvent event) {
    }

    @FXML
    private void clickField19(ActionEvent event) {
    }

    @FXML
    private void clickField22(ActionEvent event) {
    }

    @FXML
    private void clickField25(ActionEvent event) {
    }

    @FXML
    private void clickField28(ActionEvent event) {
    }

    @FXML
    private void clickField31(ActionEvent event) {
    }

    @FXML
    private void clickField34(ActionEvent event) {
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click Field 0 / 00 ">

    @FXML
    private void clickField0(ActionEvent event) {
    }

    @FXML
    private void clickField00(ActionEvent event) {
    }
    // </editor-fold>
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click 1-3 Rows ">

    @FXML
    private void clickField1to34(ActionEvent event) {
    }

    @FXML
    private void clickField2to35(ActionEvent event) {
    }

    @FXML
    private void clickField3to36(ActionEvent event) {
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click 1st, 2nd, 3rd ">

    @FXML
    private void click1to12(ActionEvent event) {
    }

    @FXML
    private void click13to24(ActionEvent event) {
    }

    @FXML
    private void click25to36(ActionEvent event) {
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click Ungerade / Gerade ">

    @FXML
    private void clickUngerade(ActionEvent event) {
    }

    @FXML
    private void clickGerade(ActionEvent event) {
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click Schwarz / Rot ">
    @FXML
    private void clickSchwarz(ActionEvent event) {
    }

    @FXML
    private void clickRot(ActionEvent event) {
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click Niedrig / Hoch ">
    @FXML
    private void clickNiedrig(ActionEvent event) {
    }

    @FXML
    private void clickHoch(ActionEvent event) {
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Click Chips ">

    @FXML
    private void clickChip1(ActionEvent event) {
        //BetMoney
        bm.addChip(1);
        resultlb.setText("" + bm.getMoney());
        //Chip value
        chip.setValue(chip.getValue() + 1);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip1.png");
    }

    @FXML
    private void clickChip10(ActionEvent event) {
        bm.addChip(10);
        resultlb.setText("" + bm.getMoney());
        chip.setValue(chip.getValue() + 10);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip10.png");
    }

    @FXML
    private void clickChip50(ActionEvent event) {
        bm.addChip(50);
        resultlb.setText("" + bm.getMoney());
    }

    @FXML
    private void clickChip100(ActionEvent event) {
        bm.addChip(100);
        resultlb.setText("" + bm.getMoney());
    }

    @FXML
    private void clickChip250(ActionEvent event) {
        bm.addChip(250);
        resultlb.setText("" + bm.getMoney());
    }

    @FXML
    private void clickChip500(ActionEvent event) {
        bm.addChip(500);
        resultlb.setText("" + bm.getMoney());
    }
// </editor-fold>

    @FXML
    private void clickFieldMouse(MouseEvent event) {
        roulette.setMouseX(event.getScreenX() - 230);
        roulette.setMouseY(event.getScreenY() - 80);
        chiplb = new Label();
        chiplb.setText(chip.getText());
        chiplb.setStyle("-fx-background-image : url(" + "/images/chips/chipdrop.png" + ")");
        chiplb.setAlignment(Pos.CENTER);
        chiplb.setMinSize(50, 50);
        chiplb.setLayoutX(roulette.getMouseX());
        chiplb.setLayoutY(roulette.getMouseY());
        chiplb.setMouseTransparent(true);
        anchorpane.getChildren().add(chiplb);
        //Stuck here!!!!!
            
        //Clear Chip value
        chip.clearValue();
    }
}
