package wordnet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class TestSAP {

	private SAP sap;

	@BeforeEach
	public void setUpDigraph() {
		In in = new In("/Users/sergeynasekin/eclipse_workspace_algos_2/WordNet/tests/wordnet/digraph1.txt");
		Digraph G = new Digraph(in);
		sap = new SAP(G);
	}

	@Test
	public void testLength1() {
		int length = sap.length(3, 11);
		assertEquals(4, length);
	}

	@Test
	public void testAncestor1() {
		int ancestor = sap.ancestor(3, 11);
		assertEquals(1, ancestor);
	}

	@Test
	public void testLength2() {
		int length = sap.length(9, 12);
		assertEquals(3, length);
	}

	@Test
	public void testAncestor2() {
		int ancestor = sap.ancestor(9, 12);
		assertEquals(5, ancestor);
	}

}
