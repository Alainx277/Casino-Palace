package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.blackjack.BlackjackUbersichtsController;
import ch.bbbaden.casinopalace.common.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CasinoController extends Controller implements Initializable {

    private int index = 0;

    private Label label;
    @FXML
    private ImageView imgViewGames;
    @FXML
    private ImageView imgViewLeft;
    @FXML
    private ImageView imgView3;

    private Image[] games = new Image[3];
    @FXML
    private ImageView imgAdd;
    @FXML
    private ImageView imgGurl;
    @FXML
    private AnchorPane ap;

    public CasinoController() {
    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addImages();
        imgViewGames.sceneProperty().addListener((observable) -> {
            imgViewGames.getScene().getRoot().requestFocus();
        });
        // TODO
    }

    @FXML
    private void handleGamesSelection(MouseEvent event) throws IOException {
        int i;
        for (i = 0; i < games.length; i++) {
            if (imgViewGames.getImage().equals(games[i])) {
                break;
            }
        }

        switch (i) {
            case 0:
                //load BlackJack
                try {
                    getStateManager().getState().handleBlackjack(getStateManager());
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }               
                break;
            case 1:
                //loadPoker
                try {
                    getStateManager().getState().handlePoker(getStateManager());
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                //load Roulette
                try {
                    getStateManager().getState().handleRoulette(getStateManager());
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 3:
                //loadYatzy
                try {
                    getStateManager().getState().handleYatzy(getStateManager());
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addImages() {
        Image image = new Image(getClass().getResourceAsStream("/images/left.png"));
        imgViewLeft.setImage(image);
        Image img = new Image("/images/right.png");
        imgView3.setImage(img);
        imgAdd.setImage(new Image("/images/add.png"));
        imgGurl.setImage(new Image("/images/girl.png"));
        games = new Image[]{new Image("/images/blackJack.png"),
            new Image("/images/poker.png"),
            new Image("/images/roulette.png"),
            new Image("/images/yatzy.png")};
        changeGame();    
    }
     public String getCommonStyleSheet(){
         return ap.getStylesheets().get(0);
     }

    public void changeGame() {
        if (index > games.length - 1) {
            index = 0;
        } else if (index < 0) {
            index = games.length - 1;
        }
        imgViewGames.setImage(games[index]);
    }

    @FXML
    private void handleLeftGame(MouseEvent event) {
        index--;
        changeGame();
    }

    @FXML
    private void handleRightGame(MouseEvent event) {
        index++;
        changeGame();
    }

    @FXML
    private void handleLeftKey() {
        index--;
        changeGame();
    }

    private void handleRightKey() {
        index = Math.min(4, index + 1);
        changeGame();
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                handleRightKey();
                break;
            case LEFT:
                handleLeftKey();
                break;
            default:
                System.out.println("something else");
        }
    }
}
