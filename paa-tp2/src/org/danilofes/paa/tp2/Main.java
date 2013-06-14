package org.danilofes.paa.tp2;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Classe principal do programa.
 * @author Danilo
 */
public class Main {

	/**
	 * Método main do programa.
	 */
	public static void main(String[] args) throws Exception {
		
		ProgramOptions options = new ProgramOptions(args);
		
		String matrixFile = options.matrixFile;
		WeightMatrix mx = WeightMatrix.SOBEL_X;
		WeightMatrix my = WeightMatrix.SOBEL_Y;
		if (matrixFile != null && !matrixFile.isEmpty()) {
			BufferedReader reader = new BufferedReader(new FileReader("input/matrix.txt"));
			try {
				MatrixFileDecoder decoder = new MatrixFileDecoder();
				WeightMatrix[] mTuple = decoder.read(reader);
				mx = mTuple[0];
				my = mTuple[1];
			} finally {
				reader.close();
			}
			
		}

		SeamCarvingAlgorithm algorithm;
		if (options.g) {
			algorithm = new GraphSeamCarving();
		} else {
			algorithm = new DynamicSeamCarving();
		}
		
		PpmDecoder decoder = new PpmDecoder();
		BufferedReader reader = new BufferedReader(new FileReader(options.inputFile));
		try {
			RgbImage img = decoder.read(reader);
			
			if (options.w > 0) {
				img = algorithm.decreaseWidth(img, mx, my, options.w);
			}
			if (options.h > 0) {
				img = algorithm.decreaseHeight(img, mx, my, options.h);
			}
			
			decoder.write(System.out, img);
			
		} finally {
			reader.close();
		}

	}

}
