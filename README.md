# WordNet
WordNet is a semantic lexicon for the English language which describes relationships between synsets (sets of synonyms).

One such relationship is the *is-a* relationship, which connects a *hyponym* (more specific synset) to a *hypernym* (more general synset).

The implementation is via a directed graph where each vertex *v* is an integer that represents a synset, and each directed edge *v→w* represents that *w* is a hypernym of *v*.
