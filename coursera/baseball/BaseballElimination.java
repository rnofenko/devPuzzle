import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class BaseballElimination {

    private List<Team> teams;

    public BaseballElimination(String filename) {
        In in = new In(filename);
        int count = in.readInt();
        ArrayList<Team> parsedTeams = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Team team = new Team();
            team.name = in.readString();
            team.wins = in.readInt();
            team.losses = in.readInt();
            team.remainingGamesCount = in.readInt();

            team.remainingGames = new int[count];

            for (int j = 0; j < count; j++) {
                team.remainingGames[j] = in.readInt();
            }

            parsedTeams.add(team);
        }

        this.teams = parsedTeams;
    }

    public int numberOfTeams() { return teams.size(); }

    public Iterable<String> teams() {
        ArrayList<String> names = new ArrayList<>();
        for (Team team : teams) {
            names.add(team.name);
        }
        return names;
    }

    public int wins(String team) { return 0; }
    public int losses(String team) { return 0; }
    public int remaining(String team) { return 0; }
    public int against(String team1, String team2) { return 0; }
    public boolean isEliminated(String team) {
        FlowNetwork network = new FlowNetwork();
        FordFulkerson fordFulkerson = new FordFulkerson(network);
        fordFulkerson.
        return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    private static class Team {
        String name;
        int wins;
        int losses;
        public int remainingGamesCount;
        public int[] remainingGames;
    }
}