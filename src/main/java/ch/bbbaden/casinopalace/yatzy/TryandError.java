/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.yatzy;

import java.util.ArrayList;
import javafx.stage.Stage;

/**
 *
 * @author denni
 */
public class TryandError {

    private Stage stage;
    private FXMLDocumentController fdc;
    private WuerfelnFXMLController wfc;
    private String label2change;
    private ArrayList<Dice> al = new ArrayList<>();
    private ArrayList<String> fields = new ArrayList<>();
    private ArrayList<String> usableFields = new ArrayList<String>();
 

    public void setFigur(boolean useFigur, String figur) {
        fdc.setFigur(figur, getDiceValue(useFigur, figur));
        
    }
    
    private  int getDiceValue(boolean use, String figur){
        if(use){
            int value= 0;
            switch (figur) {
                case "Einser":
                    for(int i = 0; i < 5;i++){
                        if(al.get(i).getWert() == 1){
                            value+=1;
                        }
                    }
                    break;
                case "Zweier":
                    for(int i = 0; i < 5;i++){
                        if(al.get(i).getWert() == 2){
                            value+=2;
                        }
                    }
                    break;
                case "Dreier":
                    for(int i = 0; i < 5;i++){
                        if(al.get(i).getWert() == 3){
                            value+=3;
                        }
                    }
                    break;
                case "Vierer":
                    for(int i = 0; i < 5;i++){
                        if(al.get(i).getWert() == 4){
                            value+=4;
                        }
                    }
                    break;
                case "Fünfer":
                    for(int i = 0; i < 5;i++){
                        if(al.get(i).getWert() == 5){
                            value+=5;
                        }
                    }
                    break;
                case "Sechser":
                    for(int i = 0; i < 5;i++){
                        if(al.get(i).getWert() == 6){
                            value+=6;
                        }
                    }
                    break;
                case "Ein Paar":
                    for(int i = 0; i < 5;i++){
                        value += al.get(i).getWert();
                    }
                    break;
                case "Zwei Paar":
                    for(int i = 0; i < 5;i++){
                        value += al.get(i).getWert();
                    }
                    break;
                case "Dreierpasch":
                    for(int i = 0; i < 5;i++){
                        value += al.get(i).getWert();
                    }
                    break;
                case "Viererpasch":
                    for(int i = 0; i < 5;i++){
                        value += al.get(i).getWert();
                    }
                    break;
                case "Full House":
                    value = 30;
                    break;
                case "Kleine Strasse":
                    value = 35;
                    break;
                case "Grosse Strasse":
                    value = 45;
                    break;
                case "Yatzy":
                   value = 75;
                    break;
                case "Chance":
                    for(int i = 0; i < 5;i++){
                        value += al.get(i).getWert();
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            return value;
        }else {
            return 0;
        }
    }



    //Schauen welche Felder mit den Würfeln genutzt werden können
    public ArrayList<String> getuseFields(ArrayList<Dice> ald) {
        usableFields.clear();
        int einser = 0;
        int zweier = 0;
        int dreier = 0;
        int vierer = 0;
        int fuenfer = 0;
        int sechser = 0;

        for (int i = 0; i < 5; i++) {
            switch (ald.get(i).getWert()) {
                case 1:
                    einser++;
                    break;
                case 2:
                    zweier++;
                    break;
                case 3:
                    dreier++;
                    break;
                case 4:
                    vierer++;
                    break;
                case 5:
                    fuenfer++;
                    break;
                case 6:
                    sechser++;
                    break;
                default:
                    throw new AssertionError();
            }
        }
        usableFields = (ArrayList<String>) fields.clone();
        if (einser == 0) {
            usableFields.remove("Einser");
        }
        if (zweier == 0) {
            usableFields.remove("Zweier");
        }
        if (dreier == 0) {
            usableFields.remove("Dreier");
        }
        if (vierer == 0) {
            usableFields.remove("Vierer");
        }
        if (fuenfer == 0) {
            usableFields.remove("Fünfer");
        }
        if (sechser == 0) {
            usableFields.remove("Sechser");
        }
        int pair = 0;
        if (einser >= 2) {
            pair++;
        }
        if (einser >= 4) {
            pair++;
        }
        if (zweier >= 2) {
            pair++;
        }
        if (zweier >= 4) {
            pair++;
        }
        if (dreier >= 2) {
            pair++;
        }
        if (dreier >= 4) {
            pair++;
        }
        if (vierer >= 2) {
            pair++;
        }
        if (vierer >= 4) {
            pair++;
        }
        if (fuenfer >= 2) {
            pair++;
        }
        if (fuenfer >= 4) {
            pair++;
        }
        if (sechser >= 2) {
            pair++;
        }
        if (sechser >= 4) {
            pair++;
        }
        if (pair == 0) {
            usableFields.remove("Ein Paar");
        }
        if (pair < 2) {
            usableFields.remove("Zwei Paar");
        }
        if (einser < 3 && zweier < 3 && dreier < 3 && vierer < 3 && fuenfer < 3 && sechser < 3) {
            usableFields.remove("Dreierpasch");
        }
        if (einser < 4 && zweier < 4 && dreier < 4 && vierer < 4 && fuenfer < 4 && sechser < 4) {
            usableFields.remove("Viererpasch");
        }
        if (einser < 5 && zweier < 5 && dreier < 5 && vierer < 5 && fuenfer < 5 && sechser < 5) {
            usableFields.remove("Yatzy");
        }else {
            usableFields.remove("Full House");
        }
        boolean dreierpasch = false;
        boolean zweierpasch = false;
        if (einser == 3 || zweier == 3 || dreier == 3 || vierer == 3 || fuenfer == 3 || sechser == 3) {
            dreierpasch = true;
        }
        if (einser == 2 || zweier == 2 || dreier == 2 || vierer == 2 || fuenfer == 2 || sechser == 2) {
            zweierpasch = true;
        }
        if (zweierpasch == false || dreierpasch == false) {
            usableFields.remove("Full House");
        }
        if (einser == 0 && zweier == 0 && dreier == 0|| sechser == 0 && fuenfer == 0 && vierer == 0|| dreier == 0 || vierer == 0) {
            usableFields.remove("Kleine Strasse");
        }
        if(einser >1 || zweier >1 || dreier >1 || vierer > 1 || fuenfer >1 || sechser >1){
            usableFields.remove("Grosse Strasse");
        }

        return usableFields;
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public void setFields(ArrayList<String> fields) {
        this.fields = fields;
    }

    public ArrayList<Dice> getAlDice() {
        return al;
    }

    public void setAlDice(ArrayList<Dice> al) {
        this.al = al;
    }

    public boolean hasAl() {
        if (al.size() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setFdc(FXMLDocumentController fdc) {
        this.fdc = fdc;
    }

    public void setWfc(WuerfelnFXMLController wfc) {
        this.wfc = wfc;
    }

    public String getLabel2change() {
        return label2change;
    }

    public void setLabel2change(String label2change) {
        this.label2change = label2change;
    }

    public void showStage() {
        //stage.show();
        fdc.setHide(false);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
