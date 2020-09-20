package HashMap;

import java.util.*;

/*Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.(i.e if a corresponds to s, then b cannot correspond to s. For example, given pattern = "ab", str = "ss", return false.)

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
        // write your code here
    	// char in pattern : word in str
    	Map<Character, String> map = new HashMap<>();
    	// matched word in str
    	Set<String> visited = new HashSet<>();
    	return match(pattern, str, map, visited);
    }
    private boolean match(String pattern, String str, Map<Character, String> map, Set<String> visited){
    	if(pattern.length() == 0){
    		return str.length() == 0;
    	}
    	Character c = pattern.charAt(0);
    	if(map.containsKey(c)){
    		String matched = map.get(c);
    		if(!str.startsWith(matched)){
    			return false;
    		}
    		return match(pattern.substring(1), str.substring(matched.length()), map, visited);
    	}
    	for(int i = 0; i < str.length(); i++){
    		String sub = str.substring(0, i + 1);
    		if(!visited.contains(sub)){
    			visited.add(sub);
    			map.put(c, sub);
    			if(match(pattern.substring(1), str.substring(sub.length()), map, visited)){
    				return true;
    			}
    			map.remove(c);
    			visited.remove(sub);
    		}
    	}
    	return false;
    }
}
