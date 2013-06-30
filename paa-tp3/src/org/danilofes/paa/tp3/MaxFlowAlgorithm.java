package org.danilofes.paa.tp3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.danilofes.paa.tp3.FlowNetwork.Edge;
import org.danilofes.paa.tp3.FlowNetwork.Vertex;

/**
 * Implementação do algoritmo Edmonds-Karp, que é uma variante do Ford-Fulkerson
 * que usa busca em laargura para encontrar caminhos que incrementem o fluxo.
 */
public class MaxFlowAlgorithm {

	Set<Integer> sTCut;

	/**
	 * Encontra o fluxo máximo de <code>source</code> para <code>sink</code> na rede <code>g</code>,
	 * preenchendo os fluxos de cada aresta. 
	 * @param g O grafo da rede de fluxos. 
	 * @param source O vértice de origem do fluxo.
	 * @param sink O vértice de destino.
	 * @return O valor do fluxo máximo.
	 */
	public int findMaxFlow(FlowNetwork g, Vertex source, Vertex sink) {
		int flow = 0;
		int maxFlow = 0;
		
		g.clearFlow();
		this.sTCut = new HashSet<Integer>();
		
		do {
			flow = this.augmentFlow(g, source, sink);
			maxFlow += flow;
			// Enquanto encontrar um caminho que aumenta o fluxo continua
		} while (flow > 0);
		
		return maxFlow;
	}

	public Set<Integer> getSCut() {
		return this.sTCut;
	}
	
	private static final int NIL = -1;
	private static final int UNDEFINED = -2;

	/**
	 * Aumenta o fluxo em <code>g</code> passando pelo primeiro caminho encontrado pela
	 * busca em largura.
	 * @param g O grafo da rede de fluxos. 
	 * @param source O vértice de origem do fluxo.
	 * @param sink O vértice de destino.
	 * @return O fluxo incrementado.
	 */
	private int augmentFlow(FlowNetwork g, Vertex source, Vertex sink) {
		int n = g.vertices.size();
		int[] precursor = new int[n];
		int[] bottleneck = new int[n];
		for (int i = 0; i < n; i++) {
			precursor[i] = UNDEFINED;
		}
		precursor[source.id] = NIL;
		bottleneck[source.id] = Integer.MAX_VALUE;

		LinkedList<Vertex> q = new LinkedList<Vertex>();
		q.add(source);
		
		// busca em largura
		while(!q.isEmpty()) {
			Vertex u = q.removeFirst();
			for (Edge e : u.edges) {
				Vertex v = g.vertex(e.v);
				int flow = e.capacity - g.flow(u, v);
				// se pode passar fluxo pela aresta e o nodo ainda não foi visitado
				if (flow > 0 && precursor[v.id] == UNDEFINED) {
					precursor[v.id] = u.id;
					bottleneck[v.id] = Math.min(bottleneck[u.id], flow);
					if (v != sink) {
						q.add(v);
					} else {
						// se chegou no sink termina
						break;
					}
				}
			}
		}

		// navega de trás para frente em precursor aumentando o fluxo do caminho 
		if (precursor[sink.id] != UNDEFINED) {
			int flow = bottleneck[sink.id];
			Vertex v = sink;
			int u = precursor[sink.id];
			while (u != NIL) {
				g.passFlow(g.vertex(u), v, flow);
				v = g.vertex(u);
				u = precursor[u];
			}
			return flow;
		} else {
			// Determina a partição S do corte S-T mínimo.
			for (int i = 0; i < n; i++) {
				if (precursor[i] != UNDEFINED) {
					this.sTCut.add(i);
				}
			}
		}
		
		return 0;
	}

}
