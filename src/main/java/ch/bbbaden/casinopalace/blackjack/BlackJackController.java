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
import javafx.scene.control.Button;
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
    
    private int hit = 0;
    
    private BlackJack bj;
    
    @FXML
    private ImageView imgStacks;
    @FXML
    private AnchorPane ap;
    @FXML
    private ImageView betImgView;
    @FXML
    private Label betLbl;
    @FXML
    private Button insuranceBtn;
    @FXML
    private Button splitBtn;
    @FXML
    private Button doubleBtn;
    @FXML
    private Button standBtn;
    @FXML
    private Button hitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bj = new BlackJack(ap);
        ap.getStylesheets().add(CasinoController.class.getResource("Common.css").toExternalForm());
        addImages();
        disableButtons();
    }
    
    private void disableButtons() {
        insuranceBtn.setDisable(true);
        splitBtn.setDisable(true);
        doubleBtn.setDisable(true);
        standBtn.setDisable(true);
        hitBtn.setDisable(true);
    }
    
    @FXML
    private void handleInsurrance(ActionEvent event) {
        bj.setState((BJState) new Insurance());
        bj.requestState().handleInsurance(bj);
        System.out.println("Insurance");
    }
    
    @FXML
    private void handleSplit(ActionEvent event) {
        bj.setState((BJState) new Split());
        bj.requestState().handleSplit(bj); // Not supported yet
        System.out.println("Split");
        splitBtn.setDisable(true);
        bj.setState((BJState) new Hit());
    }
    
    @FXML
    private void handleDouble(ActionEvent event) {
        bj.setState((BJState) new Double());
        bj.setBet(betAmount * 2);
        betLbl.setText(Integer.toString(bj.getBet()));
        doubleBtn.setDisable(true);
        System.out.println("Double");
    }
    
    @FXML
    private void handleStand(ActionEvent event) {
        bj.setState((BJState) new Stand());
        bj.requestState().handleStand(bj);
        hitBtn.setDisable(true);
        System.out.println("Stand");
    }
    
    @FXML
    private void handleHit(ActionEvent event) {
        bj.setBet(betAmount);
        bj.requestState().handleHit(bj);
        if (bj.getWorthpointeur() >= 21) {
            for (int i = 0; i < bj.getcoP().size(); i++) {
                if (bj.getcoP().get(i).getCount() == 11) {
                    System.out.println("ACE FOUND");
                    bj.getcoP().get(i).setCount(1);
                    bj.setWorthpointeur(bj.getWorthpointeur() - 10);
                    handleHit(event);
                }
            }
            bj.setState((BJState) new Stand());
            handleStand(event);
            disableButtons();
            hitBtn.setDisable(true);
        } else {
            if (hit == 0) {
                doubleBtn.setDisable(false);
                standBtn.setDisable(false);
                if (bj.getcoP().get(0).getNumber() == (bj.getcoP().get(1).getNumber())) {
                    System.out.println("SPLIT");
                    splitBtn.setDisable(false);
                } else {
                    System.out.println("FUCK OFF");
                }
            }
            if (hit == 1) {
                insuranceBtn.setDisable(true);
                System.out.println(bj.getcoP());
                splitBtn.setDisable(true);
                System.out.println("Hit = 1");
                
            }
        }
        
        hit++;
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
        hitBtn.setDisable(false);
        betAmount++;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }
    
    @FXML
    private void handleTen(MouseEvent event) {
        hitBtn.setDisable(false);
        betAmount += 10;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }
    
    @FXML
    private void handleFifty(MouseEvent event) {
        hitBtn.setDisable(false);
        betAmount += 50;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }
    
    @FXML
    private void handleHundred(MouseEvent event) {
        hitBtn.setDisable(false);
        betAmount += 100;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }
    
    @FXML
    private void handleTwoFifty(MouseEvent event) {
        hitBtn.setDisable(false);
        betAmount += 250;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }
    
    @FXML
    private void handleFivehundred(MouseEvent event) {
        hitBtn.setDisable(false);
        betAmount += 500;
        betImgView.setImage(bet);
        betLbl.setText(Integer.toString(betAmount));
    }
    
}
