package Lab11;

/**
 * @author Kirsty Alexandra Nguegang
 * @version 4/10/2018
 */

public class BingoChip
{
    private char letter;
    private int number;

    public BingoChip(int number)
    {
        setNumber(number);
        setLetter();
    }

    private void setNumber(int number)
    {
       // TODO Project 2.1
        this.number = number;
    }

    private void setLetter()
    {
        // TODO Project 2.1
        int index = (this.number - 1)/BingoCard.MAX_VALUES_PER_LETTER;
        this.letter = BingoCard.BINGO_KEYS.charAt(index);
    }

    public int getNumber()
    {
        // TODO Project 2.1
        return this.number;
    }

    public char getLetter()
    {
        // TODO Project 2.1
        return this.letter;
    }

    public String toString()
    {
        // TODO Project 2.1
        return getLetter() + " " + getNumber();
    }
}