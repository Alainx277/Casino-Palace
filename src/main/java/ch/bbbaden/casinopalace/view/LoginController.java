package ch.bbbaden.casinopalace.view;

import ch.bbbaden.casinopalace.common.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoginController extends Controller {

    @FXML
    private TextField userfield;
    @FXML
    private Button buttonlogin;
    @FXML
    private Button buttonsignin;

    @FXML
    private void clickLogin(ActionEvent event) {
        try {
            getStateManager().getState().handleCasino(getStateManager());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickSignup(ActionEvent event) throws IOException {
        try {
            getStateManager().getState().handleSignUp(getStateManager());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
