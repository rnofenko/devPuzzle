package rn.puzzle.dynamic.hard.cherry;

import java.util.List;
import java.util.stream.Collectors;

public class GraphPrinter {
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
