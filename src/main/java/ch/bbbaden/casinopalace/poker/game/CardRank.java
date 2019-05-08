package ch.bbbaden.casinopalace.poker.game;

public enum CardRank {
    Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(13), Ace(14);

    private int value;

    CardRank(int value) {
        this.value = value;
    }

    public String toShortString(){
        switch (this) {

            case Two:
                return "2";
            case Three:
                return "3";
            case Four:
                return "4";
            case Five:
                return "5";
            case Six:
                return "6";
            case Seven:
                return "7";
            case Eight:
                return "8";
            case Nine:
                return "9";
            case Ten:
                return "10";
            case Jack:
                return "J";
            case Queen:
                return "Q";
            case King:
                return "K";
            case Ace:
                return "A";
        }

        return "?";
    }

    int getValue() {
        return value;
    }
}
