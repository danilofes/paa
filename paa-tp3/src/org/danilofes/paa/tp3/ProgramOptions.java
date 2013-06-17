package org.danilofes.paa.tp3;

/**
 * Decodifica os argumentos da linha de comando.
 */
public class ProgramOptions {

	String inputFile = null;
	String outputFile = null;

	public ProgramOptions(String[] args) {
		if (args.length < 1) {
			throw new RuntimeException("Input file not specified.");
		}
		this.inputFile = args[0];
		if (args.length > 1) {
			this.outputFile = args[1];
		}
	}

}
