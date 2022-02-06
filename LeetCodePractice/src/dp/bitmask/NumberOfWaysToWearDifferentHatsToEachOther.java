package dp.bitmask;

/*
There are n people and 40 types of hats labeled from 1 to 40.

Given a list of integers hats, where hats[i] is a list of all hats preferred by the i-th person.

Return the number of ways that the n people wear different hats to each other.

Since the answer may be too large, return it modulo 10^9 + 7.


Example 1:
Input: hats = [[3,4],[4,5],[5]]
Output: 1
Explanation: There is only one way to choose hats given the conditions.
First person choose hat 3, Second person choose hat 4 and last one hat 5.

Example 2:
Input: hats = [[3,5,1],[3,5]]
Output: 4
Explanation: There are 4 ways to choose hats
(3,5), (5,3), (1,3) and (1,5)

Example 3:
Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
Output: 24
Explanation: Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.

Example 4:
Input: hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
Output: 111

Constraints:
n == hats.length
1 <= n <= 10
1 <= hats[i].length <= 40
1 <= hats[i][j] <= 40
hats[i] contains a list of unique integers.

hint:
1 Dynamic programming + bitmask.
2 dp(peopleMask, idHat) number of ways to wear different hats given a bitmask (people visited) and used hats from 1 to idHat-1.
*/

import java.util.*;

public class NumberOfWaysToWearDifferentHatsToEachOther {
    int MOD = (int) (1e9 + 7);
    int people;
    public int numberWays(List<List<Integer>> hats) {
        people = hats.size();
        List<Integer>[] prefer = new List[41]; // hat to it is preferred-by person list
        for (int i = 1; i <= 40; i++) {
            prefer[i] = new ArrayList<>();
        }
        for (int p = 0; p < people; p++) {
            for (int hat : hats.get(p)) {
                prefer[hat].add(p);
            }
        }
        // 40 hats; people <= 10; dp[i][s] means # of ways to choose start from i hat for s people to wear.
        Integer[][] dp = new Integer[41][1 << 10];
        return dfs(prefer, dp, 0, 1);
    }

    private int dfs(List<Integer>[] prefer, Integer[][] dp, int cur, int hat) {
        if (cur == (1 << people) - 1) {
            return 1;  // finish process
        }
        if (hat > 40) {
            return 0; // invalid
        }
        if (dp[hat][cur] != null) {
            return dp[hat][cur];
        }
        //two case: not wear this hat; wear this hat
        int res = dfs(prefer, dp, cur, hat + 1);
        for (int person : prefer[hat]) {
            if (((cur >> person) & 1) == 1) {
                continue;
            }
            res += dfs(prefer, dp, cur | (1 << person), hat + 1);
            res %= MOD;
        }
        return dp[hat][cur] = res;
    }
}
