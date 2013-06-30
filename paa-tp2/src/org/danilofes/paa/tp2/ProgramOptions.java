package org.danilofes.paa.tp2;

public class ProgramOptions {

	int h = 0;
	int w = 0;
	String matrixFile = null;
	boolean d = true;
	boolean g = false;
	String inputFile = null;

	public ProgramOptions(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("-h")) {
				i++;
				h = Integer.parseInt(args[i]);
			} else if (arg.equals("-w")) {
				i++;
				w = Integer.parseInt(args[i]);
			} else if (arg.equals("-m")) {
				i++;
				matrixFile = args[i];
			} else if (arg.equals("-d")) {
				d = true;
			} else if (arg.equals("-g")) {
				g = true;
			} else {
				inputFile = arg;
			}
		}
		
		if (inputFile == null || inputFile.isEmpty()) {
			throw new RuntimeException("Input file not specified.");
		}
	}
}
