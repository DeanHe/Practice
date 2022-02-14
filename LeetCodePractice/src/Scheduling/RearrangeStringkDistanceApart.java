package Scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1: str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.

Example 2: str = "aaabc", k = 3

Answer: ""

It is not possible to rearrange the string.

Example 3: str = "aaadbbcc", k = 2

Answer: "abacabcd"
Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other

similar:
task scheduler
Reorganize string

TC: O(N log N)
*/
public class RearrangeStringkDistanceApart {
    public String rearrangeString(String str, int k) {
        if (k == 0) {
            return str;
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : str.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> {
            if(freq.get(a) == freq.get(b) ){
               return a.compareTo(b);
            }
            return freq.get(b) - freq.get(a);
        }

        );
        for (char c : freq.keySet()) {
            pq.offer(c);
        }
        List<Character> ls = new ArrayList<>();
        while (!pq.isEmpty()) {
            int gap = Math.min(k, len);
            for (int i = 0; i < gap; i++) {
                if (pq.isEmpty()) {
                    return "";
                }
                char c = pq.poll();
                sb.append(c);
                freq.put(c, freq.get(c) - 1);
                if (freq.get(c) > 0) {
                    ls.add(c);
                }
                len--;
            }
            pq.addAll(ls);
            ls.clear();
        }
        return sb.toString();
    }
}
