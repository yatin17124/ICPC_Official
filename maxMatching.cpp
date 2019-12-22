vector<int> v[1001];
bool vis[1001];
int previous[1001];
bool match(int i){
	if(i == -1) return 1;
	if(vis[i]) return 0;
	vis[i]=1;
	for(auto x : v[i]){
		if(match(previous[x]))
		{
			previous[x]=i;
			return 1;
		}
	}
	return 0;
}
/*for(int i=0;i<n;i++){
	previous[i]=-1;
}
int matchings = 0 ;
for(int i=0;i<n;i++){
	memset(vis,0,sizeof(vis));
	if(match(i)) matchings++;
}
cout<<n-matchings;*/