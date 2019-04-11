package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.common.Controller;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import ch.bbbaden.casinopalace.common.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoginController extends Controller {

    @FXML
    private TextField passwordField;
    @FXML
    private TextField userfield;
    @FXML
    private Button buttonlogin;
    @FXML
    private Button buttonsignin;

    @FXML
    private void clickLogin(ActionEvent event) {
        try {
            Pattern pattern = Pattern.compile("^[^ ]+$");


            // Password not empty
            if (!pattern.matcher(passwordField.getText()).matches()){
                // TODO: Show in ui
                return;
            }

            // Username not empty
            if (!pattern.matcher(userfield.getText()).matches()){
                // TODO: Show in ui
                return;
            }

            // Get user from storage
            Optional<User> userFromAuthentication = getStateManager().getCasino().getUserFromAuthentication(userfield.getText(), passwordField.getText());
            if (!userFromAuthentication.isPresent()){
                // Username or password incorrect
                // TODO: Show in ui
                return;
            }

            getStateManager().getCasino().setCurrentUser(userFromAuthentication.get());

            getStateManager().getState().handleCasino(getStateManager());
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
