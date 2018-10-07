import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Imperialroads8196 {
	public static class Edge implements Comparable<Edge>{
		int src;
		int dest;
		int weight;
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(weight, o.weight);
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return src+"_"+dest+"_"+weight;
		}
	}
	public static class Graf{
		ArrayList<Edge>[] aristas;
		HashMap<String,Edge> edges2;
		ArrayList<Edge> edges;
		int weight;
		int v;
		int e;
		@SuppressWarnings("unchecked")
		public Graf(int v,int e) {
			this.v=v;
			this.e=e;
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
			Edge n1=new Edge();
			n1.src=j;
			n1.dest=i;
			n1.weight=w;
			edges2.put(getKey(i, j), n);
			edges.add(n);
			ArrayList<Edge> p1=aristas[i];
			if(p1==null) {
				p1=new ArrayList<>();
			}
			p1.add(n);
			aristas[i]=p1;
			ArrayList<Edge> p2=aristas[j];
			if(p2==null) {
				p2=new ArrayList<>();
			}
			p2.add(n1);
			aristas[j]=p2;
		}
		int DFS(int i,int j,Edge e,boolean[] mar) {
			if(i==j) {
				mar[i]=true;
				return e!=null?e.weight:-1;
			}
			if(!mar[i]) {
				mar[i]=true;
				int max=-1;
				ArrayList<Edge> ady=aristas[i];
				for (int k = 0; k < ady.size(); k++) {
					Edge tmp=ady.get(k);
					if(!mar[tmp.dest]) {
						int v=DFS(tmp.dest, j, tmp, mar);
						if(v!=-1)
						{
							max=Math.max(tmp.weight, v);
							break;
						}
							
					}
				}
				return max;
			}else {
				return -1;
			}
			
		}
		Graf MST() {
			edges.sort(new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					// TODO Auto-generated method stub
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
			return getR(union, union[t1]);
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
		int q=Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			String[] query=br.readLine().split(" ");
			int s=Integer.parseInt(query[0]);
			int d=Integer.parseInt(query[1]);
			int val=gfT.DFS(s-1, d-1, null, new boolean[v]);
			int ret=gfT.weight-val+gf.edges2.get(gfT.getKey(s-1, d-1)).weight;
			bw.write(ret+"\n");
		}
	}
		
		
		br.close();
		bw.close();
	}

}