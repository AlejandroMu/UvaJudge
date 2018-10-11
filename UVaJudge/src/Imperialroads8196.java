import java.io.*;
import java.util.*;



public class Imperialroads8196 {
	
	public static class Edge implements Comparable<Edge>{
		int src;
		int dest;
		int weight;
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
		@Override
		public String toString() {
			
			return src+"_"+dest+"_"+weight;
		}
	}
	public static class Graf{
		HashMap<String,Edge> edges2;
		ArrayList<Edge> edges;
		ArrayList<Integer>[] aristas;
		int[] positionway;
		HashMap<String, Integer> memo;
		int roots[];
		int weight;
		int v;
		int e;
		@SuppressWarnings("unchecked")
		public Graf(int v,int e) {
			this.v=v;
			this.e=e;
			roots=new int[v];
			for (int i = 0; i < roots.length; i++) {
				roots[i]=i;
			}
			positionway=new int[v];
			memo=new HashMap<>();
			edges=new ArrayList<>();
			edges2=new HashMap<>();
			aristas=new ArrayList[v];
		}
		String getKey(int i,int j) {
			int min=Math.min(j, i);
			int max=Math.max(j, i);
			return max+"_"+min;
		}
		void add(int i,int j,int w) {
			weight+=w;
			Edge n=new Edge();
			n.src=i;
			n.dest= j;
			n.weight=w;
			if(roots[j]==j) {
				roots[j]=i;
			}else if(roots[i]==i) {
				roots[i]=j;
			}
			edges2.put(getKey(i, j), n);
			edges.add(n);
			ArrayList<Integer> ars=aristas[i];
			ArrayList<Integer> ars1=aristas[j];
			if(ars==null) {
				ars=new ArrayList<>();
			}
			if(ars1==null) {
				ars1=new ArrayList<>();
			}
			ars.add(j);
			ars1.add(i);
			aristas[i]=ars;
			aristas[j]=ars1;
		}
		int response(int i,int j) {
			if(i==j) {
				return 0;
			}else {
				int p=roots[i];
				return Math.max(edges2.get(getKey(i, p)).weight,response(p, j));
			}
		}
		void way(int a,boolean mar[],int[] i,int ret[],HashMap<Integer,Integer> con) {
			if(!mar[a]) {
				mar[a]=true;
				ret[i[0]]=i[0];
				positionway[a]=i[0];
				con.put(i[0],a);
				
			}else {
				ret[i[0]]=ret[positionway[a]];
			}
			ArrayList<Integer> ady=aristas[a];
			for (int j = 0; j < ady.size(); j++) {
				if(!mar[ady.get(j)]) {
					i[0]++;
					way(ady.get(j), mar, i, ret,con);
					ret[i[0]]=ret[positionway[a]];
				}
			
			}
			i[0]=i[0]+1;
			
		}
		Graf MST() {
			edges.sort(new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					
					return o1.compareTo(o2);
				}
			});
			int[] union=new int[v];
			
			for (int i = 0; i < union.length; i++) {
				union[i]=i;
			}
			int j=0;
			Graf n=new Graf(v, v-1);
			for (int i = 0; i < e&&j<v-1; i++) {
				Edge tm=edges.get(i);
				int p1 = getR(union, tm.dest);
				int p2 = getR(union, tm.src);
				if(p1!=p2) {
					union[p2] = p1;
					n.add(tm.src, tm.dest,tm.weight);
					j++;
				}
				
			}
			return n;
		}

		
	}
	private static int getR(int[] union, int t1) {
		if (union[t1] == t1) {
			return t1;
		} else {
			int p=getR(union, union[t1]);
			union[t1]=p;
			return p;
		}
	}
	public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	String line;
	while((line=br.readLine())!=null&&!line.equals("")) {
		String[] nE=line.split(" ");
		int v=Integer.parseInt(nE[0]);
		int e=Integer.parseInt(nE[1]);
		Graf gf=new Graf(v, e);

		for (int i = 0; i < e; i++) {
			String[] edge=br.readLine().split(" ");
			int src=Integer.parseInt(edge[0]);
			int des=Integer.parseInt(edge[1]);
			int weight=Integer.parseInt(edge[2]);
			gf.add(src-1, des-1, weight);
			
		}
		Graf gfT=gf.MST();
		int[] way=new int[2*v-1];
		HashMap<Integer,Integer> inv=new HashMap<>();
		gfT.way(0, new boolean[v], new int[1], way,inv);
		SegmentTree sgt=new SegmentTree(0, way.length-1);
		
		for (int i = 0; i < way.length; i++) {
			sgt.set(i,way[i]);
		}
		int q=Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			String[] query=br.readLine().split(" ");
			int s=Integer.parseInt(query[0]);
			int d=Integer.parseInt(query[1]);
			if(!gfT.edges2.containsKey(gfT.getKey(s-1, d-1))) {
				int cpV=sgt.getMax(gfT.positionway[s-1], gfT.positionway[d-1]);
				int cp=inv.get(cpV);
				int val=Math.max(gfT.response(s-1, cp),gfT.response(d-1, cp));
				int ret=gfT.weight-val+gf.edges2.get(gfT.getKey(s-1, d-1)).weight;
				bw.write(ret+"\n");
			}else {
				int ret=gfT.weight;
				bw.write(ret+"\n");
			}
		
		}
	}
		
		
		br.close();
		bw.close();
	}

	static class SegmentTree {
		int start, end;
		SegmentTree leftTree, rightTree;
		int maxValue;

		public SegmentTree(int start, int end) {
			this.start = start;
			this.end = end;
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
				maxValue=value;
				return;
			}

			int mid = (start + end) / 2;

			if(pos <= mid)
				leftTree.set(pos, value);
			else
				rightTree.set(pos, value);

			maxValue=Math.max(leftTree.maxValue, rightTree.maxValue);
				

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