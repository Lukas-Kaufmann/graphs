package at.fhv.lka2;

import java.util.Map;

public class Edge<T, K, V> {
    public T from;
    public T to;

    public Map<K, V> attributes;

    public Edge(T from, T to, Map<K,V> attributes) {
        this.from = from;
        this.to = to;
        this.attributes = attributes;
    }

}
