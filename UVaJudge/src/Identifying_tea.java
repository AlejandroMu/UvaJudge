import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

 public class Identifying_tea {
	
	public static void main(String[] args) throws IOException {
		BufferedReader lec=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter es=new BufferedWriter(new OutputStreamWriter(System.out));
		String line;
		while((line=lec.readLine())!=null&&!line.equals("")) {
			String sec[]=lec.readLine().split(" ");
			int[] res=new int[6];
			for (int i = 0; i < sec.length; i++) {
				int pos=Integer.parseInt(sec[i]);
				res[pos]+=1;
			}
			int val=Integer.parseInt(line);
			es.write(res[val]+"\n");
						
		}
		es.close();
		lec.close();
				
	}

}