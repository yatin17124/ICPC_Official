struct Line {
	mutable ll k, m, p;
	bool operator<(const Line& o) const { return k  <o.k; }
	bool operator<(ll x) const { return p < x; }
};
 
struct LineContainer : multiset<Line, less<>> {
	// (for doubles, use inf = 1/.0, div(a,b) = a/b)
	const ll inf = LLONG_MAX;
	ll div(ll a, ll b) { // floored division
		return a / b - ((a ^ b) < 0 && a % b); }
	bool isect(iterator x, iterator y) {
		if (y == end()) { x->p = inf; return false; }
		if (x->k == y->k) x->p = x->m > y->m ? inf : -inf;
		else x->p = div(y->m - x->m, x->k - y->k);
		return x->p >= y->p;
	}
	//NOTE: Here 'k' represents slope of the line and 'm' represents the y-intercept
	void add(ll k, ll m) {
		auto z = insert({k, m, 0}), y = z++, x = y;
		while (isect(y, z)) z = erase(z);
		if (x != begin() && isect(--x, y)) isect(x, y = erase(y));
		while ((y = x) != begin() && (--x)->p >= y->p)
			isect(x, erase(y));
	}
	//Querying for the max value of 'x' in all linear functions
	ll query(ll x) {
		assert(!empty());
		auto l = *lower_bound(x);
		return l.k * x + l.m;
	}
};
/*
LineContainer L;
L.add(-a[0].f.f, a[0].f.f * a[0].f.s - a[0].s);
for(int i = 1; i < n; i++){
	int temp = L.query(a[i].f.s);
	dp[i] = max(dp[i],temp + a[i].f.f * a[i].f.s - a[i].s);
	L.add(-a[i].f.f,dp[i]);
}
*/