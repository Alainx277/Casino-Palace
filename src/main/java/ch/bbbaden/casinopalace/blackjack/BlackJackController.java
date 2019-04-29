/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.view.CasinoController;
import ch.bbbaden.casinopalace.view.LoginController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author doemu
 */
public class BlackJackController extends Controller implements Initializable {

    @FXML
    private ImageView chip1;
    @FXML
    private ImageView chip10;
    @FXML
    private ImageView chip50;
    @FXML
    private ImageView chip100;
    @FXML
    private ImageView chip250;
    @FXML
    private ImageView chip500;
    @FXML
    private ImageView imgViewBack;
    @FXML
    private HBox croupier;
        @FXML
    private HBox hand1;
    @FXML
    private HBox hand2;

    
    private int betAmount;
    private Image bet = new Image("/images/chips.png");

    private static Stage ubersichtsStage;
    
    private BlackJack bj;

    @FXML
    private ImageView imgStacks;
    @FXML
    private AnchorPane ap;
    @FXML
    private ImageView betImgView;
    @FXML
    private Label betLbl;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bj = new BlackJack(hand1, hand2, croupier);
        ap.getStylesheets().add(CasinoController.class.getResource("Common.css").toExternalForm());
        addImages();
    }

    @FXML
    private void handleInsurrance(ActionEvent event) {
        bj.requestState().handleInsurance(bj);
    }

    @FXML
    private void handleSplit(ActionEvent event) {
        bj.requestState().handleSplit(bj);
    }

    @FXML
    private void handleDouble(ActionEvent event) {
        bj.requestState().handleDouble(bj);
    }

    @FXML
    private void handleStand(ActionEvent event) {
        bj.requestState().handleStand(bj);
    }

    @FXML
    private void handleHit(ActionEvent event) {
        
        System.out.println(bj.requestState());
        bj.setBet(betAmount);
        bj.requestState().handleHit(bj);

    }

    private void addImages() {
        chip1.setImage(new Image("/images/chips/Chip1.png"));
        chip10.setImage(new Image("/images/chips/Chip10.png"));
        chip50.setImage(new Image("/images/chips/Chip50.png"));
        chip100.setImage(new Image("/images/chips/Chip100.png"));
        chip250.setImage(new Image("/images/chips/Chip250.png"));
        chip500.setImage(new Image("/images/chips/Chip500.png"));
        imgViewBack.setImage(new Image("/images/back.png"));
        imgStacks.setImage(new Image("/images/cards/background.png"));
    }

    @FXML
    private void handleBack(MouseEvent event) {
        Stage currentStage = (Stage) chip1.getScene().getWindow();
        currentStage.close();
        ubersichtsStage.show();

    }

    public static void fillBack(Stage stage) {
        ubersichtsStage = stage;
    }

    @FXML
    private void handleOne(MouseEvent event) {
        betAmount++;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }

    @FXML
    private void handleTen(MouseEvent event) {
        betAmount += 10;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }

    @FXML
    private void handleFifty(MouseEvent event) {
        betAmount += 50;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }

    @FXML
    private void handleHundred(MouseEvent event) {
        betAmount += 100;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }

    @FXML
    private void handleTwoFifty(MouseEvent event) {
        betAmount += 250;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }

    @FXML
    private void handleFivehundred(MouseEvent event) {
        betAmount += 500;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }

}
