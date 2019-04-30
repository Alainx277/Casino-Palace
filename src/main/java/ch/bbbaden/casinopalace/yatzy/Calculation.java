/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.yatzy;

/**
 *
 * @author denni
 */
public class Calculation {
     private FXMLDocumentController fdc;
     private int T1 = 0;
     private int T2 = 0;
     private boolean sb = false;

    public void setFdc(FXMLDocumentController fdc) {
        this.fdc = fdc;
    }
    
    public void setT1(int i){
        T1+=i;
        if(T1 >= 63){
            setSB();
        }
        setGPT1();
        setTPT1();
        setES();
    }
    private void setSB(){
        sb = true;
        fdc.setlabelSB("35");
    }
    private void setGPT1(){
        int c;
        if(sb){
             c = T1 +35;
        }else {
            c = T1;
        }
        fdc.setLabelGPT1(""+c);
    }
    private void setTPT1(){
        fdc.setLabelzpt1(""+T1);
    }
    private void setGPT2(){
        fdc.setLabelGPT2(""+T2);
    }
    private void setES(){
        int d = 0;
        if(sb){
            d+=35;
        }
        d += T1;
        d += T2;
        fdc.setLabelES(""+d);
    }
     public void setT2(int i){
        T2+=i;
        setGPT2();
        setES();
    }
     
}
