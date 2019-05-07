package ch.bbbaden.casinopalace.poker.game.hand;

import java.util.Objects;

public class Hand {
    private final String name;
    private final int value;

    public Hand(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public boolean isWin(){
        return value != 0;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return value == hand.value &&
                Objects.equals(name, hand.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }
}
