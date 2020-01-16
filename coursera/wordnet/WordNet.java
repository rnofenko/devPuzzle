/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {

    private final List<String> synsetsList;
    private final Digraph hypernyms;
    private final Map<String, List<Integer>> nounsMap;

    public WordNet(String synsetsFilename, String hypernymsFile) {
        if (synsetsFilename == null || hypernymsFile == null) {
            throw new IllegalArgumentException();
        }

        this.synsetsList = parseSynsetFile(synsetsFilename);
        this.hypernyms = parseHypernymFile(hypernymsFile, synsetsList.size());
        this.nounsMap = convertSynsetListToMapOfNouns(synsetsList);
    }

    private Map<String, List<Integer>> convertSynsetListToMapOfNouns(List<String> synsets) {
        HashMap<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < synsets.size(); i++) {
            String[] nouns = synsets.get(i).split(" ");
            for (String noun : nouns) {
                List<Integer> indexes = map.computeIfAbsent(noun, k -> new ArrayList<>());
                indexes.add(i);
            }
        }

        return map;
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
        return nounsMap.keySet();
    }

    public boolean isNoun(String word) {
        return nounsMap.containsKey(word);
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
        return synsetsList.get(resIndex);
    }

    private List<Integer> getIndexesForNoun(String noun) {
        if (noun == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> list = nounsMap.get(noun);
        return list == null ? new ArrayList<>() : list;
    }
}
