package dfs;

import java.util.HashMap;
import java.util.Map;

/*
You are given an array of transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
Return the minimum number of transactions required to settle the debt.

Example 1:
Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

Example 2:
Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.

Constraints:
1 <= transactions.length <= 8
transactions[i].length == 3
0 <= fromi, toi <= 20
fromi != toi
1 <= amounti <= 100
 */
public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] trans : transactions){
            int from = trans[0];
            int to = trans[1];
            int amount = trans[2];
            map.put(from, map.getOrDefault(from, 0) - amount);
            map.put(to, map.getOrDefault(to, 0) + amount);
        }
        int[] balance = new int[map.size()];
        for(Map.Entry entry : map.entrySet()){
            balance[(int)entry.getKey()] = (int)entry.getValue();
        }
        return dfs(0, balance);
    }

    private int dfs(int idx, int[] balance) {
        while(idx < balance.length && balance[idx] == 0){
            idx++;
        }
        if(idx == balance.length){
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for(int i = idx + 1; i < balance.length; i++){
            if(balance[i] != 0){
                if((balance[idx] > 0 && balance[i] < 0) || (balance[i] > 0 && balance[idx] < 0)){
                    balance[i] += balance[idx];
                    res = Math.min(res, dfs(idx + 1, balance) + 1);
                    balance[i] -= balance[idx];
                }
            }
        }
        return res;
    }
}
