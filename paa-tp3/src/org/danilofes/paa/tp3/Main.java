package org.danilofes.paa.tp3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

/**
 * Classe principal do programa.
 */
public class Main {

	/**
	 * Método main do programa.
	 */
	public static void main(String[] args) throws Exception {

		ProgramOptions options = new ProgramOptions(args);

		Input input;
		BufferedReader reader = new BufferedReader(new FileReader(options.inputFile));
		try {
			input = new Input(reader);
		} finally {
			reader.close();
		}

		EliminationAnalyser analyser = new EliminationAnalyser();
		
		PrintStream ps;
		if (options.outputFile != null) {
			ps = new PrintStream(options.outputFile);
		} else {
			ps = System.out;
		}
		try {
			analyser.findEliminated(input, ps);
		} finally {
			ps.close();
		}

	}

}
