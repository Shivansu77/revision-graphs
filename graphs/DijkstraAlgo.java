import java.util.*;

public class DijkstraAlgo {
    public static class Pair implements Comparable<Pair> {
        int vertex;
        String path;
        int distance;
        
        Pair(int vertex, String path, int distance) {
            this.vertex = vertex;
            this.path = path;
            this.distance = distance;
        }
        
        public int compareTo(Pair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    public static int[] dijkstra(int v, int[][] edges, int src) {
        PriorityQueue<Pair>pq=new PriorityQueue<>();
        // Create adjacency list with weights
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build graph: adj.get(u) contains [neighbor, weight]
        for (int[] edge : edges) {
            int u = edge[0];
            int neighbor = edge[1];
            int weight = edge[2];
            adj.get(u).add(new int[]{neighbor, weight});
            adj.get(neighbor).add(new int[]{u, weight});
        }
        
        // Distance array to store shortest distances
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Pair(src, src + "", 0));
        boolean[] visited = new boolean[v];
        
        while (!pq.isEmpty()) {
            Pair current = pq.remove();
            int u = current.vertex;
            
            // Skip if already visited
            if (visited[u]) continue;
            visited[u] = true;
            
            // Explore neighbors
            for (int[] neighbor : adj.get(u)) {
                int v_neighbor = neighbor[0];
                int weight = neighbor[1];
                
                if (!visited[v_neighbor]) {
                    int newDist = current.distance + weight;
                    
                    if (newDist < dist[v_neighbor]) {
                        dist[v_neighbor] = newDist;
                        pq.add(new Pair(v_neighbor, current.path + " -> " + v_neighbor, newDist));
                    }
                }
            }
        }
        
        return dist;
    }
    
    public static void main(String[] args) {
        int vertices = 5;
        int[][] edges = {
            {0, 1, 4},
            {0, 2, 2},
            {1, 2, 1},
            {1, 3, 5},
            {2, 3, 8},
            {2, 4, 10},
            {3, 4, 2}
        };
        
        dijkstra(vertices, edges, 0);
        int[] result = dijkstra(vertices, edges, 0);
        
        System.out.println("\nShortest distances from source 0:");
        for (int i = 0; i < result.length; i++) {
            if (result[i] != Integer.MAX_VALUE)
                System.out.println("To vertex " + i + ": " + result[i]);
        }
    }
}