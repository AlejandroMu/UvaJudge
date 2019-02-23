import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sort {

	public static void main(String[] arg) throws IOException {
		BufferedReader lec=new BufferedReader(new InputStreamReader(System.in));
		String line[]=lec.readLine().split(" ");
		long[] number=new long[line.length];
		for(int val=0;val<line.length;val++) {
			number[val]=Long.parseLong(line[val]);
		}
		burbuja(number);
		for(long n:number) {
			System.out.print(n+" ");
		}
		
	}

	private static void burbuja(long[] number) {
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number.length-1; j++) {
				if(number[j]>number[j+1]) {
					long aux=number[j];
					number[j]=number[j+1];
					number[j+1]=aux;
				}
			}
		}
	}
}
