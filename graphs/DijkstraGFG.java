import java.util.*;

public class DijkstraGFG {
    static class Pair implements Comparable<Pair> {
        int v;
        int wsf;
        
        Pair(int v, int wsf) {
            this.v = v;
            this.wsf = wsf;
        }
        
        public int compareTo(Pair other) {
            return Integer.compare(this.wsf, other.wsf);
        }
    }

    public int[] dijkstra(int V, int[][] edges, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        List<List<int[]>> adj = new ArrayList<>();
        
        // Initialize adjacency list
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build adjacency list from edges
        for(int[] edge : edges) {
            int u = edge[0];
            int neighbor = edge[1];
            int weight = edge[2];
            adj.get(u).add(new int[]{neighbor, weight});
            adj.get(neighbor).add(new int[]{u, weight});
        }
        
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Pair(src, 0));
        boolean[] vis = new boolean[V];
        
        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            int u = curr.v;
            
            if(vis[u]) continue;
            vis[u] = true;
            
            for(int[] nbr : adj.get(u)) {
                int v_nbr = nbr[0];
                int weight = nbr[1];
                if(!vis[v_nbr]) {
                    int newDist = dist[u] + weight;
                    if(newDist < dist[v_nbr]) {
                        dist[v_nbr] = newDist;
                        pq.add(new Pair(v_nbr, newDist));
                    }
                }
            }
        }
        
        return dist;
    }

    public static void main(String[] args) {
        DijkstraGFG solution = new DijkstraGFG();
        
        // Example graph
        int V = 5; // Number of vertices
        int[][] edges = {
            {0, 1, 4},
            {0, 2, 8},
            {1, 2, 2},
            {1, 3, 5},
            {2, 3, 5},
            {2, 4, 9},
            {3, 4, 4}
        };
        int source = 0;
        
        int[] distances = solution.dijkstra(V, edges, source);
        
        System.out.println("Shortest distances from source " + source + ":");
        for(int i = 0; i < V; i++) {
            System.out.println("To vertex " + i + ": " + distances[i]);
        }
    }
}