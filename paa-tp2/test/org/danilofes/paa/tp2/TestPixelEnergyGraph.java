package org.danilofes.paa.tp2;

import org.danilofes.paa.tp2.PixelEnergyGraph.Vertex;
import org.junit.Assert;
import org.junit.Test;

public class TestPixelEnergyGraph {

	@Test
	public void shouldConnectToNeighbors() throws Exception {
		RgbImage img = new RgbImage(255, new int[][][]{
			{ {255,   0,   0}, {  0,   0,   0}, {255, 255, 255} },
			{ {  0,   0,   0}, {  0, 255,   0}, {  0,   0,   0} },
			{ {128, 128, 128}, {  0,   0,   0}, {  0,   0, 255} }
		});
		
		PixelEnergyGraph g = new PixelEnergyGraph(img, WeightMatrix.SOBEL_X, WeightMatrix.SOBEL_Y);
		
		Vertex s = g.vertices[0];
		Vertex v1 = g.vertices[1];
		Vertex v2 = g.vertices[2];
		Vertex v3 = g.vertices[3];
		Vertex v4 = g.vertices[4];
		Vertex v5 = g.vertices[5];
		Vertex v6 = g.vertices[6];
		Vertex v7 = g.vertices[7];
		Vertex v8 = g.vertices[8];
		Vertex v9 = g.vertices[9];
		Vertex t = g.vertices[10];
		
		Assert.assertArrayEquals(new Vertex[]{v1, v2, v3}, g.outgoingVertices(s));
		Assert.assertArrayEquals(new Vertex[]{v4, v5}, g.outgoingVertices(v1));
		Assert.assertArrayEquals(new Vertex[]{v4, v5, v6}, g.outgoingVertices(v2));
		Assert.assertArrayEquals(new Vertex[]{v5, v6}, g.outgoingVertices(v3));
		Assert.assertArrayEquals(new Vertex[]{v7, v8}, g.outgoingVertices(v4));
		Assert.assertArrayEquals(new Vertex[]{v7, v8, v9}, g.outgoingVertices(v5));
		Assert.assertArrayEquals(new Vertex[]{v8, v9}, g.outgoingVertices(v6));
		Assert.assertArrayEquals(new Vertex[]{t}, g.outgoingVertices(v7));
		Assert.assertArrayEquals(new Vertex[]{t}, g.outgoingVertices(v8));
		Assert.assertArrayEquals(new Vertex[]{t}, g.outgoingVertices(v9));
		Assert.assertArrayEquals(new Vertex[]{}, g.outgoingVertices(t));

	}
	
}
