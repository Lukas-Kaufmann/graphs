package at.fhv.lka2.traverser;

import at.fhv.lka2.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthTraverser<V> implements Traverser<V> {

    @Override
    public List<V> traverse(Graph<V, ?, ?> graph, V startVertex) {
        List<V> path = new LinkedList<>();

        HashSet<V> closeList = new HashSet<>();

        Stack<V> openList = new Stack<>();
        openList.add(startVertex);
        while (!openList.isEmpty()) {
            V elem = openList.pop();
            if (!closeList.contains(elem)) {
                path.add(elem);
                closeList.add(elem);
                List<V> neighbours = graph.getNeighbours(elem);
                for (V n : neighbours) {
                    openList.add(n);
                }
            }

        }
        return path;
    }
}
