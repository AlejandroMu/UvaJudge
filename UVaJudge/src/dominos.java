

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class dominos {
	
	public static void main(String[] args) throws IOException {
		Scanner es=new Scanner(System.in);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int cas=es.nextInt();
		for (int i = 0; i < cas; i++) {
			int n=es.nextInt();
			int m=es.nextInt();
			Graph gr=new Graph(n);
			for (int j = 0; j < m; j++) {
				int in=es.nextInt();
				int f=es.nextInt();
				gr.add(in, f);
			}
			int r=gr.forest();
			bw.write(r+"\n");
		}
		bw.close();
		es.close();
	}
	public static class Graph{
		ArrayList<Integer>[] aristas;
		int n;
		@SuppressWarnings("unchecked")
		public Graph(int a) {
			aristas=new ArrayList[a+1];
			n=a+1;
		}
		public void add(int i,int f) {
			ArrayList<Integer> tmp=aristas[i];
			if(tmp==null) {
				tmp=new ArrayList<>();
			}
			tmp.add(f);
			aristas[i]=tmp;
			
		}
		
		public void DFS(int el,boolean[] mar,Stack<Integer> pila){
			if(!mar[el]) {
				mar[el]=true;
				ArrayList<Integer> ady=aristas[el];
				for (int i = 0;ady!=null&& i < ady.size(); i++) {
					if(!mar[ady.get(i)]) {
						DFS(ady.get(i), mar, pila);
					}
				}
			}
			
			if(pila!=null) {
				pila.push(el);
			}
		
		}
		public int forest() {
			Stack<Integer> pila=new Stack<>();
			boolean[] mar=new boolean[n];
			for (int i = 1; i < mar.length; i++) {
				if(!mar[i]) {
					DFS(i, mar, pila);
				}
			}
			mar=new boolean[n];
			int ret=0;
			while(!pila.isEmpty()) {
				int tmp=pila.pop();
				if(!mar[tmp]) {
					ret++;
					DFS(tmp, mar, null);
				}
			}
			return ret;
		}
		
	}

}
