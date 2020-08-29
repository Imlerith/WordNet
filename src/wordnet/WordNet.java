package wordnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {

	private final Digraph digraph;
	private final Map<String, List<Integer>> words = new HashMap<>();
	private final Map<Integer, String> synsets = new HashMap<>();

	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		In in = new In(synsets);
		while (!in.isEmpty()) {
			String[] line = in.readLine().split(",");
			int index = Integer.parseInt(line[0]);
			this.synsets.put(index, line[1]);
			// filter words by if they are keys in the dictionary
			Arrays.stream(line[1].split(" ")).forEach(word -> {
				if (!words.containsKey(word)) {
					words.put(word, new ArrayList<>());
				}
				words.get(word).add(index);
			});
		}
		in.close();

		digraph = new Digraph(this.synsets.size());
		in = new In(hypernyms);
		while (!in.isEmpty()) {
			String[] line = in.readLine().split(",");
			int synset = Integer.parseInt(line[0]);
			for (int i = 1; i < line.length; i++) {
				digraph.addEdge(synset, Integer.parseInt(line[i]));
			}
		}
		in.close();

		DirectedCycle finder = new DirectedCycle(digraph);
		if (finder.hasCycle()) {
			throw new IllegalArgumentException("The graph is not a rooted DAG");
		}
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return words.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		if (word == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		return words.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		if (nounA == null || nounB == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		if (!isNoun(nounA) || !isNoun(nounB)) {
			throw new IllegalArgumentException("Wrong word");
		}
		SAP sap = new SAP(digraph);
		return sap.length(words.get(nounA), words.get(nounB));
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA
	// and nounB in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		if (nounA == null || nounB == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		if (!isNoun(nounA) || !isNoun(nounB)) {
			throw new IllegalArgumentException("Wrong word");
		}
		SAP sap = new SAP(digraph);
		int ancestor = sap.ancestor(words.get(nounA), words.get(nounB));

		if (ancestor == -1) {
			throw new IllegalArgumentException("No common ancestor");
		}
		return synsets.get(ancestor);
	}

}
