package HashMap;

import java.util.*;
/*Given an array of strings, group anagrams together.

All inputs will be in lower-case.

Have you met this question in a real interview?  
Example
Given strs = ["eat", "tea", "tan", "ate", "nat", "bat"],
Return 
[
    ["ate", "eat","tea"],
    ["nat","tan"],
    ["bat"]
]*/
public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0) {
			return new ArrayList<>();
		}
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
            	count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)){
            	map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
