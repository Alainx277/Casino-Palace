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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BlackjackUbersichtsController extends Controller implements Initializable {

    @FXML
    private Label chips;
    @FXML
    private ImageView imgChips;
    @FXML
    private Label rules;
    @FXML
    private Button startGame;
    @FXML
    private AnchorPane ap;

    @FXML
    private void startGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Blackjack.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) rules.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ap.getStylesheets().add(CasinoController.class.getResource("Common.css").toExternalForm());
        startGame.setText("Spiel beginnen");
        imgChips.setImage(new Image("/images/chips.png"));
        setRulesLabel();

    }

    private void setRulesLabel() {
        rules.setText("BlackJack ist das in Casinos meisten gespielte Spiel," + "\n"
                + "Das Ziel dieses Spiels ist es möglichst nahe an die 21 zu kommen, weshalb es" + "\n"
                + "in einigen Ländern auch 21 gennant wird. Sie als Spieler sind der Pointeur" + "\n"
                + "Die Bank, Ihr Gegenüber und zu besiegende Spieler wird Croupier gennant." + "\n"
                + "Es gibt die Knöpfe/Spielzüge Hit, Stand, Double, Insurance und Split. Während Hit" + "\n"
                + "bedeutet eine neue Karte zu ziehen, meint Stand das halten der momentanten" + "\n"
                + "Karten, Double das verdoppeln des Einsatzes, Insurance" + "\n"
                + "das versichern gegen ein BlackJack des Croupiers und Split das trennen von zwei Karten" + "\n"
                + "in zwei verschiedene Hände. Es sind 6 Kartendecks an jeweils 52 Karten im Spiel" + "\n"
                + "Jede Karte zählt wie ihre Nummer. Bildkarten zählen jeweils 10 und das Ass kann" + "\n"
                + "nachbelieben 1 oder 11 zählen. Ein BlackJack ist das erreichen von 21 Punkten" + "\n"
                + "mit einem Ass und einer Bildkarte, also nur 2 Karte." + "\n"
                + "Zu allererst muss der Pointeur seinen Einsatz wählen. Beim ersten Mal Hit drücken" + "\n"
                + "wird der Einsatz bestätigt und das Spiel beginnt. Der Pointeur erhält 2 Karten" + "\n"
                + "während der Croupier nur eine erhält. Der Pointeur muss jetzt solange Karten" + "\n"
                + "ziehen, bis er denkt genügend Nahe an 21 herangelangt zu sein." + "\n"
                + "Daraufhin drückt er auf Stand und haltet seine Anzahl Karten" + "\n"
                + "Wenn der Pointeur seine Karten hält, zieht der Croupier, bis er denkt nahe" + "\n"
                + "genug zu sein. Wenn der Croupier aber ein Ass als erste Karte in der Hand hält" + "\n"
                + "Kann sich der Pointeur eine Versicherung kaufen, oder falls der Pointeur den" + "\n"
                + "gleichen Wert in 2 Karten hält, kann er splitten und somit mit zweit Händen" + "\n"
                + "spielen. Mit zwei Karten, darf er auch verdoppeln und seinen Einsatz somit" + "\n"
                + "erhöhen" + "\n" + "\n"
                + "Viel Spass");
    }

    public void setScene(Scene scene) {
        scene.getRoot().requestFocus();
    }
}
