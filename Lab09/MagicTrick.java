package Lab09;

import java.util.*;

/**
 * MagicTrick is a program that will guess a number that user is thinking of.
 *
 * @author Kirsty Alexandra Nguegang
 * @author atb
 * @version 3/27/2018
 */
public class MagicTrick
{
    public final int NUM_OF_SEQUENCES = 5;
    public final int NUMBERS_PER_SEQUENCE = 16;
    private ArrayList<Integer>[] sequences;

    public MagicTrick()
    {
        // TODO Project 5
        this.sequences = new ArrayList[NUM_OF_SEQUENCES];
        int first, last, num;
        for (int i = 0; i < this.sequences.length; i++)
        {
            this.sequences[i] = new ArrayList<Integer>();
            first = (int)Math.pow(2, i);
            this.sequences[i].add(first); // add first number 1, 2, 4, 8, 16
            for (int j = 1; j < NUMBERS_PER_SEQUENCE; j++)
            {
                last = this.sequences[i].get(j - 1);
                if (j % first == 0)
                {
                    this.sequences[i].add(1 + first + last);
                }
                else
                {
                    this.sequences[i].add(1 + last);
                }
            }
        }
        // create this.sequences object
        // using a for loop create ArrayList object for each slot and fill it with appropriate values
    }

    public void displaySequences()
    {
        // TODO Project 5
        for (int i = 0; i < NUM_OF_SEQUENCES; i++)
        {
            System.out.println("Sequence " + (i + 1) + ": " + this.sequences[i].toString());
        }
    }

    public void guessNumber(String[] answer)
    {
        // TODO Project 5
       int guessedNumber = 0;
       int firstNum = 0;
       for (int i = 0; i < answer.length; i++)
       {
           int num = Integer.parseInt(answer[i]);
           guessedNumber += this.sequences[num - 1].get(firstNum);
       }

       System.out.println("Your number is " + guessedNumber + " :)");
    }

    public static void main(String[] args)
    {
        MagicTrick magic = new MagicTrick();
        System.out.println("Think of a number between 1 and 31\n");
        magic.displaySequences();

        System.out.println("\nList all the sequences that your number is in (ie. 1 3)");
        Scanner scan = new Scanner(System.in);
        magic.guessNumber(scan.nextLine().split(" "));
    }
}