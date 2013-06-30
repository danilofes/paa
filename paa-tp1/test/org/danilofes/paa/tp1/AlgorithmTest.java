package org.danilofes.paa.tp1;

import static org.junit.Assert.assertEquals;

import org.danilofes.paa.tp1.backtracking.BackTrackingAlgorithm;
import org.danilofes.paa.tp1.dynamic.DynamicAlgorithm;
import org.danilofes.paa.tp1.greedy.GreedyAlgorithm;
import org.danilofes.paa.tp1.shared.LcssAlgorithm;
import org.junit.Test;

public class AlgorithmTest {

	@Test
	public void testBacktracking() {
		testGreedyInput(new BackTrackingAlgorithm());
		testWithBacktrackingInput(new BackTrackingAlgorithm());
	}

	@Test
	public void testGreedy() {
		testGreedyInput(new GreedyAlgorithm());
	}

	@Test
	public void testDynamic() {
		testGreedyInput(new DynamicAlgorithm());
		testWithBacktrackingInput(new DynamicAlgorithm());
	}

	private void testGreedyInput(LcssAlgorithm algorithm) {
//		assertEquals(0, algorithm.lcss(1, "", ""));
//		assertEquals(1, algorithm.lcss(1, "a", "a"));
//		assertEquals(2, algorithm.lcss(1, "abc", "afc"));
		assertEquals(3, algorithm.lcss(2, "bola", "cola"));
//		assertEquals(3, algorithm.lcss(1, "abbbc", "xaucbibi"));
//		assertEquals(3, algorithm.lcss(3, "defkk", "defykk"));
//		assertEquals(6, algorithm.lcss(3, "abcxxdefkk", "abcdyydefykk"));
//		assertEquals(6, algorithm.lcss(3, "lovxxelyxxxxx", "xxxxxxxlovely"));
//		assertEquals(0, algorithm.lcss(4, "lovxxxelyxxx", "xxxxxxlovely"));
//		assertEquals(6, algorithm.lcss(6, "ccbcdbacda", "adaccbcdbc"));
	}

	private void testWithBacktrackingInput(LcssAlgorithm algorithm) {
//		assertEquals(7, algorithm.lcss(1, "lovxxelyxxxxx", "xxxxxxxlovely"));
//		assertEquals(10, algorithm.lcss(3, "lovxxxelxyxxxx", "xxxlovelyxxxxxxx"));
	}

}
