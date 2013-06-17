package org.danilofes.paa.tp3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class StatsMain {

	/**
	 * Método main do programa.
	 */
	public static void main(String[] args) throws Exception {
		
		// Aquecimento
		execTime("input/i10.txt");
		
		long[][] timeByN = new long[][]{
			{5,  execTime("input/i5.txt")},
			{10,  execTime("input/i10.txt")},
			{15,  execTime("input/i15.txt")},
			{20,  execTime("input/i20.txt")},
			{25,  execTime("input/i25.txt")},
			{30,  execTime("input/i30.txt")},
			{35,  execTime("input/i35.txt")},
			{40,  execTime("input/i40.txt")},
			{45,  execTime("input/i45.txt")},
			{50,  execTime("input/i50.txt")}
		};

		writeCsv(timeByN, "stats/timeByN.csv");

	}

	private static void writeCsv(long[][] data, String file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		try {
			for (int i = 0; i < data.length; i++) {
				long[] row = data[i];
				for (int j = 0; j < row.length; j++) {
					if (j > 0) {
						writer.write("; ");
					}
					writer.write("" + row[j]);
				}
				writer.write("\n");
			}
		} finally {
			writer.close();
		}
	}

	private static long execTime(String ... args) throws Exception {
		long start = System.currentTimeMillis();
		Main.main(args);
		return System.currentTimeMillis() - start;
	}

}
