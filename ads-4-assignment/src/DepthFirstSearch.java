import java.util.*;

public class DepthFirstSearch<V> extends Search<V> {
    public DepthFirstSearch(WeightedGraph<V> graph, V source) {
        super(graph.getVertex(source));
        dfs(graph, graph.getVertex(source));
    }

    private void dfs(WeightedGraph<V> graph, Vertex<V> current) {
        marked.add(current);

        for (V neighborData : graph.getAdjacentVertices(current.getData())) {
            Vertex<V> neighbor = graph.getVertex(neighborData);
            if (!marked.contains(neighbor)) {
                edgeTo.put(neighbor, current);
                dfs(graph, neighbor);
            }
        }
    }
}