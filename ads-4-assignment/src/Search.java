import java.util.*;

public class Search<V> {
    protected Set<Vertex<V>> marked = new HashSet<>();
    protected Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    protected final Vertex<V> source;

    public Search(Vertex<V> source) {
        this.source = source;
    }

    public boolean hasPathTo(Vertex<V> vertex) {
        return marked.contains(vertex);
    }

    public Iterable<V> pathTo(Vertex<V> vertex) {
        if (!hasPathTo(vertex)) return null;

        LinkedList<V> path = new LinkedList<>();
        for (Vertex<V> v = vertex; v != source; v = edgeTo.get(v)) {
            path.addFirst(v.getData());
        }
        path.addFirst(source.getData());
        return path;
    }
}