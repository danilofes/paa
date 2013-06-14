package org.danilofes.paa.tp1.dynamic;

import static java.lang.Math.max;

import org.danilofes.paa.tp1.shared.AbstractLcssAlgorithm;

/**
 * Algoritmo usando o paradigma de programação dinâmica.
 * @author Danilo
 */
public class DynamicAlgorithm extends AbstractLcssAlgorithm {

	@Override
	public int lcss(int k, String w1, String w2) {
		init(k, w1, w2);
		int result = getResult(0, 0, false);
		this.table = null;
		return result;
	}

	int[][][] table;

	@Override
	protected void init(int k, String wi, String wj) {
		super.init(k, wi, wj);
		this.table = new int[wi.length()][wj.length()][2];
	
		// Preenche a tabela de resultados parciais, da direita para esquerda e de baixo pra cima.
		for (int i = wi.length() - 1; i >= 0; i--) {
			for (int j = wj.length()- 1; j >= 0; j--) {
				computeResult(i, j, false);
				computeResult(i, j, true);
			}
		}
	}
	
	private int getResult(int i, int j, boolean segmentOpen) {
		boolean outOfBounds = i >= wi.length() || j >= wj.length();
		if (outOfBounds) {
			return 0;
		}
		return table[i][j][segmentOpen ? 1 : 0];
	}
	
	private void computeResult(int i, int j, boolean segmentOpen) {
		int kMin = segmentOpen ? 1 : k;
		boolean canMatch = kMatch(kMin, i, j);
		
		// O resultado de um problema depende dos subproblemas...
		// (i + 1, j + 1, true) ou (i + k, j + k, true)
		int matchResult = canMatch ? kMin + getResult(i + kMin, j + kMin, true)
								   : 0;
		// (i + 1, j, false)
		int skipIResult = getResult(i + 1, j, false);
		// (i, j + 1, false)
		int skipJResult = getResult(i, j + 1, false);
		
		table[i][j][segmentOpen ? 1 : 0] = max(max(matchResult, skipIResult), skipJResult);
	}
}
