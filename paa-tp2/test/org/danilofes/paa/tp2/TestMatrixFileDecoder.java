package org.danilofes.paa.tp2;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

public class TestMatrixFileDecoder {

	@Test
	public void shouldReadBothMatrix() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("input/matrix.txt"));
		try {
			MatrixFileDecoder decoder = new MatrixFileDecoder();
			WeightMatrix[] m = decoder.read(reader);
			WeightMatrix mx = m[0];
			WeightMatrix my = m[1];

			assertEquals(3, mx.width);
			assertEquals(3, mx.height);
			
			assertEquals(-1, mx.valueAt(0, 0), 0.01);
			assertEquals(-1, mx.valueAt(0, 1), 0.01);
			assertEquals(0, mx.valueAt(0, 2), 0.01);

			assertEquals(-1, mx.valueAt(1, 0), 0.01);
			assertEquals(0, mx.valueAt(1, 1), 0.01);
			assertEquals(1, mx.valueAt(1, 2), 0.01);
			
			assertEquals(0, mx.valueAt(2, 0), 0.01);
			assertEquals(1, mx.valueAt(2, 1), 0.01);
			assertEquals(1, mx.valueAt(2, 2), 0.01);

			assertEquals(5, my.width);
			assertEquals(3, my.height);
			
			assertEquals(-0.5, my.valueAt(0, 0), 0.01);
			assertEquals(-1, my.valueAt(0, 1), 0.01);
			assertEquals(0, my.valueAt(0, 2), 0.01);
			assertEquals(1, my.valueAt(0, 3), 0.01);
			assertEquals(0.5, my.valueAt(0, 4), 0.01);
			
			assertEquals(-0.5, my.valueAt(1, 0), 0.01);
			assertEquals(-1, my.valueAt(1, 1), 0.01);
			assertEquals(0, my.valueAt(1, 2), 0.01);
			assertEquals(1, my.valueAt(1, 3), 0.01);
			assertEquals(0.5, my.valueAt(1, 4), 0.01);
			
			assertEquals(-0.5, my.valueAt(2, 0), 0.01);
			assertEquals(-1, my.valueAt(2, 1), 0.01);
			assertEquals(0, my.valueAt(2, 2), 0.01);
			assertEquals(1, my.valueAt(2, 3), 0.01);
			assertEquals(0.5, my.valueAt(2, 4), 0.01);
			
		} finally {
			reader.close();
		}
	}
	
}
