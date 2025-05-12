    import java.util.*;
    public class BreadthFirstSearch<V> extends Search<V> {
        public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
            super(graph.getVertex(source));
            bfs(graph, graph.getVertex(source));
        }
        private void bfs(WeightedGraph<V> graph, Vertex<V> source) {
            Queue<Vertex<V>> queue = new LinkedList<>();
            marked.add(source);
            queue.add(source);
            while (!queue.isEmpty()) {
                Vertex<V> current = queue.poll();
                for (V neighborData : graph.getAdjacentVertices(current.getData())) {
                    Vertex<V> neighbor = graph.getVertex(neighborData);
                    if (!marked.contains(neighbor)) {
                        marked.add(neighbor);
                        edgeTo.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }