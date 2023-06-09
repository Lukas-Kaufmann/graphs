package at.fhv.lka2;

import at.fhv.lka2.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectGraph<T, K, V extends Comparable> implements Graph<T, K, V> {



    private List<T> verteces;

    private List<Edge<T, K, V>> edges;

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

    @Override
    public List<T> getNeighbours(T vertex) {
        List<T> list = new LinkedList<>();
        for (Edge e : this.edges) {
            if (e.from.equals(vertex)) {
                list.add((T) e.to);
            }
        }
        return list;
    }

    @Override
    public List<Pair<T, Integer>> getNeighbourDistances(T vertex) {
        List<Pair<T, Integer>> list = new LinkedList<>();
        for (Edge e : this.edges) {
            if (e.from.equals(vertex)) {
                list.add(new Pair(e.to, e.attributes.get("weight")));
            }
        }
        return list;
    }

    @Override
    public List<T> getVerteces() {
        return this.verteces;
    }

    @Override
    public List<Edge<T, K, V>> getEdges() {
        return this.edges;
    }

}
