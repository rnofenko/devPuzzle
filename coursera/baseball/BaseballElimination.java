import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class BaseballElimination {

    private final Map<String, Team> teams;

    public BaseballElimination(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException();
        }

        In in = new In(filename);
        int count = in.readInt();
        HashMap<String, Team> parsedTeams = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Team team = new Team();
            team.name = in.readString();
            team.wins = in.readInt();
            team.losses = in.readInt();
            team.remainingGamesCount = in.readInt();
            team.index = i;

            team.remainingGames = new int[count];

            for (int j = 0; j < count; j++) {
                team.remainingGames[j] = in.readInt();
            }

            parsedTeams.put(team.name, team);
        }

        this.teams = parsedTeams;
    }

    public int numberOfTeams() { return teams.size(); }

    public Iterable<String> teams() {
        return teams.keySet();
    }

    public int wins(String team) { return getTeam(team).wins; }
    public int losses(String team) { return getTeam(team).losses; }
    public int remaining(String team) { return getTeam(team).remainingGamesCount; }

    public int against(String team1, String team2) {
        return getTeam(team1).remainingGames[getTeam(team2).index];
    }

    public boolean isEliminated(String team) {
        return checkElimination(team) != null;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return checkElimination(team);
    }

    private Team getTeam(String name) {
        Team team = teams.get(name);
        if (team == null) {
            throw new IllegalArgumentException();
        }
        return team;
    }

    private Iterable<String> checkElimination(String name) {
        Team main = getTeam(name);
        int targetWins = main.getPossibleWins();

        ArrayList<String> certificates = new ArrayList<>();
        ArrayList<Team> competitors = new ArrayList<>();
        for (Team team : teams.values()) {
            if (main.index == team.index) {
                continue;
            }
            if (targetWins < team.wins) {
                certificates.add(team.name);
                return certificates;
            }
            if (team.remainingGamesCount - main.remainingGames[team.index] <= 0) {
                continue;
            }
            competitors.add(team);
        }

        if (competitors.isEmpty()) {
            return null;
        }

        int source = competitors.size();
        int sink = source + 1;

        FlowNetwork network = createNetwork(competitors, source, sink, targetWins);

        FordFulkerson fordFulkerson = new FordFulkerson(network, source, sink);
        for (int i = 0; i < competitors.size(); i++) {
            if (fordFulkerson.inCut(i)) {
                certificates.add(competitors.get(i).name);
            }
        }

        if (certificates.isEmpty()) {
            return null;
        }

        return certificates;
    }

    private FlowNetwork createNetwork(List<Team> competitors, int source, int sink, int targetWins) {
        int currentVertexId = sink + 1;

        ArrayList<FlowEdge> edges = new ArrayList<>();
        for (int i = 0; i < competitors.size(); i++) {
            for (int j = i + 1; j < competitors.size(); j++) {
                Team t1 = competitors.get(i);
                Team t2 = competitors.get(j);
                int remainingGames = t1.remainingGames[t2.index];
                if (remainingGames == 0) {
                    continue;
                }

                int gameVertexId = currentVertexId++;
                edges.add(new FlowEdge(source, gameVertexId, remainingGames));

                edges.add(new FlowEdge(gameVertexId, i, Double.POSITIVE_INFINITY));
                edges.add(new FlowEdge(gameVertexId, j, Double.POSITIVE_INFINITY));
            }
        }

        for (int i = 0; i < competitors.size(); i++) {
            edges.add(new FlowEdge(i, sink, targetWins - competitors.get(i).wins));
        }

        FlowNetwork network = new FlowNetwork(currentVertexId);
        for (FlowEdge edge : edges) {
            network.addEdge(edge);
        }

        return network;
    }

    private static class Team {
        String name;
        int wins;
        int losses;
        int remainingGamesCount;
        int[] remainingGames;
        int index;

        public int getPossibleWins() {
            return wins + remainingGamesCount;
        }

        @Override
        public String toString() {
            return name + ", wins=" + wins + ", remaining=" + remainingGamesCount;
        }
    }
}