public class BaseballElimination {
    public BaseballElimination(String filename) {
    }

    public int numberOfTeams() { return 0; }
    public Iterable<String> teams() { return null; }
    public int wins(String team) { return 0; }
    public int losses(String team) { return 0; }
    public int remaining(String team) { return 0; }
    public int against(String team1, String team2) { return 0; }
    public boolean isEliminated(String team) { return false; }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }
}