package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
#1735
You are given a 2D integer array, queries. For each queries[i], where queries[i] = [ni, ki], find the number of different ways you can place positive integers into an array of size ni such that the product of the integers is ki. As the number of ways may be too large, the answer to the ith query is the number of ways modulo 109 + 7.

Return an integer array answer where answer.length == queries.length, and answer[i] is the answer to the ith query.



Example 1:

Input: queries = [[2,6],[5,1],[73,660]]
Output: [4,1,50734910]
Explanation: Each query is independent.
[2,6]: There are 4 ways to fill an array of size 2 that multiply to 6: [1,6], [2,3], [3,2], [6,1].
[5,1]: There is 1 way to fill an array of size 5 that multiply to 1: [1,1,1,1,1].
[73,660]: There are 1050734917 ways to fill an array of size 73 that multiply to 660. 1050734917 modulo 109 + 7 = 50734910.
Example 2:

Input: queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
Output: [1,2,3,10,5]


Constraints:

1 <= queries.length <= 10^4
1 <= ni, ki <= 10^4

analysis:
Stars and bars partition
pre compute combination
 */
public class CountWaysToMakeArrayWithProduct {
    int MOD = (int)(1e9 + 7);
    long[][] comb = new long[10013][14];
    Map<Integer, List<Integer>> factorsMap;
    public int[] waysToFillArray(int[][] queries) {
        //precompute comb
        for(int i = 0; i < 10013; i++){
            comb[i][0] = 1;
        }
        for(int i = 1; i < 10013; i++){
            for(int j = 1; j < 14; j++){
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        factorsMap = new HashMap<>();
        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            res[i] = fill(queries[i][0], queries[i][1]);
        }
        return res;
    }

    private int fill(int slots, int target) {
        if(target == 1){
            return 1;
        }
        List<Integer> factors = getPrimeFactorCounts(target);
        long res = 1;
        for(int n : factors){
            res = res * choose(slots + n - 1, slots - 1) % MOD;
        }
        return (int)res;
    }

    private long choose(int n, int k) {
        if(k > n / 2){
            return comb[n][n - k];
        }
        return comb[n][k];
    }

    private List<Integer> getPrimeFactorCounts(int n){
        if(factorsMap.containsKey(n)){
            return factorsMap.get(n);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            if(n % i == 0){
                int cnt = 0;
                while(n % i == 0){
                    cnt++;
                    n /= i;
                }
                res.add(cnt);
            }
        }
        factorsMap.put(n, res);
        return res;
    }
}
