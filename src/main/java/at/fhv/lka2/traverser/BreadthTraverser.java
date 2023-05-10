package at.fhv.lka2.traverser;

import at.fhv.lka2.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BreadthTraverser<V> implements Traverser<V> {
    @Override
    public List<V> traverse(Graph<V, ?, ?> graph, V startVertex) {
        List<V> path = new LinkedList<>();
        HashSet<V> closeList = new HashSet<>();
        LinkedList<V> openList = new LinkedList<>();
        openList.add(startVertex);

        while (!openList.isEmpty()) {
            V elem = openList.removeFirst();
            if (!closeList.contains(elem)) {
                path.add(elem);
                closeList.add(elem);

                List<V> neighbours = graph.getNeighbours(elem);

                for (V n : neighbours) {
                    openList.addLast(n);
                }
            }
        }
        return path;
    }
}
