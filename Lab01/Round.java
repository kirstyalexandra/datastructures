/**
 * A client class that implements a piggy bank functionality.
 *
 * @author Kirsty Alexandra Nguegang
 * @version 1/30/2018
 */
import java.text.DecimalFormat;

public class Round
{
    private PiggyBank myPiggyBank; //commented out so the class compile for now

    //initializes this.myPiggyBank
    public Round(int numberOfMonies, int capacity)
    {
        //todo
        this.myPiggyBank = new PiggyBank(numberOfMonies, capacity);
    }

    //adds additional money to the piggy bank
    public void addTwoMonies() throws PiggyBankFullException
    {
       // todo
        if (this.myPiggyBank.isFull())
        {
            throw new PiggyBankFullException("Piggy bank is full. Cannot add money.");
        }
        else
        {
            DecimalFormat df = new DecimalFormat("$0.00");
            Coin coin = new Coin();
            Bill bill = new Bill();
            this.myPiggyBank.add(coin);
            this.myPiggyBank.add(bill);

            System.out.println("--> Adding additional monies:");
            System.out.println("Added " + df.format(coin.getValue()) + " to the piggy bank");
            System.out.println("Added " + df.format(bill.getValue()) + " to the piggy bank");
        }
    }

    // adds two monies, shakes the piggy bank, and counts how many coins/bills landed HEADS
    public int run()
    {
        //todo
        int headsCount;
        System.out.println();
        addTwoMonies();
        this.myPiggyBank.shake();
        headsCount = this.myPiggyBank.emptyAndCountHeads();

        return headsCount ;
    }
}