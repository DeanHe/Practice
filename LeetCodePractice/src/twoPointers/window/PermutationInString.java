package twoPointers.window;

import java.util.*;
/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False

Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

tag:
similar to 'find all Anagrams'
*/

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        int s = 0, e = 0;
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s1.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        int cnt = freq.size();
        while (e < l2) {
            char ec = s2.charAt(e);
            if (freq.containsKey(ec)) {
                freq.put(ec, freq.get(ec) - 1);
                if (freq.get(ec) == 0) {
                    cnt--;
                }
            }
            e++;
            while (cnt == 0) {
                if (e - s == l1) {
                    return true;
                }
                char sc = s2.charAt(s);
                if (freq.containsKey(sc)) {
                    freq.put(sc, freq.get(sc) + 1);
                    if (freq.get(sc) == 1) {
                        cnt++;
                    }
                }
                s++;
            }
        }
        return false;
    }
}
