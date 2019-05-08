package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.common.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import ch.bbbaden.casinopalace.common.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;


public class LoginController extends Controller implements Initializable {

    @FXML
    private TextField passwordField;
    @FXML
    private TextField userfield;
    @FXML
    private Button buttonlogin;
    @FXML
    private Button buttonsignin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userfield.textProperty().addListener(observable -> userfield.getStyleClass().removeAll("error"));
        passwordField.textProperty().addListener(observable -> passwordField.getStyleClass().removeAll("error"));
    }

    @FXML
    private void clickLogin(ActionEvent event) {
        try {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9$#@!_\\-]+$");

            boolean passesRequirements = true;
            // Password not empty
            if (!pattern.matcher(passwordField.getText()).matches()){
                passwordField.getStyleClass().add("error");
                passesRequirements = false;
            }

            // Username not empty
            if (!pattern.matcher(userfield.getText()).matches()){
                userfield.getStyleClass().add("error");
                passesRequirements = false;
            }

            if (!passesRequirements){
                return;
            }

            // Get user from storage
            Optional<User> userFromAuthentication = getStateManager().getCasino().getUserFromAuthentication(userfield.getText(), passwordField.getText());
            if (!userFromAuthentication.isPresent()){
                // Username or password incorrect
                passwordField.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Benutzer existiert nicht oder Passwort inkorrekt.", ButtonType.OK);
                alert.show();
                return;
            }

            User user = userFromAuthentication.get();
            getStateManager().getCasino().setCurrentUser(user);

            if (user.isAdmin()){
                getStateManager().getState().handleAdmin(getStateManager());
            } else {
                getStateManager().getState().handleCasino(getStateManager());
            }

        } catch (Exception ex) {
            // TODO: Notify user
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickSignup(ActionEvent event) throws IOException {
        try {
            getStateManager().getState().handleSignUp(getStateManager());
        } catch (Exception ex) {
            // TODO: Notify user
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
