
import java.util.*;
import java.io.*;
//12519
public class Farnsworth
{
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
			int a=BellmanFord(this, i);
			res=a<0;
		}
		return res;
	}
	int BellmanFord(Graph graph,int src)
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
		
		if(dist[src]<0) {
			dist[src]=-1;
		}
		return dist[src];
	}

	void printArr(int dist[], int V)
	{
		System.out.println("Vertex Distance from Source");
		for (int i=0; i<V; ++i)
			System.out.println(i+"\t\t"+dist[i]);
	}
	}
	public static void main(String[] args)throws Exception
	{
		Scanner sc=new Scanner(System.in);
		BufferedWriter es=new BufferedWriter(new OutputStreamWriter(System.out));
		int n=sc.nextInt();
		int box=sc.nextInt();
//		int ca=1;
		while(n!=0&&box!=0) {
			Graph graph=new Graph(n+1,2*box);
			for (int i = 0; i <2*box; i+=2) {
				int a=sc.nextInt();
				int b=sc.nextInt();
				int c=sc.nextInt();
				 graph.edge[i].src = a;
                 graph.edge[i].dest = b;
                 graph.edge[i].weight = c;
                 graph.edge[i+1].src = b;
                 graph.edge[i+1].dest = a;
                 graph.edge[i+1].weight = -1*c;
			}
			boolean res=graph.decided();
//			System.out.println(ca);
//			ca++;
			if(res){
                es.write("Y");
			}else{
		        es.write("N");
    
			}
			es.newLine();
		 n=sc.nextInt();
		 box=sc.nextInt();
		}
		sc.close();
		es.close();
	}
}
