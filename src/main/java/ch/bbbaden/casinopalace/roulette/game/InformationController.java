/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class InformationController implements Initializable {

    @FXML
    private Button inforoulettebtn;
    @FXML
    private Button infoprogrambtn;
    @FXML
    private Button backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickInfoRouletteButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RouletteInfo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Roulette Info");
        stage.setResizable(false);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickInfoProgramButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProgramInfo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Programm Info");
        stage.setResizable(false);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RouletteGame.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Roulette");
        stage.setResizable(false);
        stage.setScene(scene);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        stage.show();
    }

}
