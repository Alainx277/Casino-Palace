package ch.bbbaden.casinopalace.poker.game;

public class PokerBet {
    public static final double[] CHIP_VALUES = {0.25, 0.5, 1, 2, 5, 10, 50, 100};
    public static final int MAX_CHIPS = 5;

    final private int chipIndex;
    final private int numberOfChips;

    public PokerBet(int chipIndex, int numberOfChips){
        if (chipIndex >= CHIP_VALUES.length)
        {
            throw new IllegalArgumentException("Chip index needs to be in bounds");
        }

        if (numberOfChips > MAX_CHIPS){
            throw new IllegalArgumentException("Maximum number of chips is " + MAX_CHIPS);
        }

        this.chipIndex = chipIndex;
        this.numberOfChips = numberOfChips;
    }

    public int getChipIndex() {
        return chipIndex;
    }

    public double getSingleValue(){
        return CHIP_VALUES[chipIndex];
    }

    public double getValue(){
        return getSingleValue() * numberOfChips;
    }

    public int getNumberOfChips() {
        return numberOfChips;
    }

    public static PokerBet fromValue(double value, int numberOfChips){
        for (int i = 0; i < CHIP_VALUES.length; i++) {
            if (CHIP_VALUES[i] == value){
                return new PokerBet(i, numberOfChips);
            }
        }
        return null;
    }
}
