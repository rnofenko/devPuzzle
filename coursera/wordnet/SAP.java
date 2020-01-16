import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.List;

public class SAP {

    private static final int NEGATIVE = -1;
    private final Digraph digraph;

    public SAP(Digraph G) {
        digraph = new Digraph(G);
        edu.princeton.cs.algs4.Graph g = new Graph(G);
    }

    public int length(int v, int w) {
        return length(toList(v), toList(w));
    }

    public int ancestor(int v, int w) {
        return ancestor(toList(v), toList(w));
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int a = ancestor(v, w);
        if (a == NEGATIVE) {
            return NEGATIVE;
        }

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
        return bfsV.distTo(a) + bfsW.distTo(a);
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        final int EMPTY = 0;
        final int markV = 1;
        final int markW = 2;

        int[] visited = new int[digraph.V()];

        Queue<Integer> queue = new Queue<>();
        for (Integer vertex : v) {
            if (vertex == null) {
                throw new IllegalArgumentException();
            }
            if (visited[vertex] == EMPTY) {
                queue.enqueue(vertex);
                visited[vertex] = markV;
            }
        }
        for (Integer vertex : w) {
            if (vertex == null) {
                throw new IllegalArgumentException();
            }
            if (visited[vertex] == EMPTY) {
                queue.enqueue(vertex);
                visited[vertex] = markW;
            } else if (visited[vertex] != markW) {
                return vertex;
            }
        }

        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            int mark = visited[vertex];
            for (int parent : digraph.adj(vertex)) {
                if (visited[parent] == EMPTY) {
                    visited[parent] = mark;
                    queue.enqueue(parent);
                } else if (visited[parent] != mark) {
                    return parent;
                }
            }
        }

        return NEGATIVE;
    }

    private List<Integer> toList(int v) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(v);
        return list;
    }
}
