package hashMap;

import java.util.*;

/*Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full dfs, such that there is a bijection between a letter in pattern and a non-empty substring in str.(i.e if a corresponds to s, then b cannot correspond to s. For example, given pattern = "ab", str = "ss", return false.)

Example
Given pattern = "abab", str = "redblueredblue", return true.
Given pattern = "aaaa", str = "asdasdasdasd", return true.
Given pattern = "aabb", str = "xyzabcxzyabc", return false.

Notice
You may assume both pattern and str contains only lowercase letters.
*/
public class WordPatternII {
	/**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
    	// char in pattern : word in str
    	Map<Character, String> map = new HashMap<>();
    	// matched word in str
    	Set<String> visited = new HashSet<>();
    	return dfs(pattern, str, map, visited);
    }
    private boolean dfs(String pattern, String str, Map<Character, String> map, Set<String> visited){
    	if(pattern.length() == 0){
    		return str.length() == 0;
    	}
    	char c = pattern.charAt(0);
    	if(map.containsKey(c)){
    		String match = map.get(c);
    		if(!str.startsWith(match)){
    			return false;
    		}
    		return dfs(pattern.substring(1), str.substring(match.length()), map, visited);
    	}
    	for(int i = 1; i <= str.length(); i++){
    		String match = str.substring(0, i);
    		if(!visited.contains(match)){
    			visited.add(match);
    			map.put(c, match);
    			if(dfs(pattern.substring(1), str.substring(match.length()), map, visited)){
    				return true;
    			}
    			map.remove(c);
    			visited.remove(match);
    		}
    	}
    	return false;
    }
}
