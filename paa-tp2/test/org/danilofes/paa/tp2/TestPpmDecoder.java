package org.danilofes.paa.tp2;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;

import org.danilofes.paa.tp2.RgbImage.Pixel;
import org.junit.Test;

public class TestPpmDecoder {

	@Test
	public void shouldReadPpm() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("input/img01.ppm"));
		try {
			PpmDecoder decoder = new PpmDecoder();
			RgbImage img = decoder.read(reader);
			
			assertEquals(3, img.width);
			assertEquals(3, img.height);
			assertEquals(255, img.maxColor);
			
			assertColor(255, 0, 0, img.pixelAt(0, 0));
			assertColor(0, 0, 0, img.pixelAt(0, 1));
			assertColor(255, 255, 255, img.pixelAt(0, 2));
			
			assertColor(0, 0, 0, img.pixelAt(1, 0));
			assertColor(0, 255, 0, img.pixelAt(1, 1));
			assertColor(0, 0, 0, img.pixelAt(1, 2));
			
			assertColor(128, 128, 128, img.pixelAt(2, 0));
			assertColor(0, 0, 0, img.pixelAt(2, 1));
			assertColor(0, 0, 255, img.pixelAt(2, 2));
		} finally {
			reader.close();
		}
	}
	
	@Test
	public void shouldWritePpm() throws Exception {
		PpmDecoder decoder = new PpmDecoder();
		RgbImage img = new RgbImage(255, new int[][][]{
			{ {255,   0,   0}, {  0,   0,   0}, {255, 255, 255} },
			{ {  0,   0,   0}, {  0, 255,   0}, {  0,   0,   0} },
			{ {128, 128, 128}, {  0,   0,   0}, {  0,   0, 255} }
		});
		
		decoder.write(System.out, img);
	}
	
	private void assertColor(int r, int g, int b, Pixel pixel) {
		assertEquals(r, pixel.r());
		assertEquals(g, pixel.g());
		assertEquals(b, pixel.b());
	}
	
}
