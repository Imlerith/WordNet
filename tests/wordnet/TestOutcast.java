package wordnet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOutcast {

	private Outcast outcast;

	@BeforeEach
	void setUp() throws Exception {
		String synsets = "/Users/sergeynasekin/eclipse_workspace_algos_2/WordNet/tests/wordnet/synsets.txt";
		String hypernyms = "/Users/sergeynasekin/eclipse_workspace_algos_2/WordNet/tests/wordnet/hypernyms.txt";
		WordNet wordnet = new WordNet(synsets, hypernyms);
		outcast = new Outcast(wordnet);
	}

	@Test
	void testOutcast1() {
		String[] nouns = { "fibrinogen", "corn_gluten", "immunoglobulin_M", "proaccelerin", "whacker" };
		assertEquals("whacker", outcast.outcast(nouns));
	}

	@Test
	void testOutcast2() {
		String[] nouns = { "stinker", "corn_gluten", "transferase", "proaccelerin", "enzyme" };
		assertEquals("stinker", outcast.outcast(nouns));
	}

}
