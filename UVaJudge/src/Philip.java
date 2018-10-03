import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Philip {

	public static void main(String[] args) throws IOException {
		Scanner es = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int v = es.nextInt();
		while (v != 0) {
			int d = es.nextInt();
			ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
			ArrayList<Integer> tmp = new ArrayList<>();
			tmp.add(0);
			tmp.add(d);
			for (int i = 0; i < v; i++) {
				int esf = es.nextInt();
				d = es.nextInt();
				if (esf == 0) {
					if (i != v - 1) {
						tmp.add(d);
					}
				} else {
					arr.add(tmp);
					tmp = new ArrayList<>();
					tmp.add(esf);
					if (i != v - 1) {
						tmp.add(d);
					}
				}
			}
			arr.add(tmp);
			int sum = 0;
			for (int i = arr.size() - 1; i >= 1; i--) {
				tmp = arr.get(i);
				int esf = tmp.get(0);
				tmp.remove(0);
				sum += proces(tmp, esf);
				arr.get(i - 1).addAll(tmp);
			}
			tmp = arr.get(0);
			for (int i = 1; i < tmp.size(); i++) {
				sum += tmp.get(i);
			}
			bw.write(sum + "\n");
			v = d;
		}
		es.close();
		bw.close();
	}

	private static int proces(ArrayList<Integer> tmp, int esf) {
		int sum = 0;
		if (esf == 0) {
			return 0;
		}
		if (tmp.size() < esf) {
			for (int i = 0; i < tmp.size(); i++) {
				sum += tmp.get(i) / 2;
			}
			tmp.removeAll(tmp);
		} else {
			tmp.sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer arg0, Integer arg1) {
					// TODO Auto-generated method stub
					return -1 * arg0.compareTo(arg1);
				}
			});
			for (int i = 0; i < esf; i++) {
				sum += tmp.get(i) / 2;
			}
			for (int i = 0; i < esf; i++) {
				tmp.remove(0);
			}
		}
		return sum;
	}

}
