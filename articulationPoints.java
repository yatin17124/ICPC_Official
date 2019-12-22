package graph;

import java.util.ArrayList;
import java.util.Arrays;


// for undirected graph

public class articulationPoints {

    ArrayList<Integer>[] g;
    int[] low;
    int[] dfsNum;
    int[] maxLow; // max of low values of all child in dfs tree
    boolean[] visit;
    int currentNum = 0;

    articulationPoints(int N){
        g = new ArrayList[N];
        low = new int[N];
        maxLow = new int[N];
        dfsNum = new int[N];
        visit = new boolean[N];
        for(int i = 0; i < N; i++){g[i] = new ArrayList<>();}
    }



    void dfs(int u, int parent){
        visit[u] = true;
        dfsNum[u] = low[u] = currentNum++;
        for(int v : g[u]){
            if(visit[v]){
                if(v == parent) continue;
                low[u] = Math.min(low[u], dfsNum[v]);   //back - edge
            }
            else{
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);   // if(low[v] > dfsNum[u]) then u -- v is a bridge
                maxLow[u] = Math.max(maxLow[u], low[v]);    // if (maxLow[u] >= dfsNum[u]) then u is an articulation point [Note - this is for except the root of dfs tree]
                                                            // for root child > 1
            }
        }
    }



}
