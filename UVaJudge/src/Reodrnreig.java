import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;

public class Reodrnreig {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
			int n=Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				Hashtable<String, ArrayList<String>> sums = new Hashtable<>();
				
					String dictio[]=br.readLine().split(" ");
					for (int k = 0; k < dictio.length; k++) {
						String clave=getKey(dictio[k]);
						String palabra=dictio[k];
						if(sums.containsKey(clave)) {
							ArrayList<String> lista=sums.get(clave);
							lista.add(palabra);
							sums.put(clave, lista);
						}else {
							ArrayList<String> d=new ArrayList<>();
							d.add(palabra);
							sums.put(clave, d);
						}
					}
					dictio=br.readLine().split(" ");
					for (int j = 0; j < dictio.length; j++) {
						String clave=getKey(dictio[j]);
						if(sums.containsKey(clave)) {
							if(sums.get(clave).size()>1) {
								ArrayList<String > dicionario=sums.get(clave);
								dicionario.sort(new Comparator<String>() {

									@Override
									public int compare(String o1, String o2) {
										// TODO Auto-generated method stub
										
										return o1.compareToIgnoreCase(o2);
									}
								});
								dictio[j]=dicionario.get(0);
								
							}
							else if(sums.get(clave).size()==1) {
								ArrayList<String > dicionario=sums.get(clave);
									dictio[j]=dicionario.get(0);
								
							}
						}
					}
				for (int j = 0; j < dictio.length; j++) {
					if((j+1)==dictio.length) {
						bw.write(dictio[j]);
					}else {
						
						bw.write(dictio[j]+" ");
					}
				}
				bw.newLine();
			}
			
						
		
		br.close();
		bw.close();
				
	}
	
	public static String getKey(String a) {
		String res=a.charAt(0)+"_"+a.charAt(a.length()-1)+"_"+a.length();
		char[] s=a.toCharArray();
		Arrays.sort(s);
		String tmp=new String(s);
		res=tmp+"_"+res;
		
		
		return res;
	}
}