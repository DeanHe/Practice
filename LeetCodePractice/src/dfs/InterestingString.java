package dfs;

import java.util.LinkedList;
import java.util.Queue;

/*
There is now a string s consisting of only numbers and lowercase letters. If the string s is interesting, then s must be split into several substrings,
each substring satisfies the beginning of the number, and the number represents the number of characters after it.

For example, s = "4g12y6hunter", we can divide it into "4g12y" and "6hunter", so the string is interesting.
If s is an interesting string, output "yes", otherwise output "no"

Example
s = "124gray6hunter",return true

we can divide it into "12", "4gray", "6hunter".
s = "31ba2a" ,return false

Notice
the length of string no more than 10000
*/
public class InterestingString {

	/**
     * @param s: the string s
     * @return: check if the string is interesting
     */
    public boolean check(String s) {
    	if(s == null || s.length() == 0){
    		return false;
		}
        // Write your code here
    	if(dfs(s,0)){
    		return true;
    	} else {
    		return false;
    	}
    }
    private boolean dfs(String s, int pos){
    	int len = s.length();
    	if(pos > len){
    		return false;
		}
    	if(pos == len){
    		return true;
    	}
    	int num = 0;
    	for(int i = pos; i < len; i++){
    		char c = s.charAt(i);
    		if(Character.isDigit(c)){
				num = 10 * num + c - '0';
				if(dfs(s, i + num + 1)){
					return true;
				}
    		}
    	}
    	return false;
    }
    
    public boolean checkBFS(String s){
		if(s == null || s.length() == 0){
			return false;
		}
    	int len = s.length();
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(0);
    	while(!queue.isEmpty()){
    		int pos = queue.poll();
    		if(pos == len){
    			return true;
    		}
    		int num = 0;
    		for(int i = pos; i < len; i++){
    			char c = s.charAt(i);
    			if(Character.isDigit(c)){
					num = num * 10 + c - '0';
					if(i + num + 1 <= len){
						queue.offer(i + num + 1);
					}
    			}
    		}
    	}
    	return false;
    }
}
