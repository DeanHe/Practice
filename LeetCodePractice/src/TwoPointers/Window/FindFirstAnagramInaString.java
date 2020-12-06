package TwoPointers.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 40,000.

The order of output does not matter.

Have you met this question in a real interview?
Example
Given s = "cbaebabacd" p = "abc"

return [0, 6]

The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Sliding window problem
Time Complexity will be O(n) because the "start" and "end" points will only move from left to right once.
*/
public class FindFirstAnagramInaString {
    /**
     * @param s a string
     * @param p a non-empty string
     * @return index of anagram in s
     */
    public int findFirstAnagram(String s, String p) {
        if (s == null || p == null || p.length() == 0 || s.length() == 0 || s.length() < p.length()) {
            return -1;
        }
        int start = 0, end = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = map.size();
        while(end < s.length()){
            char end_c = s.charAt(end);
            if(map.containsKey(end_c)){
                map.put(end_c, map.get(end_c) - 1);
                if(map.get(end_c) == 0){
                    count--;
                }
            }
            end++;
            while(count == 0){
                char start_c = s.charAt(start);
                if(map.containsKey(start_c)){
                    map.put(start_c, map.get(start_c) + 1);
                    if(map.get(start_c) == 1){
                        count++;
                    }
                }
                if(end - start == p.length()){
                    return start;
                }
                start++;
            }
        }
        return -1;
    }
}
