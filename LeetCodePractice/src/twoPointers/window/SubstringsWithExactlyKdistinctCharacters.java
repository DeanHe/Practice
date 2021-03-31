package twoPointers.window;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://www.geeksforgeeks.org/count-number-of-substrings-with-exactly-k-distinct-characters

TC O(N ^ 2)
*/
public class SubstringsWithExactlyKdistinctCharacters {
    // Function to count number of substrings
    // with exactly k unique characters
    int countkDistII(String str, int k) {
        // Initialize result
        int res = 0;
        int len = str.length();
        // To store count of characters from 'a' to 'z'
        int[] cnt = new int[26];
        // Consider all substrings beginning with
        // str[i]
        for (int i = 0; i < len; i++) {
            int dist_count = 0;
            // Initializing count array with 0
            Arrays.fill(cnt, 0);
            // Consider all substrings between str[i..j]
            for (int j = i; j < len; j++) {
                // If this is a new character for this
                // substring, increment dist_count.
                if (cnt[str.charAt(i) - 'a'] == 0) {
                    dist_count++;
                }
                // Increment count of current character
                cnt[str.charAt(i) - 'a']++;
                // If distinct character count becomes k,
                // then increment result.
                if (dist_count == k) {
                    res++;
                }
            }
        }
        return res;
    }

    int countkDist(String str, int k) {
        int res = 0, len = str.length(), s = 0, e = 0;
        Map<Character, Integer> cnt = new HashMap<>();
        while (e < len) {
            char ec = str.charAt(e);
            cnt.put(ec, cnt.getOrDefault(ec, 0) + 1);
            e++;
            while (cnt.size() > k) {
                char sc = str.charAt(s);
                cnt.put(sc, cnt.get(sc) - 1);
                if (cnt.get(sc) == 0) {
                    cnt.remove(sc);
                }
                s++;
            }
            if(cnt.size() == k){
                res += helper(cnt, str, s, e, k);
            }
        }
        return res;
    }

    private int helper(Map<Character, Integer> cnt, String str, int s, int e, int k) {
        //System.out.println(str.substring(s, e));
        Map<Character, Integer> copy = new HashMap<>();
        for(char c : cnt.keySet()){
            copy.put(c, cnt.get(c));
        }
        int res = 0;
        while(copy.size() == k){
            res++;
            char sc = str.charAt(s);
            copy.put(sc, copy.get(sc) - 1);
            if (copy.get(sc) == 0) {
                copy.remove(sc);
            }
            s++;
        }
        return res;
    }
}
