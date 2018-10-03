import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class familyFibonacci {
	static long mod=1000000009;
	static long[][] matriz;

	public static long[][] multiplicyMatriz(long[][] m1,long[][] m2){
		long[][] ret=new long[m1.length][m2[0].length];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[0].length; j++) {
				for (int j2 = 0; j2 < m1[0].length; j2++) {
					ret[i][j]+=(m1[i][j2]*m2[j2][j])%mod;
					ret[i][j]=ret[i][j]%mod;
				}
			}
		}
		return ret;
	}
	public static long[][] pow(long[][] mat,long p){
		if(p==1) {
			return mat;
		}else if(p==2) {
			return multiplicyMatriz(mat, mat);
		}else {
			long m=0;
			if(p%2==0) {
				m=p/2;
			}else {
				m=(p-1)/2;
			}
			long[][] res=pow(mat, m);
			res=multiplicyMatriz(res, res);
			if(p%2!=0) {
				res=multiplicyMatriz(res, matriz);
			}
			return res;
		}
	}
	public static long calculate(int f,long n) {
		if(f==1||n<2) {
			return 1;
		}
		long[][] mat=new long[f][f];
		matriz=new long[f][f];
		for (int i = 0; i < mat.length-1; i++) {
			mat[i][i+1]=1;
			matriz[i][i+1]=1;

		}
		for (int i = 0; i < mat.length; i++) {
			matriz[mat.length-1][i]=1;
			mat[mat.length-1][i]=1;

		}
		
		return pow(mat, n)[f-1][f-1];
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		long n=sc.nextLong();
		long d=sc.nextLong();
		while(n!=0||d!=0) {
			long res=calculate((int) n, d);
			bw.write(res+"\n");
			 n=sc.nextLong();
			 d=sc.nextLong();
		}
		sc.close();
		bw.close();
	}

}
