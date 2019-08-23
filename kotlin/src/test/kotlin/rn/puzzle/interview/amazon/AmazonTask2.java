package rn.puzzle.interview.amazon;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AmazonTask2 {
    public List<List<Integer>> optimalUtilization(int deviceCapacity, List<List<Integer>> foregroundAppList,
                                                  List<List<Integer>> backgroundAppList) {
        //sort all applications by memory
        foregroundAppList.sort(Comparator.comparing(o -> o.get(1)));
        backgroundAppList.sort(Comparator.comparing(o -> o.get(1)));

        int maxSize = 0;
        ArrayList<List<Integer>> resultList = new ArrayList<>();

        int foreIdx = 0;
        int backIdx = backgroundAppList.size() - 1;
        while (foreIdx < foregroundAppList.size() && backIdx >= 0) {
            List<Integer> foreApp = foregroundAppList.get(foreIdx);
            List<Integer> backApp = backgroundAppList.get(backIdx);
            int memorySize = foreApp.get(1) + backApp.get(1);
            if(memorySize > deviceCapacity) {
                backIdx--;
            } else {
                if(memorySize >= maxSize) {
                    if(memorySize > maxSize) {
                        maxSize = memorySize;
                        resultList = new ArrayList<>();
                    }

                    //in case when foreground app has the same memory size
                    ArrayList<List<Integer>> localForeground = new ArrayList<>();
                    int foregroundAppSize = foreApp.get(1);
                    while (foreIdx < foregroundAppList.size() && foregroundAppList.get(foreIdx).get(1) == foregroundAppSize) {
                        localForeground.add(foregroundAppList.get(foreIdx));
                        foreIdx++;
                    }
                    //in case when background app has the same memory size
                    ArrayList<List<Integer>> localBackground = new ArrayList<>();
                    int innerBackIdx = backIdx;
                    int backgroundAppSize = backApp.get(1);
                    while (innerBackIdx >= 0 && backgroundAppList.get(innerBackIdx).get(1) == backgroundAppSize) {
                        localBackground.add(backgroundAppList.get(innerBackIdx));
                        innerBackIdx--;
                    }

                    //join result. It needs when we have repeated memory sizes.
                    for (List<Integer> localForeApp : localForeground) {
                        for (List<Integer> localBackApp : localBackground) {
                            List<Integer> pair = Arrays.asList(localForeApp.get(0), localBackApp.get(0));
                            resultList.add(pair);
                        }
                    }
                } else {
                    foreIdx++;
                }
            }
        }

        return resultList;
    }

    @Test
    public void test1() {
        ArrayList<List<Integer>> foreground = new ArrayList<>();
        foreground.add(Arrays.asList(1,3));
        foreground.add(Arrays.asList(2,5));
        foreground.add(Arrays.asList(3,7));
        foreground.add(Arrays.asList(4,10));
        ArrayList<List<Integer>> background = new ArrayList<>();
        background.add(Arrays.asList(1,2));
        background.add(Arrays.asList(2,3));
        background.add(Arrays.asList(3,4));
        background.add(Arrays.asList(4,5));
        List<List<Integer>> res = optimalUtilization(10, foreground, background);
        Assert.assertEquals(2, res.size());
    }
}
