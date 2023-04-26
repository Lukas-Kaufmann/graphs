package at.fhv.lka2;

import java.util.HashMap;
import java.util.Map;

public interface Graph<T> {
    void addVertex(T vertex);

    void addDirectedEdge(T source, T destination);

    void addEdge(T a, T b);

    void removeVertex(T vertex);

    void removeEdge(T source, T destination);

}
