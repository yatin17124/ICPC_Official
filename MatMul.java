
class MatMul {
	static long mod;
	
	long[][] mul(long[][] a1,long[][] a2, int n){
		long[][] a3=new long[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					a3[i][j]+=a1[i][k]*a2[k][j];
					a3[i][j]%=mod;
				}
			}
		}
		return a3;
	}
	
	long[][] expo(long[][] arr, long k,int n){
		long[][] ret=new long[n][n];
		for(int i=0;i<n;i++) ret[i][i]=1;
		if(k==0) return ret;
		if(k==1) return arr;
		ret=expo(arr,k/2,n);
		ret=mul(ret,ret,n);
		if(k%2==1) {
			ret=mul(ret,arr,n);
		}
		
		return ret;
	}
}
