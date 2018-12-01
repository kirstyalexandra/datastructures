/*
@author Kirsty Alexandra Nguegang
@version 1/30/2018
 */
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.text.DecimalFormat;


public class PiggyBank
{
    private BagInterface<Money> bucket;
    private int capacity;
    Random rand = new Random();

    //adds coins/bills, displays value of added coins/bills & prints content of piggy bank
    public PiggyBank(int numberOfMonies, int capacity)
    {
        //todo
        DecimalFormat df = new DecimalFormat("$0.00");
        this.capacity = capacity;
        this.bucket = new ResizableArrayBag<>();
        int num = 0;
        double total = 0;

        System.out.println(">> Adding " + numberOfMonies + " monies to your piggy bank <<");
        for (int i = 0; i < numberOfMonies; i++)
        {
            num = rand.nextInt(2); //randomly generates whether a coin or bill will get added
            if (num == 0)
            {
                Coin coin = new Coin();
                this.bucket.add(coin);
                System.out.println("Added " + df.format(coin.getValue()) + " to the piggy bank");
                total += coin.getValue();
            }

            if (num == 1)
            {
                Bill bill = new Bill();
                this.bucket.add(bill);
                System.out.println("Added " + df.format(bill.getValue()) + " to the piggy bank");
                total += bill.getValue();
            }
        }
        System.out.println("\n>> The content of your piggy bank <<");
        System.out.println(toString());
    }

    // adds Money objects to this.bucket
    public void add(Money a) throws PiggyBankFullException
    {
        //todo
        if (this.capacity >= getNumberOfMonies())
        {
            this.bucket.add(a);
        }
        else
        {
            throw new PiggyBankFullException("Piggy bank is full. Too bad.");
        }
    }


    // removes coins/bills from this.bucket and returns Money object
    public Money remove() throws PiggyBankEmptyException
    {
        //todo
        if (this.bucket.isEmpty())
        {
            throw new PiggyBankEmptyException("Piggy bank is empty. You're broke as hell.");
        }
        else
        {
            return this.bucket.remove();
        }
    }

    // checks if this.bucket is empty
    public boolean isEmpty()
    {
        //todo
        return this.bucket.isEmpty();
    }

    // checks if this.bucket is full
    public boolean isFull()
    {
        //todo
        int numOfMonies = getNumberOfMonies();
        boolean bool = (numOfMonies >= this.capacity) ? true : false;
        return bool;
    }

    // returns the capacity of this.bucket
    public int getCapacity()
    {
        //todo
        return this.capacity;
    }

    // returns the amount of monies in this.bucket
    public int getNumberOfMonies()
    {
        //todo
        return this.bucket.getCurrentSize();
    }

    // randomly shuffles Money objects, adds Money object back into this.bucket and displays its contents
    public void shake()
    {
        //todo
        Random rand = new Random();

        int  index;
        Money holder;
        Object temp;
        double total = 0;
        Object [] toShake = this.bucket.toArray();
        this.bucket.clear();// removes coins/bills as long as the bucket is not empty

        for (int i = toShake.length -1; i > 0; i--) // randomly shuffles elements
        {
            index = rand.nextInt(i + 1);
            temp = toShake[index];
            toShake[index] = toShake[i];
            toShake[i] = temp;
        }
        for (int i = 0; i < toShake.length; i++)
        {
            holder = (Money)toShake[i];
            total += holder.getValue();
            this.bucket.add(holder);
        }
        System.out.println("\n>> Shaking your piggy bank <<");
        System.out.println(toString());
    }

    //removes coins/bills, tosses the piggy bank and prints out the amount of money that landed on heads, and their count
    public int emptyAndCountHeads()
    {
        //todo
        DecimalFormat df = new DecimalFormat("$0.00");
        double totalHeads = 0;
        int counter = 0;
        int nOfMonies = getNumberOfMonies();

        System.out.println("\n>> Emptying your piggy bank <<");
        while(!this.bucket.isEmpty())
        {
            Money i = this.bucket.remove();
            i.toss();
            if (i.isHeads())
            {
                counter++;
                totalHeads += i.getValue();
                System.out.print(i.toString() + "\n");
            }
            else
            {
                System.out.print(i.toString() + "\n");
            }
        }
        System.out.println("\n" + counter + " out of " + nOfMonies + " coins/bills landed on \"HEADS\"");
        System.out.println("The total value of \"HEADS\" " + df.format(totalHeads));

        return counter; //counts number of HEADS and determines who wins
    }

    // displays piggy bank's content
    public String toString()
    {
        //todo
        DecimalFormat df = new DecimalFormat("$0.00");
        Object [] content = this.bucket.toArray();
        double total = 0;

        for (int i = 0; i < content.length; i++)
        {
            total += (((Money)content[i]).getValue());
        }

        return "The piggy bank can hold " + getCapacity() + " coins/bills; \nThere are " + this.bucket.getCurrentSize() + " coins/bills in the piggy bank: " + Arrays.toString(content) + "\nThe total of " + df.format(total);
     }
}
