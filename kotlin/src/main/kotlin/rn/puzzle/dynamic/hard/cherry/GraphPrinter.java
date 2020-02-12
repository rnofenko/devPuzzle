package rn.puzzle.dynamic.hard.cherry;

import java.util.List;
import java.util.stream.Collectors;

public class GraphPrinter {

    private static final int EMPTY = 0;

    public static void printGrid(int[] coordinateToVertex, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int v = coordinateToVertex[y * width + x];
                String str = String.format("%4s", v);
                if (v == EMPTY) {
                    str = String.format("%4s", "-");
                }
                System.out.print(str);
            }
            System.out.println("");
        }
    }

    public static void printConnections(List<CherryNode> graph) {
        System.out.println("\nVertices count = " + graph.size());

        for (int i = 0; i < graph.size(); i++) {
            String parent = String.format("%3s", i);
            List<CherryNode> kids = graph.get(i).kids;
            if (kids.isEmpty()) continue;
            String kidsStr = kids.stream().map(n -> String.valueOf(n.id)).collect(Collectors.joining(","));
            System.out.println(String.format("%s   %s", parent, kidsStr));
        }
    }
}
