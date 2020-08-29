package wordnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestWordNet {

	private WordNet net;

	@BeforeEach
	void setUp() throws Exception {
		String synsets = "/Users/sergeynasekin/eclipse_workspace_algos_2/WordNet/tests/wordnet/synsets.txt";
		String hypernyms = "/Users/sergeynasekin/eclipse_workspace_algos_2/WordNet/tests/wordnet/hypernyms.txt";
		net = new WordNet(synsets, hypernyms);
	}

	@Test
	void testDistance() {
		String v = "coagulation_factor";
		String w = "corn_gluten";
		int length = net.distance(v, w);
		assertEquals(4, length);
	}

	@Test
	void testSap() {
		String v = "coagulation_factor";
		String w = "corn_gluten";
		String ancestor = net.sap(v, w);
		assertEquals("protein", ancestor);
	}

	@Test
	void testIsNoun() {
		String word = "albumin";
		boolean in_wordnet = net.isNoun(word);
		assertTrue(in_wordnet);
	}
	
	@Test
	void testIsNotNoun() {
		String word = "willow";
		boolean in_wordnet = net.isNoun(word);
		assertFalse(in_wordnet);
	}

}
