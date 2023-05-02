package at.fhv.lka2;

import at.fhv.lka2.util.Pair;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class AdjacencylistGraph<T, K, V> implements Graph<T, K, V> {
    private Map<T, List<Pair<T, Map<K, V>>>> adjacencyList;

    public AdjacencylistGraph() {
        adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new LinkedList<>());
    }

    @Override
    public void addDirectedEdge(T source, T destination, Map<K, V> attributes) {
        if (!adjacencyList.containsKey(source))
            addVertex(source);

        if (!adjacencyList.containsKey(destination))
            addVertex(destination);

        adjacencyList.get(source).add(new Pair(destination, attributes));
    }

    @Override
    public void addEdge(T a, T b, Map<K, V> attributes) {
        if (!adjacencyList.containsKey(a))
            addVertex(a);

        if (!adjacencyList.containsKey(b))
            addVertex(b);

        addDirectedEdge(a, b, attributes);
        addDirectedEdge(b, a, attributes);
    }

    @Override
    public void removeVertex(T vertex) {
        adjacencyList.values().forEach(list -> list.remove(vertex));
        adjacencyList.remove(vertex);
    }

    @Override
    public void removeEdge(T source, T destination) {
        List<T> sourceList = adjacencyList.get(source);
        List<T> destinationList = adjacencyList.get(destination);

        if (sourceList != null)
            sourceList.remove(destination);

        if (destinationList != null)
            destinationList.remove(source);
    }

    @Override
    public List<Pair<T, Map<K, V>>> getNeighbours(T vertex) {
        adjacencyList.get(vertex);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T vertex : adjacencyList.keySet()) {
            builder.append(vertex.toString()).append(": ");

            for (T neighbor : adjacencyList.get(vertex)) {
                builder.append(neighbor.toString()).append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}