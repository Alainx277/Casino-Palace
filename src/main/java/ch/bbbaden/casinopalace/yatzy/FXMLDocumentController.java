/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.yatzy;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ch.bbbaden.casinopalace.common.Casino;
import ch.bbbaden.casinopalace.common.Controller;
import ch.bbbaden.casinopalace.common.User;
import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author denni
 */
public class FXMLDocumentController extends Controller implements Initializable {
    
    private Label label;
    @FXML
    private GridPane gridpane;
    @FXML
    private Label labeleinser;
    @FXML
    private Label labelzweier;
    @FXML
    private Label labeldreier;
    @FXML
    private Label labelvierer;
    @FXML
    private Label labelfuenfer;
    @FXML
    private Label labelsechser;
    @FXML
    private Label labelZschwischenpunkteTeil1;
    @FXML
    private Label labelSonderbonus;
    @FXML
    private Label labelzweiPaar;
    @FXML
    private Label labelDreierpasch;
    @FXML
    private Label labelViererpasch;
    @FXML
    private Label labeleinPaar;
    @FXML
    private Label labelFullhouse;
    @FXML
    private Label labelkleineStrasse;
    @FXML
    private Label labelgrosseStrasse;
    @FXML
    private Label labelYatzy;
    @FXML
    private Label labelChance;
    @FXML
    private Label labelEndsumme;
    @FXML
    private Label labelGesamtpunkteTeil2;
    @FXML
    private Button cancel;
    @FXML
    private Label labelKontostand;
    @FXML
    private Label labelGesamtpunkteTeil1;
    @FXML
    private Spinner<Integer> spinnerBetrag;
    @FXML
    private Button buttonWuerfeln;
    @FXML
    private Button buttonSetzen;
    
    @FXML
    private Label anzahlWuerfe;
    
    private int ianzahlWuerfe = 15;
    
    private TryandError te = new TryandError();
    private Calculation ca = new Calculation();
    
    private ArrayList<String> unusedFigures = new ArrayList<>();
    
    private int gesetzterBetrag = 0;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getStateManager().getSceneCreator().getCurrentStage().setOnCloseRequest(event -> handleClose(null));
        // TODO
        te.setFdc(this);
        ca.setFdc(this);
        buttonWuerfeln.setDisable(true);
        getKontostand();
        spinnerBetrag.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(25, 100, 25, 25));
        anzahlWuerfe.setText("" + ianzahlWuerfe);
        
    }
    
    @FXML
    private void handleWuerfeln(ActionEvent event) throws IOException {
        
        setUnusedFigures();
        ianzahlWuerfe--;
        te.setFields(unusedFigures);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WuerfelnFXML.fxml"));
        Parent root = loader.load();
        WuerfelnFXMLController view1 = loader.getController();
        view1.setTryandError(te);
        view1.initi();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        setHide(true);
    }
    
    @FXML
    private void handleClose(ActionEvent event) {
        closeProgram("Aufgeben?", "Sicher dass du Aufgeben willst?\nDu verlierst den gesetzten Betrag!", false);
        
    }
    
    public void actualize() {
        
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        closeProgram("Aufgeben?", "Sicher dass du Aufgeben willst?\nDu verlierst den gesetzten Betrag!", false);
    }
    
    @FXML
    private void handleSetzen(ActionEvent event) {
        int c = Integer.parseInt(labelKontostand.getText());
        int i = spinnerBetrag.getValue();
        gesetzterBetrag = i;
        setKontostand();
        buttonWuerfeln.setDisable(false);
        spinnerBetrag.setVisible(false);
        buttonSetzen.setVisible(false);
    }
    
    public void setHide(boolean baum) {
        if (baum) {
            Stage b = (Stage)labeleinser.getScene().getWindow();
            b.hide();
        } else {
            Stage b = (Stage) labeleinser.getScene().getWindow();
            b.show();
        }
    }
    
    private void setUnusedFigures() {
        unusedFigures.clear();
        if (labeleinser.getText().equals("")) {
            unusedFigures.add("Einser");
        }
        if (labelzweier.getText().equals("")) {
            unusedFigures.add("Zweier");
        }
        if (labeldreier.getText().equals("")) {
            unusedFigures.add("Dreier");
        }
        if (labelvierer.getText().equals("")) {
            unusedFigures.add("Vierer");
        }
        if (labelfuenfer.getText().equals("")) {
            unusedFigures.add("Fünfer");
        }
        if (labelsechser.getText().equals("")) {
            unusedFigures.add("Sechser");
        }
        if (labeleinPaar.getText().equals("")) {
            unusedFigures.add("Ein Paar");
        }
        if (labelzweiPaar.getText().equals("")) {
            unusedFigures.add("Zwei Paar");
        }
        if (labelDreierpasch.getText().equals("")) {
            unusedFigures.add("Dreierpasch");
        }
        if (labelViererpasch.getText().equals("")) {
            unusedFigures.add("Viererpasch");
        }
        if (labelFullhouse.getText().equals("")) {
            unusedFigures.add("Full House");
        }
        if (labelkleineStrasse.getText().equals("")) {
            unusedFigures.add("Kleine Strasse");
        }
        if (labelgrosseStrasse.getText().equals("")) {
            unusedFigures.add("Grosse Strasse");
        }
        if (labelYatzy.getText().equals("")) {
            unusedFigures.add("Yatzy");
        }
        if (labelChance.getText().equals("")) {
            unusedFigures.add("Chance");
        }
        
    }
    
    public void setFigur(String figur, int value) {
        switch (figur) {
            case "Einser":
                labeleinser.setText("" + value);
                ca.setT1(value);
                break;
            case "Zweier":
                labelzweier.setText("" + value);
                ca.setT1(value);
                break;
            case "Dreier":
                labeldreier.setText("" + value);
                ca.setT1(value);
                break;
            case "Vierer":
                labelvierer.setText("" + value);
                ca.setT1(value);
                break;
            case "Fünfer":
                labelfuenfer.setText("" + value);
                ca.setT1(value);
                break;
            case "Sechser":
                labelsechser.setText("" + value);
                ca.setT1(value);
                break;
            case "Ein Paar":
                labeleinPaar.setText("" + value);
                ca.setT2(value);
                break;
            case "Zwei Paar":
                labelzweiPaar.setText("" + value);
                ca.setT2(value);
                break;
            case "Dreierpasch":
                labelDreierpasch.setText("" + value);
                ca.setT2(value);
                break;
            case "Viererpasch":
                labelViererpasch.setText("" + value);
                ca.setT2(value);
                break;
            case "Full House":
                labelFullhouse.setText("" + value);
                ca.setT2(value);
                break;
            case "Kleine Strasse":
                labelkleineStrasse.setText("" + value);
                ca.setT2(value);
                break;
            case "Grosse Strasse":
                labelgrosseStrasse.setText("" + value);
                ca.setT2(value);
                break;
            case "Yatzy":
                labelYatzy.setText("" + value);
                ca.setT2(value);
                break;
            case "Chance":
                labelChance.setText("" + value);
                ca.setT2(value);
                break;
            default:
                throw new AssertionError();
        }
        anzahlWuerfe.setText("" + ianzahlWuerfe);
        
        getKontostand();
    }
    
    private void setSetzen(int wert) {
        int baum = Integer.parseInt(labelKontostand.getText());
        baum -= wert;
        labelKontostand.setText("" + baum);
    }
    
    private void closeProgram(String title, String message, boolean nextWindow) {
        int n = JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION);
        
        if (n == JOptionPane.YES_OPTION) {
            
            if (nextWindow) {
                te.showStage();
            } else {
                try {
                    getStateManager().getState().handleCasino(getStateManager());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Stage stage = (Stage) labeleinser.getScene().getWindow();
            stage.close();
        }
    }
    
    public void setLabelzpt1(String i) {
        labelZschwischenpunkteTeil1.setText(i);
        
    }

    public void setlabelSB(String i) {
        labelSonderbonus.setText(i);
    }

    public void setLabelES(String i) {
        labelEndsumme.setText(i);
    }

    public void setLabelGPT2(String i) {
        labelGesamtpunkteTeil2.setText(i);
    }

    public void setLabelGPT1(String i) {
        labelGesamtpunkteTeil1.setText(i);
    }
    

    private void getKontostand() {
        labelKontostand.setText(getStateManager().getCasino().getCurrentUser().getChips().stripTrailingZeros().toPlainString());
    }

    private void setKontostand() {
        User user = getStateManager().getCasino().getCurrentUser();
        BigDecimal decimal = user.getChips().subtract(new BigDecimal(gesetzterBetrag));
        user.setChips(decimal);
        try {
            getStateManager().getCasino().updateUser(user);
        } catch (IOException | UserDoesNotExistException e) {
            e.printStackTrace();
        }
        labelKontostand.setText(decimal.stripTrailingZeros().toPlainString());
    }

    public void gewinn() {
        if (ianzahlWuerfe == 0) {

            int i = Integer.parseInt(labelEndsumme.getText());
            if (i > 350) {
                gesetzterBetrag *= 2;
            } else if (i < 350) {
                gesetzterBetrag = 0;
            }
        } else {
           gesetzterBetrag = 0;
        }
        setDatabase();
    }
    
    private void setDatabase(){
        if (gesetzterBetrag != 0){
            Casino casino = getStateManager().getCasino();
            User user = casino.getCurrentUser();
            user.setChips(user.getChips().add(BigDecimal.valueOf(gesetzterBetrag)));
            try {
                casino.updateUser(user);
            } catch (IOException | UserDoesNotExistException e) {
                e.printStackTrace();
            }
        }
    }
}
