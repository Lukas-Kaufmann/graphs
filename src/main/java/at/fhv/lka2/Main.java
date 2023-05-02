package at.fhv.lka2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    public static AdjacencylistGraph<String, String, Integer> loadGraph(String filename) throws IOException {
        AdjacencylistGraph<String, String, Integer> graph = new AdjacencylistGraph();
        Path filePath = Path.of("sample-graph-input.json");

        String content = Files.readString(filePath);
        JSONObject obj = new JSONObject(content);

        JSONArray vertexes = obj.getJSONArray("vertexes");

        for (Iterator<Object> it = vertexes.iterator(); it.hasNext(); ) {
            Object v = it.next();
            graph.addVertex((String) v);
        }

        JSONArray edges = obj.getJSONArray("edges");
        for (int i = 0; i < edges.length(); i+=1) {
            JSONObject edge = edges.getJSONObject(i);
            Map<String, Object> attributesObj = edge.getJSONObject("attributes").toMap();
            Map<String, Integer> attributes = new HashMap<>();
            for (Map.Entry<String, Object> entry : attributesObj.entrySet()) {
                attributes.put(entry.getKey(), (Integer) entry.getValue());
            }
            graph.addDirectedEdge(edge.getString("from"), edge.getString("to"), attributes);
        }

        return graph;
    }

    public static void main(String[] args) throws IOException {
        Graph graph = loadGraph("sample-graph1-input.json");
        System.out.println(graph.toString());

        Graph<String, String, String> graph1 = new ObjectGraph<>();
        //MatrixGraph<String, String, String> graph1 = new MatrixGraph<>(2);

        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.addVertex("D");

        graph1.addEdge("A", "B", new HashMap<>());
        graph1.addDirectedEdge("B", "C", new HashMap<>());

        graph1.addDirectedEdge("C", "A", new HashMap<>());

        graph1.removeEdge("A", "C");

        graph1.removeVertex("D");

        //System.out.println(graph1);

    }
}