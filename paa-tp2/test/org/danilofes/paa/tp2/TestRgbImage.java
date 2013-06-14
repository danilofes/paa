package org.danilofes.paa.tp2;

import static org.junit.Assert.assertEquals;

import org.danilofes.paa.tp2.RgbImage.Pixel;
import org.junit.Assert;
import org.junit.Test;

public class TestRgbImage {

	@Test
	public void testPixelOutOfBounds() {
		RgbImage img = buildImg();
		assertColor(255, 0, 0, img.pixelAt(-1, -1));
		assertColor(255, 0, 0, img.pixelAt(0, -1));
		assertColor(0, 0, 0, img.pixelAt(1, -1));
		assertColor(128, 128, 128, img.pixelAt(3, 0));
		assertColor(0, 0, 255, img.pixelAt(2, 3));
	}

	@Test
	public void testLuminosity() {
		RgbImage img = buildImg();
		Assert.assertEquals(0.3 * 255, img.luminosity(-1, -1), 0.01);
		Assert.assertEquals(0.3 * 255, img.luminosity(0, 0), 0.01);
		Assert.assertEquals(0.59 * 255, img.luminosity(1, 1), 0.01);
		Assert.assertEquals(0.11 * 255, img.luminosity(2, 2), 0.01);
		Assert.assertEquals(0.11 * 255, img.luminosity(3, 3), 0.01);
	}

	@Test
	public void testEnergy() {
		RgbImage img = buildImg();
		WeightMatrix mx = WeightMatrix.SOBEL_X;
		WeightMatrix my = WeightMatrix.SOBEL_Y;
		Assert.assertEquals(111.79, img.energy(mx, my, 0, 0), 0.01);
		Assert.assertEquals(192.23, img.energy(mx, my, 1, 1), 0.01);
		Assert.assertEquals(93.76, img.energy(mx, my, 2, 2), 0.01);
	}

	private RgbImage buildImg() {
		RgbImage img = new RgbImage(255, new int[][][]{
			{ {255,   0,   0}, {  0,   0,   0}, {255, 255, 255} },
			{ {  0,   0,   0}, {  0, 255,   0}, {  0,   0,   0} },
			{ {128, 128, 128}, {  0,   0,   0}, {  0,   0, 255} }
		});
		return img;
	}

	private void assertColor(int r, int g, int b, Pixel pixel) {
		assertEquals(r, pixel.r());
		assertEquals(g, pixel.g());
		assertEquals(b, pixel.b());
	}

}
