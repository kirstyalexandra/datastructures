package Lab11;
/**
 * @author Kirsty Alexandra Nguegang
 * @version 4/10/2018
 */
import java.util.TreeSet;

public class Player
{
    private TreeSet<Character> bingoChars;
    private BingoCard bingoCard;

    public Player()
    {
        // TODO 2.3
        this.bingoChars = new TreeSet<>();
        this.bingoCard = new BingoCard();
        printCard();
    }

    public boolean isWinner()
    {
    	// TODO 2.3
        return this.bingoChars.size() == BingoCard.BINGO_KEYS.length();
    }

    public void checkCard(BingoChip chip)
    {
        // TODO 2.3
        if (this.bingoCard.hasNumber(chip))
        {
            this.bingoChars.add(chip.getLetter());
        }
    }

    public void printCard()
    {
        // TODO 2.3
       System.out.println(this.bingoCard.toString());
    }

}
