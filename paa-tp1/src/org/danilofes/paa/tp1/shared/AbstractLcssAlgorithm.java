package org.danilofes.paa.tp1.shared;

/**
 * Classe abstrata base para implemntação dos algoritmos. Define a função básica
 * kMatch que todos os algoritmos usam para verificar casamentos de caracteres nas strings.
 * @author Danilo
 */
public abstract class AbstractLcssAlgorithm implements LcssAlgorithm {

	protected int k;
	protected String wi;
	protected String wj;
	protected long numComp;

	protected void init(int k, String wi, String wj) {
		this.k = k;
		this.wi = wi;
		this.wj = wj;
		numComp = 0;
	}

	@Override
	public long getNumComp() {
		return numComp;
	}
	
	/**
	 * Verifica se os k caracteres nas strings wi, wj, partindo das posições i, j, são iguais.
	 * @param kMin Número de caracteres a casar.
	 * @param i Posição na string 1.
	 * @param j Posição na string 2.
	 * @return true caso os k carcteres casem.
	 */
	protected boolean kMatch(int kMin, int i, int j) {
		for (int offset = 0; offset < kMin; offset++) {
			numComp++;
			int io = i + offset;
			int jo = j + offset;
			if (io >= wi.length() || jo >= wj.length() || wi.charAt(io) != wj.charAt(jo)) {
				return false;
			}
		}
		return true;
	}

}
