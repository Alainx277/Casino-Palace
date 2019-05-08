package ch.bbbaden.casinopalace.poker.game;

import ch.bbbaden.casinopalace.poker.StateMachine;

import java.math.BigDecimal;
import java.util.Arrays;

public class GambleState extends PokerState {

    private final Card flippedCard;

    public GambleState(Card flippedCard) {
        this.flippedCard = flippedCard;
    }

    @Override
    public boolean canGamblePick(StateMachine<Poker, PokerState> machine) {
        return true;
    }

    @Override
    public void handleGamblePick(StateMachine<Poker, PokerState> machine, Card pick) {
        Poker poker = machine.getOwner();
        if (pick.getRank().getValue() > flippedCard.getRank().getValue()){
           poker.setAmountWon(poker.getAmountWon().multiply(BigDecimal.valueOf(2)));
        } else if (pick.getRank().getValue() < flippedCard.getRank().getValue()){
            poker.setAmountWon(BigDecimal.ZERO);
            poker.setWon(false);
            machine.transition(new HeldState());
            return;
        }
        machine.transition(new HeldState());
    }
}
