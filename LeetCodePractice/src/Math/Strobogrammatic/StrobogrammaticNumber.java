package Math.Strobogrammatic;

import java.util.*;
/*A mirror number is a number that looks the same when rotated 180 degrees (looked at upside down).For example, the numbers "69", "88", and "818" are all mirror numbers.
Write a function to determine if a number is mirror. The number is represented as a string.

Example
Example 1:

Input : "69"
Output : true
Example 2:

Input : "68"
Output : false*/
public class StrobogrammaticNumber {
	/**
     * @param num: a string
     * @return: true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        // write your code here
    	int len = num.length();
    	Map<Character, Character> map = new HashMap<>();
    	map.put('9', '6');
    	map.put('6', '9');
    	map.put('1', '1');
    	map.put('8', '8');
    	map.put('0', '0');
    	int start = 0, end = len - 1;
    	while(start <= end){
    		char c1 = num.charAt(start);
    		char c2 = num.charAt(end);
    		if(!map.containsKey(c1)){
    			return false;
    		}
    		if(map.get(c1) != c2){
    			return false;
    		}
    		start++;
    		end--;
    	}
    	return true;
    }
}
