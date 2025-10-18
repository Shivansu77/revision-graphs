import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class numprovincesBfs {
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        int c = 0;
        boolean[] vis = new boolean[V];
        
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                bfs(i, adj, vis);
                c++;
            }
        }
        return c;
    }
    
    static void bfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        vis[node] = true;
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int v = 0; v < adj.size(); v++) {
                if(!vis[v] && adj.get(curr).get(v) == 1) {
                    q.add(v);
                    vis[v] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        int V = 3; // Number of vertices
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        // Initialize adjacency matrix
        for(int i = 0; i < V; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for(int j = 0; j < V; j++) {
                row.add(0);
            }
            adj.add(row);
        }
        
        // Example graph with 2 provinces
        // Province 1: vertices 0,1
        // Province 2: vertex 2
        adj.get(0).set(1, 1);
        adj.get(1).set(0, 1);
        
        int numProvinces = numProvinces(adj, V);
        System.out.println("Number of provinces: " + numProvinces);
    }
}