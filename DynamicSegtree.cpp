#define ll int
int const MOD = 1e9+7;
int const inf = 2e18;
int const N = 2e5+10;
ll d;
struct Node
{
	ll data,lazy;
	Node *left, *right;
	Node(){
		data = 0;
		lazy = 0;
		left = NULL;
		right = NULL;
	}
};
 
Node* root;
ll Time = 0;
void propogate(Node* root){
	if(root->left==NULL) root->left = new Node();
	if(root->right==NULL) root->right = new Node();
	ll l = root->lazy;
	root->lazy = 0;
	root->left->data += l;
	root->left->lazy += l;
	root->right->data += l;
	root->right->lazy += l;
	return ;
}
 
void update(Node* root,ll l,ll r,ll left,ll right){
	if(l>=left && r<=right){
		root->data++;
		root->lazy++;
		return;
	}
	if(l>right || r<left) return;
	ll mid = (l+r)/2;
 
	propogate(root);
 
	update(root->left,l,mid,left,right);
	update(root->right,mid+1,r,left,right);
	return;
}
 
ll query(Node* root,ll l,ll r,ll x){
	if(root==NULL) return 0;
	if(l==r) return root->data;
	
	propogate(root);
 
	ll mid = (l+r)/2;
	if(x<=mid) return query(root->left,l,mid,x);
	else return query(root->right,mid+1,r,x);
}