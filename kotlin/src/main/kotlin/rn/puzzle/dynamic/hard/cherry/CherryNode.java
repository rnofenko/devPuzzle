package rn.puzzle.dynamic.hard.cherry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CherryNode {

    private static final int SINK = 1;

    public final int id;
    private final int y;
    private final int x;
    public List<CherryNode> kids = new ArrayList<>();
    public Integer points;

    public CherryNode(int vertex, int x, int y) {
        this.id = vertex;
        this.y = y;
        this.x = x;
    }

    public void addKid(CherryNode kid) {
        kids.add(kid);
    }

    @Override
    public String toString() {
        String kidsStr = kids.stream().map(k -> String.valueOf(k.id)).collect(Collectors.joining(","));
        return String.format("#%s %sx%s kids=%s points=%s", id, x, y, kidsStr, points);
    }

    public void removeKids(Set<Integer> forRemove) {
        if (forRemove.isEmpty()) {
            return;
        }

        List<CherryNode> newKids = new ArrayList<>();
        for (CherryNode kid : kids) {
            if (!forRemove.contains(kid.id)) {
                newKids.add(kid);
            }
        }
        kids = newKids;
    }

    public int calcPoints(int[] visited) {
        Integer max = calcPointsNullable(visited);
        return max == null ? 0 : max;
    }

    private Integer calcPointsNullable(int[] visited) {
        Integer max = null;
        for (CherryNode kid : kids) {
            Integer kidPoints = kid.calcPointsNullable(visited);
            if (kidPoints == null) continue;
            if (max == null) {
                max = kidPoints;
            } else {
                max = Math.max(max, kidPoints);
            }
        }

        if (max == null) {
            if (visited[id] != 0) {
                max = 0;
            }
        } else {
            if (visited[id] == 0) {
                max++;
            }
        }

        points = max;
        return max;
    }
}
