import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class D_as_in_Daedalus {
//F
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line;
		int[] cards = { 1, 10, 100, 1000,10000 };
		while ((line = br.readLine()) != null && !line.equals("")) {
			String[] info = line.split(" ");
			
			int rounds = Integer.parseInt(info[1]);
			int totalDedalus = 0;
			int extraDedalus = 0;
			for (int i = 0; i < rounds; i++) {
				String[] round = br.readLine().split(" ");
				int b = Integer.parseInt(round[0]);
				int dedalus = Integer.parseInt(round[1]);
				Integer pDedalus = dedalus;
				int sumRound = 0;
				if (round.length > 2) {
					for (int j = 2; j < round.length; j++) {
						sumRound += Integer.parseInt(round[j]);
					}
				}

				if (sumRound + dedalus <= b) {
					totalDedalus += dedalus;
				}

				pDedalus = b - sumRound;
				if (pDedalus > 0) {
					int apostar = pDedalus.toString().length();
					if (apostar < 5) {
						pDedalus = cards[apostar - 1];
					} else {
						pDedalus = cards[4];
					}					
				} else {
					pDedalus=0;
				}
				extraDedalus+=pDedalus;
			}
			
			bw.write((extraDedalus-totalDedalus)+"\n");
		}
		br.close();
		bw.close();
	}
}