public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(false);
        fillGraph(graph);

        System.out.println("Dijkstra:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, "Almaty");
        printPath(dijkstra, graph, "Kyzylorda");

        System.out.println("\nBreadth-First Search:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, "Almaty");
        printPath(bfs, graph, "Kyzylorda");

        System.out.println("\nDepth-First Search:");
        DepthFirstSearch<String> dfs = new DepthFirstSearch<>(graph, "Almaty");
        printPath(dfs, graph, "Kyzylorda");
    }

    private static void fillGraph(WeightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    private static void printPath(Search<String> search, WeightedGraph<String> graph, String destination) {
        Vertex<String> destVertex = graph.getVertex(destination);
        Iterable<String> path = search.pathTo(destVertex);

        if (path == null) {
            System.out.println("No path found to " + destination);
            return;
        }

        System.out.print("Path: ");
        boolean first = true;
        for (String vertex : path) {
            if (!first) {
                System.out.print(" -> ");
            }
            System.out.print(vertex);
            first = false;
        }
        System.out.println();
    }
}