package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

// returns true if walk exists else false
// complexity - O(|E|)

// tested -
// for directed - https://open.kattis.com/problems/eulerianpath
// for undirected -

public class eulerWalk {

    graph g;
    int eID;  // essentially number of edges added
    int n;
    ArrayList<Integer> walk = new ArrayList<>();

    eulerWalk(int n){
        this.n = n;
        g = new graph(n);
        eID = 0;
    }

    class node{
        ArrayList<Integer> to = new ArrayList<>();  // size(to) = out degree
        ArrayList<Integer> edgeID = new ArrayList<>(); // to deal with parallel edges self loops
        int indeg = 0;
    }

    class graph{
        node[] from;
        graph(int n){
            from = new node[n];
            for(int i = 0; i < n; i++){
                from[i] = new node();
            }
        }
    }

    void addEdge(int u, int v){
        g.from[u].to.add(v);
        g.from[u].edgeID.add(eID++);
        g.from[v].indeg++;
    }

    boolean eWalk(){
        int src = 0;
        int count = 0;
        for(int i = 0; i < n; i++){
            if(g.from[i].to.size() - g.from[i].indeg == 1){src = i;}
            count += Math.abs(g.from[i].to.size() - g.from[i].indeg);
        }
        if(count > 2) return false;
        boolean[] vis = new boolean[eID];
        Stack<Integer> S = new Stack<>();
        S.push(src);
        while(!S.isEmpty()){
            int x = S.peek();
            node cur = g.from[x];
            int i = 0;
            while(i < cur.to.size() && vis[cur.edgeID.get(i)]){i++;}
            if(i == cur.to.size()){walk.add(x); S.pop();}
            else{
                S.push(cur.to.get(i));
                vis[cur.edgeID.get(i)] = true;
            }
        }
        if(walk.size() != eID + 1){
            return false;
        }

        // cycle then walk.front == walk.back

        Collections.reverse(walk);
        return true;
    }

}
