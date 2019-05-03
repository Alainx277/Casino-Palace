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
    private boolean insurance = false;
    private boolean split = false;
    private int betting = betAmount;
    
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
    @FXML
    private Label insuranceBetLbl;

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
        setBetNodes();
    }
    
    private void setBetNodes() {
        insuranceBetLbl.setVisible(false);
        betImgView.setFitWidth(70);
        betImgView.setFitHeight(70);
        betImgView.setLayoutX(hand1.getLayoutX());
        betImgView.setLayoutY(betLbl.getLayoutY() -30);
        betLbl.setLayoutX(betImgView.getLayoutX() + 120);
        insuranceBetLbl.setLayoutX(betImgView.getLayoutX() - 70);
        betLbl.setVisible(false);
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
        disableButtons();
        insurance = true;
        System.out.println("Insurance");
    }
    
    @FXML
    private void handleSplit(ActionEvent event) {
        bj.setState((BJState) new Split());
        bj.requestState().handleSplit(bj);
        System.out.println("Split");
        splitBtn.setDisable(true);
        
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
        if (insurance) {
            bj.setState((BJState) new Insurance());
            bj.requestState().handleInsurance(bj);
        }
        bj.setState((BJState) new Stand());
        split = false;
        bj.requestState().handleStand(bj);
        disableButtons();
        System.out.println("Stand");
    }
    
    @FXML
    private void handleHit(ActionEvent event) {
        bj.setBet(betAmount);
        if (bj.getSplitPointeur() > 21 || bj.getSplitPointeur1() > 21) {
            split = true;
        }
        if (split) {
            disableButtons();
            bj.setState((BJState) new Stand());
            handleStand(event);
        } else {
            bj.requestState().handleHit(bj);
            if (bj.getWorthpointeur() > 21) {
                bj.setState((BJState) new Stand());
                handleStand(event);
                disableButtons();
                hitBtn.setDisable(true);
            } else {
                if (hit == 0) {
                    doubleBtn.setDisable(false);
                    standBtn.setDisable(false);
                    if (bj.getcoP().get(0).getNumber() == (bj.getcoP().get(1).getNumber())) {
                        splitBtn.setDisable(false);
                    }
                    if (bj.getcoC().get(0).getNumber() == 14) {
                        insuranceBtn.setDisable(false);
                    }
                }
                if (hit == 1) {
                    insuranceBtn.setDisable(true);
                    splitBtn.setDisable(true);
                    
                }
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
        standBtn.setDisable(false);
        doubleBtn.setDisable(false);
        if (hit == 0) {
            hitBtn.setDisable(false);
            betAmount++;
            betImgView.setImage(bet);
            betLbl.setText(Integer.toString(betAmount));
        }
        if (insurance) {
            insuranceBetLbl.setVisible(true);
            hitBtn.setDisable(false);
            betting++;
            betImgView.setImage(bet);
            insuranceBetLbl.setText(Integer.toString(betting));
        }
        
    }
    
    @FXML
    private void handleTen(MouseEvent event) {
        standBtn.setDisable(false);
        doubleBtn.setDisable(false);
        if (hit == 0) {
            betLbl.setVisible(true);
            hitBtn.setDisable(false);
            betAmount += 10;
            betImgView.setImage(bet);
            betLbl.setText(Integer.toString(betAmount));
        }
        if (insurance) {
            insuranceBetLbl.setVisible(true);
            hitBtn.setDisable(false);
            System.out.println("TEn");
            betting += 10;
            betImgView.setImage(bet);
            insuranceBetLbl.setText(Integer.toString(betting));
        }
    }
    
    @FXML
    private void handleFifty(MouseEvent event) {
        standBtn.setDisable(false);
        doubleBtn.setDisable(false);
        if (hit == 0) {
            betLbl.setVisible(true);
            hitBtn.setDisable(false);
            betAmount += 50;
            betImgView.setImage(bet);
            betLbl.setText(Integer.toString(betAmount));
        }
        if (insurance) {
            insuranceBetLbl.setVisible(true);
            hitBtn.setDisable(false);
            betting += 50;
            betImgView.setImage(bet);
            insuranceBetLbl.setText(Integer.toString(betting));
        }
    }
    
    @FXML
    private void handleHundred(MouseEvent event) {
        standBtn.setDisable(false);
        doubleBtn.setDisable(false);
        if (hit == 0) {
            betLbl.setVisible(true);
            hitBtn.setDisable(false);
            betAmount += 100;
            betImgView.setImage(bet);
            insuranceBetLbl.setVisible(true);
            betLbl.setText(Integer.toString(betAmount));
        }
        if (insurance) {
            insuranceBetLbl.setVisible(true);
            hitBtn.setDisable(false);
            betting += 100;
            betImgView.setImage(bet);
            insuranceBetLbl.setText(Integer.toString(betting));
        }
    }
    
    @FXML
    private void handleTwoFifty(MouseEvent event) {
        standBtn.setDisable(false);
        doubleBtn.setDisable(false);
        if (hit == 0) {
            betLbl.setVisible(true);
            hitBtn.setDisable(false);
            betAmount += 250;
            betImgView.setImage(bet);
            betLbl.setText(Integer.toString(betAmount));
        }
        if (insurance) {
            insuranceBetLbl.setVisible(true);
            hitBtn.setDisable(false);
            betting += 250;
            betImgView.setImage(bet);
            insuranceBetLbl.setText(Integer.toString(betting));
        }
    }
    
    @FXML
    private void handleFivehundred(MouseEvent event) {
        standBtn.setDisable(false);
        doubleBtn.setDisable(false);
        if (hit == 0) {
            betLbl.setVisible(true);
            hitBtn.setDisable(false);
            betAmount += 500;
            betImgView.setImage(bet);
            betLbl.setText(Integer.toString(betAmount));
        }
        if (insurance) {
            insuranceBetLbl.setVisible(true);
            hitBtn.setDisable(false);
            betting += 500;
            betImgView.setImage(bet);
            insuranceBetLbl.setText(Integer.toString(betting));
        }
    }
    
}
