package Lab12;

import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author atb
 * @version 4/17/2018
 * @modifiedBy Kirsty Alexandra Nguegang
 */

public class CheckInventory
{
   private DictionaryInterface<CarID, Integer> hashDictionary;
    private static final int DEFAULT_CAPACITY = 5;
    private Random random;

    public CheckInventory()
    {
        random = new Random(101);
        createHashedDictionary();
    }

    public void createHashedDictionary()
    {
       this.hashDictionary = new HashedDictionary<>(DEFAULT_CAPACITY);
    }

    // Compares two files contents to determine if they are equal
    // by adding the first file to a hash table and scanning over the second file
    // to see if elements are equal
    public boolean compareInventory(String file1, String file2)
    {
        boolean same = true;
        if (file1.equals(file2))
        {
            throw new InputMismatchException("Duplicates were found.");
        }
        else
        {
            try
            {
                Scanner scan1 = new Scanner(new File(file1));
                while (scan1.hasNext())
                {
                    String line = scan1.nextLine();
                    Scanner lineScan = new Scanner(line);
                    lineScan.useDelimiter("\\W+");
                    while (lineScan.hasNext())
                    {
                        String charSeq = lineScan.next();
                        long numSeq = lineScan.nextLong();
                        this.hashDictionary.add(new CarID(charSeq, numSeq), 1);
                    }
                }
                System.out.println("The content of the hash table after " + file1 + " was processed: ");
                this.hashDictionary.displayHashTable();
                Scanner scan2 = new Scanner(new File(file2));
                while (scan2.hasNext() && same)
                {
                    String line = scan2.nextLine();
                    Scanner lineScan = new Scanner(line);
                    lineScan.useDelimiter("\\W+");
                    while (lineScan.hasNext())
                    {
                        String charSeq = lineScan.next();
                        long numSeq = lineScan.nextLong();
                        CarID car = new CarID(charSeq, numSeq);
                        if (this.hashDictionary.getValue(car) != null) // if value is equal to null, that means the files are not the same
                        {
                            this.hashDictionary.remove(car);
                        } else if (this.hashDictionary.remove(car) == null) // checking if they're are duplicates
                        {
                            same = false;
                        } else
                        {
                            same = false;
                        }
                    }
                }
            }
            catch (FileNotFoundException fnfe)
            {
                System.out.println(fnfe.getMessage());
            }
            if (!this.hashDictionary.isEmpty()) // hash table will be empty if both files are equal
            {
                same = false;
            }
            System.out.println("The content of the hash table after " + file2 + " was processed: ");
            this.hashDictionary.displayHashTable();
        }
        return same;
         // this is a stub
    }

    // Generates random CarID objects and writes them to a TreeSet to ensure uniqueness
    // Once distinctness has been established, writes the CarID objects to randomFile.txt
    public TreeSet<CarID> generateContentAndSaveToRandomFile(int numOfCarIDs, String randomFile)
    {
        TreeSet<CarID> tree = new TreeSet<>();
        PrintWriter pw = null;
        long defaultSequence = 100000000000000L;
        try
        {
            pw = new PrintWriter(new File(randomFile));
            for (int i = 0; i < numOfCarIDs; i++)
            {
                long randLong = (long) (Math.random() * defaultSequence);
                StringBuilder randomString = new StringBuilder();
                int sizeOfString = 0;
                while (sizeOfString < CarID.CHARACTER_SEQUENCE_LENGTH)
                {
                    int randLetter = this.random.nextInt(90 - 65) + 65; // ascii values of letters
                    randomString.append((char)randLetter);
                    sizeOfString++;
                }
                CarID car = new CarID(randomString.toString(), randLong);
                if (!tree.contains(car))
                    pw.println(car.getCharacterSequence() + " " + car.getNumericSequence()); // checking randomFile.txt doesn't have duplicates
                tree.add(car);
            }
            pw.close();
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        return tree;
    }

    // Writes the contents of the tree returned by generateContentAndSaveToRandomFile to sortedFile.txt
    public void saveSortedContentToSortedFile(TreeSet<CarID> tree, String sortedFile)
    {
        try
        {
            PrintWriter pw = new PrintWriter(new File(sortedFile));
            Iterator<CarID> itr = tree.iterator();
            while (itr.hasNext())
            {
                CarID car = itr.next();
                pw.println(car.getCharacterSequence() + " " + car.getNumericSequence());
            }
            pw.close();
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
    }

    // Uses the tree returned by generateContentAndSaveToRandomFile to randomly skip over which elements to write to sortedFile.txt
    public void createCorruptedFile(TreeSet<CarID> tree, String corruptedFile)
    {
         try
         {
             PrintWriter pw = new PrintWriter(new File(corruptedFile));
             Iterator<CarID> itr = tree.iterator();
             while (itr.hasNext())
             {
                 CarID car = itr.next();
                 int randNum = this.random.nextInt();
                 if (randNum < 0)
                     randNum = randNum * -1;
                 if (randNum % 2 == 0)
                 {
                    pw.println(car.getCharacterSequence() + " " + car.getNumericSequence());
                 }
             }
             pw.close();
         }
         catch (FileNotFoundException fnfe)
         {
             System.out.println(fnfe.getMessage());
         }
    }
    // TODO Project 2 Part1
    // uncomment main when the class
    // skeleton is in place

    public static void main(String[] args)
    {
        String receivedFile = "randomFile.txt";
        String sentFile = "sortedFile.txt";
        String corruptedFile = "corruptedFile.txt";
        CheckInventory checker = new CheckInventory();

        //try
        {
            System.out.println("How many CarIDs to generate?");
            Scanner keyboard = new Scanner(System.in);
            int amount = keyboard.nextInt();
            TreeSet<CarID> sortedSet = checker.generateContentAndSaveToRandomFile(amount, receivedFile);
            checker.saveSortedContentToSortedFile(sortedSet, sentFile);
            checker.createCorruptedFile(sortedSet, corruptedFile);
            System.out.println("\n*** Checking if \"" + sentFile + "\" and \"" + receivedFile + "\" have the same elements ***");
            boolean same = checker.compareInventory(receivedFile, sentFile);
            System.out.println("--> the elements in files \"" + receivedFile
                    + "\" and \"" + sentFile
                    + " are " + (same ? "" : "NOT ") + "the same");


            System.out.println("\n*** Checking if \"" + sentFile + "\" and \"" + corruptedFile + "\" have the same elements ***");
            checker.createHashedDictionary();
            same = checker.compareInventory(sentFile, corruptedFile);
            System.out.println("--> the elements in files \"" + corruptedFile
                    + "\" and \"" + sentFile
                    + " are " + (same ? "" : "NOT ") + "the same");
        }
            // catch (IOException ioe)
//        {
//            System.out.println("There was an error in reading or opening the file: ");
//            System.out.println(ioe.getMessage());
//        } catch (InputMismatchException ime)
//        {
//            System.out.println("Input Mismatch Exception");
//            System.out.println(ime.getMessage());
//        }
        System.out.println("\nBye!");
    }  // end main
}

