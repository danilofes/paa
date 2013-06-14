package org.danilofes.paa.tp1.greedy;

import org.danilofes.paa.tp1.shared.AbstractLcssAlgorithm;

/**
 * Algoritmo usando o paradigma guloso.
 * @author Danilo
 */
public class GreedyAlgorithm extends AbstractLcssAlgorithm {

	@Override
	public int lcss(int k, String w1, String w2) {
		init(k, w1, w2);
		return lcssGreedy();
	}

	private int lcssGreedy() {
		int subseq = 0;
		int jmin = 0;
		boolean segmentOpen = false;
		// Para cada caracter i de wi
		for (int i = 0; i < wi.length(); i++) {
			boolean matchFound = false;
			// Para cada caracter j de wj a partir do ultimo casamento
			for (int j = jmin; j < wj.length();) {
				int kMin = segmentOpen ? 1 : k;
				matchFound = kMatch(kMin, i, j);
				if (matchFound) {
					// Decisão gulosa de não voltar atrás ao encontrar um casamento
					subseq += kMin;
					jmin = j + kMin;
					// Incremento adicional em i, pois +1 sempre é incrementado 
					i += kMin - 1;
					segmentOpen = true;
					break;
				} else {
					segmentOpen = false;
					j++;
				}
			}
		}
		return subseq;
	}

}
