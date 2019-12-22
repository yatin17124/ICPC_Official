package graph;

import java.util.*;

// Tarjan's Algorithm
public class SCC {

    ArrayList<Integer>[] g;
    int[] low;
    int[] dfsNum;
    int[] component;
    int componentNum = 1;
    int currentNum = 0;
    int N;
    boolean[] inStack; // (0 - white) (1 - gray) (2 - black)
    Stack<Integer> S;

    SCC(int N){
        this.N = N;
        g = new ArrayList[N];
        low = new int[N];
        dfsNum = new int[N];
        Arrays.fill(dfsNum, -1);
        inStack = new boolean[N];
        component = new int[N];
        for(int i = 0; i < N; i++){g[i] = new ArrayList<>();}
    }

    void addEdge(int u, int v){
        g[u].add(v);
    }

    void SCC(){

        for(int i = 0; i < N; i++){
            if(dfsNum[i] == -1){dfs(i);}
        }

    }

    void dfs(int u){
        dfsNum[u] = low[u] = currentNum++;
        S.push(u);
        inStack[u] = true;
        for(int v : g[u]){
            if(dfsNum[v] == -1){
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            }
            else if(inStack[v]){
                // back-edge
                low[u] = Math.max(low[u], dfsNum[v]);
            }
        }

        if(low[u] == dfsNum[u]){

            // SCC

            while(S.peek() != u){
                int x = S.pop();
                component[x] = componentNum;
                inStack[x] = false;
            }
            component[u] = componentNum++;
            inStack[S.pop()] = false;
        }


    }


}
