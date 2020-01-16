import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.List;

public class SAP {

    private static final int EMPTY = 0;
    private static final int NEGATIVE = -1;
    private static final byte V = 1;
    private static final byte W = 2;
    private final Digraph digraph;

    public SAP(Digraph G) {
        digraph = new Digraph(G);
    }

    public int length(int v, int w) {
        return length(toList(v), toList(w));
    }

    public int ancestor(int v, int w) {
        return ancestor(toList(v), toList(w));
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return findAncestor(v, w).getFinalLength();
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return findAncestor(v, w).ancestor;
    }

    private BfsState findAncestor(Iterable<Integer> v, Iterable<Integer> w) {
        BfsState state = initializeBfsState(v, w);
        if (state.ancestor != NEGATIVE) {
            return state;
        }

        int roundNo = 0;
        while (!state.queue.isEmpty() && roundNo < state.bestLen) {
            doIterationToFindAncestor(state);
            roundNo++;
        }

        return state;
    }

    private void doIterationToFindAncestor(BfsState state) {
        Queue<Integer> nextVertexes = new Queue<>();
        byte[] who = state.who;
        int[] steps = state.steps;

        for (int vertex : state.queue) {
            byte owner = who[vertex];
            for (int parent : digraph.adj(vertex)) {
                if (who[parent] == EMPTY) {
                    who[parent] = owner;
                    steps[parent] = steps[vertex] + 1;
                    nextVertexes.enqueue(parent);
                } else if (who[parent] != owner) {
                    int length = steps[parent] + steps[vertex] + 1;
                    if (length < state.bestLen) {
                        state.bestLen = length;
                        state.ancestor = parent;
                    }
                }
            }
        }

        state.queue = nextVertexes;
    }

    private BfsState initializeBfsState(Iterable<Integer> v, Iterable<Integer> w) {
        BfsState state = new BfsState(digraph.V());
        copyElements(state, v, V);
        copyElements(state, w, W);
        if (state.ancestor != NEGATIVE) {
            return state;
        }

        return state;
    }

    private void copyElements(BfsState state, Iterable<Integer> v, byte owner) {
        byte[] who = state.who;
        Queue<Integer> queue = state.queue;

        for (Integer vertex : v) {
            if (vertex == null) {
                throw new IllegalArgumentException();
            }
            if (who[vertex] == EMPTY) {
                who[vertex] = owner;
                queue.enqueue(vertex);
            } else if (who[vertex] != owner) {
                state.ancestor = vertex;
                state.bestLen = 0;
                return;
            }
        }
    }

    private List<Integer> toList(int v) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(v);
        return list;
    }

    private static class BfsState {
        public final int[] steps;
        public final byte[] who;
        public int ancestor = NEGATIVE;
        public Queue<Integer> queue = new Queue<>();
        public int bestLen = Integer.MAX_VALUE;

        public BfsState(int size) {
            steps = new int[size];
            who = new byte[size];
        }

        public int getFinalLength() {
            return ancestor == NEGATIVE ? NEGATIVE : bestLen;
        }
    }
}
