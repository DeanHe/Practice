package BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given two strings s and t, check if s is a subsequence of t.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.


Follow up: If there are lots of incoming s, say s1, s2, ..., sk where k >= 109,
and you want to check one by one to see if t has its subsequence.
In this scenario, how would you change your code?

analysis:
mainly to speed up for follow up question
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            map.computeIfAbsent(c, x -> new ArrayList<>()).add(i);
        }
        int prev = -1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                return false;
            }
            List<Integer> ls = map.get(c);
            prev = binarySearch(ls, prev);
            if(prev == -1){
                return false;
            }
            prev++;
        }
        return true;
    }

    //find first element in ls ge target
    private int binarySearch(List<Integer> ls, int target){
        int s = 0, e = ls.size() - 1;
        while(s + 1 < e){
            int mid = s + (e - s) / 2;
            if(ls.get(mid) < target){
                s = mid;
            } else {
                e = mid;
            }
        }
        if(ls.get(s) >= target){
            return ls.get(s);
        }
        if(ls.get(e) >= target){
            return ls.get(e);
        }
        return -1;
    }
}
