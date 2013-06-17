package org.danilofes.paa.tp3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Grafo especilizado para representar uma rede de fluxos.
 */
public class FlowNetwork {

	final ArrayList<Vertex> vertices;

	private int[][] flow;
	
	public FlowNetwork() {
		this.vertices = new ArrayList<FlowNetwork.Vertex>();
	}
	
	public Vertex addVertex() {
		Vertex v = new Vertex(this.vertices.size());
		this.vertices.add(v);
		return v;
	}
	
	public void clearFlow() {
		int n = this.vertices.size();
		this.flow = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.flow[i][j] = 0;
			}
		}
	}
	
	public Vertex vertex(int i) {
		return this.vertices.get(i);
	}

	public int flow(Vertex u, Vertex v) {
		return this.flow[u.id][v.id];
	}
	
	public void connect(Vertex u, Vertex v, int capacity) {
		Edge e = new Edge(u.id, v.id, capacity);
		u.edges.add(e);
		Edge eResidual = new Edge(v.id, u.id, 0);
		v.edges.add(eResidual);
	}

	public void passFlow(Vertex u, Vertex v, int flow) {
		this.flow[u.id][v.id] += flow;
		this.flow[v.id][u.id] -= flow;
	}

	public class Vertex {
		final int id;
		final List<Edge> edges;
		public Vertex(int id) {
			this.id = id;
			this.edges = new LinkedList<FlowNetwork.Edge>();
		}
	}
	
	public class Edge {
		final int u;
		final int v;
		final int capacity;
		public Edge(int u, int v, int capacity) {
			this.u = u;
			this.v = v;
			this.capacity = capacity;
		}
	}

	
}
