package DFS;

import java.util.LinkedList;
import java.util.Queue;

/*There is now a string s consisting of only numbers and lowercase letters. If the string s is interesting, then s must be split into several substrings, each substring satisfies the beginning of the number, and the number represents the number of characters after it. For example, s = "4g12y6hunter", we can divide it into "4g12y" and "6hunter", so the string is interesting.
If s is an interesting string, output "yes", otherwise output "no"

Example
s = "124gray6hunter",return "yes"

we can divide it into "12", "4gray", "6hunter".
s = "31ba2a" ,return "no"

Notice
the length of string no more than 10000*/
public class InterestingString {
	String s;
	int len;
	/**
     * @param s: the string s
     * @return: check if the string is interesting
     */
    public String check(String s) {
        // Write your code here
    	this.s = s;
    	len = s.length();
    	if(dfs(0)){
    		return "yes";
    	} else {
    		return "no";
    	}
    }
    private boolean dfs(int pos){
    	if(pos == len){
    		return true;
    	}
    	int num = 0;
    	for(int i = pos; i < len; i++){
    		char c = s.charAt(i) ;
    		if(!Character.isDigit(c)){
    			return false;
    		}
    		num = 10 * num + c - '0';
    		if(i + num + 1 > len){
    			return false;
    		}
    		if(dfs(i + num + 1)){
    			return true;
    		}
    	}
    	return false;
    }
    
    private String checkBFS(String s){
    	int len = s.length();
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(0);
    	while(!queue.isEmpty()){
    		int pos = queue.poll();
    		if(pos == len){
    			return "yes";
    		}
    		int num = 0;
    		for(int i = pos; i < len; i++){
    			char c = s.charAt(i);
    			if(!Character.isDigit(c)){
    				break;
    			}
    			num = num * 10 + c - '0';
    			if(i + num + 1 > len){
    				break;
    			}
    			queue.offer(i + num + 1);
    		}
    	}
    	return "no";
    }
}
