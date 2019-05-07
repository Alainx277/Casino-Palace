/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.roulette.game;

import ch.bbbaden.casinopalace.common.Casino;
import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 *
 * @author gabri
 */
public class Datenbank {
    private final Casino casino;

    public Datenbank(Casino casino) {
        this.casino = casino;
    }

    public int getKonto() {
        return casino.getCurrentUser().getChips().setScale(0, RoundingMode.DOWN).intValue();
    }

    public void setKonto(int konto) {
        BigDecimal oldValue = casino.getCurrentUser().getChips();
        BigInteger integer = oldValue.toBigInteger();
        BigDecimal fraction = oldValue.subtract(new BigDecimal(integer));
        casino.getCurrentUser().setChips(new BigDecimal(konto).add(fraction));
        try {
            casino.updateUser(casino.getCurrentUser());
        } catch (IOException | UserDoesNotExistException e) {
            e.printStackTrace();
        }
    }
}
