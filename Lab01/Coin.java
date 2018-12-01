/*
@author Kirsty Alexandra Nguegang
@version 1/30/2018
 */

public class Coin extends Money
{
    private final int[] DENOMINATION_VALUE = {1, 5, 10, 25, 50};
    private final String[] DENOMINATION_NAME = {"PENNY", "NICKEL", "DIME", "QUARTER", "HALF_DOLLAR"}; // how do i even tie this and the denomination value together
    private static final int NUMBER_OF_DENOMINATIONS = 5;

    //passes NUMBER_OF_DENOMINATIONS to Money's constructors to generate denomination
    public Coin()
    {
        //todo

        super(NUMBER_OF_DENOMINATIONS);
    }

    //returns value of the bill
    public double getValue()
    {
        //todo
        int denom = super.getDenomination();
        double value = 0;
        final double hundredth = 0.01;
        if (denom <= this.NUMBER_OF_DENOMINATIONS)
        {
            value = (double) ((this.DENOMINATION_VALUE[denom]) * hundredth);
        }
        return value;
    }

    // displays the denominations that landed Heads
    public String toString()
    {
        //todo
        if (isHeads())
        {
            return DENOMINATION_NAME[getDenomination()] + " landed HEADS";
        }
        else
        {
            return DENOMINATION_NAME[getDenomination()] + " landed TAILS";
        }
    }
}
