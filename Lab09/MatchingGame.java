package Lab09;
import java.util.*;

/**
 * @author Kirsty Alexandra Nguegang
 * @author atb
 * @version 3/27/2018
 */
public class MatchingGame
{
    private ArrayList<Integer> theNumbers;
    final int MAX_NUMBER_OF_SHUFFLES = 5;
    final int MIN_NUMBER = 10;
    final int MAX_NUMBER = 99;
    Random generator;


    public MatchingGame(int numberAmount)
    {
        this.theNumbers = new ArrayList<>();
        generator = new Random(11);
        initializeList(numberAmount);
    }

    /**
     * Initialize the list with the count of random 2 digit numbers.
     *
     */
    private void initializeList(int numberAmount)
    {
        // TODO Project 6a
        ListIterator<Integer> iter = this.theNumbers.listIterator();
        for (int i = 0; i < numberAmount; i++)
        {
            int randElement = this.generator.nextInt((MAX_NUMBER + 1) - MIN_NUMBER) + MIN_NUMBER;
            iter.add(randElement);
        }
        // generate the numbers and add them to theNumbers using iterator

    }

    /**
     * See whether two numbers are removable.
     * @param first the first 2 digit integer value
     * @param second the second 2 digit integer value
     * @return true if the first and second match
     */
    private boolean removablePair(Integer first, Integer second)
    {
        // TODO Project 6c
        // implement this method
        boolean result = false;
        Integer x1, x2, y1, y2;

        x1 = first / 10;
        x2 = first % 10;
        y1 = second / 10;
        y2 = second % 10;

        if (x1 == y1)
            result = true;
        else if (x1 == y2)
            result = true;
        else if (x2 == y1)
            result = true;
        else if (x2 == y2)
            result = true;

        return result;
    }

    /**
     * Implements one pass when called by play method
     * Scans over the list and removes any pairs of values that are removable.
     * @return true if any pair of integers was removed
     */
    private boolean scanAndRemovePairs()
    {
        // TODO Project 6d
        boolean removedAPair = false;
        ListIterator<Integer> scan = this.theNumbers.listIterator();
        Integer first, second = null;
        while (scan.hasNext())
        {
            first = scan.next();
            if (scan.hasNext())
            {
                second = scan.next();

                while (scan.hasNext() && !removablePair(first, second))
                {
                    first = second;
                    second = scan.next();
                }

                if (removablePair(first, second))
                {
                    System.out.println("\tRemoved: " + first + " " + second);
                    scan.remove();
                    scan.previous();
                    scan.remove();
                    removedAPair = true;
                }
            }

        }
        // implement the method
        // this method calls helper method removablePair to see if there is a match
        return removedAPair;
    }

    private void displayTheNumbers()
    {
        // TODO Project 6b
        // using an instance of Iterator display the content of theNumbers
        Iterator<Integer> numIter = this.theNumbers.iterator();
        if (!numIter.hasNext())
        {
            System.out.println("The list is empty. ");
        }
        else
        {
            while (numIter.hasNext())
            {
                System.out.print(numIter.next() + " ");
            }
            System.out.println();
        }
        // notify the user if the list is empty
    }

    public void play()
    {
        int pass = 0;
        int numberOfShuffles = 0;
        boolean repeat;

        System.out.println("Starting with: ");
        displayTheNumbers();

        do
        {
            repeat = false;
            while (scanAndRemovePairs())
            {
                pass++;
                System.out.println("The list after pass #" + pass);
                displayTheNumbers();
            }
            System.out.println("No more pairs to remove.");
            // do we have at least 3 numbers in the list?
            if (this.theNumbers.size() > 2)
            {
                if (numberOfShuffles < MAX_NUMBER_OF_SHUFFLES)
                {
                    numberOfShuffles++;
                    System.out.println("Shuffling the numbers.");
                    Collections.shuffle(this.theNumbers, this.generator);
                    System.out.println("The list after shuffling #" + numberOfShuffles);
                    displayTheNumbers();
                    repeat = true;
                }
            }
        }while(repeat);

        if (this.theNumbers.isEmpty())
        {
            System.out.println("\n*** Winner!!! ***");
        }
        else
        {
            System.out.println("\n*** Better luck next time! ***");
        }
    }

    public static void main(String[] args)
    {
        final int MIN_NUMBER_OF_ELEMENTS = 10;
        Scanner scan = new Scanner(System.in);
        int numberAmount;

        do
        {
            System.out.println("How many numbers (no less than " + MIN_NUMBER_OF_ELEMENTS + ")?");
            numberAmount = scan.nextInt();
        }while(numberAmount < MIN_NUMBER_OF_ELEMENTS);

        MatchingGame game = new MatchingGame(numberAmount);
        game.play();
    }
}
