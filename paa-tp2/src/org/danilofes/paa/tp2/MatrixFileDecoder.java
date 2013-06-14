package org.danilofes.paa.tp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * lê e escreve imagens no formato PPM.
 */
public class MatrixFileDecoder {

	public WeightMatrix[] read(BufferedReader reader) throws IOException {
		Scanner scanner = new Scanner(reader);
		scanner.useDelimiter("\\s+");
		scanner.useLocale(Locale.US);
		
		WeightMatrix mx = this.readMatrix(scanner);
		WeightMatrix my = this.readMatrix(scanner);
		
		return new WeightMatrix[]{mx, my};
	}

	private WeightMatrix readMatrix(Scanner scanner) {
		int width = scanner.nextInt();
		int height = scanner.nextInt();

		double[][] weights = new double[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				weights[i][j] = scanner.nextDouble();
			}
		}

		return new WeightMatrix(weights);
	}

}
