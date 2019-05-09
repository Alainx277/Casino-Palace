package ch.bbbaden.casinopalace.poker;

import ch.bbbaden.casinopalace.common.Casino;
import ch.bbbaden.casinopalace.common.Game;
import ch.bbbaden.casinopalace.common.Stats;
import ch.bbbaden.casinopalace.common.User;
import ch.bbbaden.casinopalace.common.exception.UserDoesNotExistException;
import ch.bbbaden.casinopalace.poker.game.Poker;
import ch.bbbaden.casinopalace.poker.game.PokerBet;
import ch.bbbaden.casinopalace.poker.game.PokerState;
import ch.bbbaden.casinopalace.poker.game.hand.Hand;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
        if (poker.isEnd()) {
            User user = casino.getCurrentUser();
            try {
                HashMap<Game, Stats> statsForUser = casino.getStatsForUser(user);
                String key = poker.isWon() ? "Gewonnen" : "Verloren";
                Stats stats = statsForUser.getOrDefault(Game.Poker, new Stats());
                stats.put(key, stats.getOrDefault(key, BigDecimal.ZERO).add(BigDecimal.valueOf(1)));
                statsForUser.put(Game.Poker, stats);
                casino.updateStatsForUser(user, statsForUser);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Add bet amount to user account
            if (poker.getAmountWon().compareTo(BigDecimal.ZERO) != 0) {
                int multiplier = poker.getAmountWon().intValue();
                double value = betCallback.call(null).getValue() * multiplier;
                changeBalance(new BigDecimal(value));
            }
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

            HashMap<Game, Stats> statsForUser = casino.getStatsForUser(currentUser);
            Stats stats = statsForUser.getOrDefault(Game.Poker, new Stats());
            boolean positive = amount.compareTo(BigDecimal.ZERO) > 0;
            String key = positive ? "Chips gewonnen" : "Chips verloren";
            BigDecimal existingValue = stats.getOrDefault(key, BigDecimal.ZERO);
            stats.put(key, existingValue.add(amount.abs()));
            statsForUser.put(Game.Poker, stats);
            casino.updateStatsForUser(currentUser, statsForUser);
        } catch (IOException | UserDoesNotExistException e) {
            e.printStackTrace();
        }
    }
}
