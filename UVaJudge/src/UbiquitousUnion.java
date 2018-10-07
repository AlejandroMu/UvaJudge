import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class UbiquitousUnion {

	public static void main(String[] args) throws IOException {
		Scanner es = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = es.nextInt();
		int c = es.nextInt();
		int ca = 1;
		while (t != 0 && c != 0) {
			int[] union = new int[t];
			for (int i = 0; i < union.length; i++) {
				union[i] = i;
			}
			for (int i = 0; i < c; i++) {
				int t1 = es.nextInt() - 1;
				int c1 = es.nextInt() - 1;
				int p1 = getR(union, t1);
				int p2 = getR(union, c1);
				union[p2] = p1;

			}
			int th = forest(union);
			String res = "Case " + ca + ": " + th;
			bw.write(res + "\n");

			t = es.nextInt();
			c = es.nextInt();
			ca++;
		}
		bw.close();
		es.close();
	}

	private static int getR(int[] union, int t1) {
		if (union[t1] == t1) {
			return t1;
		} else {
			return getR(union, union[t1]);
		}
	}

	private static int forest(int[] union) {
		int ret = 0;
		for (int i = 0; i < union.length; i++) {
			if (i == union[i]) {
				ret++;
			}
		}
		return ret;
	}

}
