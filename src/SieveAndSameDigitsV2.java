import java.util.*;

public class SieveAndSameDigitsV2 {
    public static void main(String[] args) {

        int primeLimit = 1000;
        boolean[] primes = sieve(primeLimit);

        int max_count = 0;
        String max_element = null;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 2; i < primeLimit ; i++) {
            if (!primes[i]){
                char[] chars = String.valueOf(i).toCharArray();
                Arrays.sort(chars);
                String s = new String(chars);
                
                Integer integer;
                if (map.containsKey(s)) {
                    integer = map.get(s);
                    integer+=1;
                    map.put(s, integer);
                    if (max_count < map.get(s)){
                        max_count = map.get(s);
                        max_element = s;
                    }

                } else {
                    integer = 1;
                    map.put(s, integer);
                    if (max_count < 1){
                        max_count = 1;
                        max_element = s;
                    }
                }
            }
        }
//        System.out.println(map);
        System.out.println(max_count + " " + max_element);
    }

    private static boolean[] sieve(int n) {
        boolean[] isComposite = new boolean[n];
        isComposite[0] = true;
        isComposite[1] = true;
        for (int i = 2; i < Math.sqrt(n); i++) {
            for (int j = i + i; j < n; j += i) {
                isComposite[j] = true;
            }
        }

        return isComposite;
    }
}
