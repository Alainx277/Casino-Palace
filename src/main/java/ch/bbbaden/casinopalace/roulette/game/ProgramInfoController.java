/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import ch.bbbaden.casinopalace.roulette.menu.Roulette_MenuController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    private Label titel2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Set image
        Image hand = new Image("/images/roulette_bedienungsanleitung.PNG");
        imageanleitung.setImage(hand);
//        //Set Spinner
//        SpinnerValueFactory<Integer> spinnervalue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 1);
//        spinner.setValueFactory(spinnervalue);
//        Thread thread = new Thread() {
//            public void run() {
//                do {
//                    for (int i = 0; i < 8; i++) {
//                        if (i == spinner.getValue()) {
//                            switch (spinner.getValue()) {
//                                case 1:
////                                    titel11.setVisible(true);
////                                    line11.setVisible(true);
////                                    line12.setVisible(true);
////                                    line13.setVisible(true);
////                                    line14.setVisible(true);
////                                    line21.setVisible(false);
//                                    break;
//                                case 2:
////                                    titel11.setVisible(false);
////                                    line11.setVisible(false);
////                                    line12.setVisible(false);
////                                    line13.setVisible(false);
////                                    line14.setVisible(false);
////                                    line21.setVisible(true);
//                                    for (int j = 0; j < pane1.getChildren().size(); j++) {
//                                        pane1.getChildren().get(j).setVisible(true);
//                                        System.out.println(""+pane1.getChildren().get(j).isVisible());
//                                    }
//                                    break;
//                                default:
//                                    throw new AssertionError();
//                            }
//                        }
//                    }
//                } while (true);
//
//            }
//        };
//        thread.setDaemon(true);
//        thread.start();
    }

    @FXML
    private void enterLabel1(MouseEvent event) {
        pane1.setVisible(true);
        for (int i = 0; i < pane1.getChildren().size(); i++) {
            pane1.getChildren().get(i).setVisible(true);
        }
        System.out.println("hallo1");
    }

    @FXML
    private void exitLabel1(MouseEvent event) {
        pane1.setVisible(false);
        for (int i = 0; i < pane1.getChildren().size(); i++) {
            System.out.println(""+ pane1.getChildren().get(i));
            pane1.getChildren().get(i).setVisible(false);
        }
        System.out.println("hallo2");
    }
}
