package org.danilofes.paa.tp2;

import org.danilofes.paa.tp2.PixelEnergyGraph.Vertex;
import org.danilofes.paa.tp2.PriorityQueue.Comparator;

/**
 * Resolve o problema de encontrar o caminho de menor energia usando modelagem
 * baseada em grafos. 
 */
public class GraphSeamCarving extends SeamCarvingAlgorithm {

	@Override
	protected int[] findLowestEnergyPath(RgbImage img, WeightMatrix mx, WeightMatrix my) {
		final PixelEnergyGraph g = new PixelEnergyGraph(img, mx, my);
		
		// Vértice origem tem distância 0
		g.vertices[0].distance = 0.0;
		
		PriorityQueue queue = new PriorityQueue(g.vertices.length - 1, new VertexComparator(g));
		// A fila de prioridade construída já é um heap por natureza, pois apenas o vertice S tem
		// distância 0 e todos os outros infinito.
		//queue.buildMinHeap();
		
		// Usa Djikstra para encontrar o menor caminho de s -> t 
		while (!queue.isEmpty()) {
			Vertex v = g.vertices[queue.popMin()];
			for (Vertex neighbor : g.outgoingVertices(v)) {
				double newDistance = v.distance + neighbor.energy;
				if (neighbor.distance > newDistance) {
					neighbor.distance = newDistance;
					neighbor.previous = v;
					queue.decreaseKey(neighbor.id);
				}
			}
		}
		
		// Determina os pixels a serem removidos
		int[] path = new int[img.height];
		Vertex v = g.vertices[g.vertices.length - 1];
		v = v.previous;
		for (int i = path.length - 1; v.id != 0; i--) {
			path[i] = g.getJ(v);
			v = v.previous;
		}
		return path;
	}

	private static class VertexComparator extends Comparator {
		private final PixelEnergyGraph g; 
		public VertexComparator(PixelEnergyGraph g) {
			this.g = g;
		}
		@Override
		public int compare(int e1, int e2) {
			return Double.compare(g.vertices[e1].distance, g.vertices[e2].distance);
		}
	}
}
