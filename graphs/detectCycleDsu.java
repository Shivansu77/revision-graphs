import java.util.ArrayList;

public class detectCycleDsu {
    static class DSU {
        int[] parent;
        int[] rank;
        
        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if(rootX == rootY) {
                return false; // Cycle detected
            }
            
            if(rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if(rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
    
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        DSU dsu = new DSU(V);
        
        for(int u = 0; u < V; u++) {
            for(int v : adj.get(u)) {
                if(u < v) { // To avoid processing the same edge twice
                    if(!dsu.union(u, v)) {
                        return 1; // Cycle detected
                    }
                }
            }
        }
        return 0; // No cycle detected
    }
    
    public static void main(String[] args) {
        // Example usage
        int V = 4; // Number of vertices
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        // Initialize adjacency list
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Add edges
        // Edge 0-1
        adj.get(0).add(1);
        adj.get(1).add(0);
        
        // Edge 1-2
        adj.get(1).add(2);
        adj.get(2).add(1);
        
        // Edge 2-3
        adj.get(2).add(3);
        adj.get(3).add(2);
        
        // Edge 3-0 (creates a cycle)
        adj.get(3).add(0);
        adj.get(0).add(3);
        
        detectCycleDsu solution = new detectCycleDsu();
        int result = solution.detectCycle(V, adj);
        
        System.out.println("Cycle " + (result == 1 ? "detected" : "not detected"));
    }
}

