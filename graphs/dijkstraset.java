import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class dijkstraset {
    static class Edge {
        int to;
        int weight;
        Edge(int to,int weight){
            this.to=to;
            this.weight=weight;
        }
    }

    static class Node implements Comparable<Node>{
        int vertex;
        int dist;

        Node(int vertex,int dist){
            this.vertex=vertex;
            this.dist=dist;
        }
        @Override
        public int compareTo(Node other){
            if(this.dist == other.dist)
                return Integer.compare(this.vertex,other.vertex);
            return Integer.compare(this.dist,other.dist);
        }
    }
    static void dijkstra(List<List<Edge>>graph,int source){
        int n=graph.size();
        int []dist=new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        TreeSet<Node>set=new TreeSet<>();
        set.add(new Node(source,0));

        while(!set.isEmpty()){
            Node curr=set.pollFirst();
            int u=curr.vertex;;

            for(Edge edge:graph.get(u)){
                int v=edge.to;
                int w=edge.weight;

                if(dist[u] + w < dist[v] ){
                    set.remove(new Node(v,dist[v]));
                    dist[v]=dist[u]+w;
                    set.add(new Node(v,dist[v]));
                }
            }
        }
    }

}
