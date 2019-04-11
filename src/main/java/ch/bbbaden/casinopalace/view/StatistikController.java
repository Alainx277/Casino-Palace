package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.common.Game;
import ch.bbbaden.casinopalace.common.Stats;
import ch.bbbaden.casinopalace.common.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StatistikController extends Controller implements Initializable {
    @FXML
    private ListView<Game> listgames;
    @FXML
    private ListView liststats;
    private List<Stats> statsForUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User currentUser = getStateManager().getCasino().getCurrentUser();
        if (currentUser == null){
            try {
                getStateManager().getState().handleLogin(getStateManager());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // Cache stats
        try {
            statsForUser = getStateManager().getCasino().getStatsForUser(currentUser);
        } catch (IOException e) {
            // TODO: Notify user
            e.printStackTrace();
            return;
        }

        // Populate game list
        listgames.getItems().addAll(Game.values());
        listgames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateStatistic(newValue));
    }

    private void populateStatistic(Game game){
        liststats.getItems().clear();
        liststats.getItems().addAll(statsForUser.stream().filter(x -> x.getGame() == game).map(Stats::getValues).map(HashMap::entrySet).flatMap(Collection::stream).map(x -> x.getKey() + ": " + x.getValue().stripTrailingZeros().toPlainString()).toArray());
    }

    public void handleBack(ActionEvent actionEvent) {
        try {
            getStateManager().getState().handleCasino(getStateManager());
        } catch (Exception e) {
            // TODO: Notify user
            e.printStackTrace();
        }
    }
}
