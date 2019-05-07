package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.common.exception.UserExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9$#@!_\\-]+$");

        boolean passesRequirements = true;
        // Password not empty
        if (!pattern.matcher(passwordField.getText()).matches()){
            passwordField.getStyleClass().add("error");
            passesRequirements = false;
        }

        // Passwords the same
        if (!passwordField.getText().equals(passwordConfirmField.getText())){
            passwordConfirmField.getStyleClass().add("error");
            passesRequirements = false;
        }

        // Username not empty
        if (!pattern.matcher(usernameField.getText()).matches()){
            usernameField.getStyleClass().add("error");
            passesRequirements = false;
        }

        if (!passesRequirements){
            return;
        }

        try {
            getStateManager().getCasino().createUser(usernameField.getText(), passwordField.getText());
            getStateManager().getState().handleLogin(getStateManager());
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fehler bei der Verarbeitung, versuchen Sie es später nocheinmals.", ButtonType.OK).show();
            Logger.getLogger(getClass().getName()).log(Level.WARNING, null, e);
        } catch (UserExistsException e) {
            new Alert(Alert.AlertType.ERROR, "Dieser Benutzer existiert bereits.", ButtonType.OK).show();
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
