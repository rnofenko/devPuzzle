/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class WordNet {

    private final List<String> synsets;
    private final Digraph hypernyms;

    public WordNet(String synsetsFilename, String hypernymsFile) {
        if (synsetsFilename == null || hypernymsFile == null) {
            throw new IllegalArgumentException();
        }

        this.synsets = parseSynsetFile(synsetsFilename);
        this.hypernyms = parseHypernymFile(hypernymsFile, synsets.size());
    }

    private List<String> parseSynsetFile(String filename) {
        ArrayList<String> parsed = new ArrayList<>();
        In file = new In(filename);
        while (!file.isEmpty()) {
            String[] parts = file.readLine().split(",");
            parsed.add(parts[1]);
        }
        return parsed;
    }

    private Digraph parseHypernymFile(String filename, int size) {
        Digraph digraph = new Digraph(size);
        In file = new In(filename);
        while (!file.isEmpty()) {
            String[] parts = file.readLine().split(",");
            if (parts.length < 2) continue;

            int v = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            digraph.addEdge(v, w);
        }
        return digraph;
    }

    public Iterable<String> nouns() {
        return new ArrayList<>(synsets);
    }

    public boolean isNoun(String word) {
        return synsets.contains(word);
    }

    public int distance(String nounA, String nounB) {
        List<Integer> i1 = getIndexesForNoun(nounA);
        List<Integer> i2 = getIndexesForNoun(nounB);

        SAP sap = new SAP(hypernyms);
        return sap.length(i1, i2);
    }

    public String sap(String nounA, String nounB) {
        List<Integer> i1 = getIndexesForNoun(nounA);
        List<Integer> i2 = getIndexesForNoun(nounB);

        SAP sap = new SAP(hypernyms);
        int resIndex = sap.ancestor(i1, i2);
        if (resIndex < 0) {
            return null;
        }
        return synsets.get(resIndex);
    }

    private List<Integer> getIndexesForNoun(String noun) {
        ArrayList<Integer> indexes = new ArrayList<>();
        if (noun == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < synsets.size(); i++) {
            if (noun.equals(synsets.get(i))) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
