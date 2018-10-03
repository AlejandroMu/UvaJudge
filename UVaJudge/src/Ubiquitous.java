import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;

public class Ubiquitous{
	//E
	public static void main(String[] args) throws IOException {
		Scanner es=new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t=es.nextInt();
		int c=es.nextInt();
		int ca=1;
		while(t!=0&&c!=0) {
			Graph g=new Graph(t);

			for (int i = 0; i < c; i++) {
				int t1=es.nextInt();
				int c1=es.nextInt();
				g.addA(t1,c1);
			}
			int th=g.forest();
			String res="Case "+ca+": "+th; 
			bw.write(res+"\n");
			
			
			 t=es.nextInt();
			 c=es.nextInt();
			 ca++;
		}
		bw.close();
		es.close();
	}
	
	public static class Graph{
		ArrayList<Integer>[] aristas;
		int n;
		@SuppressWarnings("unchecked")
		public Graph(int tam) {
			// TODO Auto-generated constructor stub
			aristas=new ArrayList[tam+1];
			n=tam+1;
		}
		public void addA(int i,int f) {
			ArrayList<Integer> tmp=aristas[i];
			ArrayList<Integer> tmp1=aristas[f];
			if(tmp==null) {
				tmp=new ArrayList<>();
			}
			if(tmp1==null) {
				tmp1=new ArrayList<>();
			}
			tmp.add(f);
			tmp1.add(i);
			aristas[i]=tmp;
			aristas[f]=tmp1;
			
		}
		public ArrayList<Integer> DFS(int el,Hashtable<Integer,Integer> a){
			
			boolean[] mar=new boolean[n];
			ArrayList<Integer> ret=new ArrayList<>();
			Stack<Integer> pila=new Stack<>();
			pila.add(el);
			while(!pila.isEmpty()) {
				int tmp=pila.pop();
				a.remove(tmp);
				if(!mar[tmp]) {
					mar[tmp]=true;
					ArrayList<Integer> ady=aristas[tmp];
					if(ady!=null&&ady.size()>0) {
						pila.addAll(ady);
					}
					ret.add(tmp);
					
				}
				
			}
			
			return ret;
		}
		
	public int forest() {
		
		Hashtable<Integer,Integer> has=new Hashtable<Integer,Integer>();
		for (int i = 1; i < aristas.length; i++) {
			has.put(i,i);
		}
		int res=0;
		int ini=1;
		while(!has.isEmpty()) {
			res++;

			DFS(ini, has);
			if(has.isEmpty()) {
				break;
			}
			ini=has.keys().nextElement();
			
		}
		return res;
	}
		
		
	}

}