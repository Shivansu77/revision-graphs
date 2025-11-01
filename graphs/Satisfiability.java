

public class Satisfiability {
    int[] parent;
    int[] rank;
    
    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return true;

        if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return false;
    }
    
    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        rank = new int[26];
        for(int i = 0; i < 26; i++) {
           parent[i] = i;
           rank[i] = 0;
        }
        
        for(String s : equations) {
            if(s.charAt(1) == '=') {
                int a = s.charAt(0) - 'a';
                int b = s.charAt(3) - 'a';
                union(a, b);
            }
        }
        
        for(String s : equations) {
            if(s.charAt(1) == '!') {
                int a = s.charAt(0) - 'a';
                int b = s.charAt(3) - 'a';
                int parentA = find(a);
                int parentB = find(b);
                if(parentA == parentB) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Satisfiability solver = new Satisfiability();
        String[] equations = {"a==b", "b!=a"};
        boolean result = solver.equationsPossible(equations);
        System.out.println("Equations satisfiable: " + result); // Output: false
    }
}