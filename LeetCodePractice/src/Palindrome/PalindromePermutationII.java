package Palindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

Example
Given s = "aabb", return ["abba","baab"].
Given s = "abc", return [].
*/
public class PalindromePermutationII {
	/**
     * @param s: the given string
     * @return: all the palindromic permutations (without duplicates) of it
     */
    public List<String> generatePalindromes(String s) {
    	List<String> res = new ArrayList<>();
    	// count of character and appearance
    	Map<Character, Integer> freq = new HashMap<>();
    	int odd = 0;
    	for(int i = 0; i < s.length(); i++){
    		char c = s.charAt(i);
			freq.put(c, freq.getOrDefault(c, 0) + 1);
    		if(freq.get(c) % 2 == 0){
    			odd += -1;
    		} else {
    			odd += 1;
    		}
    	}
    	if(odd > 1){
    		// cannot form palindrome
    		return res;
    	}
    	String evenStr = "", charInMid = "";
    	for(char c : freq.keySet()){
    		if(freq.get(c) % 2 == 1){
    			charInMid += c;
    		}
    		for(int i = 0; i < freq.get(c) / 2; i++){
    			evenStr += c;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	boolean[] visited = new boolean[evenStr.length()];
    	dfs(evenStr, charInMid, sb, res, visited);
    	return res;
    }
    private void dfs(String evenStr, String charInMid, StringBuilder sb,  List<String> res, boolean[] visited){
    	if(sb.length() == evenStr.length()){
    		res.add(sb.toString() + charInMid + sb.reverse().toString());
    		sb.reverse(); // reverse back sb
    		return;
    	}
    	for(int i = 0; i < evenStr.length(); i++){
    		if(i > 0 && evenStr.charAt(i) == evenStr.charAt(i - 1) && !visited[i - 1]){
    			continue;
    		}
    		if(!visited[i]){
    			visited[i] = true;
    			char c = evenStr.charAt(i);
    			sb.append(c);
    			dfs(evenStr, charInMid, sb, res, visited);
    			visited[i] = false;
    			sb.deleteCharAt(sb.length() - 1);
    		}
    	}
    }
}
