/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import ch.bbbaden.casinopalace.common.Controller;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.util.Duration.seconds;

import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RouletteGameController extends Controller implements Initializable {

    //Meine Attribute
    Roulette roulette = new Roulette();
    Datenbank db;
    BetMoney bm = new BetMoney();
    Chip chip = new Chip();
    HashMap<Field, Integer> fieldinput = new HashMap<>();
    HashMap<int[], Integer> rowcolumninput = new HashMap<>();
    ArrayList<Label> chips = new ArrayList<>();

    // <editor-fold defaultstate="collapsed" desc=" Stats ">
    @FXML
    private ImageView handimage;
    @FXML
    private ImageView dollarimage;
    @FXML
    private ImageView kontoimage;
    @FXML
    private ImageView totaldollarimage;

    @FXML
    private Label einsatz;
    @FXML
    private Label gewinn;
    @FXML
    private Label kontobestand;
    @FXML
    private Label totalgewinn;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Table Buttons ">
    @FXML
    private Button btn3;
    @FXML
    private Button btn6;
    @FXML
    private Button btn9;
    @FXML
    private Button btn12;
    @FXML
    private Button btn15;
    @FXML
    private Button btn18;
    @FXML
    private Button btn21;
    @FXML
    private Button btn24;
    @FXML
    private Button btn27;
    @FXML
    private Button btn30;
    @FXML
    private Button btn33;
    @FXML
    private Button btn36;
    @FXML
    private Button btw1row;
    @FXML
    private Button btn2;
    @FXML
    private Button btn5;
    @FXML
    private Button btn8;
    @FXML
    private Button btn11;
    @FXML
    private Button btn14;
    @FXML
    private Button btn17;
    @FXML
    private Button btn20;
    @FXML
    private Button btn23;
    @FXML
    private Button btn26;
    @FXML
    private Button btn29;
    @FXML
    private Button btn32;
    @FXML
    private Button btn35;
    @FXML
    private Button btn2row;
    @FXML
    private Button btn4;
    @FXML
    private Button btn7;
    @FXML
    private Button btn10;
    @FXML
    private Button btn13;
    @FXML
    private Button btn16;
    @FXML
    private Button btn19;
    @FXML
    private Button btn22;
    @FXML
    private Button btn25;
    @FXML
    private Button btn28;
    @FXML
    private Button btn31;
    @FXML
    private Button btn3row;
    @FXML
    private Button btn34;
    @FXML
    private Button btn1;
    @FXML
    private Button btn0;
    @FXML
    private Button btn00;
    @FXML
    private Button btn1st12;
    @FXML
    private Button btn2nd12;
    @FXML
    private Button btn3rd12;
    @FXML
    private Button btnungerade;
    @FXML
    private Button btngerade;
    @FXML
    private Button btnblack;
    @FXML
    private Button btnred;
    @FXML
    private Button btn1to18;
    @FXML
    private Button btn19to36;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Table Labels ">
    @FXML
    private Label lb3and6;
    @FXML
    private Label lb2and3and5and6;
    @FXML
    private Label lb2and3;
    @FXML
    private Label lb2and5;
    @FXML
    private Label lb2and5and1and4;
    @FXML
    private Label lb1and2;
    @FXML
    private Label lb1and4;
    @FXML
    private Label lb6and9;
    @FXML
    private Label lb6and9and5and8;
    @FXML
    private Label lb6and5;
    @FXML
    private Label lb5and8;
    @FXML
    private Label lb5and8and4and7;
    @FXML
    private Label lb5and4;
    @FXML
    private Label lb4and7;
    @FXML
    private Label lb9and12;
    @FXML
    private Label lb9and12and8and11;
    @FXML
    private Label lb9and8;
    @FXML
    private Label lb8and11;
    @FXML
    private Label lb8and11and7and10;
    @FXML
    private Label lb8and7;
    @FXML
    private Label lb7and10;
    @FXML
    private Label lb12and15;
    @FXML
    private Label lb12and15and11and14;
    @FXML
    private Label lb12and11;
    @FXML
    private Label lb11and14;
    @FXML
    private Label lb11and14and10and13;
    @FXML
    private Label lb11and10;
    @FXML
    private Label lb10and13;
    @FXML
    private Label lb15and18;
    @FXML
    private Label lb15and18and14and17;
    @FXML
    private Label lb15and14;
    @FXML
    private Label lb14and17;
    @FXML
    private Label lb14and17and13and16;
    @FXML
    private Label lb14and13;
    @FXML
    private Label lb13and16;
    @FXML
    private Label lb18and21;
    @FXML
    private Label lb18and21and17and20;
    @FXML
    private Label lb18and17;
    @FXML
    private Label lb17and20;
    @FXML
    private Label lb17and20and16and19;
    @FXML
    private Label lb17and16;
    @FXML
    private Label lb16and19;
    @FXML
    private Label lb21and24;
    @FXML
    private Label lb21and24and20and23;
    @FXML
    private Label lb21and20;
    @FXML
    private Label lb20and23;
    @FXML
    private Label lb20and23and19and22;
    @FXML
    private Label lb20and19;
    @FXML
    private Label lb19and22;
    @FXML
    private Label lb24and27;
    @FXML
    private Label lb24and27and23and26;
    @FXML
    private Label lb24and23;
    @FXML
    private Label lb23and26;
    @FXML
    private Label lb23and26and22and25;
    @FXML
    private Label lb23and22;
    @FXML
    private Label lb22and25;
    @FXML
    private Label lb27and30;
    @FXML
    private Label lb27and30and26and29;
    @FXML
    private Label lb27and26;
    @FXML
    private Label lb26and29;
    @FXML
    private Label lb26and29and25and28;
    @FXML
    private Label lb26and25;
    @FXML
    private Label lb25and28;
    @FXML
    private Label lb30and33;
    @FXML
    private Label lb30and33and29and32;
    @FXML
    private Label lb30and29;
    @FXML
    private Label lb29and32;
    @FXML
    private Label lb29and32and28and31;
    @FXML
    private Label lb29and28;
    @FXML
    private Label lb28and31;
    @FXML
    private Label lb33and36;
    @FXML
    private Label lb33and36and32and35;
    @FXML
    private Label lb33and32;
    @FXML
    private Label lb32and35;
    @FXML
    private Label lb32and35and31and34;
    @FXML
    private Label lb32and31;
    @FXML
    private Label lb31and34;
    @FXML
    private Label lb36and35;
    @FXML
    private Label lb35and34;
    @FXML
    private Label lbmiddle3;
    @FXML
    private Label lbcorner2;
    @FXML
    private Label lbmiddle2;
    @FXML
    private Label lbcorner1;
    @FXML
    private Label lbmiddle1;
    @FXML
    private Label lbcornerdown1;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Buttons Chips ">

    @FXML
    private Button chip1;
    @FXML
    private Button chip10;
    @FXML
    private Button chip50;
    @FXML
    private Button chip100;
    @FXML
    private Button chip250;
    @FXML
    private Button chip500;
    // </editor-fold>
    @FXML
    private Label shownum;
    @FXML
    private GridPane cornerpane;
    @FXML
    private ImageView rouletteimage;
    @FXML
    private GridPane roultable;
    @FXML
    private Button btnspin;
    private Label chiplb;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button btnmoneyback;
    @FXML
    private Label hand;
    @FXML
    private Button infobtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new Datenbank(getStateManager().getCasino());

        //Images
        Image hand = new Image("/images/icons/hand.png");
        handimage.setImage(hand);
        Image dollar = new Image("/images/icons/dollar.png");
        dollarimage.setImage(dollar);
        Image konto = new Image("/images/icons/konto.png");
        kontoimage.setImage(konto);
        Image totaldollar = new Image("/images/icons/totaldollar.png");
        totaldollarimage.setImage(totaldollar);

        //Roulette
        Image roulettei = new Image("/images/roulette_2.png");
        rouletteimage.setImage(roulettei);

        //Die Felderliste wird kreiert
        roulette.createTable();
        //Konto angeben
        kontobestand.setText("" + db.getKonto());
        //Den Drehen-Knopf deaktivieren
        btnspin.setDisable(true);
    }

    @FXML
    private void clickSpin(ActionEvent event) throws InterruptedException {
        //Den Drehen-Knopf aktivieren
        btnspin.setDisable(true);
        //Die Zufallszahl ziehen
        roulette.drawNumber();
        shownum.setVisible(false);
        shownum.setText(roulette.getNumberDrawnAsText());
        shownum.setStyle("-fx-background-color: " + roulette.getNumberDrawnAsColour() + ";");
        shownum.setVisible(true);
        //Roulette drehen
        RotateTransition imageRotate = new RotateTransition(seconds(1), rouletteimage);
        imageRotate.setByAngle(360);
        imageRotate.play();
        //Die Roulette wird gespielt und alle "Bets" werden gerechnet
        roulette.setReceivedMoney(fieldinput, rowcolumninput, new Field(shownum.getText(), roulette.getColorFromField(shownum.getText())));
        //Statistik aktualisieren
        gewinn.setText("" + roulette.getWonMoney());
        totalgewinn.setText("" + roulette.getAllWonMoney());
        //Konto aktualisieren
        db.setKonto(db.getKonto() + roulette.getReceivedMoney());
        kontobestand.setText("" + db.getKonto());
        //Der Roulette-Tisch r�umen
        fieldinput.clear();
        rowcolumninput.clear();
        for (int i = 0; i < chips.size(); i++) {
            anchorpane.getChildren().remove(chips.get(i));
        }
        //Der aktuelle Einsatz r�umen
        bm.clearCommitMoney();
        einsatz.setText("" + bm.getCommitMoney());
        //Die Hand r�umen
        bm.clearBetMoney();
        hand.setText("" + bm.getMoney());
    }

    // <editor-fold defaultstate="collapsed" desc=" Click Chips ">
    @FXML
    private void clickChip1(ActionEvent event) {
        //Den Wert auf die Hand nehmen
        bm.addChip(1);
        hand.setText("" + bm.getMoney());
        //Den Wert des Chips
        chip.setValue(chip.getValue() + 1);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip1.png");
    }

    @FXML
    private void clickChip10(ActionEvent event) {
        bm.addChip(10);
        hand.setText("" + bm.getMoney());
        chip.setValue(chip.getValue() + 10);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip10.png");
    }

    @FXML
    private void clickChip50(ActionEvent event) {
        bm.addChip(50);
        hand.setText("" + bm.getMoney());
        chip.setValue(chip.getValue() + 50);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip10.png");
    }

    @FXML
    private void clickChip100(ActionEvent event) {
        bm.addChip(100);
        hand.setText("" + bm.getMoney());
        chip.setValue(chip.getValue() + 100);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip10.png");
    }

    @FXML
    private void clickChip250(ActionEvent event) {
        bm.addChip(250);
        hand.setText("" + bm.getMoney());
        chip.setValue(chip.getValue() + 250);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip10.png");
    }

    @FXML
    private void clickChip500(ActionEvent event) {
        bm.addChip(500);
        hand.setText("" + bm.getMoney());
        chip.setValue(chip.getValue() + 500);
        chip.setText("" + chip.getValue());
        chip.setUrl("/images/chips/chip10.png");
    }
// </editor-fold>

    @FXML
    private void clickFieldMouse(MouseEvent event) {
        boolean isForbidden = false;
        if (bm.getMoney() > db.getKonto()) {
            JOptionPane d = new JOptionPane();
            d.showMessageDialog(null, "Du kannst nicht mehr als dein Kontostand setzen",
                    "HALT STOP", JOptionPane.ERROR_MESSAGE);
            isForbidden = true;
        } else if (chip.getValue() == 0) {
            JOptionPane d = new JOptionPane();
            d.showMessageDialog(null, "Du kannst nicht 0 setzen",
                    "HALT STOP", JOptionPane.ERROR_MESSAGE);
            isForbidden = true;
        } else {
            //Den Drehen-Knopf deaktivieren
            btnspin.setDisable(false);
            //Chips einsetzen mit Bild und Text
            chiplb = new Label();
            chiplb.setId("chiplb");
            chiplb.setText(chip.getText());
            chiplb.setStyle("-fx-background-image : url(" + "/images/chips/chipdrop.png" + ")");
            chiplb.setAlignment(Pos.CENTER);
            chiplb.setMinSize(50, 50);
            Point2D relative = anchorpane.screenToLocal(event.getScreenX(), event.getScreenY());
            chiplb.setLayoutX(relative.getX() - 20);
            chiplb.setLayoutY(relative.getY() - 18);
            chiplb.setMouseTransparent(true);
            chips.add(chiplb);
            anchorpane.getChildren().add(chiplb);
            //Feld speichert Geld
            if (event.getSource() instanceof Button) {
                Button selectedbutton = (Button) event.getSource();
                selectedbutton.localToScreen(selectedbutton.getBoundsInLocal());
                fieldinput.put(new Field(selectedbutton.getText(), roulette.getColorFromField(selectedbutton.getText())), bm.getMoney());
            } else if (event.getSource() instanceof Label) {
                Label selectedlabel = (Label) event.getSource();
                selectedlabel.localToScreen(selectedlabel.getBoundsInLocal());
                if (selectedlabel.getId().equals("cornerlabel")) {
                    switch (GridPane.getRowIndex(selectedlabel)) {
                        case 1:
                            rowcolumninput.put(new int[]{0, 3}, bm.getMoney());
                            break;
                        case 2:
                            rowcolumninput.put(new int[]{0, 2, 3}, bm.getMoney());
                            break;
                        case 3:
                            rowcolumninput.put(new int[]{0, 100, 2}, bm.getMoney());
                            break;
                        case 4:
                            rowcolumninput.put(new int[]{100, 2, 1}, bm.getMoney());
                            break;
                        case 5:
                            rowcolumninput.put(new int[]{100, 1}, bm.getMoney());
                            break;
                        case 6:
                            rowcolumninput.put(new int[]{0, 100, 1, 2, 3}, bm.getMoney());
                            break;
                        default:
                            throw new AssertionError();
                    }
                } else {

                    if (GridPane.getRowIndex(selectedlabel) % 2 == 0 && GridPane.getColumnIndex(selectedlabel) % 2 == 1) {
                        Button linkebtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel), GridPane.getColumnIndex(selectedlabel) - 1, roultable);
                        Button rechtebtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel), GridPane.getColumnIndex(selectedlabel) + 1, roultable);

                        int[] buttons = new int[2];
                        buttons[0] = Integer.parseInt(linkebtn.getText());
                        buttons[1] = Integer.parseInt(rechtebtn.getText());
                        rowcolumninput.put(buttons, bm.getMoney());
                    }
                    if (GridPane.getColumnIndex(selectedlabel) % 2 == 0 && GridPane.getRowIndex(selectedlabel) % 2 == 1) {
                        Button obenbtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel) - 1, GridPane.getColumnIndex(selectedlabel), roultable);
                        Button untenbtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel) + 1, GridPane.getColumnIndex(selectedlabel), roultable);

                        int[] buttons = new int[2];
                        buttons[0] = Integer.parseInt(obenbtn.getText());
                        buttons[1] = Integer.parseInt(untenbtn.getText());
                        rowcolumninput.put(buttons, bm.getMoney());
                    } else if (GridPane.getRowIndex(selectedlabel) % 2 == 1 && GridPane.getColumnIndex(selectedlabel) % 2 == 1) {

                        Button obenlinkebtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel) - 1, GridPane.getColumnIndex(selectedlabel) - 1, roultable);
                        Button obenrechtebtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel) - 1, GridPane.getColumnIndex(selectedlabel) + 1, roultable);
                        Button untenlinkebtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel) + 1, GridPane.getColumnIndex(selectedlabel) - 1, roultable);
                        Button untenrechtebtn = (Button) this.getNodeByRowColumnIndex(GridPane.getRowIndex(selectedlabel) + 1, GridPane.getColumnIndex(selectedlabel) + 1, roultable);

                        int[] buttons = new int[4];
                        buttons[0] = Integer.parseInt(obenlinkebtn.getText());
                        buttons[1] = Integer.parseInt(obenrechtebtn.getText());
                        buttons[2] = Integer.parseInt(untenlinkebtn.getText());
                        buttons[3] = Integer.parseInt(untenrechtebtn.getText());
                        rowcolumninput.put(buttons, bm.getMoney());
                    }
                }
            }

        }
        if (isForbidden == false) {
            //Kontostand aktualisieren
            db.setKonto(db.getKonto() - bm.getMoney());
            kontobestand.setText("" + db.getKonto());

            //Aktueller Einsatz aktualisieren
            einsatz.setText("" + bm.getCommitMoney());

            //Den Wert des Chips aktualisieren
            chip.clearValue();

            //Die Hand aktualisieren
            bm.clearBetMoney();
            hand.setText("" + bm.getMoney());
        }
    }

    @FXML
    private void clickGeldBack(ActionEvent event) {
        chip.setValue(0);
        chip.setText("" + 0);
        bm.clearBetMoney();
        bm.clearCommitMoney();
        hand.setText("" + bm.getMoney());
    }

    // Code von https://stackoverflow.com/questions/20655024/javafx-gridpane-retrieve-specific-cell-content/20656861 [29.04.2019]
    public Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
        Node result = new Button();
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == null) {
                GridPane.setRowIndex(node, 0);
            }
            if (GridPane.getColumnIndex(node) == null) {
                GridPane.setColumnIndex(node, 0);
            }
            if ((GridPane.getRowIndex(node) == row) && (GridPane.getColumnIndex(node) == column)) {
                return node;
            }
        }

        return result;
    }

    @FXML
    private void clickInfoButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProgramInfo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Benutzeranleitung");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
