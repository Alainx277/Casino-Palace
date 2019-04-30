package ch.bbbaden.casinopalace.poker.game;

import java.util.Objects;
import java.util.Random;

public class Card {
    private CardType type;
    private CardColor color;

    public Card(CardType type, CardColor color) {
        this.type = type;
        this.color = color;
    }

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }

    public static Card random(){
        Random random = new Random();
        CardType[] cardTypes = CardType.values();
        CardType type = cardTypes[random.nextInt(cardTypes.length)];
        CardColor[] cardColors = CardColor.values();
        CardColor color = cardColors[random.nextInt(cardColors.length)];

        return new Card(type, color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type &&
                color == card.color;
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, color);
    }
}
