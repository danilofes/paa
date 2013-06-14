package org.danilofes.paa.tp2;

/**
 * Classe abstrata base para implementação dos algoritmos de Seam Carving.
 */
public abstract class SeamCarvingAlgorithm {

	public final RgbImage decreaseWidth(RgbImage originalImg, WeightMatrix mx, WeightMatrix my, int pixels) {
		RgbImage img = originalImg;
		for (int i = 0; i < pixels; i++) {
			img = decreaseWidth(img, mx, my);
		}
		return img;
	}
	
	public final RgbImage decreaseHeight(RgbImage originalImg, WeightMatrix mx, WeightMatrix my, int pixels) {
		RgbImage img = decreaseWidth(originalImg.transpose(), mx.transpose(), my.transpose(), pixels);
		return img.transpose();
	}

	public final RgbImage decreaseWidth(RgbImage img, WeightMatrix mx, WeightMatrix my) {
		int xMax = img.width;
		int yMax = img.height;
		
		if (xMax < 2 || yMax < 1) {
			throw new RuntimeException("Image too small");
		}
		
		int[] path = this.findLowestEnergyPath(img, mx, my);

		int[][][] newPixels = new int[yMax][xMax-1][3];
		
		for (int i = 0; i < yMax; i++) {
			for (int j = 0, jr = 0; j < xMax; j ++) {
				if (path[i] != j) {
					newPixels[i][jr][RgbImage.R] = img.pixelAt(i, j).r();
					newPixels[i][jr][RgbImage.G] = img.pixelAt(i, j).g();
					newPixels[i][jr][RgbImage.B] = img.pixelAt(i, j).b();
					jr++;
				}
			}
		}
		
		RgbImage newImg = new RgbImage(img.maxColor, newPixels);
		return newImg;
	}

	/**
	 * Encontra o caminho de menor energia da imagem dada.
	 * @param img A imagem a ser reduzida.
	 * @param mx A matriz de pesos do gradiente X.
	 * @param my A matrix de pesos do gradiente Y.
	 * @return Um arranjo com os índices dos pixels de menor energia para cada linha da imagem.
	 */
	protected abstract int[] findLowestEnergyPath(RgbImage img, WeightMatrix mx, WeightMatrix my);
}
