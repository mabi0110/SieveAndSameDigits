import java.util.*;

public class SieveAndSameDigitsV1 {

    public static void main(String[] args) {
        int primeLimit = 1000;
        Map<Integer, List<Integer>> primes = sieve(primeLimit);


        Set<Map.Entry<Integer, List<Integer>>> entries = primes.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
//            System.out.println(entry.getKey() + ": " + entry.getValue().size());
            for (int k = 1; k <= 25; k++) {
                if (entry.getKey() == k) {
                    List<Integer> value = entry.getValue();
                    int max = 0;
                    for (int i = 0; i < value.size(); i++) {
                        int input = value.get(i);;
                        int ans = 0;
                        int hold = getMin(input);
                        for (int j = 0; j < value.size(); j++) {
                            if (hold == getMin(value.get(j))) {
                                ans++;
                            }
                        }
                        if (ans > max) {
                            max = ans;
                        }
                    }
                    System.out.println("max: " + max);
                }

            }

        }

    }

    private static int getMin(int n) {
        int hold[] = new int[10];
        while (n > 0) {
            hold[n % 10]++;
            n /= 10;
        }

        int ans = 0;
        for (int i = 0; i < hold.length; i++) {
            while (hold[i] > 0) {
                ans = ans * 10 + i;
                hold[i]--;
            }
        }
        return ans;
    }

    private static Map<Integer, List<Integer>> sieve(int n) {
        int[] isComposite = new int[n];
        isComposite[0] = 1;
        isComposite[1] = 1;
        for (int i = 2; i < Math.sqrt(n); i++) {
            for (int j = i + i; j < n; j += i) {
                isComposite[j] = 1;
            }
        }


        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int sum = 0;
            if (isComposite[i] != 1) {
                int temp = i;
                int counter = 0;
                while (temp > 0) {
                    sum += temp % 10;
                    temp /= 10;
                    counter++;
                }
                if (counter == 3) {
                    if (map.containsKey(sum)) {
                        map.get(sum).add(i);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        map.put(sum, list);
                    }
                }
            }
        }
        return map;
    }
}
