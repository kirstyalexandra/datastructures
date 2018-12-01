package Lab06;

import java.util.*;

/**
 * This class creates all permutations of the given string
 *
 * @author Kirsty Alexandra Nguegang
 * @version 2/27/2018
 */
public class UniqueAnagrams
{
    private ArrayList<String> anagrams;

    public UniqueAnagrams()
    {
        this.anagrams = new ArrayList<>();
    }

    public void permutations(String word)
    {
        permutations("", word);
        System.out.println("Possible anagrams = " + this.anagrams);

        TreeSet<String> toTest = new TreeSet(this.anagrams);
        System.out.println("Expected unique and sorted anagrams = " + toTest);
        System.out.println();

        sort(); // duplicates will be removed during the sorting process
    }

    private void permutations(String prefix, String suffix)
    {
        int numOfChars = suffix.length();
        if (numOfChars == 1)
        {
            this.anagrams.add(prefix + suffix);
        }
        else
        {
            //TODO Project2
            for (int i = 0; i < numOfChars; i++)
            {
                permutations(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1, numOfChars));
            }
        }
    }

    private void sort()
    {
        //TODO Project2
        for (int i = 0; i < anagrams.size(); i++)
        {
            int indexOfNextSmallest = getIndexOfSmallestAndRemoveDuplicates(i, this.anagrams.size());
            swap(i, indexOfNextSmallest);
        }
        // calls getIndexOfSmallestAndRemoveDuplicates(index, this.anagrams.size());
        // calls swap(index, indexOfNextSmallest);
    }

    private int getIndexOfSmallestAndRemoveDuplicates(int first, int last)
    {
        //TODO Project2
        Set<String> dupes = new HashSet<>();
        String min = anagrams.get(first);
        int indexOfMin = first;
        for (int i = 0; i < anagrams.size() - 1; i++)
        {
            if(anagrams.get(i + 1).compareTo(min) < 0)
            {
                min = anagrams.get(i + 1);
                indexOfMin = anagrams.indexOf(anagrams.get(i + 1));
            }
        }
        dupes.addAll(anagrams);
        anagrams.clear();
        anagrams.addAll(dupes);
        Collections.sort(anagrams);
        // as the smallest is being found removes duplicates
        return indexOfMin; // THIS IS A STUB
    }

    private void swap(int i, int j)
    {
        //TODO Project2
        if (i != j)
        {
            int temp = i;
            i = j;
            j = temp;

        }
    }

    private void display()
    {
        System.out.println("Computed unique and sorted anagrams = " + this.anagrams);
    }

    public static void main(String[] args)
    {
        UniqueAnagrams uniqueAnagrams = new UniqueAnagrams();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter a word");
        String word = keyboard.next();

        uniqueAnagrams.permutations(word);
        uniqueAnagrams.display();
    }
}
