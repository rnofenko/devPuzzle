public class Outcast {
    private final WordNet wordNet;

    public Outcast(WordNet wordNet) {
        if (wordNet == null) {
            throw new IllegalArgumentException();
        }
        this.wordNet = wordNet;
    }

    public String outcast(String[] nouns) {
        if (nouns == null || nouns.length == 0) {
            throw new IllegalArgumentException();
        }

        int maxSum = Integer.MIN_VALUE;
        String out = nouns[0];

        for (String noun : nouns) {
            if (!wordNet.isNoun(noun)) {
                return noun;
            }

            int sum = 0;
            for (String s : nouns) {
                if (s.equals(noun)) continue;
                sum += wordNet.distance(noun, s);
            }

            if (sum > maxSum) {
                maxSum = sum;
                out = noun;
            }
        }

        return out;
    }
}
