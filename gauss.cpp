#include<bits/stdc++.h>
using namespace std;
#define MAX_N 100 // adjust this value as needed 
struct AugmentedMatrix { double mat[MAX_N][MAX_N + 1]; };
struct ColumnVector { double vec[MAX_N]; };
ColumnVector GaussianElimination(int N, AugmentedMatrix Aug) { // O(N^3) // input: N, Augmented Matrix Aug, output: Column vector X, the answer
 	int i, j, k, l; double t; ColumnVector X;
	for (j = 0; j < N - 1; j++) { // the forward elimination phase 
		l = j;
		for (i = j + 1; i < N; i++) // which row has largest column value 
			if (fabs(Aug.mat[i][j]) > fabs(Aug.mat[l][j]))
			l = i; // remember this row l // swap this pivot row, reason: to minimize floating point error
			for (k = j; k <= N; k++) // t is a temporary double variable
				t = Aug.mat[j][k], Aug.mat[j][k] = Aug.mat[l][k], Aug.mat[l][k] = t; for (i = j + 1; i < N; i++) // the actual forward elimination phase
			for (k = N; k >= j; k--)
				Aug.mat[i][k] -= Aug.mat[j][k] * Aug.mat[i][j] / Aug.mat[j][j];
	}
	for (j = N - 1; j >= 0; j--) { 
		// the back substitution phase
		 for (t = 0.0, k = j + 1; k < N; k++) t += Aug.mat[j][k] * X.vec[k]; X.vec[j] = (Aug.mat[j][N] - t) / Aug.mat[j][j]; // the answer is here
	}
return X; 
}
int gauss(){
    // https://cp-algorithms.com/linear_algebra/linear-system-gauss.html
    int row=0,col=0;
    for (int j=0;j<m;j++){
        for (int i=row;i<n;i++){
            if(b[i][j]){
                swap(b[i],b[row]);break;
            }
        }
        if(!b[row][j])continue;
        int p=row+1;
        for (int i=p;i<n;i++){
            if (b[i][j]){
                b[i]^=b[row];
            }
        }
        row++;col++;
    }
    return col;// number of pivot cols
}
int main(){
	AugmentedMatrix A;
	double m[3][4] = {{1,1,2,9},{2,4,-3,1},{3,6,-5,0}};
	for(int i = 0;i<3;i++){
		for(int j=0;j<4;j++){
			A.mat[i][j] = m[i][j];;
		}
	}
	ColumnVector c = GaussianElimination(3,A);
	for(int i=0;i<3;i++){
		cout << c.vec[i] << " ";
	}
	return 0;
}