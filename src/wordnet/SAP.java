package wordnet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

	private final Digraph G;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		if (G == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		this.G = new Digraph(G);
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		return get_sap(v, w)[1];
	}

	// a common ancestor of v and w that participates in a shortest ancestral path;
	// -1 if no such path
	public int ancestor(int v, int w) {
		return get_sap(v, w)[0];
	}

	// length of shortest ancestral path between any vertex in v and any vertex in
	// w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		return get_sap(v, w)[1];
	}

	// a common ancestor that participates in shortest ancestral path;
	// -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		return get_sap(v, w)[0];
	}

	private int[] get_sap(int v, int w) {
		return get_sap(new BreadthFirstDirectedPaths(G, v), new BreadthFirstDirectedPaths(G, w));
	}

	private int[] get_sap(Iterable<Integer> v, Iterable<Integer> w) {
		return get_sap(new BreadthFirstDirectedPaths(G, v), new BreadthFirstDirectedPaths(G, w));
	}

	private int[] get_sap(BreadthFirstDirectedPaths paths_v, BreadthFirstDirectedPaths paths_w) {
		int ancestor = 0;
		int min_distance = Integer.MAX_VALUE;

		for (int i = 0; i < G.V(); i++) {
			// check if the shortest paths have a common ancestor
			if (paths_v.hasPathTo(i) && paths_w.hasPathTo(i)) {
				// get the total (shortest) distance between the vertices/vertex subsets
				int distance = paths_v.distTo(i) + paths_w.distTo(i);
				if (distance < min_distance) {
					// if a smaller distance found, update both the distance and the ancestor
					min_distance = distance;
					ancestor = i;
				}
			}
		}

		if (min_distance == Integer.MAX_VALUE) {
			return new int[] { -1, -1 };
		}

		return new int[] { ancestor, min_distance };
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
