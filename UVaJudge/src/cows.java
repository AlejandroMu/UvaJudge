import java.io.*;
import java.util.*;

public class cows {
	public static void main(String[] args) throws IOException {
		Scanner br = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int cas = br.nextInt();
		while (cas-- > 0) {
			int cows = br.nextInt();
			ArrayList<ArrayDeque<Integer>> cowsP = new ArrayList<>();
			for (int i = 0; i < cows; i++) {
				int cycle = br.nextInt();
				cowsP.add(new ArrayDeque<Integer>());
				while (cycle-- > 0) {
					cowsP.get(i).add(br.nextInt());
				}
				cowsP.get(i).add(-1);
			}
			boolean change = true;
			int days = 0;
			int finalCows = 0;
			int ultimateDay=0;
			while (change) {
				int[] ret = process(cowsP, ultimateDay);
				change = ret[0] != 0;
				if(change) {
					finalCows += ret[0];
					days = ret[1];
					ultimateDay=ret[2];
				}
			}
			days++;
			days=finalCows==0?0:days;
			finalCows=cows-finalCows;
			bw.write(finalCows + " " + days);
			bw.newLine();

		}
		bw.close();
		br.close();

	}

	private static int[] process(ArrayList<ArrayDeque<Integer>> cowsP, int days) {
		int d=days;
		boolean stop = false;
		int[] ret = new int[3];
		while (!stop) {
			stop = true;
			boolean delete = false;
			int cows = -1;
			int milkMin = 251;
			for (int k = 0; k < cowsP.size(); k++) {
				ArrayDeque<Integer> i = cowsP.get(k);
					int tmp = i.poll();
					if (tmp == -1) {
						stop = stop && true;
						i.add(tmp);
						tmp = i.poll();
					} else {
						stop = false;

					}
					if (tmp < milkMin) {
						milkMin = tmp;
						cows = k;
						delete = true;
					} else if (tmp == milkMin) {
						delete = false;
					}
					i.add(tmp);
				
			}
			if (delete) {
				cowsP.remove(cows);
				ret[0]++;
				ret[1] = d;
			}
			d++;
		}
		ret[2]=d;
		return ret;
	}

}
