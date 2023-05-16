package at.fhv.lka2;

import at.fhv.lka2.util.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DjakstraTests {


    @Test
    void sanity() throws IOException {
        Graph<String, String, Integer> graph = Main.loadGraph("djakstra_test.json");

        List<Pair<String, Integer>> distances = graph.djakstra("A");

        assertTrue(distances.contains(new Pair("A", 0)));
        assertTrue(distances.contains(new Pair("B", 6)));
        assertTrue(distances.contains(new Pair("C", 8)));
        assertTrue(distances.contains(new Pair("D", 9)));
        assertTrue(distances.contains(new Pair("E", 10)));

        System.out.println(graph);
    }

    @Test
    void otherStartingNode() throws IOException {
        Graph<String, String, Integer> graph = Main.loadGraph("djakstra_test.json");

        List<Pair<String, Integer>> distances = graph.djakstra("D");

        assertTrue(distances.contains(new Pair("A", 9)));
        assertTrue(distances.contains(new Pair("B", 3)));
        assertTrue(distances.contains(new Pair("C", 5)));
        assertTrue(distances.contains(new Pair("D", 0)));
        assertTrue(distances.contains(new Pair("E", 7)));

        System.out.println(graph);
    }

    @Test
    void withObject() throws IOException {
        Graph<String, String, Integer> graph = Main.loadObjectGraph("djakstra_test.json");

        List<Pair<String, Integer>> distances = graph.djakstra("A");

        assertTrue(distances.contains(new Pair("A", 0)));
        assertTrue(distances.contains(new Pair("B", 6)));
        assertTrue(distances.contains(new Pair("C", 8)));
        assertTrue(distances.contains(new Pair("D", 9)));
        assertTrue(distances.contains(new Pair("E", 10)));

        System.out.println(graph);
    }
}
