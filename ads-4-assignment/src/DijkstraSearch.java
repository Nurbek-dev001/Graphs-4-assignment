import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<Vertex<V>, Double> distTo;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(graph.getVertex(source));
        this.distTo = new HashMap<>();
        dijkstra(graph);
    }

    private void dijkstra(WeightedGraph<V> graph) {
        PriorityQueue<VertexDistance<V>> pq = new PriorityQueue<>();

        for (V vertexData : graph.getAllVertices()) {
            Vertex<V> vertex = graph.getVertex(vertexData);
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);
        pq.add(new VertexDistance<>(source, 0.0));

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll().vertex;
            marked.add(current);

            for (V neighborData : graph.getAdjacentVertices(current.getData())) {
                Vertex<V> neighbor = graph.getVertex(neighborData);
                double edgeWeight = graph.getEdgeWeight(current.getData(), neighborData);
                double newDist = distTo.get(current) + edgeWeight;

                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.add(new VertexDistance<>(neighbor, newDist));
                }
            }
        }
    }

    private static class VertexDistance<V> implements Comparable<VertexDistance<V>> {
        Vertex<V> vertex;
        double distance;

        public VertexDistance(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistance<V> other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public double distanceTo(V vertexData) {
        Vertex<V> vertex = edgeTo.keySet().stream()
                .filter(v -> v.getData().equals(vertexData))
                .findFirst()
                .orElse(null);
        return vertex != null ? distTo.get(vertex) : Double.POSITIVE_INFINITY;
    }
}