package at.fhv.lka2;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class NikolausTests {


    @Test
    void shouldHaveEulerPath() throws IOException {
        Graph<String, String, Integer> graph = Main.loadGraph("haus_des_nikolaus.json");

        assertTrue(graph.hasEulerPath());
    }
}
