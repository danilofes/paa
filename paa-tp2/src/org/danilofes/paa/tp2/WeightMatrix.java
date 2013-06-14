package org.danilofes.paa.tp2;

public class WeightMatrix {

	public static WeightMatrix SOBEL_X = new WeightMatrix(new double[][]{
		{-1,  0,  1},
        {-2,  0,  2},
        {-1,  0,  1}
	});
	
	public static WeightMatrix SOBEL_Y = new WeightMatrix(new double[][]{
		{-1, -2, -1},
        { 0,  0,  0},
        { 1,  2,  1}
	});
	
	public final int height;
	public final int width;
	private final double[][] m;

	public WeightMatrix(double[][] m) {
		this.m = m;
		this.height = m.length;
		this.width = m[0].length;
	}

	public double valueAt(int i, int j) {
		return m[i][j];
	}
	
	public WeightMatrix transpose() {
		
		int height = this.width;
		int width = this.height;
		
		double[][] mT = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mT[i][j] = m[j][i]; 
			}
		}

		return new WeightMatrix(mT);
	}
}
