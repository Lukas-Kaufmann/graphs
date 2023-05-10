package at.fhv.lka2.traverser;

import at.fhv.lka2.Graph;

import java.util.LinkedList;
import java.util.List;

public class RecursiveDepthTraverser<V> implements Traverser<V> {

    private List<V> traverse_rec(Graph<V, ?, ?> graph, V vertex, List<V> closeList, List<V> path) {
        if (closeList.contains(vertex)) {
            return path;
        }
        closeList.add(vertex);
        path.add(vertex);

        List<V> neighbours = graph.getNeighbours(vertex);
        neighbours.forEach((v) ->
                traverse_rec(graph, v, closeList, path)
        );

        return path;
    }

    @Override
    public List<V> traverse(Graph<V, ?, ?> graph, V startVertex) {
        return traverse_rec(graph, startVertex, new LinkedList<>(), new LinkedList<>());
    }
}
