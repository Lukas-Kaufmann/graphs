package at.fhv.lka2;

import at.fhv.lka2.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Graph<T, K, V> {
    void addVertex(T vertex);

    void addDirectedEdge(T source, T destination, Map<K, V> attributes);

    void addEdge(T a, T b, Map<K, V> attributes);

    void removeVertex(T vertex);

    void removeEdge(T source, T destination);

    List<Pair<T, Map<K, V>>> getNeighbours(T vertex);

}
