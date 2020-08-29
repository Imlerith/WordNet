# WordNet
WordNet is a semantic lexicon for the English language which describes relationships between synsets (sets of synonyms).

One such relationship is the *is-a* relationship, which connects a *hyponym* (more specific synset) to a *hypernym* (more general synset).

The implementation is via a *rooted acyclic directed graph (DAG)* where each vertex *v* is an integer that represents a synset, and each directed edge *v→w* represents that *w* is a hypernym of *v*.

Realized functionality:
* building a WordNet from the sets of synsets and hypernyms,
* finding shortest ancestral paths between synsets and subsets of synsets (using breadth-first search in the process),
* finding outliers in given sets of words based on the constructed wordnet.
