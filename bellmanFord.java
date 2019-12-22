package graph;

// u --- w --> v
// calculates shortest path for graph with negative edge weights
// returns true if negative edge cycle.
// and assigns -inf distance value to such nodes accordingly
// time complexity - O(E*V)

import java.util.*;



public class bellmanFord {

    graph g;
    long inf = Long.MAX_VALUE/2;

    bellmanFord(int N){
        g = new graph(N);
    }

    class edge {
        int u; int v; long w;
        edge(int uu, int vv, int ww){ u = uu; v = vv; w = ww; }
    }

    class node{
        long dist;
        int prev;
        node(long dd, int p){dist = dd; prev = p;}
    }

    class graph{
        int n;
        ArrayList<node> nodes = new ArrayList<>(); // index 'i' is the ith node
        ArrayList<edge> edges = new ArrayList<>(); // edge list
        graph(int n){
            // graph indexed [0 to (n-1)]
            this.n = n;
            for(int i = 0; i < n; i++){
                nodes.add(new node(inf, -1));
            }
        }
    }

    void addEdge(int f, int to, int w){
        g.edges.add(new edge(f,to,w));
    }

    boolean bellmanFord(int startNode){
        g.nodes.get(startNode).dist = 0;
        for(int i = 0; i < g.n; i++){
            for(edge e : g.edges){
                node u = g.nodes.get(e.u);
                node v = g.nodes.get(e.v);
                if(u.dist == inf) continue;
                long todist = u.dist + (u.dist == -inf ? 0 : e.w);

                if(todist < v.dist){
                    v.dist = (i == g.n - 1) ? -inf : todist;
                    v.prev = e.u;
                }
            }
        }

        boolean negativeCycle = false;
        for(int i = 0; i < g.n; i++){
            for(edge e : g.edges){
                if(g.nodes.get(e.u).dist == -inf){
                    g.nodes.get(e.v).dist = -inf;
                    g.nodes.get(e.v).prev = e.u;
                    negativeCycle = true;
                }
            }
        }

        return negativeCycle;
    }
}


