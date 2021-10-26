package rn.puzzle.graph.medium;

import org.junit.Test;
import rn.tool.Ass;

import java.util.*;

//https://leetcode.com/problems/reconstruct-itinerary/
public class ReconstructItineraryTest {
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, List<String>> ticketsMap = new HashMap<>();
        for (List<String> ticket: tickets) {
            ticketsMap.compute(ticket.get(0), (key, list) -> {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(ticket.get(1));
                return list;
            });
        }

        ticketsMap.values().forEach(list -> list.sort(String::compareTo));
        ArrayDeque<String> solution = new ArrayDeque<>();

        dfs("JFK", ticketsMap, solution);

        return new ArrayList<>(solution);
    }

    private void dfs(String root, HashMap<String, List<String>> tickets, ArrayDeque<String> solution) {
        List<String> destinations = tickets.get(root);
        while (destinations != null && !destinations.isEmpty()) {
            String destination = destinations.get(0);
            destinations.remove(0);
            dfs(destination, tickets, solution);
        }

        solution.push(root);
    }

    @Test
    public void test1() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK","SFO"),
                Arrays.asList("JFK","ATL"),
                Arrays.asList("SFO","ATL"),
                Arrays.asList("ATL","JFK"),
                Arrays.asList("ATL","SFO")
        );
        List<String> path = findItinerary(tickets);
        Ass.assertListEquals(Arrays.asList("JFK","ATL","JFK","SFO","ATL","SFO"), path);
    }
}
