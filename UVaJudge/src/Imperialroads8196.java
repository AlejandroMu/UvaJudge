import java.io.*;
import java.nio.channels.OverlappingFileLockException;
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
			this.e=e+1;
			roots=new int[v];
			positionway=new int[v];
			memo=new HashMap<>();
			edges=new ArrayList<>();
			edges2=new HashMap<>();
			aristas=new ArrayList[v];
			add(0,0,0);
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
			if(i==j||i==0) {
				return 0;
			}else {
				int p=roots[i];
				return Math.max(edges2.get(getKey(i, p)).weight,response(p, j));
			}
//			int i1=i;
//			int j1=j;
//			int res=0;
//			char[] mar=new char[v];
//			int maxi=-1;
//			int maxj=-1;
//			int common=-1;
//			boolean stop=false;
//			while(!stop) {
//				if(mar[j]!='i') {
//					mar[j]='j';
//				}else {
//					common=j;
//					break;
//				}
//				if(mar[i]!='j') {
//					mar[i]='i';
//				}else {
//					common=i;
//					break;
//				}
//				int pi=roots[i];
//				int pj=roots[j];
//				int wip=edges2.get(getKey(i,pi)).weight;
//				int wjp=edges2.get(getKey(j,pj)).weight;
//				maxi=Math.max(maxi, wip);
//				maxj=Math.max(maxj, wjp);
//				memo.put(getKey(i1, pi),maxi);
//				memo.put(getKey(j1, pj),maxj);
//				i=pi;
//				j=pj;
//			} 
//			int maxI=memo.get(getKey(i1, common));
//			int maxJ=memo.get(getKey(j1, common));
//			res=Math.max(maxI, maxJ);
//			return res;
		}
		void setRoots(int r){
			Queue<Integer> cola=new ArrayDeque<>();
			boolean mar[]=new boolean[v];
			boolean as[]=new boolean[v];
			cola.add(r);
			while(!cola.isEmpty()) {
				int tmp=cola.poll();
				if(!mar[tmp]) {
					mar[tmp]=true;
					ArrayList<Integer> ad=aristas[tmp];
					for (int i = 0; i < ad.size(); i++) {
						int t=ad.get(i);
						if(!mar[t]&&!as[t]) {
							roots[t]=tmp;
							as[t]=true;
							cola.add(t);
						}
					}
				}
			}
		}
		void way(int a,boolean mar[],int[] i,int way[],HashMap<Integer,Integer> V,HashMap<Integer,Integer> VR) {
			if(!mar[a]) {
				mar[a]=true;
				V.put(a,i[1]);
				VR.put(i[1],a);
				way[i[0]]=i[1];
				positionway[a]=i[0];
				i[0]++;
				i[1]++;
				ArrayList<Integer> ady= aristas[a];
				for (int j = 0; j < ady.size(); j++) {
					if(!mar[ady.get(j)]) {
						roots[ady.get(j)]=a;
						way(ady.get(j), mar, i, way, V, VR);
						way[i[0]]=V.get(a);
						i[0]++;
					}
				}
				
			}
			
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
		public int getRoot() {
			int ret=0;
			for (int i = 0; i < roots.length; i++) {
				if(roots[i]==i) {
					return i;
				}
			}
			return ret;
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
	BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/alejandro/git/uvaJudge/UVaJudge/bin/respuesta.txt"));
	String line;
	while((line=br.readLine())!=null&&!line.equals("")) {
		String[] nE=line.split(" ");
		int v=Integer.parseInt(nE[0]);
		int e=Integer.parseInt(nE[1]);
		Graf gf=new Graf(v, e);

		for (int i = 0; i < e; i++) {
			String[] edge=br.readLine().split(" ");
			int src=Integer.parseInt(edge[0])-1;
			int des=Integer.parseInt(edge[1])-1;
			int weight=Integer.parseInt(edge[2]);
			gf.add(src, des, weight);
			
		}
		Graf gfT=gf.MST();
		int[] way=new int[2*v-1];
		HashMap<Integer,Integer> inv=new HashMap<>();
		HashMap<Integer,Integer> primeravez=new HashMap<>();
		gfT.way(0, new boolean[v], new int[2], way,primeravez,inv);
		SegmentTree sgt=new SegmentTree(0, way.length-1);
		
		for (int i = 0; i < way.length; i++) {
			sgt.set(i,way[i]);
		}
//		gfT.setRoots(0);
		int q=Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			String[] query=br.readLine().split(" ");
			int s=Integer.parseInt(query[0])-1;
			int d=Integer.parseInt(query[1])-1;
			if(!gfT.edges2.containsKey(gfT.getKey(s, d))) {
				int pS=gfT.positionway[s];
				int pD=gfT.positionway[d];
				int cpV=sgt.getMin(Math.min(pS, pD), Math.max(pS, pD));
				int cp=inv.get(cpV);
				int val=Math.max(gfT.response(s, cp),gfT.response(d, cp));
				Edge ed=gf.edges2.get(gf.getKey(s, d));
				boolean exist=ed!=null;
				int sum=exist?ed.weight:0;
				int ret=gfT.weight-val+sum;
				bw.write(ret+"");
				bw.newLine();
			}else {
				int ret=gfT.weight;
				bw.write(ret+"");
				bw.newLine();

			}
		
		}
	}
		
		
		br.close();
		bw.close();
	}

	static class SegmentTree {
		int start, end;
		SegmentTree leftTree, rightTree;
		int minValue;
		int maxValue;

		public SegmentTree(int start, int end) {
			this.start = start;
			this.end = end;
			this.minValue = Integer.MAX_VALUE;
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



