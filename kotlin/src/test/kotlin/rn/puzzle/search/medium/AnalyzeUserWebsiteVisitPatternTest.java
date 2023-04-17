package rn.puzzle.search.medium;

import org.junit.Test;
import rn.tool.Ass;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeUserWebsiteVisitPatternTest {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, UserData> users = new HashMap<>();
        Set<String> uniqueSites = new HashSet<>();

        for (int i = 0; i < username.length; i++) {
            UserData user = users.computeIfAbsent(username[i], (k) -> new UserData());
            user.add(timestamp[i], website[i]);
            uniqueSites.add(website[i]);
        }
        for (UserData user : users.values()) {
            for (List<Integer> times : user.sites.values()) {
                times.sort(Integer::compareTo);
            }
        }

        List<Step> steps = users.values().stream().map(u -> new Step(-1, u)).collect(Collectors.toList());
        List<String> sites = new ArrayList<>(uniqueSites);
        sites.sort(String::compareTo);
        MaxPattern res = find(sites, steps, new ArrayList<>());

        return res.pattern;
    }

    private MaxPattern find(List<String> uniqueSites, List<Step> steps, List<String> pattern) {
        MaxPattern max = null;
        for (String site : uniqueSites) {
            MaxPattern res = goNextLevel(site, uniqueSites, steps, pattern);
            if (res != null) {
                max = max == null || max.count < res.count ? res : max;
            }
        }
        return max;
    }

    private MaxPattern goNextLevel(String site, List<String> uniqueSites, List<Step> steps, List<String> pattern) {
        List<String> newPattern = new ArrayList<>(pattern);
        newPattern.add(site);

        List<Step> nextSteps = new ArrayList<>();
        for (Step step : steps) {
            Integer time = step.user.getTime(site, step.time);
            if (time != null) {
                nextSteps.add(new Step(time, step.user));
            }
        }

        if (nextSteps.isEmpty()) {
            return null;
        }
        if (newPattern.size() == 3) {
            return new MaxPattern(nextSteps.size(), newPattern);
        }
        return find(uniqueSites, nextSteps, newPattern);
    }

    private static class MaxPattern {
        int count;
        List<String> pattern;

        MaxPattern(int count, List<String> pattern) {
            this.count = count;
            this.pattern = pattern;
        }
    }

    private static class Step {
        int time;
        UserData user;

        Step(int time, UserData user) {
            this.time = time;
            this.user = user;
        }
    }

    private static class UserData {
        private final Map<String, List<Integer>> sites = new HashMap<>();

        public void add(int time, String site) {
            List<Integer> times = sites.computeIfAbsent(site, k -> new ArrayList<>());
            times.add(time);
        }

        public Integer getTime(String site, int minTime) {
            List<Integer> times = sites.get(site);
            if (times == null) {
                return null;
            }
            for (int time : times) {
                if (time > minTime) {
                    return time;
                }
            }
            return null;
        }
    }

    @Test
    public void test1() {
        List<String> res = mostVisitedPattern(new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                new String[]{"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"});
        Ass.assertListEquals(Arrays.asList("home","about","career"), res);
    }

    @Test
    public void test2() {
        List<String> res = mostVisitedPattern(new String[]{"a", "a", "a"},
                new int[]{158931262,562600350,148438945},
                new String[]{"y","r","y"});
        Ass.assertListEquals(Arrays.asList("y","y","r"), res);
    }
}
