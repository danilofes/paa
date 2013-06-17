package org.danilofes.paa.tp3;

import java.util.ArrayList;
import java.util.List;


public class EliminationAnalysis {

	final int x;
	private boolean eliminated = false;
	private List<Integer> unreachableTeams;
	private List<Integer> unbeatableGroup;
	
	public EliminationAnalysis(int x) {
		this.x = x;
		this.unreachableTeams = new ArrayList<Integer>();
		this.unbeatableGroup = new ArrayList<Integer>();
	}

	public boolean isEliminated() {
		return this.eliminated;
	}
	
	public void unreachable(int team) {
		this.eliminated = true;
		this.unreachableTeams.add(team);
	}
	
	public void unbeatable(int team) {
		this.eliminated = true;
		this.unbeatableGroup.add(team);
	}

	public List<Integer> getUnreachableTeams() {
		return this.unreachableTeams;
	}

	public List<Integer> getUnbeatableGroup() {
		return this.unbeatableGroup;
	}

}
