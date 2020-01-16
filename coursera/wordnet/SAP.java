import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.List;

public class SAP {

    private static final int EMPTY = 0;
    private static final int NEGATIVE = -1;
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
        BfsState state = new BfsState();
        Flow vFlow = createFlow(state, v, null);
        Flow wFlow = createFlow(state, w, vFlow.steps);

        if (state.ancestor != NEGATIVE) {
            return state;
        }

        int roundNo = 0;
        while ((!vFlow.queue.isEmpty() || !wFlow.queue.isEmpty()) && roundNo < state.length) {
            doIterationToFindAncestor(vFlow, state, wFlow.steps);
            doIterationToFindAncestor(wFlow, state, vFlow.steps);
            roundNo++;
        }

        return state;
    }

    private void doIterationToFindAncestor(Flow flow, BfsState state, int[] others) {
        Queue<Integer> nextVertexes = new Queue<>();
        int[] steps = flow.steps;

        for (int vertex : flow.queue) {
            for (int parent : digraph.adj(vertex)) {
                if (steps[parent] != EMPTY) {
                    continue;
                }
                steps[parent] = steps[vertex] + 1;
                nextVertexes.enqueue(parent);

                if (others[parent] != EMPTY) {
                    int length = steps[parent] + others[parent] - 2;
                    if (length < state.length) {
                        state.length = length;
                        state.ancestor = parent;
                    }
                }
            }
        }

        flow.queue = nextVertexes;
    }

    private Flow createFlow(BfsState state, Iterable<Integer> v, int[] others) {
        if (v == null) {
            throw new IllegalArgumentException();
        }

        int size = digraph.V();
        Flow f = new Flow(size);

        for (Integer vertex : v) {
            if (vertex == null || vertex < 0 || vertex >= size) {
                throw new IllegalArgumentException();
            }
            f.queue.enqueue(vertex);
            f.steps[vertex] = 1;
            if (others != null && others[vertex] != EMPTY) {
                state.ancestor = vertex;
                state.length = 0;
            }
        }

        return f;
    }

    private List<Integer> toList(int v) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(v);
        return list;
    }

    private static class BfsState {
        public int ancestor = NEGATIVE;
        public int length = Integer.MAX_VALUE;

        public int getFinalLength() {
            return ancestor == NEGATIVE ? NEGATIVE : length;
        }

        @Override
        public String toString() {
            return String.format("ancestor=%s, length=%s", ancestor, length);
        }
    }

    private static class Flow {
        public final int[] steps;
        public Queue<Integer> queue = new Queue<>();

        public Flow(int size) {
            steps = new int[size];
        }
    }
}
