class lca {
	HashMap<Integer,Integer>[] adj;
	int[][] par, min;
	int[] depth;
	int n,d;
	lca(int nn){
		n=nn;
		depth=new int[n];
		d=Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
		par=new int[d+1][n];
		min=new int[d+1][n];
		adj=new HashMap[n];
		for(int i=0;i<n;i++) {
			adj[i]=new HashMap<Integer,Integer>();
		}
	}
	void addEdge(int u, int v, int w) {
		adj[u].put(v, w);
	}
	void dfs(int x, int p) {
		for(int j:adj[x].keySet()) {
			if(j!=p) {
				par[0][j]=x;
				min[0][j]=adj[x].get(j);
				depth[j]=depth[x]+1;
				dfs(j,x);
			}
		}
	}
	int walk(int i, int steps) {
        for(int k=0;k<=d && i!=-1;k++) {
            if(((1<<k)&steps)>0)i=par[k][i];
        }
        return i;
    }
    
    int lca(int i, int j) {
        if(depth[i]>depth[j]) {
            i=walk(i,depth[i]-depth[j]);
        }
        else if(depth[j]>depth[i]) j=walk(j,depth[j]-depth[i]);
        if(i==j) return i;
        //if(i==-1 || j==-1) return 0;
        for(int k=d;k>=0;k--) {
            if(par[k][i]!=par[k][j]) {
                i=par[k][i];
                j=par[k][j];
            }
        }
        return par[0][i];
    }
    void complete() {
    	for(int i=1;i<=d;i++) {
    		for(int j=0;j<n;j++) {
    			int mid=par[i-1][j];
    			if(mid!=-1) {
    				par[i][j]=par[i-1][mid];
    				min[i][j]=Math.max(min[i-1][mid], min[i-1][j]);
    			}
    		}
    	}
	}
}
