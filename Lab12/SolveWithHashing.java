package Lab12;

import java.util.*;

/**
 * A class that implements solutions to several problems using hashing
 *
 * @author Kirsty Alexandra Nguegang
 * @version 4/17/2018
 */
public class SolveWithHashing
{
    private DictionaryInterface<Integer, Integer> hashedDictionary;
    private final int DEFAULT_CAPACITY = 5;

    public SolveWithHashing()
    {
        createHashedDictionary();
    }

    public void createHashedDictionary()
    {
        this.hashedDictionary = new HashedDictionary<>(DEFAULT_CAPACITY);
    }

    private void testDisplayHashTable()
    {
        System.out.println("displaying empty dictionary");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.add(1, 1);
        this.hashedDictionary.add(7, 7);
        System.out.println("displaying dictionary after 2 entries have been added");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.add(13, 13);
        this.hashedDictionary.add(17, 17);
        this.hashedDictionary.add(8, 8);
        System.out.println("displaying dictionary after 3 additional entries have been added");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.remove(1);
        this.hashedDictionary.remove(17);
        System.out.println("displaying dictionary after 2 entries have been removed");
        this.hashedDictionary.displayHashTable();
    }

    public Integer getFirstRepeatedElement(int[] a)
    {
        // TODO Project1 #1
        for (int i = 0; i < a.length; i++)
        {
            if (this.hashedDictionary.getValue(a[i]) == null)
            {
                this.hashedDictionary.add(a[i], i + 1);
            }
            else
            {
                if (this.hashedDictionary.getValue(a[i]) > 0)
                {
                    this.hashedDictionary.add(a[i], (-1 * this.hashedDictionary.getValue(a[i])));
                }
            }
        }
        Iterator<Integer> iterator = this.hashedDictionary.getValueIterator();
        Integer result = null;
        Integer item = iterator.next();
        Integer item2 = null;
        while (iterator.hasNext())
        {
            if (item > 0)
            {
                item = iterator.next();
            }
            else
            {
                item2 = iterator.next();
                if (item2 < 0)
                {
                    if (item2 > item)
                    {
                        item = item2;
                    }
                }
            }
        }
        this.hashedDictionary.displayHashTable();
        if (item < 0)
            result = a[((-1) * item) - 1];
        else
            result = null;
        return result;
    }

    public boolean lookForPair(int[] a, int[] b, int k)
    {
        // TODO Project1 #2
        boolean found = false;
        int [] smallest;
        int [] biggest;
        if (a.length < b.length)
        {
            smallest = a;
            biggest = b;
        }
        else
        {
            smallest = b;
            biggest = a;
        }
        System.out.println("toPutInHashTable: " + Arrays.toString(smallest));
        System.out.println("toCheck: " + Arrays.toString(biggest));
        HashedDictionary<Integer, Integer> hashTable = new HashedDictionary<>(smallest.length);
        for (int i = 0; i < smallest.length; i++)
        {
            hashTable.add(smallest[i], smallest[i]);
        }
        for (int i = 0; i < biggest.length && !found; i++)
        {
            int difference = k - biggest[i];
            Integer result = hashTable.getValue(difference);
            if (result != null)
            {
                System.out.println("The pair {" + biggest[i] + ", " + difference + "} adds up to " + k);
                found = true;
            }
        }
        return found;
    }

    public static void main(String[] args)
    {
        ArrayList<int[]> toCheck = new ArrayList<>();
        toCheck.add(new int[]{9, 3, 5, 1, 2, 2, 5, 3});
        toCheck.add(new int[]{6, 6, 3, 2, 1, 2, 2, 3});
        toCheck.add(new int[]{2, 1, 6, 2, 3, 2, 3, 6});
        toCheck.add(new int[]{3, 2, 1, 2, 2, 3, 6, 6});
        toCheck.add(new int[]{9, 3, 4, 4, 4, 1, 2, 2, 5, 3});
        toCheck.add(new int[]{3, 3, 3, 3, 3, 3, 3});
        toCheck.add(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        toCheck.add(new int[]{8, 1, 2, 3, 4, 5, 6, 7});

        SolveWithHashing solver = new SolveWithHashing();

        System.out.println("\n\t*** Testing displayHashTable ***");
        solver.testDisplayHashTable();

        System.out.println("\n\t*** Find The First Element With Duplicate ***");
        Integer firstDuplicate;
        for (int[] array : toCheck)
        {
            solver.createHashedDictionary();
            firstDuplicate = solver.getFirstRepeatedElement(array);
            if (firstDuplicate != null)
                System.out.println("--> the first element that is repeated is: " + firstDuplicate);
            else
                System.out.println("--> duplicates not found");
            System.out.println();
        }

        System.out.println("\n\t*** Check If There Exists A Pair Of Elements That Add Up To k ***");
        boolean found;
        for (int k = 2; k < 10; k++)
        {
            System.out.println("k = " + k);
            for (int i = 1; i < toCheck.size(); i++)
            {
                solver.createHashedDictionary();
                found = solver.lookForPair(toCheck.get(i - 1), toCheck.get(i), k);
                System.out.println("--> pair that add up to " + k + (found ? "" : " NOT") + " found.");
            }
            System.out.println();
        }
        System.out.println("\nBye!");
    }  // end main
}
