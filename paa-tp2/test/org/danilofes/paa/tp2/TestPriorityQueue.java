package org.danilofes.paa.tp2;

import org.danilofes.paa.tp2.PriorityQueue.Comparator;
import org.junit.Assert;
import org.junit.Test;

public class TestPriorityQueue {

	@Test
	public void testOrdering() {
		
		final double[] w = new double[]{4.0, 1.0, 5.0, 3.0, 6.0, 0.0, 2.0}; 
		
		Comparator comparator = new Comparator() {
			@Override
			public int compare(int e1, int e2) {
				return Double.compare(w[e1], w[e2]);
			}
		};
		
		PriorityQueue q = new PriorityQueue(6, comparator);
		
		Assert.assertEquals(5, q.popMin());
		Assert.assertEquals(1, q.popMin());
		
		w[2] = 0.75;
		q.decreaseKey(2);

		w[4] = 3.5;
		q.decreaseKey(4);
		
		Assert.assertEquals(2, q.popMin());
		Assert.assertEquals(6, q.popMin());
		Assert.assertEquals(3, q.popMin());
		Assert.assertEquals(4, q.popMin());
		Assert.assertEquals(0, q.popMin());
	}

	@Test
	public void testDecreaseKey() {
		
		final double inf = Double.POSITIVE_INFINITY;
		final double[] w = new double[]{inf, 135.0, 5.0, 3.0, inf, 40.0, inf}; 
		
		Comparator comparator = new Comparator() {
			@Override
			public int compare(int e1, int e2) {
				return Double.compare(w[e1], w[e2]);
			}
		};
		
		PriorityQueue q = new PriorityQueue(6, comparator);
		
		w[6] = 0.75;
		q.decreaseKey(6);

		Assert.assertEquals(6, q.popMin());
	}

	
}
