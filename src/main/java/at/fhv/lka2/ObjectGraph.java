package at.fhv.lka2;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectGraph<T, K, V> implements Graph<T, K, V> {

    private class Edge {
        private T from;
        private T to;

        private Map<K, V> attributes;

        private Edge(T from, T to, Map<K,V> attributes) {
            this.from = from;
            this.to = to;
            this.attributes = attributes;
        }

    }

    private List<T> verteces;

    private List<Edge> edges;

    public ObjectGraph() {
        this.verteces = new LinkedList<>();
        this.edges = new LinkedList<>();
    }

    @Override
    public void addVertex(T vertex) {
        this.verteces.add(vertex);
    }

    @Override
    public void addDirectedEdge(T source, T destination, Map<K, V> attributes) {
        this.edges.add(new Edge(source, destination, attributes));
    }

    public void addEdge(T a, T b, Map<K, V> attributes) {
        this.edges.add(new Edge(a, b, attributes));
        this.edges.add(new Edge(b, a, attributes));
    }

    @Override
    public void removeVertex(T vertex) {
        this.verteces.remove(vertex);
    }

    @Override
    public void removeEdge(T source, T destination) {
        this.edges.removeIf((e) -> e.from.equals(source) && e.to.equals(destination));
    }
}
