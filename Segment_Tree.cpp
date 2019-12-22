#define lc (i<<1)
#define rc (lc|1)
#define mid ((l+r)>>1)

int const N = 2e5 + 10;
int tree[4*N],lazy[4*N],a[N],n;

int merge(int x, int y) { return x + y; }
int cnt(int l, int r){ return r-l+1; } // 1 for RMQ

void build(int i,int l,int r){
    if(l == r){
        tree[i] = a[l];return;
    }
    build(lc,l,mid);build(rc,mid+1,r);
    tree[i] = merge(tree[lc],tree[rc]);
}
void propogate(int i, int l, int r) {
  if(l != r){lazy[lc] += lazy[i]; lazy[rc] += lazy[i];}
  tree[i] += cnt(l, r) * lazy[i]; lazy[i] = 0;
}

void update(int i, int l, int r, int ql, int qr, int val) {
  propogate(i, l, r);
  if(r < ql || l > qr) return;
  if(ql <= l && r <= qr)
  { lazy[i] += val; propogate(i, l, r); return; }
  update(lc, l, mid, ql, qr, val);
  update(rc, mid+1, r, ql, qr, val);
  tree[i] = merge(tree[lc], tree[rc]);
}

int query(int i, int l, int r, int ql, int qr) {
  propogate(i, l, r);
  if(r < ql || l > qr) return 0; // -inf for RMQ
  if(ql <= l && r <= qr) return tree[i];
  int q1 = query(lc, l, mid, ql, qr);
  int q2 = query(rc, mid+1, r, ql, qr);
  return merge(q1, q2);
}