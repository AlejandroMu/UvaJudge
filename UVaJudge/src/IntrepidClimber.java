import java.awt.Point;
import java.io.*;
import java.util.*;

public class IntrepidClimber {
	/*
	 * sin completar
	 */
	
	
	public static void main(String[] args) throws IOException {
		BufferedWriter es=new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader lec=new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line=lec.readLine())!=null&&!line.equals("")) {
			String pa[]=line.split(" ");
			int n=Integer.parseInt(pa[0]);
			int c=Integer.parseInt(pa[1]);
			HashMap<Integer,Integer> levels=new HashMap<>();
			levels.put(1,1);
			
			Graph gr=new Graph(n+1, 2*n-2);
			for (int i = 0; i < 2*(n-1); i+=2) {
				String[] values=lec.readLine().split(" ");
				int s=Integer.parseInt(values[0]);
				int d=Integer.parseInt(values[1]);
				int w=Integer.parseInt(values[2]);
				gr.edge[i].src=s;
				gr.edge[i].dest=d;
				gr.edge[i].weight=0;
				gr.edge[i+1].src=d;
				gr.edge[i+1].dest=s;
				gr.edge[i+1].weight=w;
				int lR=levels.get(s);
				levels.put(d,lR+1);
			}
			String[] con=lec.readLine().split(" ");
			int[] consultas=new int[c];
			Point[] points=new  Point[c];
			for (int i = 0; i < consultas.length; i++) {
				consultas[i]=Integer.parseInt(con[i]);
				int l=levels.get(consultas[i]);
				points[i]=new Point(consultas[i],l);
			}
			Arrays.sort(points, new Comparator<Point>() {

				@Override
				public int compare(Point o1, Point o2) {
					return Integer.compare(o1.y,o2.y);
				}
			});
			int w=gr.BellmanFord(gr, 1, points[0].x);
			for (int i = 1; i < points.length; i++) {
				int dis=gr.BellmanFord(gr, points[i-1].x, points[i].x);
				w+=dis;
			}
			es.write(w+"\n");
						
			
			
			
		}
		es.close();
		lec.close();
		

	}

	
	public static class Edge {
		int src, dest, weight;
		Edge() {
			src = dest = weight = 0;
		}
	}
	public static class Graph{
	int V, E;
	Edge edge[];
	
	Graph(int v, int e)
	{
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i=0; i<e; ++i)
			edge[i] = new Edge();
	}

	boolean decided() {
		boolean res=false;
		for (int i = 0; i < V&&!res; i++) {
			int a=BellmanFord(this, i,i);
			res=a<0;
		}
		return res;
	}
	int BellmanFord(Graph graph,int src,int des)
	{
		int V = graph.V, E = graph.E;
		int dist[] = new int[V];

		for (int i=0; i<V; ++i)
			dist[i] = Integer.MAX_VALUE;
		dist[src] = 0;

		for (int i=1; i<V; ++i)
		{
			for (int j=0; j<E; ++j)
			{
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				if (dist[u]!=Integer.MAX_VALUE &&dist[u]+weight<dist[v])
					dist[v]=dist[u]+weight;
			}
		}
		int a=dist[des];
		for (int j=0; j<E; ++j)
		{
			int u = graph.edge[j].src;
			int v = graph.edge[j].dest;
			int weight = graph.edge[j].weight;
			if (dist[u]!=Integer.MAX_VALUE &&dist[u]+weight<dist[v])
			{	
				dist[v]=dist[u]+weight;			
			}

		}
		if(dist[des]<a) {
			dist[des]=-1;
		}
		return dist[des];
	}

	void printArr(int dist[], int V)
	{
		System.out.println("Vertex Distance from Source");
		for (int i=0; i<V; ++i)
			System.out.println(i+"\t\t"+dist[i]);
	}
	}
	
}
