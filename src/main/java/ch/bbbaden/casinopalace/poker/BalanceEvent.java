package ch.bbbaden.casinopalace.poker;

import java.math.BigDecimal;

public class BalanceEvent {
    private final BigDecimal oldValue;
    private final BigDecimal newValue;

    public BalanceEvent(BigDecimal oldValue, BigDecimal newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public BigDecimal getOldValue() {
        return oldValue;
    }

    public BigDecimal getNewValue() {
        return newValue;
    }

    public BigDecimal getDifference(){
        return newValue.subtract(oldValue);
    }
}
