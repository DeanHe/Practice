package DP;

import java.util.Stack;

/*Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 

Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
 

Constraints:

2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).*/

public class MinimumCostTreeFromLeafValues {
	public int mctFromLeafValues(int[] arr) {
		int len = arr.length;
		int[][] max = new int[len][len]; // max[i][j] means max value in arr[i:j];
		int[][] dp = new int[len][len]; // mem[i][j] means minimum cost tree formed by arr[i:j];
		for (int i = 0; i < len; i++) {
			int temp = arr[i];
			for (int j = i; j < len; j++) {
				temp = Math.max(temp, arr[j]);
				max[i][j] = temp;
			}
		}
		for (int l = 1; l < len; l++) {
			for (int s = 0; s + l < len; s++) {
				int e = s + l;
				dp[s][e] = Integer.MAX_VALUE;
				if (l == 1) {
					dp[s][e] = arr[s] * arr[e];
				} else {
					for (int k = s; k < e; k++) {
						dp[s][e] = Math.min(dp[s][e], dp[s][k] + dp[k + 1][e] + max[s][k] * max[k + 1][e]);
					}
				}
			}
		}
		return dp[0][len - 1];
	}

	public int mctFromLeafValuesByStack(int[] arr) {
		int res = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(Integer.MAX_VALUE);
		for(int n : arr){
			while(stack.peek() <= n){
				int mid = stack.pop();
				res += Math.min(stack.peek(), n) * mid;
			}
			stack.push(n);
		}
		while(stack.size() > 2){
			res += stack.pop() * stack.peek();
		}
		return res;
	}
}
