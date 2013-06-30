package org.danilofes.paa.tp1.shared;

/**
 * Interface que define um algoritmo para o problema Longest common segment subsequence. 
 * @author Danilo
 */
public interface LcssAlgorithm {

	/**
	 * Computa o tamanho da Longest common segment subsequence.
	 * @param k O tamanho mínimo de segmentos que compoem a subsequência.
	 * @param wi A primeira string.
	 * @param wj A segunda string.
	 * @return Resultado.
	 */
	int lcss(int k, String wi, String wj); 

	long getNumComp();
}
