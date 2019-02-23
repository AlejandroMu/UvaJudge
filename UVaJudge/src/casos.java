import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class casos {
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		BufferedWriter es=new BufferedWriter(new FileWriter("C:/Users/alejandro/git/uvaJudge/UVaJudge/bin/pruebas.txt"));
		int n=sc.nextInt();
		int e=sc.nextInt();
		int c=sc.nextInt();
		Random rs=new Random();
		ArrayList<String> lines=new ArrayList<>();
		ArrayList<String> cas=new ArrayList<>();

		for (int i = 1; i <= n-1; i++) {
			int w=(int) (rs.nextInt(100))+1;
			String l=i+" "+(i+1)+" "+w;
			lines.add(l);
		}
		for (int i =n; i <=e; i++) {
			int w=(int) (Math.random()*100);
			int s=(int)(rs.nextInt(n))+1;
			int d=(int)(rs.nextInt(n))+1;
			if(s==d) {
				if(s==n) {
					s=n-1;
				}else {
					s=n;
				}
			}
			String l=s+" "+d+" "+w;
			lines.add(l);
		}
		for (int i = 0; i <c; i++) {
			int s=(int)(rs.nextInt(n))+1;
			int d=(int)(rs.nextInt(n))+1;
			if(s==d) {
				if(s==n) {
					s=n-1;
				}else {
					s=n;
				}
			}
			String l=s+" "+d;
			cas.add(l);
		}
		
		es.write(n+" "+e);
		es.newLine();
		for (int i = 0; i < lines.size(); i++) {
			es.write(lines.get(i));
			es.newLine();
		}
		es.write(c+"");
		es.newLine();
		for (int i = 0; i < c; i++) {
			es.write(cas.get(i));
			es.newLine();
		}
		sc.close();
		es.close();
	}

}
