package ch.bbbaden.casinopalace.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public class Stats {
    private Game game;
    private HashMap<String, BigDecimal> values;

    public Stats(Game game) {
        this(game, new HashMap<>());
    }

    public Stats(Game game, HashMap<String, BigDecimal> values) {
        this.game = game;
        this.values = values;
    }

    public HashMap<String, BigDecimal> getValues() {
        return values;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return game == stats.game &&
                Objects.equals(values, stats.values);
    }

    @Override
    public int hashCode() {

        return Objects.hash(game, values);
    }
}
