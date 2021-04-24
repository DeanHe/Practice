package math.lexicographical;
/*Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
Note: 1 ≤ k ≤ n ≤ 109.

Example:
Input:
n: 13   k: 2
Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/discuss/92242/ConciseEasy-to-understand-Java-5ms-solution-with-Explaination
the idea is to calculate the steps between cur and cur + 1 (neighbor nodes in same level), in order to skip some unnecessary moves.*/
public class KthSmallestInLexicographicalOrder {
	public int findKthNumber(int n, int k) {
        int cur = 1;
        k = k - 1;
        while(k > 0){
        	int steps = calculateSteps(n, cur, cur + 1);
        	if(steps <= k){
        		k = k - steps;
        		cur = cur + 1;
        	} else {
        		k = k - 1;
        		cur = cur * 10;
        	}
        }
        return cur;
    }
	private int calculateSteps(int n, long a, long b){
		int steps = 0;
		while(a <= n){
			steps += Math.min(b, n + 1) - a;
			a = a * 10;
			b = b * 10;
		}
		return steps;
	}
}
