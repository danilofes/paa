package org.danilofes.paa.tp2;

public final class RgbImage {

	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;

	public final int height;
	public final int width;
	public final int maxColor;
	
	private final int[][][] pixels;
	private final double[][] luminosity;

	public RgbImage(int maxColor, int[][][] pixels) {
		this.maxColor = maxColor;
		this.pixels = pixels;
		this.height = pixels.length;
		this.width = pixels[0].length;
		
		luminosity = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int[] p = pixels[i][j];
				luminosity[i][j] = 0.3 * p[R] + 0.59 * p[G] + 0.11 * p[B]; 
			}
		}
	}

	public Pixel pixelAt(int i, int j) {
		return new Pixel(pixels[constrainI(i)][constrainJ(j)]);
	}
	
	public double energy(WeightMatrix mx, WeightMatrix my, int i, int j) {
		double dx = this.convolution(mx, i, j);
		double dy = this.convolution(my, i, j);
		return Math.sqrt(dx*dx + dy*dy);
	}

	public RgbImage transpose() {
		
		int height = this.width;
		int width = this.height;
		
		int[][][] pixelsT = new int[height][width][3];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pixelsT[i][j][R] = pixels[j][i][R]; 
				pixelsT[i][j][G] = pixels[j][i][G]; 
				pixelsT[i][j][B] = pixels[j][i][B]; 
			}
		}

		return new RgbImage(this.maxColor, pixelsT);
	}
	
	double convolution(WeightMatrix m, int pi, int pj) {
		double sum = 0.0;
		int hOffset = m.height / 2;
		int wOffset = m.width / 2;
		for (int i = -hOffset; i <= hOffset; i++) {
			for (int j = -wOffset; j <= wOffset; j++) {
				double factor = m.valueAt(hOffset + i, wOffset + j);
				if (factor != 0.0) {
					sum += factor * luminosity[constrainI(pi + i)][constrainJ(pj + j)];
				}
			}
		}
		return sum;
	}

	double luminosity(int i, int j) {
		return luminosity[constrainI(i)][constrainJ(j)];
	}
	
	public class Pixel {
		private final int[] colors;
		public Pixel(int[] colors) {
			this.colors = colors;
		}
		public int r() {
			return colors[R];
		}
		public int g() {
			return colors[G];
		}
		public int b() {
			return colors[B];
		}
	}

	private int constrainI(int i) {
		if (i < 0) {
			return 0;
		} 
		if (i >= height) {
			return height - 1;
		}
		return i;
	}

	private int constrainJ(int j) {
		if (j < 0) {
			return 0;
		}
		if (j >= width) {
			return width - 1;
		}
		return j;
	}
}
