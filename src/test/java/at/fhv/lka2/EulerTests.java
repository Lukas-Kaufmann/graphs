package at.fhv.lka2;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EulerTests {


    @Test
    void nikolausShouldHaveEulerPath() throws IOException {
        Graph<String, String, Integer> graph = Main.loadGraph("haus_des_nikolaus.json");

        assertTrue(graph.hasEulerPath());
    }

    @Test
    void testingGraphShouldNotHaveEulerPath() throws IOException {
        Graph<String, String, Integer>  graph = Main.loadGraph("sample-graph-input.json");

        assertFalse(graph.hasEulerPath());
    }
}
