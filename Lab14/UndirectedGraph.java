package Lab14;

//@author Kirsty Alexandra Nguegang

import java.util.Queue;
import java.util.Stack;

public class UndirectedGraph<T> extends DirectedGraph<T> implements ConnectedGraphInterface<T>, GraphInterface<T>, java.io.Serializable
{
    public UndirectedGraph()
    {
        super();
    }

    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        return super.addEdge(begin, end, edgeWeight) && super.addEdge(end, begin, edgeWeight);
    }
    public boolean addEdge(T begin, T end)
    {
        return super.addEdge(begin, end) && super.addEdge(end, begin);
    }
    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges()/2;
    }

    public Stack<T> getTopologicalOrder()
    {
        throw new UnsupportedOperationException("UnsupportedOperationException thrown: \"Topological sort illegal in an undirected graph.");
    }

    public boolean isConnected(T origin)
    {
        Queue<T> queue = super.getBreadthFirstTraversal(origin);
        return queue.size() == getNumberOfVertices();
    }
}
