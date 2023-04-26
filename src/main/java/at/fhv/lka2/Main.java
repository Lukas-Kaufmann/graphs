package at.fhv.lka2;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //AdjacencylistGraph<String> graph = new AdjacencylistGraph<String>();
        MatrixGraph<String> graph = new MatrixGraph(2);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addDirectedEdge("B", "C");

        graph.addDirectedEdge("C", "A");

        graph.removeEdge("A", "C");

        graph.removeVertex("D");

        System.out.println(graph);

    }
}