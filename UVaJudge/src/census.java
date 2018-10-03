import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class census {
	static final int INF = Integer.MAX_VALUE;
	


	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedWriter es=new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader lec=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(lec.readLine());
		SegmentTree[] seg=new SegmentTree[n];
		for (int i = 0; i < n; i++) {
			String[] line=lec.readLine().split(" ");
			SegmentTree st = new SegmentTree(0, n-1); 

			for (int j = 0; j < line.length; j++) {
				st.set(j, Integer.parseInt(line[j]));
			}
			seg[i]=st;

		}
		int test=Integer.parseInt(lec.readLine());
		for (int i = 0; i < test; i++) {
			String line[]=lec.readLine().split(" ");
			int x=Integer.parseInt(line[1]);
			int y=Integer.parseInt(line[2]);
			int v=Integer.parseInt(line[3]);
			if(line[0].equals("q")) {
				int vy=Integer.parseInt(line[4]);
				int max=Integer.MIN_VALUE;
				int min=Integer.MAX_VALUE;
				for (int j = x-1; j < v; j++) {
					SegmentTree tmp=seg[j];
					int mintm=tmp.getMin(y-1, vy-1);
					int maxtm=tmp.getMax(y-1, vy-1);
					max=Math.max(max, maxtm);
					min=Math.min(mintm, min);
				}
				es.write(max+" "+min);
				es.newLine();
				
			}else {
			
				seg[x-1].set(y-1, v);
			}
		}
		es.close();
		lec.close();

	}


	static class SegmentTree {
		int start, end;
		SegmentTree leftTree, rightTree;
		int minValue;
		int maxValue;

		public SegmentTree(int start, int end) {
			this.start = start;
			this.end = end;
			this.minValue = INF;
			this.maxValue=Integer.MIN_VALUE;
			if(start == end) {  // es una hoja
				leftTree = rightTree = null;
				return;
			}
			int mid = (start + end) / 2;
			leftTree = new SegmentTree(start, mid);
			rightTree = new SegmentTree(mid + 1, end);

		}

		public void set(int pos, int value) {
			// es una hoja, CASO BASE
			if(start == end) {
				minValue = value;
				maxValue=value;
				return;
			}

			int mid = (start + end) / 2;

			if(pos <= mid)
				leftTree.set(pos, value);
			else
				rightTree.set(pos, value);

			minValue = Math.min(leftTree.minValue, rightTree.minValue);
			maxValue=Math.max(leftTree.maxValue, rightTree.maxValue);
				

		}
		public int getMin(int low, int high) {

			if(start == low && end == high)
				return minValue;

			int mid = (start + end) / 2;

			if(high <= mid)
				return leftTree.getMin(low, high);

			if(low > mid)
				return rightTree.getMin(low, high);

			int leftMin = leftTree.getMin(low, mid);
			int rightMin = rightTree.getMin(mid+1, high);
			return Math.min(leftMin, rightMin);

		}
		public int getMax(int low, int high) {

			
			if(start == low && end == high)
				return maxValue;

			int mid = (start + end) / 2;

			if(high <= mid)
				return leftTree.getMax(low, high);

			if(low > mid)
				return rightTree.getMax(low, high);

			int leftMin = leftTree.getMax(low, mid);
			int rightMin = rightTree.getMax(mid+1, high);
			return Math.max(leftMin, rightMin);

		}
	}




}