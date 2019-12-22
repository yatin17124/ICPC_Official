package Flow;

import java.util.*;

// java push relabel implementation with gap heuristic 
// n is the number of nodes i.e. 0 to n -1
// tested - https://www.spoj.com/problems/FASTFLOW/
// https://loj.ac/submission/453124


class pushRelabel {

    int MAXN;
    ArrayList<edge>[] adj;
    ArrayList<Integer>[] list;
    ArrayList<Integer>[] gap;
    long[] excess;
    long INF = (long)1e15;
    int highest; int[] height; int[] cnt; int work;
    int s, t;
    void addEdge(int from, int to, int f, boolean isDirected){
        adj[from].add(new edge(to, adj[to].size(), f));
        adj[to].add(new edge(from, adj[from].size() - 1, isDirected ? 0 : f));
    }

    class edge{
        int to, rev;
        long f;
        edge(int TO, int REV, long F){
            to = TO; rev = REV; f = F;
        }
    }

    pushRelabel(int n){
        this.MAXN = n;
        adj = new ArrayList[n + 1];
        list = new ArrayList[n + 1];
        gap = new ArrayList[n + 1];
        excess = new long[n + 1];
        height = new int[n + 1];
        cnt = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            list[i] = new ArrayList<>();
            gap[i] = new ArrayList<>();
        }
    }

    void push(int v, edge e) {
        if (excess[e.to] == 0)
            list[height[e.to]].add(e.to);
        long df = Math.min(excess[v], e.f);
        e.f -= df; adj[e.to].get(e.rev).f += df;
        excess[v] -= df; excess[e.to] += df;
    }

    void globalRelabel(){
        work = 0;
        Arrays.fill(height, MAXN);
        Arrays.fill(cnt, 0);
        for(int i = 0; i < highest; i++){
            list[i].clear(); gap[i].clear();
        }
        height[t] = 0;
        Queue<Integer> Q = new LinkedList<>();
        Q.add(t);
        while(!Q.isEmpty()){
            int v = Q.poll();
            for(edge e : adj[v]){
                if(height[e.to] == MAXN && adj[e.to].get(e.rev).f  > 0){
                    Q.add(e.to);
                    updHeight(e.to, height[v] + 1);
                }
                highest = height[v];
            }
        }
    }

    void updHeight(int v, int nh) {
        work++;
        if (height[v] != MAXN)
            cnt[height[v]]--;
        height[v] = nh;
        if (nh == MAXN)
            return;
        cnt[nh]++; highest = nh;
        gap[nh].add(v);
        if (excess[v] > 0)
            list[nh].add(v);
    }

    void discharge(int v) {
        int nh = MAXN;
        for (edge e : adj[v]) {
            if (e.f > 0) {
                if (height[v] == height[e.to] + 1) {
                    push(v, e);
                    if (excess[v] <= 0)
                        return;
                } else
                    nh = Math.min(nh, height[e.to] + 1);
            }
        }
        if (cnt[height[v]] > 1)
            updHeight(v, nh);
        else {
            for (int i = height[v]; i <= highest; i++) {
                for (int j : gap[i])
                    updHeight(j, MAXN);
                gap[i].clear();
            }
        }
    }


    long flow(int src, int sink){
        s = src;
        t = sink;
        int heur_n = MAXN;
        Arrays.fill(excess, 0);
        excess[s] = INF; excess[t] = -INF;
        globalRelabel();
        for (edge e : adj[s]) push(s, e);
        for (; highest >= 0; highest--) {
            while (!list[highest].isEmpty()) {
                int v = list[highest].remove(list[highest].size() - 1);
                discharge(v);
                if (work > 4 * heur_n)
                    globalRelabel();
            }
        }
        return excess[t] + INF;
    }
}
