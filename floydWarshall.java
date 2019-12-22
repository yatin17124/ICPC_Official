
package graph;

import java.util.*;


// all pairs shortest path
// negative edges allowed
// dist[i][j] == -inf if goes through negative cycle
// O(N^3)

public class floydWarshall {

    long inf = Long.MAX_VALUE/2;
    long[][] dist;
    int n;

    floydWarshall(int n){
        n = n;
        dist = new long[n][n];
        for(int i = 0; i < n; i++){Arrays.fill(dist[i], inf);dist[i][i] = 0;}
    }

    void addEdge(int u, int v, int w){
        dist[u][v] = Math.min(dist[u][v],w);
    }

    void fWarshall(){
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(dist[i][k] != inf && dist[k][j] != inf){
                        long newD = Math.max(dist[i][k] + dist[k][j], -inf);
                        dist[i][j] = Math.min(newD, dist[i][j]);
                    }
                }
            }
        }

        // negative self loop
        for(int k = 0; k < n; k++){
            if(dist[k][k] < 0){
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        if(dist[i][k] != inf && dist[k][j] != inf){
                            dist[i][j] = -inf;
                        }
                    }
                }
            }
        }
    }



}
