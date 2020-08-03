package HashMap;

import java.util.*;
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
public class FindAllAnagramsInaString {
	/**
     * @param s a string
     * @param p a non-empty string
     * @return a list of index
     */
	public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        if(s == null || p == null || p.length() == 0 || s.length() == 0 || s.length() < p.length()){
            return res;
        }
        int[] dict = new int[26];
        char[] arr = p.toCharArray();
        for(char c : arr){
            int idx = c - 'a';
            dict[idx]++;
        }
        int s_len = s.length();
        int p_len = p.length();
        int start = 0, end = 0, match = 0;
        while(end < s_len){
            int end_idx = s.charAt(end) - 'a';
        	if(dict[end_idx] > 0){
        		match++;
        	}
        	dict[end_idx]--;
        	end++;
        	if(match == p_len){
        		res.add(start);
        	}
        	if(end - start == p_len){
        	    int start_idx = s.charAt(start) - 'a';
                dict[start_idx]++;
        		if(dict[start_idx] > 0){
        			match--;
        		}
        		start++;
        	}
        }
        return res;
    }
	
    public List<Integer> findAnagramsNaive(String s, String p) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        if(s == null || p == null || p.length() == 0 || s.length() == 0 || s.length() < p.length()){
            return res;
        }
        int[] dict = new int[26];
        char[] arr = p.toCharArray();
        for(char c : arr){
            dict[c - 'a']++;
        }
        int s_len = s.length();
        int p_len = p.length();
        for(int i = 0; i < s_len - p_len + 1; i++){
            String temp = s.substring(i, i + p_len);
            if(isAnagram(temp, dict)){
                res.add(i);
            }
        }
        return res;
    }
    private boolean isAnagram(String temp, int[] dict){
        int[] match = Arrays.copyOf(dict, dict.length);
        for(int i = 0; i < temp.length(); i++){
            char c = temp.charAt(i);
            int idx = c - 'a';
            match[idx]--;
            if(match[idx] < 0){
                return false;
            }
        }
        return true;
    }
}
