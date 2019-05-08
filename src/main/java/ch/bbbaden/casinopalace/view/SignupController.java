package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class SignupController extends Controller {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField passwordConfirmField;

    public void handleRegister(ActionEvent actionEvent) throws Exception {
        Pattern pattern = Pattern.compile("^[^ ]+$");

        // Password not empty
        if (!pattern.matcher(passwordField.getText()).matches()){
            // TODO: Show in ui
            return;
        }

        // Passwords the same
        if (!passwordField.getText().equals(passwordConfirmField.getText())){
            // TODO: Show in ui
            return;
        }

        // Username not empty
        if (!pattern.matcher(usernameField.getText()).matches()){
            // TODO: Show in ui
            return;
        }

        try {
            getStateManager().getCasino().createUser(usernameField.getText(), passwordField.getText());
            getStateManager().getState().handleLogin(getStateManager());
        } catch (IOException e) {
            // TODO: Notify user
            Logger.getLogger(getClass().getName()).log(Level.WARNING, null, e);
        } catch (UserExistsException e) {
            // TODO: Notify user
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        try {
            getStateManager().getState().handleLogin(getStateManager());
        } catch (Exception e) {
            // TODO: Notify user
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }
}
