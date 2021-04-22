package hashMap;

import java.util.*;
/*
Given an array of strings, group anagrams together.

All inputs will be in lower-case.

Have you met this question in a real interview?  
Example
Given strs = ["eat", "tea", "tan", "ate", "nat", "bat"],
Return 
[
    ["ate", "eat","tea"],
    ["nat","tan"],
    ["bat"]
]
*/
public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0) {
			return new ArrayList<>();
		}
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cnt = new char[26];
            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                cnt[idx]++;
            }

            String key = String.valueOf(cnt);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
