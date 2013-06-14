package org.danilofes.paa.tp1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.danilofes.paa.tp1.backtracking.BackTrackingAlgorithm;
import org.danilofes.paa.tp1.dynamic.DynamicAlgorithm;
import org.danilofes.paa.tp1.greedy.GreedyAlgorithm;
import org.danilofes.paa.tp1.shared.LcssAlgorithm;

/**
 * Classe principal do programa.
 * @author Danilo
 */
public class Main {

	/**
	 * Método main do programa.
	 * @param args args[0] deve receber "backtracking", "dynamic" ou "greedy". 
	 * @throws Exception em caso de falha para ler entrada.
	 */
	public static void main(String[] args) throws Exception {
		// Obtem o algoritmo de acordo com parâmetro passado
		LcssAlgorithm algorithm = getAlgorithm(args[0]);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine();
		while (line != null && !line.isEmpty() && !line.equals("0")) {
			// Leitura da entrada
			int k = Integer.parseInt(line);
			String wi = reader.readLine();
			String wj = reader.readLine();

			// Resolve o problema e imprime
			int result = algorithm.lcss(k, wi, wj);
			System.out.println(result);

			line = reader.readLine();
		}

	}

	private static LcssAlgorithm getAlgorithm(String id) {
		if ("backtracking".equals(id)) {
			return new BackTrackingAlgorithm();
		} else if ("greedy".equals(id)) {
			return new GreedyAlgorithm();
		}
		return new DynamicAlgorithm();
	}

}
