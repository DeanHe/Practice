package dp.memorization;

import java.util.List;

/*
There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.

In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.

Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.



Example 1:


Input: piles = [[1,100,3],[7,8,9]], k = 2
Output: 101
Explanation:
The above diagram shows the different ways we can choose k coins.
The maximum total we can obtain is 101.
Example 2:

Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
Output: 706
Explanation:
The maximum total can be obtained if we choose all coins from the last pile.

Constraints:

n == piles.length
1 <= n <= 1000
1 <= piles[i][j] <= 10^5
1 <= k <= sum(piles[i].length) <= 2000

hint:
1 For each pile i, what will be the total value of coins we can collect if we choose the first j coins?
2 How can we use dynamic programming to combine the results from different piles to find the most optimal answer?

analysis:
backpack
 */
public class MaximumValueOfKCoinsFromPiles {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int len = piles.size();
        Integer[][] mem = new Integer[len + 1][k + 1];
        return dfs(mem, piles, k, 0);
    }

    private int dfs(Integer[][] mem, List<List<Integer>> piles, int k, int i) {
        if (i == piles.size() || k == 0) {
            return 0;
        }
        if (mem[i][k] != null) {
            return mem[i][k];
        }
        int res = dfs(mem, piles, k, i + 1);
        int sum = 0;
        for (int j = 0; j < Math.min(piles.get(i).size(), k); j++) {
            sum += piles.get(i).get(j);
            res = Math.max(res, sum + dfs(mem, piles, k - j - 1, i + 1));
        }
        return mem[i][k] = res;
    }
}
