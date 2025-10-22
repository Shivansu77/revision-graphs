public class makeconnected {
    int []parent;
    int []rank;

    public int find(int x){
        if(parent[x]!=x){
            parent[x]=find(parent[x]);
        }
        return parent[x];
    }
    public boolean union(int x,int y){
        int rootX=find(x);
        int rootY=find(y);
        if(rootX==rootY) return false;

        if(rank[rootX]<rank[rootY]){
            parent[rootX]=rootY;
        }else if(rank[rootX]>rank[rootY]){
            parent[rootY]=rootX;
        }else{
            parent[rootY]=rootX;
            rank[rootX]++;
        }
        return true;
    }
    public int makeConnected(int n, int[][] connections) {
        parent = new int[n];
        rank = new int[n];
        if(connections.length<n-1)return -1;
        for(int i=0;i<n;i++){
            parent[i]=i;
            rank[i]=0;  
        }
        int components=n;
        for(int []conn:connections){
            if(find(conn[0])!=find(conn[1])){
                union(conn[0],conn[1]);
                components--;
            }
        }
        return components-1;
    }
    public static void main(String[] args) {
        makeconnected mc = new makeconnected();
        int n = 6;
        int[][] connections = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        int result = mc.makeConnected(n, connections);
        System.out.println("Minimum number of operations to connect all computers: " + result);
    }
}
