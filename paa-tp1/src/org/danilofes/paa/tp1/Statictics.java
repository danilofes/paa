package org.danilofes.paa.tp1;

import java.util.Random;

import org.danilofes.paa.tp1.backtracking.BackTrackingAlgorithm;
import org.danilofes.paa.tp1.dynamic.DynamicAlgorithm;
import org.danilofes.paa.tp1.greedy.GreedyAlgorithm;
import org.danilofes.paa.tp1.shared.LcssAlgorithm;

public class Statictics {

	private Random random = new Random(System.currentTimeMillis());
	
	public static void main(String[] args) {
		Statictics statistics = new Statictics();
		
		LcssAlgorithm backTracking = new BackTrackingAlgorithm();
		LcssAlgorithm dynamic = new DynamicAlgorithm();
		LcssAlgorithm greedy = new GreedyAlgorithm();

//		System.out.println("backTracking");
//		statistics.numCompStatistics(backTracking, 3, 3, 1);
//		System.out.println("dynamic");
//		statistics.numCompStatistics(dynamic, 3, 5, 5);
//		System.out.println("greedy");
//		statistics.numCompStatistics(greedy, 3, 5, 5);

		System.out.println("dynamic vs greedy");
		statistics.dynamicVsGreedy(8);
		
//		System.out.println("precisionBySize");
//		statistics.precisionBySize(3);
//		System.out.println("precisionByK");
//		statistics.precisionByK(100);
	}
	
	public void numCompStatistics(LcssAlgorithm algorithm, int k, int n0, int increment) {
		int max = n0 + 12 * increment;
		for (int n = n0; n <= max; n += increment) {
			String w1 = randomString(n, 'a', 'f');
			String w2 = randomString(n, 'a', 'f');
			String w3 = randomString(n, 'g', 'l');
			System.out.print(n + "\t");
			// Melhor caso, strings identicas
			algorithm.lcss(k, w1, w1);
			System.out.print(algorithm.getNumComp() + "\t");
			// Caso típico, string diferentes mas com partes iguais
			algorithm.lcss(k, w1, w2);
			System.out.print(algorithm.getNumComp() + "\t");
			// Pior caso, strings totalmente diferentes
			algorithm.lcss(k, w1, w3);
			System.out.print(algorithm.getNumComp());
			System.out.println();
		}
	}

	public void precisionBySize(int k) {
		for (int n = 5; n <= 120; n+=5) {
			measurePrecision(k, n);
		}
	}

	public void precisionByK(int n) {
		for (int k = 1; k <= 10; k++) {
			measurePrecision(k, n);
		}
	}

	private void measurePrecision(int k, int n) {
		LcssAlgorithm dynamic = new DynamicAlgorithm();
		LcssAlgorithm greedy = new GreedyAlgorithm();
		long errorSum = 0;
		long totalSum = 0;
		long okFreq = 0;
		int runs = 100;
		for (int i = 0; i < runs; i++) {
			String wi = randomString(n, 'a', 'd');
			String wj = randomString(n, 'a', 'd');
			int v1 = dynamic.lcss(k, wi, wj);
			int v2 = greedy.lcss(k, wi, wj);
			int error = v1 - v2;
			errorSum += error;
			totalSum += v1;
			okFreq += error > 0 ? 0 : 1;
		}
		System.out.print(k + "\t");
		System.out.print(n + "\t");
		System.out.print(((double) okFreq * 100/runs) + "\t");
		System.out.print((100.0 * (double) errorSum / totalSum) + "\t");
		System.out.println(((double) totalSum) / runs);
	}
	
	public void dynamicVsGreedy(int k) {
		for (int n = 100; n <= 2000; n += 100) {
			dynamicVsGreedy(k, n);
		}
	}

	private void dynamicVsGreedy(int k, int n) {
		LcssAlgorithm dynamic = new DynamicAlgorithm();
		LcssAlgorithm greedy = new GreedyAlgorithm();
		int runs = 3;
		long t1 = 0;
		long t2 = 0;
		for (int i = 0; i < runs; i++) {
			String wi = randomString(n, 'a', 'h');
			String wj = randomString(n, 'a', 'h');
			t1 += measureTime(dynamic, k, wi, wj);
			t2 += measureTime(greedy, k, wi, wj);
		}
		System.out.print(k + "\t");
		System.out.print(n + "\t");
		System.out.print(((double) t1/runs) + "\t");
		System.out.print(((double) t2/runs));
		System.out.println();
	}
	
	private long measureTime(LcssAlgorithm algorithm, int k, String wi, String wj) {
		long t0 = System.currentTimeMillis();
		algorithm.lcss(k, wi, wj);
		long t = System.currentTimeMillis() - t0;
		return t;
	}

	private String randomString(int length, char fromChar, char toChar) {
		StringBuilder builder = new StringBuilder();
		int variation = toChar - fromChar;
		for (int i = 0; i < length; i++) {
			builder.append((char) (fromChar + random.nextInt(variation)));
		}
		return builder.toString();
	}

}
