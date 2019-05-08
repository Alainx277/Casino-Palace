package ch.bbbaden.casinopalace.poker;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.poker.controller.PokerGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PokerController extends Controller implements Initializable {
    @FXML
    private Label balanceLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        balanceLabel.setText(getStateManager().getCasino().getCurrentUser().getChips().stripTrailingZeros().toPlainString());
        getStateManager().getSceneCreator().getCurrentStage().setOnCloseRequest(event -> {
            try {
                getStateManager().getState().handleCasino(getStateManager());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handlePlay(ActionEvent actionEvent) {
        try {
            getStateManager().switchStage(getClass().getResource("view/PokerGame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
