package Lab03;
import java.util.Arrays;
/**
 * A class that implements the ADT set by using a linked bag.
 * The set is never full.
 *
 * @author Kirsty Alexandra Nguegang
 * @version 2/6/2018
 */
public class LinkedSetWithLinkedBag<T extends Comparable<? super T>> implements SetInterface<T>
{
    private LinkedBag<T> bagOfSetEntries;

    /**
     * Creates a set from a new, empty linked bag.
     */
    public LinkedSetWithLinkedBag()
    {
        //TODO Project1
        this.bagOfSetEntries = new LinkedBag<T>();
    } // end default constructor

    public boolean add(T newEntry)
    {
        //TODO Project1
        boolean addResult = true;
        if (!this.bagOfSetEntries.contains(newEntry))
        {
            this.bagOfSetEntries.add(newEntry);
            addResult = true;
        }
        return addResult; //THIS IS A STUB
    } // end add

    public T[] toArray()
    {
        //TODO Project1
        return this.bagOfSetEntries.toArray(); //THIS IS A STUB
    } // end toArray

    public boolean isEmpty()
    {
        //TODO Project1
        return this.bagOfSetEntries.isEmpty(); //THIS IS A STUB
    } // end isEmpty

    public boolean contains(T anEntry)
    {
        //TODO Project1
        return this.bagOfSetEntries.contains(anEntry); //THIS IS A STUB
    } // end contains

    public void clear()
    {
        //TODO Project1
        this.bagOfSetEntries.clear();
    } // end clear

    public T remove()
    {
        //TODO Project1
        return this.bagOfSetEntries.remove(); //THIS IS A STUB
    } // end remove

    public boolean removeElement(T anEntry)
    {
        //TODO Project1
        return this.bagOfSetEntries.removeElement(anEntry); //THIS IS A STUB
    } // end remove

    // Displays a set.
    public void displaySet()
    {
        //TODO Project1
        T [] arr = this.toArray();
        if (this.isEmpty())
        {
            System.out.println("The set is empty.");
        }
        else
        {
            System.out.println("The set contains "  + toArray().length + " string(s), as follows: ");
            for (int i = 0; i < toArray().length; i++)
            {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    } // end displaySet

    public static void main(String[] args)
    {
        String[] inputData = {"A", "B", "C", "D", "A", "C", "B", "B"};
        System.out.println("Creating aSet and adding to it elements from inputData: " + Arrays.toString(inputData));
        SetInterface<String> aSet = new LinkedSetWithLinkedBag<>();
        for (int i=0; i < inputData.length; i++)
        {
            aSet.add(inputData[i]);
        }
        aSet.displaySet();

        System.out.println("\nClearing aSet");
        aSet.clear();
        aSet.displaySet();
        System.out.println("aSet isEmpty returns " + aSet.isEmpty());

        System.out.println("\nCreating set1 and set2");
        SetInterface<String> set1 = new LinkedSetWithLinkedBag<>();
        SetInterface<String> set2 = new LinkedSetWithLinkedBag<>();

        System.out.println("\nAdding elements to set1");
        set1.add("A");
        set1.add("A");
        set1.add("B");
        set1.add("A");
        set1.add("C");
        set1.add("A");
        System.out.println("set1 is ");
        set1.displaySet();

        System.out.println("\nAdding elements to set2");
        set2.add("A");
        set2.add("B");
        set2.add("B");
        set2.add("A");
        set2.add("C");
        set2.add("C");
        set2.add("D");
        System.out.println("set2 is ");
        set2.displaySet();

        System.out.println("\nset1 contains A: " + set1.contains("A"));
        System.out.println("set1 contains E: " + set1.contains("E"));

        set1.removeElement("B");
        System.out.println("After removing B from set1, ");
        set1.displaySet();

        System.out.println();
        System.out.println("After removing " + set1.remove() + " from set1, ");
        set1.displaySet();
    } // end main
} // end LinkedSetWithLinkedBag
