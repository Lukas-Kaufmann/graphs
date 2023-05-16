package at.fhv.lka2;

import at.fhv.lka2.util.Pair;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class MatrixGraph<T, K, V extends Comparable> implements Graph<T, K, V> {
    private int[][] adjacencyMatrix;
    private Map<T, Integer> vertexIndices;

    public MatrixGraph(int size) {
        adjacencyMatrix = new int[size][size];
        vertexIndices = new HashMap<>();
    }

    public void addVertex(T vertex) {
        if (vertexIndices.containsKey(vertex)) {
            return;
        }

        int index = vertexIndices.size();
        vertexIndices.put(vertex, index);

        if (index >= adjacencyMatrix.length) {
            int[][] newMatrix = new int[index * 2][index * 2];
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, adjacencyMatrix[i].length);
            }

            adjacencyMatrix = newMatrix;
        }
    }

    public void addDirectedEdge(T source, T destination, Map<K, V> attributes) {
        addVertex(source);
        addVertex(destination);

        int sourceIndex = vertexIndices.get(source);
        int destinationIndex = vertexIndices.get(destination);

        adjacencyMatrix[sourceIndex][destinationIndex] = 1;
    }

    public void addEdge(T source, T destination, Map<K, V> attributes) {
        addDirectedEdge(source, destination, attributes);
        addDirectedEdge(destination, source, attributes);
    }

    public void removeVertex(T vertex) {
        if (!vertexIndices.containsKey(vertex)) {
            return;
        }

        int indexToRemove = vertexIndices.get(vertex);

        for (int i = indexToRemove; i < vertexIndices.size() - 1; i++) {
            T vertexToMove = null;

            for (Map.Entry<T, Integer> entry : vertexIndices.entrySet()) {
                if (entry.getValue() == i + 1) {
                    vertexToMove = entry.getKey();
                    break;
                }
            }

            if (vertexToMove != null) {
                vertexIndices.put(vertexToMove, i);
            }

            for (int j = 0; j < adjacencyMatrix.length; j++) {
                adjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j];
            }

            for (int j = 0; j < adjacencyMatrix.length; j++) {
                adjacencyMatrix[j][i] = adjacencyMatrix[j][i + 1];
            }
        }

        vertexIndices.remove(vertex);
    }

    public void removeEdge(T source, T destination) {
        if (!vertexIndices.containsKey(source) || !vertexIndices.containsKey(destination)) {
            return;
        }

        int sourceIndex = vertexIndices.get(source);
        int destinationIndex = vertexIndices.get(destination);

        adjacencyMatrix[sourceIndex][destinationIndex] = 0;
    }

    @Override
    public List<T> getNeighbours(T vertex) {
        List<T> list = new LinkedList<>();
        Integer index = this.vertexIndices.get(vertex);
        if (index == null) {
            return Collections.emptyList();
        }

        int[] column = this.adjacencyMatrix[index];

        for (int i = 0; i < column.length; i+=1) {
            if (column[i] > 0) {
                for (Map.Entry<T, Integer> e: this.vertexIndices.entrySet()) {
                    if (e.getValue() == i) {
                        list.add(e.getKey());
                    }
                }
            }
        }

        return list;
    }

    @Override
    public List<Pair<T, Integer>> getNeighbourDistances(T vertex) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public List<T> getVerteces() {
        return this.vertexIndices.keySet().stream().toList();
    }

    @Override
    public List<Edge<T, K, V>> getEdges() {
        //TODO
        return new LinkedList<>();
    }


    public List<T> getAdjacencyList(T vertex) {
        if (!vertexIndices.containsKey(vertex)) {
            return null;
        }

        List<T> neighbors = new ArrayList<>();

        int vertexIndex = vertexIndices.get(vertex);
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertexIndex][i] == 1) {
                for (Map.Entry<T, Integer> entry : vertexIndices.entrySet()) {
                    if (entry.getValue() == i) {
                        neighbors.add(entry.getKey());
                        break;
                    }
                }
            }
        }

        return neighbors;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<T, Integer> entry : vertexIndices.entrySet()) {
            builder.append(entry.getKey() + ": ");

            int index = entry.getValue();
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[index][i] == 1) {
                    for (Map.Entry<T, Integer> entry2 : vertexIndices.entrySet()) {
                        if (entry2.getValue() == i) {
                            builder.append(entry2.getKey() + " ");
                            break;
                        }
                    }
                }
            }

            builder.append("\n");
        }
        return builder.toString();
    }
}
