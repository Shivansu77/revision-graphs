class DSU {
    private int[] parent;
    private int[] rank;

    // Constructor: initializes DSU for n elements
    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Each node is its own parent initially
            rank[i] = 0;    // Rank starts at 0
        }
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    // Union by rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;  // Already in the same set

        // Attach smaller rank tree under larger rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // Check if two elements are in the same set
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
public class Main {
    public static void main(String[] args) {
        DSU dsu = new DSU(5);  // 5 elements: 0 to 4

        dsu.union(0, 1);
        dsu.union(1, 2);

        System.out.println(dsu.isConnected(0, 2)); // true
        System.out.println(dsu.isConnected(0, 3)); // false

        dsu.union(3, 4);
        dsu.union(2, 4);

        System.out.println(dsu.isConnected(0, 4)); // true
    }
}
