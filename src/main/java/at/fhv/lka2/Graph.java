package at.fhv.lka2;

import at.fhv.lka2.util.Pair;

import java.util.*;

public interface Graph<T, K, V> {
    void addVertex(T vertex);

    void addDirectedEdge(T source, T destination, Map<K, V> attributes);

    void addEdge(T a, T b, Map<K, V> attributes);

    void removeVertex(T vertex);

    void removeEdge(T source, T destination);

    List<T> getNeighbours(T vertex);

    List<Pair<T, Integer>> getNeighbourDistances(T vertex);

    List<T> getVerteces();

    default boolean hasEulerPath() {
        List<T> verteces = getVerteces();

        int oddCount = 0;

        for (T v : verteces) {
            List<T> neighbours = this.getNeighbours(v);
            oddCount += neighbours.size() % 2;
        }

        return oddCount <= 2;
    }

    default List<Pair<T, Integer>> djakstra(T startVertex) {
        Set<T> visited = new HashSet<>();
        Map<T, Integer> lengths = new HashMap<>();
        lengths.put(startVertex, 0);

        while (visited.size() < this.getVerteces().size()) {

            Integer distance = Integer.MAX_VALUE;
            T nearest = null;
            for (Map.Entry<T, Integer> entry : lengths.entrySet()) {
                if (distance >= entry.getValue() && !visited.contains(entry.getKey())) {
                    distance = entry.getValue();
                    nearest = entry.getKey();
                }
            }

            visited.add(nearest);
            for (Pair<T, Integer> edge : this.getNeighbourDistances(nearest)) {
                Integer distanceToOther = lengths.get(edge.first);
                if (distanceToOther == null) {
                    distanceToOther = Integer.MAX_VALUE;
                }
                if (!visited.contains(edge.first) && distance + edge.second < distanceToOther) {
                    lengths.put(edge.first, distance + edge.second);
                }
            }
        }

        return lengths.entrySet().stream().map((entry) -> new Pair<>(entry.getKey(), entry.getValue())).toList();
    }

    default void prim() {

    }

}
