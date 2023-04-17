package rn.coursera.algtool.w3;

import java.util.*;

public class DifferentPrizes {
    private static List<Integer> optimalPrizes(int n) {
        List<Integer> prizes = new ArrayList<>();
        int prize = 0;
        while (n > 0) {
            prize++;
            if (prize <= n) {
                prizes.add(prize);
            } else {
                prizes.set(prizes.size() - 1, prizes.get(prizes.size() - 1) + n);
            }
            n = n - prize;
        }
        return prizes;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> prizes = optimalPrizes(n);
        System.out.println(prizes.size());
        for (Integer prize : prizes) {
            System.out.print(prize + " ");
        }
    }
}

