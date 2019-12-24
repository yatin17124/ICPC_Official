#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp> 
#pragma GCC optimize("Ofast,unroll-loops,fast-math")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx,avx,avx2,tune=native")
#pragma GCC optimize("O3")
using namespace std;
using namespace __gnu_pbds; 
#define int long long
#define pii pair<int,int>
#define pb push_back
#define f first
#define s second
#define IOS ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
int power(int x, int y, int mod = 2e18){
	int ans = 1; x%= mod;
	while(y){
		if(y&1) ans = (x * ans) % mod;
		x = (x * x) % mod; y>>=1;
	}
	return ans;
}
typedef tree<int, null_type, less<int>, rb_tree_tag,tree_order_statistics_node_update> ordered_set;
// s.order_of_key(key)
int const MOD = 1e9+7;
int const inf = 2e18;
int const N = 2e5+10;
signed main() {
freopen("pattern.in", "r", stdin);freopen("pattern.out", "w" , stdout)
	IOS;
return 0;
}
