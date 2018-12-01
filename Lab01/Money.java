/*
@author Kirsty Alexandra Nguegang
@version 1/30/2018
 */
import java.util.Random;

abstract public class Money
{
    private int denomination;
    private boolean heads;

    // generates denomination and sets head to false
    public Money(int numberOfDenominations)
    {
        //todo
        Random rand = new Random();
        this.denomination = rand.nextInt(numberOfDenominations); //numberOfDenominations ranges from 0-6
        this.heads = false;
    }

    //returns denomination
    public int getDenomination()
    {
        //todo
        return this.denomination;
    }

    //returns value of a coin or bill
    abstract public double getValue();

    // performs the tossing of a coin/bill to determine whether it lands on HEADS or TAILS
    public void toss()
    {
        //todo
        String [] tossUp = {"HEADS", "TAILS"};
        Random rand = new Random();
        int outcome = rand.nextInt(tossUp.length);
        this.heads = (outcome == 1) ? true : false;
    }

    //returns this.heads
    public boolean isHeads()
    {
        //todo
        return this.heads;
    }

    abstract public String toString();




}
