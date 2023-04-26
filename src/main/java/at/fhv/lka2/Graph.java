package at.fhv.lka2;

import java.util.HashMap;
import java.util.Map;

public interface Graph<T, A> {
    void addVertex(T vertex);

    default void addDirectedEdge(T source, T destination) {
        addDirectedEdge(source, destination, new HashMap<String, A>());
    }

    void addDirectedEdge(T source, T destination, Map<String, A> attributes);

    default void addEdge(T a, T b) {
        addEdge(a, b, new HashMap<String, A>());
    }

    void addEdge(T a, T b, Map<String, > attributes);

    void removeVertex(T vertex);

    void removeEdge(T source, T destination);

}
