import java.util.*;

public class bellmanFord {

    public static class Edge {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void bellmanFordAlgo(int V, int src, List<Edge> edgeList) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            for (Edge edge : edgeList) {
                int u = edge.u;
                int v = edge.v;
                int w = edge.w;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge edge : edgeList) {
            int u = edge.u;
            int v = edge.v;
            int w = edge.w;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("Graph contains a negative weight cycle!");
                return;
            }
        }

        // Print results
        printDistances(dist, src);
    }

    public static void printDistances(int[] dist, int source) {
        System.out.println("Vertex Distance from Source (" + source + "):");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "∞" : dist[i]));
        }
    }

    public static void main(String[] args) {
        int vertices = 5;

        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(0, 1, -1));
        edgeList.add(new Edge(0, 2, 4));
        edgeList.add(new Edge(1, 2, 3));
        edgeList.add(new Edge(1, 3, 2));
        edgeList.add(new Edge(1, 4, 2));
        edgeList.add(new Edge(3, 2, 5));
        edgeList.add(new Edge(3, 1, 1));
        edgeList.add(new Edge(4, 3, -3));

        bellmanFordAlgo(vertices, 0, edgeList);
    }
}
