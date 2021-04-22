package hashMap;
/*Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Clarification
Your algorithm should run in O(n) complexity.*/

import java.util.HashSet;

public class LongestConsecutiveSequence {
	/**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] num) {
        // write your code here
    	if(num == null || num.length  == 0){
    		return 0;
    	}
    	int longest = 0;
    	HashSet<Integer> set = new HashSet<>();
    	for(int n : num){
    		set.add(n);  		
    	}
    	for(int n : set){
    		int count = 1;
    		int down = n - 1;
    		int up = n + 1;
    		while(set.contains(down)){
    			count++;
    			set.remove(down);
    			down--;
    		}
    		while(set.contains(up)){
    			count++;
    			set.remove(up);
    			up++;
    		}
    		set.remove(n);
    		longest = Math.max(longest, count);
    	}
    	return longest;
    }
}
