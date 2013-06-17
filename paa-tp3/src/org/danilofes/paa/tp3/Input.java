package org.danilofes.paa.tp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Input {

	final int n;
	final String teams[];
	final int wins[];
	final int losses[];
	final int matchesLeft[];
	final int confrontsLeft[][];
	
	public Input(BufferedReader reader) throws IOException {
		Scanner scanner = new Scanner(reader);
		scanner.useDelimiter("\\s+");
		scanner.useLocale(Locale.US);
		
		this.n = scanner.nextInt();
		
		this.teams = new String[this.n];
		this.wins = new int[this.n];
		this.losses = new int[this.n];
		this.matchesLeft = new int[this.n];
		this.confrontsLeft = new int[this.n][this.n];
		
		for (int i = 0; i < this.n; i++) {
			this.teams[i] = scanner.next();
			this.wins[i] = scanner.nextInt();
			this.losses[i] = scanner.nextInt();
			this.matchesLeft[i] = scanner.nextInt();
			
			for (int j = 0; j < this.n; j++) {
				this.confrontsLeft[i][j] = scanner.nextInt();
			}
		}
	}
	
	
}
