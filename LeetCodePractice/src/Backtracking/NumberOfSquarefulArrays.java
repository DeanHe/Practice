package Backtracking;

import java.util.*;

/*Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
 
Example 1:

Input: [1,17,8]
Output: 2
Explanation: 
[1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: [2,2,2]
Output: 1
 
Note:

1 <= A.length <= 12
0 <= A[i] <= 1e9*/
public class NumberOfSquarefulArrays {
	int count = 0;
	public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        List<Integer> list = new ArrayList<>();
        boolean[] checked = new boolean[len];
        dfs(A, list, checked);
        return count;
    }
	private boolean isSquare(int a, int b){
		double sqrt = Math.sqrt(a + b);
		if(sqrt == Math.floor(sqrt)){
			return true;
		} else {
			return false;
		}	
	}
	private void dfs(int[] A, List<Integer> list, boolean[] checked){
		int len = A.length;
		if(list.size() == len){
			count++;
			return;
		}
		for(int i = 0; i < len; i++){
			if(checked[i]){
				continue;
			}
			if(i > 0 && A[i] == A[i - 1] && !checked[i - 1]){
				continue;
			}
			if(!list.isEmpty()){
				int lastNum = list.get(list.size() - 1);
				if(!isSquare(lastNum, A[i])){
					continue;
				}
			}
			checked[i] = true;
			list.add(A[i]);
			dfs(A, list, checked);
			list.remove(list.size() - 1);
			checked[i] = false;
		}
	}
	// using DP
	public int numSquarefulPermsDP(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        // dp[s][i] means # of ways to reach state s and ends with i
        int[][] dp = new int[1 << len][len];
        for(int i = 0; i < len; i++){
        	if(i == 0 || A[i] != A[i - 1]){
        		dp[1 << i][i] = 1;
        	}
        }
        for(int s = 0; s < (1 << len); s++){
        	for(int i = 0; i < len; i++){
        		if(dp[s][i] != 0){
        			for(int j = 0; j < len; j++){
        				if(!isSquare(A[i], A[j])){
        					continue;
        				}
        				boolean visited_j = (s & (1 << j)) > 0;
        				if(visited_j){
        					continue;
        				}
        				if(j > 0 && A[j] == A[j - 1]){
        					boolean visited_before_j_duplicate = (s & (1 << (j - 1))) > 0;
        					if(!visited_before_j_duplicate){
        						continue;
        					}
        				}
        				dp[s | 1 << j][j] += dp[s][i];
        			}
        		}
        	}
        }
        int count = 0;
        for(int i = 0; i < len; i++){
        	count += dp[(1 << len) - 1][i];
        }
        return count;        
    }
}
