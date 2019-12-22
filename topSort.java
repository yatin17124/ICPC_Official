package graph;


import java.util.*;

// returns true if topological sort exist otherwise false(i.e. cycle exist)
// complexity O(|V| + |E|)
// tested - https://codeforces.com/gym/100274/problem/C

public class topSort {

    ArrayList<Integer>[] g;
    int[] pos; // position of ith node in sort - a[i]
    int n;

    topSort(int n){
        this.n = n;
        g = new ArrayList[n];
        pos = new int[n];
        for(int i = 0 ; i < n; i++){
            g[i] = new ArrayList<>();
        }
    }

    void addEdge(int u, int v){
        g[u].add(v);
    }

    boolean Tsort(){
        int[] indeg = new int[n];
        for(int u = 0; u < n; u++){
            for(int v : g[u]){
                indeg[v]++;
            }
        }

        Stack<Integer> S = new Stack<>(); // use priority queue for lexi. smallest

        for(int i = 0; i < n; i++){
            if(indeg[i] == 0) S.push(i);
        }

        int num = 0;

        while(S.size() > 0) {
            int u = S.pop();
            pos[u] = num++;
            for(int v : g[u]){
                --indeg[v];
                if(indeg[v] == 0){
                    S.push(v);
                }
            }
        }
        return num == n;
    }

}
