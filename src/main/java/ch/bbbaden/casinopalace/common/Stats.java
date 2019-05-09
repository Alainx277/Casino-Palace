package ch.bbbaden.casinopalace.common;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

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

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public BigDecimal get(Object key) {
        return values.get(key);
    }

    public boolean containsKey(Object key) {
        return values.containsKey(key);
    }

    public BigDecimal put(String key, BigDecimal value) {
        return values.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends BigDecimal> m) {
        values.putAll(m);
    }

    public BigDecimal remove(Object key) {
        return values.remove(key);
    }

    public void clear() {
        values.clear();
    }

    public boolean containsValue(Object value) {
        return values.containsValue(value);
    }

    public Set<String> keySet() {
        return values.keySet();
    }

    public Collection<BigDecimal> values() {
        return values.values();
    }

    public Set<Map.Entry<String, BigDecimal>> entrySet() {
        return values.entrySet();
    }

    public BigDecimal getOrDefault(Object key, BigDecimal defaultValue) {
        return values.getOrDefault(key, defaultValue);
    }

    public BigDecimal putIfAbsent(String key, BigDecimal value) {
        return values.putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
        return values.remove(key, value);
    }

    public boolean replace(String key, BigDecimal oldValue, BigDecimal newValue) {
        return values.replace(key, oldValue, newValue);
    }

    public BigDecimal replace(String key, BigDecimal value) {
        return values.replace(key, value);
    }

    public BigDecimal computeIfAbsent(String key, Function<? super String, ? extends BigDecimal> mappingFunction) {
        return values.computeIfAbsent(key, mappingFunction);
    }

    public BigDecimal computeIfPresent(String key, BiFunction<? super String, ? super BigDecimal, ? extends BigDecimal> remappingFunction) {
        return values.computeIfPresent(key, remappingFunction);
    }

    public BigDecimal compute(String key, BiFunction<? super String, ? super BigDecimal, ? extends BigDecimal> remappingFunction) {
        return values.compute(key, remappingFunction);
    }

    public BigDecimal merge(String key, BigDecimal value, BiFunction<? super BigDecimal, ? super BigDecimal, ? extends BigDecimal> remappingFunction) {
        return values.merge(key, value, remappingFunction);
    }

    public void forEach(BiConsumer<? super String, ? super BigDecimal> action) {
        values.forEach(action);
    }

    public void replaceAll(BiFunction<? super String, ? super BigDecimal, ? extends BigDecimal> function) {
        values.replaceAll(function);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;

        boolean same = true;
        for (Map.Entry<String, BigDecimal> entry : values.entrySet()) {
           if (!stats.values.containsKey(entry.getKey()) || !(entry.getValue().compareTo(stats.values.get(entry.getKey())) == 0)){
               same = false;
               break;
           }
        }

        return game == stats.game &&
                same;
    }

    @Override
    public int hashCode() {

        return Objects.hash(game, values);
    }
}
