package org.danilofes.paa.tp2;

/**
 * Resolve o problema de encontrar o caminho de menor energia usando um algoritmo baseado
 * em programação dinâmica. 
 */
public class DynamicSeamCarving extends SeamCarvingAlgorithm {

	@Override
	protected int[] findLowestEnergyPath(RgbImage img, WeightMatrix mx, WeightMatrix my) {
		int xMax = img.width;
		int yMax = img.height;
		
		int[] lep = new int[yMax];
		
		int[][] path = new int[yMax][xMax];
		double[][] pathEnergy = new double[yMax][xMax];
		
		for (int i = 0; i < yMax; i++) {
			for (int j = 0; j < xMax; j++) {
				this.computeLep(i, j, img, mx, my, path, pathEnergy);
			}
		}
		
		int argMin = 0;
		double eMin = pathEnergy[yMax-1][0];
		for (int j = 1; j < xMax; j++) {
			if (pathEnergy[yMax-1][j] < eMin) {
				argMin = j;
				eMin = pathEnergy[yMax-1][j];
			}
		}
		lep[yMax-1] = argMin;
		for (int i = yMax-2; i > 0; i--) {
			lep[i] = path[i+1][lep[i+1]];
		}
		return lep;
	}

	private void computeLep(int i, int j, RgbImage img, WeightMatrix mx, WeightMatrix my, int[][] path, double[][] pathEnergy) {
		if (i == 0) {
			path[i][j] = j; 
			pathEnergy[i][j] = img.energy(mx, my, i, j);
		} else {
			double e = img.energy(mx, my, i, j);
			int argMin = j;
			double eMin = pathEnergy[i-1][j];
			
			if (j-1 >= 0 && pathEnergy[i-1][j-1] < eMin) {
				argMin = j-1;
				eMin = pathEnergy[i-1][j-1];
			}
			if (j+1 < img.width && pathEnergy[i-1][j+1] < eMin) {
				argMin = j+1;
				eMin = pathEnergy[i-1][j+1];
			}
			
			path[i][j] = argMin; 
			pathEnergy[i][j] = e + eMin;
		}
	}

}
