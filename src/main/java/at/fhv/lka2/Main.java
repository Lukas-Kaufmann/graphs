package at.fhv.lka2;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        AdjacencylistGraph<String, String, String> graph = new AdjacencylistGraph<>();
        //MatrixGraph<String, String, String> graph = new MatrixGraph<>(2);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", new HashMap<>());
        graph.addDirectedEdge("B", "C", new HashMap<>());

        graph.addDirectedEdge("C", "A", new HashMap<>());

        graph.removeEdge("A", "C");

        graph.removeVertex("D");

        System.out.println(graph);

    }
}