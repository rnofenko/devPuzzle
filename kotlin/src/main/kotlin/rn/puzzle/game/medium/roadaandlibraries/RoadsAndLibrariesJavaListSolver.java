package rn.puzzle.game.medium.roadaandlibraries;

import java.util.ArrayList;

public class RoadsAndLibrariesJavaListSolver {
    public long roadsAndLibraries(int n, int libCost, int roadCost, int[][] graph) {
        long total = (long)n * libCost;
        long diff = (long)libCost - roadCost;
        if(roadCost >= libCost) {
            return total;
        }
        ArrayList<ArrayList<Integer>> trees = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            trees.add(null);
        }
        for (int[] p : graph) {
            int i0 = p[0];
            int i1 = p[1];
            ArrayList<Integer> t0 = trees.get(i0);
            ArrayList<Integer> t1 = trees.get(i1);
            if(t0 == null && t1 == null) {
                ArrayList<Integer> newTree = new ArrayList<>();
                newTree.add(i0);
                newTree.add(i1);
                trees.set(i0, newTree);
                trees.set(i1, newTree);
                total -= diff;
            } else if(t0 != null && t1 != null) {
                if(t0.equals(t1)) {
                    continue;
                }
                ArrayList<Integer> small = t0.size() > t1.size() ? t1 : t0;
                ArrayList<Integer> big = small.equals(t0) ? t1 : t0;
                for (Integer smallNode : small) {
                    trees.set(smallNode, big);
                }
                big.addAll(small);
                total -= diff;
            } else {
                if(t0 != null) {
                    t0.add(i1);
                    trees.set(i1, t0);
                } else {
                    t1.add(i0);
                    trees.set(i0, t1);
                }
                total -= diff;
            }
        }

        return total;
    }
}
