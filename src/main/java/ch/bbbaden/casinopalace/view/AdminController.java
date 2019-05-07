package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.common.Game;
import ch.bbbaden.casinopalace.common.Stats;
import ch.bbbaden.casinopalace.common.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminController extends Controller implements Initializable {

    @FXML
    private ListView<User> listplayer;
    @FXML
    private ListView<Game> listgames;
    @FXML
    private ListView<Object> liststats;

    private List<Stats> statsForCurrentUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Populate users list
        try {
            listplayer.setCellFactory(param -> new ListCell<User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null){
                        setText(item.getUsername());
                    }
                }
            });

            getStateManager().getCasino().getUsers().stream().filter(x -> !x.isAdmin()).forEach(x -> listplayer.getItems().add(x));
            listplayer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    statsForCurrentUser = getStateManager().getCasino().getStatsForUser(newValue);
                    listgames.getSelectionModel().selectFirst();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Notify user
        }

        // Populate game list
        listgames.getItems().addAll(Game.values());
        listgames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateStatistic(newValue));
    }

    private void populateStatistic(Game game){
        liststats.getItems().clear();
        if (statsForCurrentUser == null){
            return;
        }
        liststats.getItems().addAll(statsForCurrentUser.stream().filter(x -> x.getGame() == game).map(Stats::getValues).map(HashMap::entrySet).flatMap(Collection::stream).map(x -> x.getKey() + ": " + x.getValue().stripTrailingZeros().toPlainString()).toArray());
    }

    @FXML
    private void handleBack(ActionEvent actionEvent) {
        try {
            getStateManager().getState().handleLogin(getStateManager());
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Notify user
        }
    }
}
