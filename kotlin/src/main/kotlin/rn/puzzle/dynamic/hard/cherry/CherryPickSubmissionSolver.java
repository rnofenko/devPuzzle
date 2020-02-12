package rn.puzzle.dynamic.hard.cherry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CherryPickSubmissionSolver {

    private static final int SOURCE = 0;
    private static final int SINK = 1;
    private static final int WALL = -1;
    private static final int CHERRY = 1;
    private static final int EMPTY = 0;

    private List<CherryNode> graph;

    public int cherryPickup(int[][] grid) {
        if (grid.length == 1) {
            return grid[0][0];
        }

        GraphBuilder builder = new GraphBuilder(grid);
        graph = builder.build();

        removeDoubleLinks();

        int rootPoints = depthFirst();
        if (grid[0][0] == EMPTY) {
            rootPoints--;
        }
        if (grid[grid.length - 1][grid[0].length - 1] == EMPTY) {
            rootPoints--;
        }

        return Math.max(0, rootPoints);
    }

    private int depthFirst() {
        int[] visited = new int[graph.size()];
        CherryNode root = graph.get(0);
        return depthFirst(root, visited, 1, 0);
    }

    private int depthFirst(CherryNode node, int[] visited, int level, int maxPoints) {
        int id = node.id;
        visited[id] = 1;

        if (node.id == SINK) {
            CherryNode root = graph.get(0);
            int locPoints = root.calcPoints(visited);
            maxPoints = Math.max(locPoints + level, maxPoints);
        }

        for (CherryNode kid : node.kids) {
            int locPoints = depthFirst(kid, visited, level + 1, maxPoints);
            maxPoints = Math.max(locPoints, maxPoints);
        }

        visited[id] = 0;

        return maxPoints;
    }

    private void removeDoubleLinks() {
        removeDoubleLinks(graph.get(SOURCE));
    }

    private void removeDoubleLinks(CherryNode parent) {
        Set<Integer> kidsSet = new HashSet<>();
        for (CherryNode kid : parent.kids) {
            kidsSet.add(kid.id);
        }

        Set<Integer> forRemove = new HashSet<>();
        for (CherryNode kid : parent.kids) {
            for (CherryNode grandKid : kid.kids) {
                if (kidsSet.contains(grandKid.id)) {
                    forRemove.add(grandKid.id);
                }
            }
        }

        parent.removeKids(forRemove);

        for (CherryNode kid : parent.kids) {
            removeDoubleLinks(kid);
        }
    }

    private static class CherryNode {
        public final int id;
        public List<CherryNode> kids = new ArrayList<>();

        public CherryNode(int vertex, int x, int y) {
            this.id = vertex;
        }

        public void addKid(CherryNode kid) {
            kids.add(kid);
        }

        @Override
        public String toString() {
            String kidsStr = kids.stream().map(k -> String.valueOf(k.id)).collect(Collectors.joining(","));
            return String.format("#%s kids=%s", id, kidsStr);
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

            return max;
        }
    }

    private static class GraphBuilder {

        private final int width;
        private final int height;
        private final int[][] grid;
        private final int fullLen;
        private final int[] visited;
        private int[] coordinateToVertex;
        private List<CherryNode> graph = new ArrayList<>();

        public GraphBuilder(int[][] grid) {
            this.grid = grid;
            height = grid.length;
            width = grid[0].length;
            fullLen = width * height;
            visited = new int[fullLen];
            coordinateToVertex = new int[fullLen];
        }

        public List<CherryNode> build() {
            CherryNode source = new CherryNode(0, 0, 0);
            graph.add(source);

            CherryNode sink = new CherryNode(1, width - 1, height - 1);
            coordinateToVertex[fullLen - 1] = sink.id;
            graph.add(sink);

            build(source, 1);
            build(source, width);

            return graph;
        }

        private void build(CherryNode source, int nextIdx) {
            int y = nextIdx / width;
            int x = nextIdx % width;

            if (grid[y][x] == WALL || (visited[nextIdx] > 0 && visited[nextIdx] == source.id + 1)) {
                return;
            }
            visited[nextIdx] = source.id + 1;

            if (grid[y][x] == CHERRY || nextIdx == fullLen - 1) {
                int targetVertex = coordinateToVertex[nextIdx];
                if (targetVertex == EMPTY) {
                    targetVertex = graph.size();
                    coordinateToVertex[nextIdx] = targetVertex;

                    CherryNode newNode = new CherryNode(targetVertex, x, y);

                    source.addKid(newNode);
                    graph.add(newNode);

                    source = newNode;
                } else {
                    source.addKid(graph.get(targetVertex));
                    return;
                }
            }

            if (x + 1 < width) {
                build(source, nextIdx + 1);
            }
            if (y + 1 < height) {
                build(source, nextIdx + width);
            }
        }
    }
}
