
 /*
@author Kirsty Alexandra Nguegang
@version 1/30/2018
 */

public class Bill extends Money
{
    private final int[] DENOMINATION_VALUE = {1, 2, 5, 10, 20, 50, 100};
    private final String[] DENOMINATION_NAME = {"WASHINGTON", "JEFFERSON", "LINCOLN", "HAMILTON", "JACKSON", "GRANT", "FRANKLIN" };
    private static final int NUMBER_OF_DENOMINATIONS = 7;

    //passes NUMBER_OF_DENOMINATIONS to Money's constructors to generate denomination
    public Bill()
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
        if (denom <= this.NUMBER_OF_DENOMINATIONS)
        {
            value = (double) ((this.DENOMINATION_VALUE[denom]));
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
