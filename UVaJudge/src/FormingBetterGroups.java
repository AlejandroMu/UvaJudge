
import java.io.*;
import java.util.*;

public class FormingBetterGroups {
	static HashMap<Integer, Integer> memo = new HashMap<>();

	public static int combinaciones(int[] tmp, int d, int mask, int i) {
		int s = 0;
		if (mask == 0) {
			return 1;
		}
		for (; i < tmp.length - 2; i++) {
			boolean ai = ((mask >> i) & 1) == 1;
			for (int j = i + 1; ai && j < tmp.length - 1; j++) {
				boolean aj = ((mask >> j) & 1) == 1;
				for (int j2 = j + 1; aj && j2 < tmp.length; j2++) {
					boolean ak = ((mask >> j2) & 1) == 1;
					if (ak) {
						int[] arr = new int[3];
						arr[0] = tmp[i];
						arr[1] = tmp[j];
						arr[2] = tmp[j2];
						boolean val = response(arr, d);
						if (val) {
							int maskT = mask ^ (1 << i);
							maskT = maskT ^ (1 << j);
							maskT = maskT ^ (1 << j2);
							if (memo.containsKey(maskT)) {
								s += memo.get(maskT);
							} else {
								int v = combinaciones(tmp, d, maskT, i);
								s += v;
								memo.put(maskT, v);
							}
						}
					}

				}
			}
		}

		return s;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = sc.nextInt();
		int d = sc.nextInt();
		while (n != 0 || d != 0) {
			memo = new HashMap<>();
			int[] tmp = new int[n];
			for (int i = 0; i < tmp.length; i++) {
				tmp[i] = sc.nextInt();
			}
			int allcombinations = combinaciones(tmp, d, (1 << n) - 1, 0);

			bw.write(allcombinations + "\n");

			n = sc.nextInt();
			d = sc.nextInt();
		}
		sc.close();
		bw.close();
	}

	private static boolean response(int[] arr, int d) {

		return Math.max(arr[0], Math.max(arr[1], arr[2])) - Math.min(arr[0], Math.min(arr[1], arr[2])) <= d;
	}

}
