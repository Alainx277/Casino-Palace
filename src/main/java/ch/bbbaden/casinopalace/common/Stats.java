package ch.bbbaden.casinopalace.common;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Stats extends HashMap<String, BigDecimal>{


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;

        boolean same = true;
        for (Map.Entry<String, BigDecimal> entry : entrySet()) {
           if (!stats.containsKey(entry.getKey()) || !(entry.getValue().compareTo(stats.get(entry.getKey())) == 0)){
               return false;
           }
        }

        return true;
    }

    @Override
    public int hashCode() {

        return Objects.hash(entrySet());
    }
}
