package DP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.*/
public class PerfectSquares {
	public int numSquares(int n) {
       int[] dp = new int[n + 1];
       Arrays.fill(dp, Integer.MAX_VALUE);
       dp[0] = 0;
       dp[1] = 1;
       for(int i = 2; i <= n; i++){
    	   for(int j = 1; j * j <= i; j++){
    		   if(dp[i - j * j] < Integer.MAX_VALUE){
    			  dp[i] = Math.min(dp[i], dp[i - j * j] + 1); 
    		   }
    	   }
       }
       return dp[n];
    }
	
	public int numSquaresBFS(int n) {
	       boolean[] visited = new boolean[n + 1];
	       visited[n] = true;
	       Queue<Integer> queue = new LinkedList<>();
	       queue.offer(n);
	       int steps = 0;
	       while(!queue.isEmpty()){
	    	   int len = queue.size();
	    	   for(int i = 0; i < len; i++){
	    		   int cur = queue.poll();
	    		   if(cur == 0){
	    			   return steps;
	    		   }
	    		   for(int j = 1; j * j <= cur; j++){
	    			   int num = cur - j * j;
	    			   if(!visited[num]){
	    				   queue.offer(num);
	    				   visited[num] = true;
	    			   }
	    		   }
	    	   }
	    	   steps++;
	       }
	       return -1;       
	    }
}
