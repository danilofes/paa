package org.danilofes.paa.tp2;

public class PixelEnergyGraph {

	private final RgbImage img;
	final Vertex[] vertices;

	public PixelEnergyGraph(RgbImage img, WeightMatrix mx, WeightMatrix my) {
		this.img = img;
		int v = img.height * img.width + 2;
		vertices = new Vertex[v];
		
		vertices[0] = new Vertex(0, 0.0);
		for (int i = 0; i < img.height; i++) {
			for (int j = 0; j < img.width; j++) {
				int vertexId = i * img.width + j + 1;
				vertices[vertexId] = new Vertex(vertexId, img.energy(mx, my, i, j));
			}
		}
		vertices[v-1] = new Vertex(v-1, 0.0);
	}
	public Vertex[] outgoingVertices(Vertex v) {
		int lastVertex = (img.height * img.width) + 1;
		
		// Vertice S liga a todos os pixels da primeira linha
		if (v.id == 0) {
			Vertex[] edges = new Vertex[img.width];
			for (int j = 0; j < img.width; j++) {
				edges[j] = vertices[j+1];
			}
			return edges;
		}
		
		// Vertice T não liga a ninguém
		if (v.id == lastVertex) {
			return new Vertex[]{};
		}
		
		int i = getI(v);
		int j = getJ(v);

		// Vertices da última linha ligam a T
		if (i == img.height - 1) {
			return new Vertex[]{vertices[lastVertex]};
		}
		
		// Vertices da borda esquerda |\
		int vertexBelow = v.id + img.width;
		if (j == 0) {
			return new Vertex[]{vertices[vertexBelow], vertices[vertexBelow + 1]};
		}
		
		// Vertices da borda direita /|
		if (j == img.width - 1) {
			return new Vertex[]{vertices[vertexBelow - 1], vertices[vertexBelow]};
		}
		
		// Vertices que não são de borda /|\
		return new Vertex[]{vertices[vertexBelow - 1], vertices[vertexBelow], vertices[vertexBelow + 1]};
		
	}
	private int getI(Vertex v) {
		return (v.id - 1) / img.width;
	}
	public int getJ(Vertex v) {
		return (v.id - 1) % img.width;
	} 

	public static class Vertex {
		final int id;
		final double energy;
		Vertex previous;
		double distance;
		public Vertex(int id, double energy) {
			this.id = id;
			this.energy = energy;
			this.previous = null;
			this.distance = Double.POSITIVE_INFINITY;
		}
		@Override
		public String toString() {
			return id + "";
		}
	}
}
