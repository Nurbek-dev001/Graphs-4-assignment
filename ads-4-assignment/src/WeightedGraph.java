import java.util.*;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean isDirected;

    public WeightedGraph() {
        this(false);
    }

    public WeightedGraph(boolean isDirected) {
        this.vertices = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V destination, double weight) {
        addVertex(source);
        addVertex(destination);
        Vertex<V> srcVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(destination);

        srcVertex.addEdge(destVertex, weight);
        if (!isDirected) {
            destVertex.addEdge(srcVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Set<V> getAllVertices() {
        return vertices.keySet();
    }

    public List<V> getAdjacentVertices(V data) {
        Vertex<V> vertex = vertices.get(data);
        if (vertex == null) return Collections.emptyList();

        List<V> neighbors = new ArrayList<>();
        for (Vertex<V> adj : vertex.getAdjacentVertices().keySet()) {
            neighbors.add(adj.getData());
        }
        return neighbors;
    }

    public double getEdgeWeight(V source, V destination) {
        Vertex<V> src = vertices.get(source);
        Vertex<V> dest = vertices.get(destination);
        if (src == null || dest == null) {
            throw new IllegalArgumentException("Vertex not found");
        }
        Double weight = src.getAdjacentVertices().get(dest);
        if (weight == null) {
            throw new IllegalArgumentException("Edge not found");
        }
        return weight;
    }
}