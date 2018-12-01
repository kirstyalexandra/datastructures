package Lab08;
// Kirsty Alexandra Nguegang
/**
 * @version 3/13/2018.
 */
public class CircularArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue; // Circular array of queue entries and one unused location
    private int frontIndex; // Index of front entry
    private int backIndex;  // Index of back entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_CAPACITY = 10000;

    public CircularArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public CircularArrayQueue(int initialCapacity)
    {
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        this.queue = tempQueue;
        this.frontIndex = 0;
        this.backIndex = initialCapacity;
        this.initialized = true;
    } // end constructor

    public CircularArrayQueue(T[] initialContent)
    {
        // TODO Project 2A
        this(initialContent.length);
        for (int i = 0; i < initialContent.length; i++)
        {
           this.enqueue(initialContent[i]);// filling this.queue with entries
        }

    } // end constructor

    public void enqueue(T newEntry)
    {
        // ***TESTING
        // TODO Project 2A
        checkInitialization();
        ensureCapacity();
        this.backIndex = (backIndex + 1) % queue.length;
        this.queue[backIndex] = newEntry;
//        System.out.println("enqueue(" + newEntry + ")");
//        System.out.println("queue[" + backIndex + "] = " + newEntry);  // ***TESTING
    } // end enqueue

    public T getFront() throws EmptyQueueException
    {
        // TODO Project 2A
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return queue[frontIndex];
    } // end getFront

    public T dequeue() throws EmptyQueueException
    {
        // TODO Project 2A
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
        {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        }
    } // end dequeue

    public boolean isEmpty()
    {
        // TODO Project 2A
        return frontIndex == ((backIndex + 1) % this.queue.length); // THIS IS A STUB
    } // end isEmpty

    public void clear()
    {
        // null out only the used slots
        for (int i = 0; i < queue.length; i++)
        {
            if (this.queue[i] != null)
            {
                this.queue[i] = null;
            }
        }
        // TODO Project 2A

    } // end clear


    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!this.initialized)
            throw new SecurityException("CircularArrayQueue object is not initialized properly.");
    } // end checkInitialization

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a queue " +
                    "whose capacity exceeds " +
                    "allowed maximum.");
    } // end checkCapacity

    // Doubles the size of the array queue if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity()
    {
        // TODO Project 2A
        if (frontIndex == ((backIndex + 2) % queue.length))
        {
            T[] oldQueue = this.queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize - 1);
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            this.queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++)
            {
                this.queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }
            frontIndex = 0;
            backIndex = oldSize - 2;
        }
    } // end ensureCapacity
}
