package org.danilofes.paa.tp1.backtracking;

import static java.lang.Math.max;

import org.danilofes.paa.tp1.shared.AbstractLcssAlgorithm;

/**
 * Algoritmo usando o paradigma força bruta (backtracking).
 * @author Danilo
 */
public class BackTrackingAlgorithm extends AbstractLcssAlgorithm {

	@Override
	public int lcss(int k, String w1, String w2) {
		init(k, w1, w2);
		return lcssRecursive(0, 0, false);
	}

	private int lcssRecursive(int i, int j, boolean segmentOpen) {
		boolean noMoreChars = i >= wi.length() || j >= wj.length();
		if (noMoreChars) {
			return 0;
		}
		
		int kMin = segmentOpen ? 1 : k;
		boolean canMatch = kMatch(kMin, i, j);
		
		int matchResult = canMatch ? kMin + lcssRecursive(i + kMin, j + kMin, true)
						           : 0;
		int skipIResult = lcssRecursive(i + 1, j, false);
		int skipJResult = lcssRecursive(i, j + 1, false);
		
		return max(max(matchResult, skipIResult), skipJResult);
	}

}
