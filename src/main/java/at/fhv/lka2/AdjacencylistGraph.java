package at.fhv.lka2;

import at.fhv.lka2.util.Pair;


import java.util.*;
import java.util.stream.Collectors;

public class AdjacencylistGraph<T, K, V extends Comparable> implements Graph<T, K, V> {
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
        List<Pair<T, Map<K, V>>> sourceList = adjacencyList.get(source);
        List<Pair<T, Map<K, V>>> destinationList = adjacencyList.get(destination);

        if (sourceList != null)
            sourceList.removeIf((e) -> e.first == source);

        if (destinationList != null)
            sourceList.removeIf((e) -> e.first == destination);
    }

    @Override
    public List<T> getNeighbours(T vertex) {
        return adjacencyList.get(vertex).stream().map((it) -> it.first).collect(Collectors.toList());
    }

    @Override
    public List<Pair<T, Integer>> getNeighbourDistances(T vertex) {
        return adjacencyList.get(vertex).stream().map((it) -> new Pair<>(it.first, (Integer) it.second.get("weight"))).collect(Collectors.toList());
    }

    @Override
    public List<T> getVerteces() {
        return this.adjacencyList.keySet().stream().toList();
    }

    @Override
    public List<Edge<T, K, V>> getEdges() {
        List<Edge<T, K, V>> edges = new LinkedList<>();

        for (Map.Entry<T, List<Pair<T, Map<K, V>>>> vertex : this.adjacencyList.entrySet()) {

            for (Pair<T, Map<K, V>> neighbour : vertex.getValue()) {
                edges.add(new Edge(vertex.getKey(), neighbour.first, neighbour.second));
            }

        }

        return edges;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T vertex : adjacencyList.keySet()) {
            builder.append(vertex.toString()).append(": ");

            for (Pair<T, Map<K, V>> neighbor : adjacencyList.get(vertex)) {
                builder.append(neighbor.first.toString()).append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}