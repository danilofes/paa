package org.danilofes.paa.tp3;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.danilofes.paa.tp3.FlowNetwork.Vertex;

/**
 * Faz a analise do times eliminados.
 */
public class EliminationAnalyser {

	/**
	 * Encontra os times eliminados e imprime um relatório.
	 * @param input A entrada com os dados do campeonato.
	 * @param ps O PrintStream para imprimir o resultado.
	 * @throws IOException caso ocorra erro na escrita.
	 */
	public void findEliminated(Input input, PrintStream ps) throws IOException {
		int eliminatedCount = 0;
		for (int i = 0; i < input.n; i++) {
			
			EliminationAnalysis analysis = this.analyseTeam(input, i);
			boolean eliminated = analysis.isEliminated();
			if (eliminated) {
				eliminatedCount++;
			}
			
			if (eliminatedCount > 1 && eliminated) {
				ps.println();
			}
			printReport(input, analysis, ps);
			
		}
		
		if (eliminatedCount == 0) {
			ps.println("Nenhum time eliminado.");
		}
	}

	/**
	 * Analisa se o time <code>x</code> está eliminado.
	 * @param input A entrada do programa.
	 * @param x O time a analisar.
	 * @return O resultado da análise.
	 */
	private EliminationAnalysis analyseTeam(Input input, int x) {
		EliminationAnalysis analysis = new EliminationAnalysis(x);
		FlowNetwork fn = new FlowNetwork();
		Vertex source = fn.addVertex();
		Vertex sink = fn.addVertex();
		
		int xMaxWins = input.wins[x] + input.matchesLeft[x];
		
		// vértices dos times
		Vertex[] teams = new Vertex[input.n];
		for (int i = 0; i < input.n; i++) {
			teams[i] = fn.addVertex();
			// um time i não pode ganhar mais partidas que x
			if (i != x) {
				int maxWins = xMaxWins - input.wins[i];
				fn.connect(teams[i], sink, maxWins);  
				if (maxWins < 0) {
					analysis.unreachable(i);
				}
			}
		}
		
		// vértices dos jogos
		int totalMatches = 0;
		Map<Integer, Integer[]> matches = new HashMap<Integer, Integer[]>();
		for (int i = 0; i < input.n; i++) {
			for (int j = i + 1; j < input.n; j++) {
				if (i != x && j != x) {
					int matchesToPlay = input.confrontsLeft[i][j];
					if (matchesToPlay > 0) {
						totalMatches += matchesToPlay;
						Vertex matchIxJ = fn.addVertex();
						fn.connect(source, matchIxJ, matchesToPlay);
						fn.connect(matchIxJ, teams[i], matchesToPlay);
						fn.connect(matchIxJ, teams[j], matchesToPlay);
						matches.put(matchIxJ.id, new Integer[]{i, j});
					}
				}
			}
		}
		
		// Encontra o fluxo máximo.
		MaxFlowAlgorithm algorithm = new MaxFlowAlgorithm();
		int maxFlow = algorithm.findMaxFlow(fn, source, sink);
		
		if (maxFlow < totalMatches) {
			// time está eliminado pois não existem atribuições para toda partida
			
			Set<Integer> sCut = algorithm.getSCut();
			
			for (int i = 0; i < teams.length; i++) {
				if (sCut.contains(teams[i].id)) {
					analysis.unbeatable(i);
				}
			}
			
//			for (Edge matchEdge : source.edges) {
//				Vertex match = fn.vertex(matchEdge.v);
//				if (fn.flow(source, match) < matchEdge.capacity) {
//					// Aresta não saturada, então vencedor da partida não pode ser atribuído.
//					// Logo, os dois times fazem parte do grupo que impede X de ser campeão.
//					Integer[] matchTeams = matches.get(match.id);
//					analysis.unbeatable(matchTeams[0]);
//					analysis.unbeatable(matchTeams[1]);
//				}
//			}
			
//			for (int i = 0; i < input.n; i++) {
//				if (i != x) {
//					Vertex u = teams[i];
//					Edge edge = u.edges.get(0);
//					if (fn.flow(u, sink) == edge.capacity) {
//						// TODO
//						
//						analysis.unbeatable(i);
//					}
//				}
//			}
			
		}
		
		return analysis;
	}

	/**
	 * Imprime o resultado da análise.
	 * @param input Entrada do programa.
	 * @param analysis Análise do time.
	 * @param ps PrintStream de saída.
	 */
	private void printReport(Input input, EliminationAnalysis analysis, PrintStream ps) {
		if (!analysis.isEliminated()) {
			return;
		}
		int x = analysis.x;
		int xMaxWins = input.wins[x] + input.matchesLeft[x];
		
		ps.printf("%s é eliminado.\n", input.teams[x]);
		ps.printf("Ele pode ganhar, no máximo, %d + %d = %d jogos.\n", input.wins[x], input.matchesLeft[x], xMaxWins);
		
		List<Integer> unreachable = analysis.getUnreachableTeams();
		if (!unreachable.isEmpty()) {
			for (Integer t : unreachable) {
				ps.printf("%s ganhou um total de %d jogos.\n", input.teams[t], input.wins[t]);
				ps.printf("Eles jogam entre si %d vezes.\n", 0);
				ps.printf("Assim, em média, cada uma das equipes vence %d/%d = %d jogos.\n", input.wins[t], 1, input.wins[t]);
				break;
			}
		} else {
			List<Integer> unbeatable = analysis.getUnbeatableGroup();
			StringBuilder names = new StringBuilder();
			int teams = unbeatable.size();
			int totalWins = 0;
			int totalMatches = 0;
			for (int i = 0; i < teams; i++) {
				int ti = unbeatable.get(i);
				
				// concatena os times
				if (i == teams - 1) {
					names.append(" e ");
				} else if (i > 0) {
					names.append(", ");
				}
				names.append(input.teams[ti]);
				
				// soma as vitórias
				totalWins += input.wins[ti];
				
				// soma as partidas restantes
				for (int j = i + 1; j < teams; j++) {
					int tj = unbeatable.get(j);
					if (ti < tj) {
						totalMatches += input.confrontsLeft[ti][tj];
					}
				}
			} 
			
			int finalWins = totalWins + totalMatches;
			double mean = ((double) finalWins) / teams;
			
			ps.printf("%s ganharam um total de %d jogos.\n", names.toString(), totalWins);
			ps.printf("Eles jogam entre si %d vezes.\n", totalMatches);
			ps.printf("Assim, em média, cada uma das equipes vence %d/%d = %.1f jogos.\n", finalWins, teams, mean);
		}
		ps.flush();
	}

}
