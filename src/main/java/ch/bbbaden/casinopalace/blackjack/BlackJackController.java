/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.blackjack;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.view.CasinoController;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.Pane;
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
    private HBox newHand2;
    private Label lblNewBet;

    private int betAmount;
    private Image bet = new Image("/images/chips.png");

    private int hit = 0;
    private boolean insurance = false;
    private boolean split = false;
    private int betting = betAmount;

    private BlackJack bj;

    @FXML
    private ImageView imgStacks;
    private ImageView chipsView;
    @FXML
    private AnchorPane ap;
    @FXML
    private ImageView betImgView;
    @FXML
    private Label betLbl;
    @FXML
    private Button insuranceBtn;
    @FXML
    private Button doubleBtn;
    @FXML
    private Button standBtn;
    @FXML
    private Button hitBtn;
    @FXML
    private Label insuranceBetLbl;
    @FXML
    private Pane resultPane;
    private Label chipsLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getStateManager().getSceneCreator().getCurrentStage().setOnCloseRequest(event -> {
            goBack();
        });

        bj = new BlackJack(ap, getStateManager().getCasino());
        ap.getStylesheets().add(CasinoController.class.getResource("Common.css").toExternalForm());
        addImages();
        disableButtons();
        setNodes();
    }

    private void goBack() {
        try {
            getStateManager().getState().handleCasino(getStateManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNodes() {
        ImageView chips = new ImageView();
        chipsLbl = new Label();
        chipsLbl.setId("chipsLbl");
        Image chipStack = new Image("/images/chips.png");
        chips.setImage(chipStack);
        chips.setFitWidth(70);
        chips.setFitHeight(70);
        chips.setLayoutY(50);
        chips.setLayoutX(700);
        chipsLbl.setLayoutX(800);
        chipsLbl.setLayoutY(75);
        chipsLbl.setText(bj.getCasino().getCurrentUser().getChips().stripTrailingZeros().toPlainString());
        ap.getChildren().addAll(chipsLbl, chips);
        resultPane.setVisible(false);
        newHand2 = new HBox();
        lblNewBet = new Label();
        chipsView = new ImageView();
        ap.getChildren().addAll(newHand2, lblNewBet, chipsView);
        newHand2.setId("newHand2");
        lblNewBet.setId("lblNewBet");
        chipsView.setId("chipsView");
        insuranceBetLbl.setVisible(false);
        hand1.setLayoutX(400);
        betLbl.setLayoutY(260);
        betImgView.setFitWidth(70);
        betImgView.setFitHeight(70);
        betImgView.setLayoutX(hand1.getLayoutX());
        betImgView.setLayoutY(betLbl.getLayoutY() - 30);
        betLbl.setLayoutX(betImgView.getLayoutX() + 120);
        insuranceBetLbl.setLayoutX(betImgView.getLayoutX() - 70);
        betLbl.setVisible(false);
    }

    private void disableButtons() {
        insuranceBtn.setDisable(true);
        doubleBtn.setDisable(true);
        standBtn.setDisable(true);
        hitBtn.setDisable(true);
    }

    private boolean checkChips(int chip) {
        boolean b = false;
        if (bj.getCasino().getCurrentUser().getChips().intValue() >= chip) {
            bj.getCasino().getCurrentUser().setChips(bj.getCasino().getCurrentUser().getChips().subtract(new BigDecimal(chip)));
            chipsLbl.setText(bj.getCasino().getCurrentUser().getChips().stripTrailingZeros().toPlainString());
            b = true;
        } else {
            System.out.println("NO");
        }
        return b;
    }

    @FXML
    private void handleInsurrance(ActionEvent event) {
        disableButtons();
        insurance = true;
    }

    private void handleSplit(ActionEvent event) {
        bj.setState((BJState) new Split());
        bj.requestState().handleSplit(bj);
        doubleBtn.setDisable(true);

    }

    @FXML
    private void handleDouble(ActionEvent event) {
        bj.setState((BJState) new Double());
        bj.setBet(betAmount * 2);
        betLbl.setText(Integer.toString(bj.getBet()));
        doubleBtn.setDisable(true);
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
        finishPlayThrough();
    }

    @FXML
    private void handleHit(ActionEvent event) {
        bj.setBet(betAmount);
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
                    //Split
                }
                if (bj.getcoC().get(0).getNumber() == 14) {
                    insuranceBtn.setDisable(false);
                }
            }
            if (hit == 1) {
                insuranceBtn.setDisable(true);

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
        goBack();
    }

    @FXML
    private void handleOne(MouseEvent event) {
        if (checkChips(1)) {
            if (hit == 0) {
                betLbl.setVisible(true);
                hitBtn.setDisable(false);
                betAmount++;
                betImgView.setImage(bet);
                betImgView.setLayoutX(400);
                betImgView.setFitHeight(70);
                betImgView.setFitWidth(70);
                betImgView.setVisible(true);
                betLbl.setLayoutX(betImgView.getLayoutX() + 120);
                betLbl.setLayoutY(260);
                betLbl.setText(Integer.toString(betAmount));
            }
            if (insurance) {
                standBtn.setDisable(false);
                doubleBtn.setDisable(false);
                insuranceBetLbl.setVisible(true);
                hitBtn.setDisable(false);
                betting++;
                betImgView.setImage(bet);
                bj.setInsuranceBet(betting);
                insuranceBetLbl.setText(Integer.toString(betting));
                insuranceBetLbl.setLayoutY(260);
            }
        }

    }

    @FXML
    private void handleTen(MouseEvent event) {
        if (checkChips(10)) {
            if (hit == 0) {
                betLbl.setVisible(true);
                hitBtn.setDisable(false);
                betAmount += 10;
                betImgView.setImage(bet);
                betImgView.setLayoutX(400);
                betImgView.setFitHeight(70);
                betImgView.setFitWidth(70);
                betImgView.setVisible(true);
                betLbl.setLayoutX(betImgView.getLayoutX() + 120);
                betLbl.setLayoutY(260);
                betLbl.setText(Integer.toString(betAmount));
            }
            if (insurance) {
                standBtn.setDisable(false);
                doubleBtn.setDisable(false);
                insuranceBetLbl.setVisible(true);
                hitBtn.setDisable(false);
                betting += 10;
                betImgView.setImage(bet);
                bj.setInsuranceBet(betting);
                insuranceBetLbl.setText(Integer.toString(betting));
                insuranceBetLbl.setLayoutY(260);
            }
        }
    }

    @FXML
    private void handleFifty(MouseEvent event) {
        if (checkChips(50)) {
            if (hit == 0) {
                betLbl.setVisible(true);
                hitBtn.setDisable(false);
                betAmount += 50;
                betImgView.setImage(bet);
                betImgView.setLayoutX(400);
                betImgView.setFitHeight(70);
                betImgView.setFitWidth(70);
                betImgView.setVisible(true);
                betLbl.setLayoutX(betImgView.getLayoutX() + 120);
                betLbl.setLayoutY(260);
                betLbl.setText(Integer.toString(betAmount));
            }
            if (insurance) {
                standBtn.setDisable(false);
                doubleBtn.setDisable(false);
                insuranceBetLbl.setVisible(true);
                hitBtn.setDisable(false);
                betting += 10;
                betImgView.setImage(bet);
                bj.setInsuranceBet(betting);
                insuranceBetLbl.setText(Integer.toString(betting));
                insuranceBetLbl.setLayoutY(260);
            }
        }
    }

    @FXML
    private void handleHundred(MouseEvent event) {
        if (checkChips(100)) {
            if (hit == 0) {
                betLbl.setVisible(true);
                hitBtn.setDisable(false);
                betAmount += 100;
                betImgView.setImage(bet);
                betImgView.setLayoutX(400);
                betImgView.setFitHeight(70);
                betImgView.setFitWidth(70);
                betImgView.setVisible(true);
                betLbl.setLayoutX(betImgView.getLayoutX() + 120);
                betLbl.setLayoutY(260);
                betLbl.setText(Integer.toString(betAmount));
            }
            if (insurance) {
                standBtn.setDisable(false);
                doubleBtn.setDisable(false);
                insuranceBetLbl.setVisible(true);
                hitBtn.setDisable(false);
                betting += 10;
                betImgView.setImage(bet);
                bj.setInsuranceBet(betting);
                insuranceBetLbl.setText(Integer.toString(betting));
                insuranceBetLbl.setLayoutY(260);
            }
        }
    }

    @FXML
    private void handleTwoFifty(MouseEvent event) {
        if (checkChips(250)) {
            if (hit == 0) {
                betLbl.setVisible(true);
                hitBtn.setDisable(false);
                betAmount += 250;
                betImgView.setImage(bet);
                betImgView.setLayoutX(400);
                betImgView.setFitHeight(70);
                betImgView.setFitWidth(70);
                betImgView.setVisible(true);
                betLbl.setLayoutX(betImgView.getLayoutX() + 120);
                betLbl.setLayoutY(260);
                betLbl.setText(Integer.toString(betAmount));
            }
            if (insurance) {
                standBtn.setDisable(false);
                doubleBtn.setDisable(false);
                insuranceBetLbl.setVisible(true);
                hitBtn.setDisable(false);
                betting += 10;
                betImgView.setImage(bet);
                bj.setInsuranceBet(betting);
                insuranceBetLbl.setText(Integer.toString(betting));
                insuranceBetLbl.setLayoutY(260);
            }
        }
    }

    @FXML
    private void handleFivehundred(MouseEvent event) {
        if (checkChips(500)) {
            if (hit == 0) {
                betLbl.setVisible(true);
                hitBtn.setDisable(false);
                betAmount += 500;
                betImgView.setImage(bet);
                betImgView.setLayoutX(400);
                betImgView.setFitHeight(70);
                betImgView.setFitWidth(70);
                betImgView.setVisible(true);
                betLbl.setLayoutX(betImgView.getLayoutX() + 120);
                betLbl.setLayoutY(260);
                betLbl.setText(Integer.toString(betAmount));
            }
            if (insurance) {
                standBtn.setDisable(false);
                doubleBtn.setDisable(false);
                insuranceBetLbl.setVisible(true);
                hitBtn.setDisable(false);
                betting += 10;
                betImgView.setImage(bet);
                bj.setInsuranceBet(betting);
                insuranceBetLbl.setText(Integer.toString(betting));
                insuranceBetLbl.setLayoutY(260);
            }
        }
    }

    private void finishPlayThrough() {
        //handle Cards
        //handle all Bets
        betLbl.setVisible(false);
        insuranceBetLbl.setVisible(false);
        betImgView.setVisible(false);
        lblNewBet.setVisible(false);
        chipsView.setVisible(false);
        //Handle notification
        Label endMessage = new Label();
        Label insuranceLbl = new Label();
        endMessage.getStyleClass().add("EndLbls");
        insuranceLbl.getStyleClass().add("EndLbls");
        insuranceLbl.setLayoutX(180);
        insuranceLbl.setLayoutY(10);
        endMessage.setLayoutX(180);
        endMessage.setLayoutY(60);
        String endString;
        String endingMsg;
        String insuranceMsg;

        insuranceMsg = "Versicherung: " + bj.getInsuranceBet();

        insuranceLbl.setText(insuranceMsg);
        switch (bj.getResult()) {
            case 2:
                //Also won
                endString = "!!Gewonnen!!";
                endingMsg = " Chips gewonnen!";
                break;
            case 1:
                //undeceided
                endString = "!!Unentschieden!!";
                endingMsg = " Chips gewonnen!";
                break;
            default:
                //lost
                endString = "!!Verloren!!";
                endingMsg = " Chips verloren!";
                break;
        }

        endMessage.setText(endString);

        //Handle message
        Label message = new Label();
        String baseMsg = "Sie haben ";

        message.getStyleClass().add("EndLbls");
        message.setLayoutX(100);
        message.setLayoutY(30);
        int change = bj.getChange();
        bj.onMoneyChanged(change);

        if (change > 0) {
            chipsLbl.setText(bj.getCasino().getCurrentUser().getChips().stripTrailingZeros().toPlainString());
        }
        String extendedMsg = Integer.toString(change);
        message.setText(baseMsg + extendedMsg + endingMsg);

        //Add Button
        Button restart = new Button("Neustarten");
        restart.setLayoutX(195);
        restart.setLayoutY(90);
        restart.setOnAction((ActionEvent event) -> {
            //Resart
            //Disable All Buttons
            disableButtons();
            //Set everything back
            hand1.getChildren().clear();
            hand2.getChildren().clear();
            newHand2.getChildren().clear();
            croupier.getChildren().clear();
            //What's the difference???
            bj.setBet(0);
            bj.setCardAmountCroupier(0);
            bj.setCardAmountPointeur(0);
            bj.setInsuranceBet(0);
            bj.setSplitPointeur(0);
            bj.setSplitPointeur1(0);
            bj.setWorthCroupier(0);
            bj.setWorthpointeur(0);
            bj.getcoC().clear();
            bj.getcoP().clear();
            hit = 0;
            insurance = false;
            resultPane.setVisible(false);
            resultPane.getChildren().clear();
            lblNewBet.setVisible(false);
            chipsView.setVisible(false);
            insuranceBetLbl.setVisible(false);
            betLbl.setVisible(false);
            betImgView.setVisible(false);
            betAmount = 0;
            betting = 0;
            bj.setState(new StandBy());
        });

        //handle resultPane
        resultPane.getChildren().addAll(message, endMessage, restart, insuranceLbl);
        resultPane.setVisible(true);
    }
}
