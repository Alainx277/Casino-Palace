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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class ProgramInfoController implements Initializable {

    @FXML
    private ImageView imageanleitung;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Label line11;
    @FXML
    private Label line12;
    @FXML
    private Label line13;
    @FXML
    private Label line14;
    @FXML
    private Label titel1;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image hand = new Image("/images/roulette_bedienungsanleitung.PNG");
        imageanleitung.setImage(hand);
    }

    @FXML
    private void exitLabel1(MouseEvent event) {
        pane1.setVisible(false);
        for (int i = 0; i < pane1.getChildren().size(); i++) {
            pane1.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void enterLabel1(MouseEvent event) {
        pane1.setVisible(true);
        for (int i = 0; i < pane1.getChildren().size(); i++) {
            pane1.getChildren().get(i).setVisible(true);
        }
    }

    @FXML
    private void exitLabel2(MouseEvent event) {
        pane2.setVisible(false);
        for (int i = 0; i < pane2.getChildren().size(); i++) {
            pane2.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void enterLabel2(MouseEvent event) {
        pane2.setVisible(true);
        for (int i = 0; i < pane2.getChildren().size(); i++) {
            pane2.getChildren().get(i).setVisible(true);
        }
    }

    @FXML
    private void exitLabel5(MouseEvent event) {
        pane5.setVisible(false);
        for (int i = 0; i < pane5.getChildren().size(); i++) {
            pane5.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void enterLabel5(MouseEvent event) {
        pane5.setVisible(true);
        for (int i = 0; i < pane5.getChildren().size(); i++) {
            pane5.getChildren().get(i).setVisible(true);
        }
    }

    @FXML
    private void exitLabel6(MouseEvent event) {
        pane6.setVisible(false);
        for (int i = 0; i < pane6.getChildren().size(); i++) {
            pane6.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void enterLabel6(MouseEvent event) {
        pane6.setVisible(true);
        for (int i = 0; i < pane6.getChildren().size(); i++) {
            pane6.getChildren().get(i).setVisible(true);
        }
    }

    @FXML
    private void exitLabel4(MouseEvent event) {
        pane4.setVisible(false);
        for (int i = 0; i < pane4.getChildren().size(); i++) {
            pane4.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void enterLabel4(MouseEvent event) {
        pane4.setVisible(true);
        for (int i = 0; i < pane4.getChildren().size(); i++) {
            pane4.getChildren().get(i).setVisible(true);
        }
    }

    @FXML
    private void exitLabel3(MouseEvent event) {
        pane3.setVisible(false);
        for (int i = 0; i < pane3.getChildren().size(); i++) {
            pane3.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void enterLabel3(MouseEvent event) {
        pane3.setVisible(true);
        for (int i = 0; i < pane3.getChildren().size(); i++) {
            pane3.getChildren().get(i).setVisible(true);
        }
    }

    @FXML
    private void exitLabel7(MouseEvent event) {
        pane7.setVisible(false);
        for (int i = 0; i < pane7.getChildren().size(); i++) {
            pane7.getChildren().get(i).setVisible(false);
        }
    }

    @FXML
    private void enterLabel7(MouseEvent event) {
        pane7.setVisible(true);
        for (int i = 0; i < pane7.getChildren().size(); i++) {
            pane7.getChildren().get(i).setVisible(true);
        }
    }
}
