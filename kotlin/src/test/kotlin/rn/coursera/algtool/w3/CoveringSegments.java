package rn.coursera.algtool.w3;

import java.util.*;
import java.util.stream.Collectors;

public class CoveringSegments {

    public static int[] optimalPoints(Segment[] segments) {
        List<Segment> sorted = Arrays.stream(segments)
                .sorted(Comparator.comparingInt(o -> o.end))
                .collect(Collectors.toList());

        List<Integer> points = new ArrayList<>();
        for (Segment segment : sorted) {
            if (!points.isEmpty()) {
                int last = points.get(points.size() - 1);
                if (segment.contains(last)) {
                    continue;
                }
            }
            points.add(segment.end);
        }

        return points.stream().mapToInt(i -> i).toArray();
    }

    public static int[] runTest(String[] inputs) {
        Segment[] segments = new Segment[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            String[] parts = inputs[i].split("\\s+");
            segments[i] = new Segment(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }

        return optimalPoints(segments);
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        boolean contains(int point) {
            return start <= point && point <= end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
