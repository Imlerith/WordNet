package wordnet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

	private final WordNet wordnet;

	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		String outcast = nouns[0];
		int d = distance_from_each(nouns[0], nouns);
		for (int i = 1; i < nouns.length; i++) {
			int min_distance = distance_from_each(nouns[i], nouns);
			if (d < min_distance) {
				d = min_distance;
				outcast = nouns[i];
			}
		}
		return outcast;
	}

	private int distance_from_each(String noun, String[] nouns) {
		int d = 0;
		for (String n : nouns) {
			d += wordnet.distance(noun, n);
		}
		return d;
	}

	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}
