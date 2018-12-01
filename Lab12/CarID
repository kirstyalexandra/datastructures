package Lab12;
/*
* @author: Kirsty Alexandra Nguegang
* */

public class CarID implements Comparable<CarID>
{
    private String characterSequence;
    private long numericSequence;
    public static final int CHARACTER_SEQUENCE_LENGTH = 3;
    public static final int NUMERIC_SEQUENCE_LENGTH = 14;
    public final String DEFAULT_CHARACTER_SEQUENCE = "???";
    public final long DEFAULT_NUMERIC_SEQUENCE = 10000000000000L;

    public CarID(String charSeq, long numSeq)
    {
        setCharacterSequence(charSeq);
        setNumericSequence(numSeq);
    }

    public void setCharacterSequence(String charSeq)
    {
        this.characterSequence = charSeq;
    }
    public void setNumericSequence(long numSeq)
    {
        this.numericSequence = numSeq;
    }
    public String getCharacterSequence()
    {
        return this.characterSequence;
    }
    public long getNumericSequence()
    {
        return this.numericSequence;
    }
    public boolean equals(Object o)
    {
        boolean same = false;
        if (this == o)
        {
            same = true;
        }
        else if (o == null || o.getClass() != getClass())
        {
            same = false;
        }
        else
        {
            CarID other = (CarID) o;
            if (this.numericSequence == other.numericSequence && this.characterSequence.equals(other.characterSequence))
                same = true;
        }
        return same;
    }
    public int compareTo(CarID cd)
    {
        int result = 0;
        int numComparison = Long.compare(this.numericSequence, cd.numericSequence);
        int stringComparison = this.characterSequence.compareTo(cd.characterSequence);
        if (numComparison < 0 || stringComparison < 0)
        {
            result = 1;
        }
        else if (numComparison > 0 || stringComparison > 0)
        {
            result = -1;
        }
        return result;
    }
    public int hashCode()
    {
        int G = 31;
        long bits = Double.doubleToLongBits(this.numericSequence);
        int code = G * (this.characterSequence.hashCode()) + (int) (bits ^ (bits >> 32));
        return code;
    }
    public String toString()
    {
        return "CarID{" + getCharacterSequence() + " " + getNumericSequence() + "}";
    }


}
