package org.danilofes.paa.tp2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Classe principal do programa.
 * @author Danilo
 */
public class StatsMain {

	/**
	 * Método main do programa.
	 */
	public static void main(String[] args) throws Exception {
		
		// Aquecimento
		execTime("input/syncducks.ppm", "-d", "-w", "1");
		
		long[][] dataByDimension = new long[][]{
			{80, execTime("input/syncducks80.ppm", "-d", "-w", "5"), execTime("input/syncducks80.ppm", "-g", "-w", "5")},
			{160, execTime("input/syncducks160.ppm", "-d", "-w", "5"), execTime("input/syncducks160.ppm", "-g", "-w", "5")},
			{320, execTime("input/syncducks320.ppm", "-d", "-w", "5"), execTime("input/syncducks320.ppm", "-g", "-w", "5")},
			{640, execTime("input/syncducks.ppm", "-d", "-w", "5"), execTime("input/syncducks.ppm", "-g", "-w", "5")}
		};

		writeCsv(dataByDimension, "stats/dimension.csv");

		long[][] dataByConvolution = new long[][]{
			{3, execTime("input/syncducks320.ppm", "-d", "-w", "10", "-m", "input/m3.txt")},
			{5, execTime("input/syncducks320.ppm", "-d", "-w", "10", "-m", "input/m5.txt")},
			{7, execTime("input/syncducks320.ppm", "-d", "-w", "10", "-m", "input/m7.txt")},
			{9, execTime("input/syncducks320.ppm", "-d", "-w", "10", "-m", "input/m9.txt")}
		};

		writeCsv(dataByConvolution, "stats/convolution.csv");
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
