
public class SegTree {
	
	static long[] st,lazy,arr;
	
	SegTree(int n, long[] a){
		arr=a;
		st=new long[4*n];
		lazy=new long[4*n];
	}
	void push(int index, int left, int right) {
		if(lazy[index]!=0) {
			st[index]+=lazy[index]*(right-left+1);
			if(left!=right) {
				lazy[2*index+1]+=lazy[index];
				lazy[2*index+2]+=lazy[index];
			}
			lazy[index]=0;
		}
	}
	
	void build(int index, int left, int right) {
		if(left==right) {
			st[index]+=arr[left];
		}
		else {
			int mid=(left+right)/2;
			build(2*index+1,left,mid);
			build(2*index+2,mid+1,right);
			st[index]=st[2*index+1]+st[2*index+2];
		}
	}
	
	long query(int index, int left, int right, int ql, int qr) {
		push(index,left,right);
		if(ql<=left && right<=qr) return st[index];
		if(ql>right || qr <left) return 0;
		int mid=(left+right)/2;
		long v=query(2*index+1,left,mid,ql,qr);
		v+=query(2*index+2,mid+1,right,ql,qr);
		st[index]=st[2*index+1]+st[2*index+2];
		return v;
	}
	
	void update(int index, int left, int right, int ql, int qr, long val) {
		push(index,left,right);
		if(ql<=left && right<=qr) {
			lazy[index]+=val;
			push(index,left,right);
		}
		if(ql>right || qr <left) return;
		int mid=(left+right)/2;
		update(2*index+1,left,mid,ql,qr,val);
		update(2*index+2,mid+1,right,ql,qr,val);
		st[index]=st[2*index+1]+st[2*index+2];
	}
	
}
