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
    private final Map<String, List<Integer>> nounsMap;
    private final SAP sap;

    public WordNet(String synsetsFilename, String hypernymsFile) {
        if (synsetsFilename == null || hypernymsFile == null) {
            throw new IllegalArgumentException();
        }

        this.synsetsList = parseSynsetFile(synsetsFilename);
        this.nounsMap = convertSynsetListToMapOfNouns(synsetsList);
        this.sap = parseHypernymFileToSap(hypernymsFile, synsetsList.size());
    }

    public Iterable<String> nouns() {
        return nounsMap.keySet();
    }

    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return nounsMap.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        List<Integer> i1 = getIndexesForNoun(nounA);
        List<Integer> i2 = getIndexesForNoun(nounB);

        return sap.length(i1, i2);
    }

    public String sap(String nounA, String nounB) {
        List<Integer> i1 = getIndexesForNoun(nounA);
        List<Integer> i2 = getIndexesForNoun(nounB);

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
        if (list == null) {
            throw new IllegalArgumentException();
        }
        return list;
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

    private SAP parseHypernymFileToSap(String filename, int size) {
        Digraph digraph = new Digraph(size);
        In file = new In(filename);
        while (!file.isEmpty()) {
            String[] parts = file.readLine().split(",");
            int v = Integer.parseInt(parts[0]);

            for (int i = 1; i < parts.length; i++) {
                int w = Integer.parseInt(parts[i]);
                digraph.addEdge(v, w);
            }
        }

        int rootsCount = countRootsCount(digraph);
        if (rootsCount != 1) {
            throw new IllegalArgumentException();
        }

        return new SAP(digraph);
    }

    private int countRootsCount(Digraph d) {
        int rootsCount = 0;
        for (int i = 0; i < d.V(); i++) {
            if (!d.adj(i).iterator().hasNext()) {
                rootsCount++;
            }
        }
        return rootsCount;
    }
}
