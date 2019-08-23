package rn.puzzle.interview.amazon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AmazonTask1 {
    public List<String> prioritizedOrders(int numOrders, List<String> orderList)
    {
        //split orders to Prime and Not Prime
        ArrayList<String> notPrime = new ArrayList<>();
        ArrayList<PrimeNumber> prime = new ArrayList<>();
        for (String orderNoStr : orderList) {
            PrimeNumber primeNo = PrimeNumber.create(orderNoStr);
            if(primeNo == null) {
                notPrime.add(orderNoStr);
            } else {
                prime.add(primeNo);
            }
        }

        prime.sort(new PrimeNumberComparator());


        //join prime and not prime together
        ArrayList<String> allOrders = new ArrayList<>();
        allOrders.addAll(prime.stream().map(PrimeNumber::toStringNo).collect(Collectors.toList()));
        allOrders.addAll(notPrime);

        return allOrders;
    }

    private static class PrimeNumberComparator implements Comparator<PrimeNumber> {
        @Override
        public int compare(PrimeNumber o1, PrimeNumber o2) {
            int compareResult = o1.name.compareTo(o2.name);//sort by name
            if(compareResult == 0) {//then sort by id
                return o1.id.compareTo(o2.id);
            }
            return compareResult;
        }
    }

    private static class PrimeNumber {
        private final String id;
        private final String name;

        public PrimeNumber(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public static PrimeNumber create(String orderNo) {
            int spaceIndex = orderNo.indexOf(' ');

            if(Character.isDigit(orderNo.charAt(spaceIndex + 1))) {
                return null;
            }

            return new PrimeNumber(orderNo.substring(0, spaceIndex), orderNo.substring(spaceIndex + 1));
        }

        public String toStringNo() {
            return id + " " + name;
        }
    }

    @Test
    public void test1() {
        prioritizedOrders(1, Arrays.asList("zip 93 12", "fp kindle book", "aa kindle book"));
    }
}
