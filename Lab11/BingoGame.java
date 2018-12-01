package Lab11;

/**
 * @author Kirsty Alexandra Nguegang
 * @version 4/10/2018
 */

import java.util.*;

public class BingoGame
{
    public final static int NUMBER_OF_CHIPS = 75;
    private int numberOfPlayers;
    private ArrayList<BingoChip> bingoDrum;
    private Player[] players;

    public BingoGame(int numOfPlayers)
    {
        // TODO Project 2.4
        setNumOfPlayers(numOfPlayers);
        createPlayers();
        createBingoDrum();
    }

    private void createBingoDrum()
    {
        // TODO Project 2.4
        this.bingoDrum = new ArrayList<>(NUMBER_OF_CHIPS);
        for (int i = 1; i <= NUMBER_OF_CHIPS; i++)
        {
            this.bingoDrum.add(new BingoChip(i));
        }
    }

    private void createPlayers()
    {
        // TODO Project 2.4
        for (int i = 0; i < this.numberOfPlayers; i++)
        {
            System.out.println("---> Creating bingo card for Player " + (i + 1));
            this.players[i] = new Player();
        }
    }

    private void setNumOfPlayers(int numOfPlayers)
    {
        // TODO Project 2.4
        this.numberOfPlayers = numOfPlayers;
        this.players = new Player[this.numberOfPlayers];
    }

    public int getNumberOfPulledChips()
    {
        // TODO Project 2.4
        return NUMBER_OF_CHIPS - this.bingoDrum.size();
    }

    public BingoChip pullChip()
    {
        // TODO Project 2.4
        Random rand = new Random();
        int randInt = rand.nextInt(this.bingoDrum.size());
        return this.bingoDrum.remove(randInt);
    }

    public void play()
    {
        // TODO Project 2.4
         boolean end = false;
         while (this.bingoDrum.size() > 0 && !end)
         {
             BingoChip bc = pullChip();
             System.out.println(" ---> Calling: " + bc.toString());
             for (int i = 0; i < this.numberOfPlayers; i++)
             {
                 this.players[i].checkCard(bc);
                 System.out.println("Player " + (i + 1) + "'s card: ");
                 this.players[i].printCard();
             }
             for (int j = 0; j < this.numberOfPlayers; j++)
             {
                 if (this.players[j].isWinner())
                 {
                     System.out.println("!!! Player " + (j + 1) + " says BINGO !!!");
                     end = true;
                 }
             }
         }
         System.out.println(getNumberOfPulledChips() + " chips were called.");
    }

}
