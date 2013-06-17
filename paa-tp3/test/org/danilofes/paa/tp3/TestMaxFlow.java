package org.danilofes.paa.tp3;

import junit.framework.Assert;

import org.danilofes.paa.tp3.FlowNetwork.Vertex;
import org.junit.Test;

public class TestMaxFlow {

	@Test
	public void testMaxFlow() {
		
		FlowNetwork fn = new FlowNetwork();
		Vertex a = fn.addVertex();
		Vertex b = fn.addVertex();
		Vertex c = fn.addVertex();
		Vertex d = fn.addVertex();
		Vertex e = fn.addVertex();
		Vertex f = fn.addVertex();
		Vertex g = fn.addVertex();
		
		fn.connect(a, b, 3);
		fn.connect(a, d, 3);

		fn.connect(b, c, 4);

		fn.connect(c, a, 3);
		fn.connect(c, d, 1);
		fn.connect(c, e, 2);

		fn.connect(d, e, 2);
		fn.connect(d, f, 6);
		
		fn.connect(e, g, 1);
		
		fn.connect(f, g, 9);
		
		MaxFlowAlgorithm algo = new MaxFlowAlgorithm();
		int maxFlow = algo.findMaxFlow(fn, a, g);
		
		Assert.assertEquals(5, maxFlow);

		Assert.assertEquals(2, fn.flow(a, b));
		Assert.assertEquals(3, fn.flow(a, d));

		Assert.assertEquals(2, fn.flow(b, c));

		Assert.assertEquals(0, fn.flow(c, a));
		Assert.assertEquals(1, fn.flow(c, d));
		Assert.assertEquals(1, fn.flow(c, e));

		Assert.assertEquals(0, fn.flow(d, e));
		Assert.assertEquals(4, fn.flow(d, f));
		
		Assert.assertEquals(1, fn.flow(e, g));
		
		Assert.assertEquals(4, fn.flow(f, g));
	}

}
