package org.danilofes.paa.tp3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInput {

	public static void main(String[] args) throws Exception {
		Random r = new Random(System.currentTimeMillis());

		for (int i = 5; i <= 50; i += 5) {
			generateInput(i, r);
		}

	}

	private static void generateInput(int n, Random r) throws IOException {
		
		String fileName = String.format("input/i%d.txt", n);
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		try {
			//
			int k1 = 2 * n;
			int k2 = n / 4;
			
			int wins[] = new int[n];
			int losses[] = new int[n];
			int matches[][] = new int[n][n];
			int matchesLeft[] = new int[n];
			
			for (int i = 0; i < n; i++) {
				wins[i] = r.nextInt(k1);
				losses[i] = r.nextInt(k1);
				matchesLeft[i] = 0;
				
				for (int j = i; j < n; j++) {
					if (j == i) {
						matches[i][j] = 0;
						matches[j][i] = 0;
					} else {
						int mIJ = r.nextInt(k2);
						matches[i][j] = mIJ;
						matches[j][i] = mIJ;
						matchesLeft[i] += mIJ;
						matchesLeft[j] += mIJ;
					}
				}

				matchesLeft[i] += r.nextInt(k2);
			}

			writer.write(n + "\n");
			for (int i = 0; i < n; i++) {
				writer.write(String.format("T%d %d %d %d", i, wins[i], losses[i], matchesLeft[i]));
				for (int j = 0; j < n; j++) {
					writer.write(" " + matches[i][j]);
				}
				writer.write("\n");
			}
			
		} finally {
			writer.close();
		}
	}

}
