package BFS;

import java.util.*;
import java.util.function.IntPredicate;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
*/
public class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null){
        	return res;
        }
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while(!queue.isEmpty()){
        	String cur = queue.poll();
        	if(isValid(cur)){
        		res.add(cur);
        		found = true;
        	}
        	if(found){
        		continue;
        	}
        	// generate all possible states
        	for(int i = 0; i < cur.length(); i++){
        		// if s[i] is letter
        		if(cur.charAt(i) == '(' || cur.charAt(i) == ')'){
        			String temp = cur.substring(0, i) + cur.substring(i + 1);
            		if(!visited.contains(temp)){
            			queue.offer(temp);
            			visited.add(temp);
            		}
        		}
        	}
        }
        return res;
    }
	// helper function checks if string s contains valid parantheses
	private boolean isValid(String s){
		int count = 0;
		char[] arr = s.toCharArray();
		for(char c : arr){
			if(c == '('){
				count++;
			} else if(c == ')'){
				count--;
				if(count < 0){
					return false;
				}
			}
		}
		return count == 0;
	}
}
