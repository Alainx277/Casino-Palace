/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import ch.bbbaden.casinopalace.common.Casino;
import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.common.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class BuyController extends Controller implements Initializable {

    @FXML
    private Spinner spinnerChips;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleConvert(ActionEvent actionEvent) {
        Casino casino = getStateManager().getCasino();
        User user = casino.getCurrentUser();

        user.setChips(user.getChips().add(BigDecimal.valueOf(((Integer) spinnerChips.getValue()))));

        try {
            getStateManager().switchStage(getClass().getResource("Casino.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        try {
            getStateManager().switchStage(getClass().getResource("Casino.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
