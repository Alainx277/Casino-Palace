package ch.bbbaden.casinopalace.common;

import java.util.HashMap;

public class Stats {
    private HashMap<String, Integer> values;

    public Stats(HashMap<String, Integer> values) {
        this.values = values;
    }

    public HashMap<String, Integer> getValues() {
        return values;
    }
}
