package rn.puzzle.structure.heap.hard;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumAverageWaitingTimeTest {
    static long minimumAverage(int[][] customers) {
        final int START = 0;
        final int DURATION = 1;
        int inputIdx = 0;
        long sum = 0;
        long time = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[DURATION]));
        Arrays.sort(customers, Comparator.comparingInt(o -> o[0]));
        while (true) {
            while (inputIdx < customers.length && (heap.isEmpty() || customers[inputIdx][START] <= time)) {
                heap.add(customers[inputIdx]);
                inputIdx++;
            }
            if (heap.isEmpty()) {
                break;
            }
            int[] customer = heap.poll();
            long customerStart = customer[START];
            long customerDuration = customer[DURATION];
            if (customerStart > time) {
                time = customerStart;
            }
            long wait = time - customerStart;
            wait += customerDuration;
            sum += wait;

            time += customerDuration;
        }
        double avg = sum * 1.0 / customers.length;
        return (long)avg;
    }

    @Test
    public void input02() {
        test("934504950 861289137,682111322 496887795,603236774 713893904,871832073 264986656,9361848 539221796,923314579 687524372,"+
                "516631496 436533893,762108851 407784436,409617807 615448498,222634400 208442731,431098638 148834388,858636239 742132465,"+
                "65595465 104576384,27592708 611298565,68765513 26367219,201384166 114673012,7863143 797762645,543771279 29132945,"+
                "29130952 854671993,585445692 593951356,692134770 533881497,891653076 49240670,174260196 913929115,66288118 194879400,"+
                "487706 119659344,194435589 429754201,4080371 967838129,793446473 9878839,701794212 68187682,628896716 302869266,"+
                "333961924 324019889,757782965 264845318,753067776 450723670,986021138 205616567,180047190 52602162,175679615 308585876,"+
                "22888493 142102482,704380635 733794326,271062208 578646122,773379847 193003008,678995092 976381723,336906181 900464030,"+
                "239697689 835175471,676537797 206323720,946885385 254902618,254065061 694451805,786166049 329338105,70498307 874368266,"+
                "775661614 254369620,948249666 853779203", 6667863382L);
    }

    @Test
    public void input01() {
        test("137857688 413115088,679783990 171274023,783725811 742261681,238387441 531682046,683427743 559301752,843391076 398722857," +
                "593760457 2628387,441381803 788912528,771854880 916901718,976015955 978145894,235492265 264125858,866638949 551120745," +
                "238176883 201620672,254029772 950305054,356294983 203393748,291672611 722032663,560013448 126478507,929678215 321924654,"+
                "737812220 884493567,388266395 252551113,79292652 229453232,367753702 242882326,930211560 461283594,955372388 594944846,"+
                "506995906 872449795,538015463 457419763,950540066 820099707,823860276 896193555,538832788 47584891,88242680 700680580,"+
                "196842555 623621497,700528228 610051112,668066226 170226832,522278872 914689320,375621149 336628938,418416931 270027322,"+
                "179882058 480538711,540671906 215602397,201411561 930064341,36616963 35887002,772894889 944088968,891134170 633761602,"+
                "975099001 434725536,926070958 326905396,727328509 867529847,340789259 890185621,923620442 986091986,747688776 107231383,"+
                "38070714 495529501,610348800 235616181", 8485548331L);
    }

    @Test
    public void test3() {
        test("961148050 385599125,951133776 376367013,283280121 782916802,317664929 898415172,980913391 847912645", 1418670047);
    }

    @Test
    public void test1() {
        test("0 3,1 9,2 5", 8);
    }

    @Test
    public void test2() {
        test("0 3,1 9,2 6", 9);
    }

    public void test(String strArray, long expected) {
        int[][] arr = StrConverter.to2dIntArray(strArray);
        long res = minimumAverage(arr);
        Assert.assertEquals(expected, res);
    }
}
