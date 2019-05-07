/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.yatzy;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author denni
 */
public class WuerfelnFXMLController implements Initializable {

    @FXML
    private Button finish;
    @FXML
    private RadioButton radiobutton1;
    @FXML
    private RadioButton radiobutton2;
    @FXML
    private ComboBox<String> combobox1;
    @FXML
    private ComboBox<String> combobox2;
    @FXML
    private Button closeButton;

    TryandError te;

    private ArrayList<Dice> throwDices = new ArrayList<>();
    private ArrayList<Dice> keepDices = new ArrayList<>();
    private ArrayList<Dice> sortDices = new ArrayList<>();
    private ArrayList<String> useFigur = new ArrayList<>();
    private ArrayList<String> closeFigur = new ArrayList<>();

    private int keep = 0;

    @FXML
    private ImageView imageview1;
    @FXML
    private ImageView imageview2;
    @FXML
    private ImageView imageview4;
    @FXML
    private ImageView imageview5;
    @FXML
    private ImageView imageview3;

    private boolean thrown;
    private int gewuerfelt = 0;
    @FXML
    private Button wuerfelButton;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pan2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private ImageView throw3;
    @FXML
    private ImageView throw2;
    @FXML
    private ImageView throw1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox1.setDisable(true);
        combobox2.setDisable(true);
        radiobutton1.setDisable(true);
        radiobutton2.setDisable(true);
        finish.setDisable(true);
        thrown = false;

    }

    public void initi() {
        if (te.hasAl()) {
            throwDices = te.getAlDice();
            //Wuerfel von vorhin anzeigen
            for (int c = 0; c < 5; c++) {
                setImages(c);

            }
        }

    }

    @FXML
    private void handleimageview1(MouseEvent event) {

        if (thrown) {
            if (throwDices.get(0).getKeep()) {
                throwDices.get(0).setKeep(false);

                keep--;
            } else {
                throwDices.get(0).setKeep(true);

                keep++;
            }
            setPane1(0);
        }
    }

    @FXML
    private void handleimageview2(MouseEvent event) {
        if (thrown) {
            if (throwDices.get(1).getKeep()) {
                throwDices.get(1).setKeep(false);
                keep--;
            } else {
                throwDices.get(1).setKeep(true);
                keep++;
            }
            setPane2(1);
        }
    }

    @FXML
    private void handleimageview4(MouseEvent event) {
        if (thrown) {
            if (throwDices.get(3).getKeep()) {
                throwDices.get(3).setKeep(false);
                keep--;
            } else {
                throwDices.get(3).setKeep(true);
                keep++;
            }
            setPane4(3);
        }
    }

    @FXML
    private void handleimageview5(MouseEvent event) {
        if (thrown) {
            if (throwDices.get(4).getKeep()) {
                throwDices.get(4).setKeep(false);
                keep--;
            } else {
                throwDices.get(4).setKeep(true);
                keep++;
            }
            setPane5(4);
        }
    }

    @FXML
    private void handleimageview3(MouseEvent event) {
        if (thrown) {
            if (throwDices.get(2).getKeep()) {
                throwDices.get(2).setKeep(false);
                keep--;
            } else {
                throwDices.get(2).setKeep(true);
                keep++;
            }
            setPane3(2);
        }
    }

    @FXML
    private void handleWuerfeln(ActionEvent event) {
        radiobutton1.setSelected(false);
        radiobutton2.setSelected(false);
        combobox1.disableProperty().set(true);
        combobox2.disableProperty().set(true);
        combobox1.setValue("");
        combobox2.setValue("");
        radiobutton1.setDisable(false);
        radiobutton2.setDisable(false);
        finish.setDisable(true);

        if (thrown == false) {
            for (int i = 0; i < 5; i++) {
                throwDices.add(new Dice());
            }
        }

        for (int i = 4; i >= 0; i--) {
            if (throwDices.get(i).getKeep()) {
                keepDices.add(throwDices.get(i));
                throwDices.remove(i);
            } else {
                throwDices.remove(i);
            }

        }
        throwDices.clear();
        //5 Würfel machen - behaltene Würfel

        for (int i = 0; i < 5 - keepDices.size(); i++) {

            throwDices.add(new Dice());
        }
        //Ordnen nach Grösse
        throwDices = organizeDices(throwDices);
        for (int d = 0; d < keepDices.size(); d++) {
            sortDices.add(keepDices.get(d));

        }
        sortDices = organizeDices(sortDices);
        for (int i = 0; i < 5 - keepDices.size(); i++) {
            sortDices.add(throwDices.get(i));
        }
        throwDices.clear();
        throwDices = (ArrayList<Dice>) sortDices.clone();
        keepDices.clear();
        sortDices.clear();

        //damit nicht mehr als 3 Mal gewürfelt wird
        gewuerfelt++;
        //Bilder setzen und wuerfeln disablen wenn 3 mal gewürfelt
        for (int c = 0; c < 5; c++) {
            setImages(c);

            if (gewuerfelt == 3) {
                   wuerfelButton.setDisable(true);
            }
        }
        //3 Wuerfe anzeigen
        if(gewuerfelt == 1){
            throw1.setOpacity(0.0);
        }else if (gewuerfelt == 2){
            throw2.setOpacity(0.0);
        }else {
            throw3.setOpacity(0.0);
        }
        //Setzen ob schon einmal gewürfelt wurde wegen paneBorder
        thrown = true;

    }

    @FXML
    private void handlefinish(ActionEvent event) {
        if (combobox1.getValue() == "" && combobox2.getValue() == "") {

        } else {
            te.setAlDice(throwDices);
            te.showStage();
            if (radiobutton1.selectedProperty().get()) {
                te.setFigur(true, combobox1.getValue());
            } else {
                te.setFigur(false, combobox2.getValue());
            }

            for (int i = 0; i < 5; i++) {
                throwDices.get(i).setKeep(false);
            }

            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }

    private void setUseFigur(ArrayList<String> al) {
        useFigur = (ArrayList<String>) al.clone();
    }

    @FXML
    private void handleuseFgur(ActionEvent event) {
        radiobutton2.setSelected(false);
        if (radiobutton1.selectedProperty().get()) {
            combobox1.setDisable(false);
            combobox2.setDisable(true);
            finish.setDisable(false);
            setUseFigur(te.getuseFields(throwDices));

            combobox1.getItems().setAll(useFigur);
        } else {
            combobox1.setDisable(true);
            finish.setDisable(true);
        }
    }

    private void setCloseFigur(ArrayList<String> al) {
        closeFigur = (ArrayList<String>) al.clone();
    }

    @FXML
    private void handlecloseFigur(ActionEvent event) {
        radiobutton1.setSelected(false);
        if (radiobutton2.selectedProperty().get()) {
            combobox2.setDisable(false);
            combobox1.setDisable(true);
            finish.setDisable(false);
            setCloseFigur(te.getFields());
            combobox2.getItems().setAll(closeFigur);
        } else {
            combobox2.setDisable(true);
            finish.setDisable(true);
        }
    }

    @FXML
    private void handleclose(ActionEvent event) {
        closeProgram("Aufgeben?", "Sicher dass du Aufgeben willst?\nDu verlierst den gesetzten Betrag!", false);
    }

    public void setTryandError(TryandError te) {
        this.te = te;
    }

    private ArrayList organizeDices(ArrayList<Dice> array) {

        for (int i = 0; i < 20; i++) {
            Dice di;
            for (int d = 1; d < array.size(); d++) {
                di = array.get(d - 1);
                if (array.get(d - 1).getWert() < array.get(d).getWert()) {
                    array.set(d - 1, array.get(d));

                    array.set(d, di);
                }

            }
        }
        return array;
    }

    private void setPane1(int c) {
        if (throwDices.get(c).getKeep()) {
            pane1.setStyle("-fx-border-color: red; \n -fx-border-width: 15;");
        } else {
            pane1.setStyle("");
        }
    }

    private void setPane2(int c) {
        if (throwDices.get(c).getKeep()) {
            pan2.setStyle("-fx-border-color: red; \n -fx-border-width: 15;");
        } else {
            pan2.setStyle("");
        }
    }

    private void setPane3(int c) {
        if (throwDices.get(c).getKeep()) {
            pane3.setStyle("-fx-border-color: red; \n -fx-border-width: 15;");
        } else {
            pane3.setStyle("");
        }
    }

    private void setPane4(int c) {
        if (throwDices.get(c).getKeep()) {
            pane4.setStyle("-fx-border-color: red; \n -fx-border-width: 15;");
        } else {
            pane4.setStyle("");
        }
    }

    private void setPane5(int c) {
        if (throwDices.get(c).getKeep()) {
            pane5.setStyle("-fx-border-color: red; \n -fx-border-width: 15;");
        } else {
            pane5.setStyle("");
        }
    }

    private void setImages(int c) {
        switch (throwDices.get(c).getWert()) {
            case 1:
                switch (c) {
                    case 0:
                        imageview1.setImage(new Image("/images/Dice1.png"));
                        setPane1(c);
                        break;
                    case 1:
                        imageview2.setImage(new Image("/images/Dice1.png"));
                        setPane2(c);
                        break;
                    case 2:
                        imageview3.setImage(new Image("/images/Dice1.png"));
                        setPane3(c);
                        break;
                    case 3:
                        imageview4.setImage(new Image("/images/Dice1.png"));
                        setPane4(c);
                        break;
                    case 4:
                        imageview5.setImage(new Image("/images/Dice1.png"));
                        setPane5(c);
                        break;

                }
                break;
            case 2:
                switch (c) {
                    case 0:
                        imageview1.setImage(new Image("/images/Dice2.png"));
                        setPane1(c);
                        break;
                    case 1:
                        imageview2.setImage(new Image("/images/Dice2.png"));
                        setPane2(c);
                        break;
                    case 2:
                        imageview3.setImage(new Image("/images/Dice2.png"));
                        setPane3(c);
                        break;
                    case 3:
                        imageview4.setImage(new Image("/images/Dice2.png"));
                        setPane4(c);
                        break;
                    case 4:
                        imageview5.setImage(new Image("/images/Dice2.png"));
                        setPane5(c);
                        break;

                }
                break;
            case 3:
                switch (c) {
                    case 0:
                        imageview1.setImage(new Image("/images/Dice3.png"));
                        setPane1(c);
                        break;
                    case 1:
                        imageview2.setImage(new Image("/images/Dice3.png"));
                        setPane2(c);
                        break;
                    case 2:
                        imageview3.setImage(new Image("/images/Dice3.png"));
                        setPane3(c);
                        break;
                    case 3:
                        imageview4.setImage(new Image("/images/Dice3.png"));
                        setPane4(c);
                        break;
                    case 4:
                        imageview5.setImage(new Image("/images/Dice3.png"));
                        setPane5(c);
                        break;

                }
                break;
            case 4:
                switch (c) {
                    case 0:
                        imageview1.setImage(new Image("/images/Dice4.png"));
                        setPane1(c);
                        break;
                    case 1:
                        imageview2.setImage(new Image("/images/Dice4.png"));
                        setPane2(c);
                        break;
                    case 2:
                        imageview3.setImage(new Image("/images/Dice4.png"));
                        setPane3(c);
                        break;
                    case 3:
                        imageview4.setImage(new Image("/images/Dice4.png"));
                        setPane4(c);
                        break;
                    case 4:
                        imageview5.setImage(new Image("/images/Dice4.png"));
                        setPane5(c);
                        break;

                }

                break;
            case 5:
                switch (c) {
                    case 0:
                        imageview1.setImage(new Image("/images/Dice5.png"));
                        setPane1(c);
                        break;
                    case 1:
                        imageview2.setImage(new Image("/images/Dice5.png"));
                        setPane2(c);
                        break;
                    case 2:
                        imageview3.setImage(new Image("/images/Dice5.png"));
                        setPane3(c);
                        break;
                    case 3:
                        imageview4.setImage(new Image("/images/Dice5.png"));
                        setPane4(c);
                        break;
                    case 4:
                        imageview5.setImage(new Image("/images/Dice5.png"));
                        setPane5(c);
                        break;

                }
                break;
            case 6:
                switch (c) {
                    case 0:
                        imageview1.setImage(new Image("/images/Dice6.png"));
                        setPane1(c);
                        break;
                    case 1:
                        imageview2.setImage(new Image("/images/Dice6.png"));
                        setPane2(c);
                        break;
                    case 2:
                        imageview3.setImage(new Image("/images/Dice6.png"));
                        setPane3(c);
                        break;
                    case 3:
                        imageview4.setImage(new Image("/images/Dice6.png"));
                        setPane4(c);
                        break;
                    case 4:
                        imageview5.setImage(new Image("/images/Dice6.png"));
                        setPane5(c);
                        break;

                }
                break;
            default:
                throw new AssertionError();
        }
    }
    private void closeProgram(String title, String message, boolean nextWindow){
        int n = JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            if(nextWindow){
                te.showStage();
            }
            Stage stage = (Stage) finish.getScene().getWindow();
            stage.close();
        }
    }
}
