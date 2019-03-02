package Palindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import String.StringToIntegeratoi;

/*Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example
Given s = "aabb", return ["abba","baab"].
Given s = "abc", return [].*/
public class PalindromePermutationII {
	/**
     * @param s: the given string
     * @return: all the palindromic permutations (without duplicates) of it
     */
    public List<String> generatePalindromes(String s) {
        // write your code here
    	List<String> res = new ArrayList<>();
    	// count of character and appearance
    	HashMap<Character, Integer> map = new HashMap<>();
    	for(int i = 0; i < s.length(); i++){
    		char c = s.charAt(i);
    		if(map.containsKey(c)){
    			map.put(c, map.get(c) + 1);
    		} else {
    			map.put(c, 1);
    		}
    	}
    	String sb = "";
    	boolean[] visited = new boolean[s.length()];
    	dfs(){
    		
    	}
    }
    private void dfs(String s, String sb, List<String> res, boolean[] visited){
    	if(sb.length() == s.length() && isPalidrome(sb)){
    		res.add(sb);
    	}
    	for(int i = 0; i < s.length(); i++){
    		if(i > 0 && s.){
    			
    		}
    		if(!visited[i]){
    			visited[i] = true;
    			dfs(s, sb + s.charAt(i), res, visited);
    			visited[i] = false;
    		}
    	}
    }
    private boolean isPalidrome(String s){
    	
    }
}
