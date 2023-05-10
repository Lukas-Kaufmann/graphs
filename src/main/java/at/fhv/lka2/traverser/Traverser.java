package at.fhv.lka2.traverser;

import at.fhv.lka2.Graph;

import java.util.List;

public interface Traverser<V> {

    List<V> traverse(Graph<V, ?, ?> graph, V startVertex);


    default void printTraverse(Graph graph, V startVertex) {
        List<V> path = traverse(graph, startVertex);
        for (V v : path) {
            System.out.println(v);
        }
    }

}
