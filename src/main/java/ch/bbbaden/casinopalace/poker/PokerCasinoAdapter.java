package ch.bbbaden.casinopalace.poker;

import ch.bbbaden.casinopalace.common.Casino;
import ch.bbbaden.casinopalace.common.User;
import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.poker.game.Poker;
import ch.bbbaden.casinopalace.poker.game.PokerBet;
import ch.bbbaden.casinopalace.poker.game.PokerState;
import ch.bbbaden.casinopalace.poker.game.hand.Hand;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Synchronizes changes from poker to the casino
 */
public class PokerCasinoAdapter {
    private final Poker poker;
    private final Casino casino;
    private final Callback<Void, PokerBet> betCallback;

    private EventSource<BalanceEvent> onBalanceChangedSource = new EventSource<>();

    public PokerCasinoAdapter(Poker poker, Casino casino, Callback<Void, PokerBet> betCallback) {
        this.poker = poker;
        this.casino = casino;
        this.betCallback = betCallback;

        this.poker.getStateMachine().addTransitionListener(this::onTransition);
    }

    public EventSource<BalanceEvent> onBalanceChanged() {
        return onBalanceChangedSource;
    }

    private void onTransition(TransitionEvent<Poker, PokerState> event) {
        Optional<Hand> currentHand = poker.getCurrentHand();
        if (poker.isWon() && currentHand.isPresent()) {
            // Add bet amount to user account
            int multiplier = currentHand.get().getValue();
            double value = betCallback.call(null).getValue() * multiplier;
            changeBalance(new BigDecimal(value));
        } else if (event.getOldState().canBet(event.getMachine()) && !event.getNewState().canBet(event.getMachine())) {
            // Remove bet amount from user account
           changeBalance(new BigDecimal(betCallback.call(null).getValue()).negate());
        }
    }

    private void changeBalance(BigDecimal amount){
        User currentUser = casino.getCurrentUser();
        BigDecimal oldValue = currentUser.getChips();
        BigDecimal newValue = oldValue.add(amount);
        currentUser.setChips(newValue);

        onBalanceChangedSource.invoke(new BalanceEvent(oldValue, newValue));

        // Update user
        try {
            casino.updateUser(currentUser);
        } catch (IOException | UserDoesNotExistException e) {
            e.printStackTrace();
        }
    }
}
