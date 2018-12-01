package Lab13;

import java.util.*;
/**
 * @author Kirsty Alexandra Nguegang
 * @version 4/24/2018
 */

public class WorkingWithHeaps
{
    private ArrayList<Node<Integer>> lists;

    public WorkingWithHeaps()
    {
        this.lists = new ArrayList<>();
        createLists();
    }

    public Node<Integer> mergeKSortedLists()
    {
        // TODO Project 4b
        Node<Integer> headOfMergedList = null;
        PriorityQueue<Node<Integer>> heap = new PriorityQueue<>(new Comparator<Node<Integer>>()
        {
            public int compare(Node<Integer> o1, Node<Integer> o2) { return o1.data - o2.data;}
        });
        for (Node<Integer> headOfEachList : this.lists)
        {
            if (headOfEachList != null)
            {
                heap.offer(headOfEachList);
                Node<Integer> element = headOfEachList.next;
                while (element != null)
                {
                    heap.offer(element);
                    element = element.next;
                }
            }
        }

        if (!heap.isEmpty())
            headOfMergedList = heap.poll();

        Node<Integer> current = headOfMergedList;
        while (current != null && !heap.isEmpty())
        {
            current.next = heap.poll();
            current = current.next;
        }
        return headOfMergedList;
    }

    public void createLists()
    {
        Scanner keyboard = new Scanner(System.in);
        int numberOfLists;
        do
        {
            System.out.println("How many lists to create?");
            numberOfLists = keyboard.nextInt();
        } while (numberOfLists < 1);

        final int MIN_SIZE = 5;
        int maxSize;
        do
        {
            System.out.println("Maximum number of elements for the lists?");
            maxSize = keyboard.nextInt();
        } while (maxSize < MIN_SIZE);

        Random random = new Random(11);
        final int MAX_FIRST_VALUE = 7;
        Node<Integer> firstNode;
        Node<Integer> current;
        int sizeUpTo;
        int randomNumber;

        for (int list = 0; list < numberOfLists; list++)
        {
            firstNode = null;
            current = null;
            sizeUpTo = random.nextInt(maxSize - MIN_SIZE + 1) + MIN_SIZE;
            randomNumber = random.nextInt(MAX_FIRST_VALUE) + 1;
            for (int i = 1; i <= sizeUpTo; i++)
            {
                if (random.nextBoolean())
                {
                    if (firstNode == null)
                    {
                        firstNode = new Node<>(randomNumber);
                        current = firstNode;
                    }
                    else
                    {
                        current.next = new Node<>(randomNumber);
                        current = current.next;
                    }
                    randomNumber += i;
                }
                randomNumber++;
            }
            this.lists.add(firstNode);
        }
        displayCreatedLists();
    }

    private void displayCreatedLists()
    {
        System.out.println("\nCreated lists:");
        for (Node<Integer> eachList: this.lists)
        {
            Node<Integer> current = eachList;
            if (current == null)
            {
                System.out.print("<empty>");
            }
            else
            {
                while (current != null)
                {
                    System.out.print(current.data + " ");
                    current = current.next;
                }
            }
            System.out.println();
        }
        // TODO Project 4a
    }

    public static void main(String args[])
    {
        WorkingWithHeaps tester = new WorkingWithHeaps();

        Node<Integer> mergedHead = tester.mergeKSortedLists();
        System.out.println("\nMerged List:");
        if (mergedHead == null)
            System.out.println("The list is empty");
        else
        {
            Node<Integer> current = mergedHead;
            while (current != null)
            {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
        System.out.println("\n*** Done ***");
    } // end main

    private class Node<S>
    {
        private S data;
        private Node<S> next;

        private Node(S dataPortion)
        {
            this.data = dataPortion;
            this.next = null;
        }

        private Node(S dataPortion, Node nextNode)
        {
            this.data = dataPortion;
            this.next = nextNode;
        }
    } // end Node
}
