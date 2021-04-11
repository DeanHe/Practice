package DP.bitmask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
There is a donuts shop that bakes donuts in batches of batchSize. They have a rule where they must serve all of the donuts of a batch before serving any donuts of the next batch. You are given an integer batchSize and an integer array groups, where groups[i] denotes that there is a group of groups[i] customers that will visit the shop. Each customer will get exactly one donut.

When a group visits the shop, all customers of the group must be served before serving any of the following groups. A group will be happy if they all get fresh donuts. That is, the first customer of the group does not receive a donut that was left over from the previous group.

You can freely rearrange the ordering of the groups. Return the maximum possible number of happy groups after rearranging the groups.



Example 1:

Input: batchSize = 3, groups = [1,2,3,4,5,6]
Output: 4
Explanation: You can arrange the groups as [6,2,4,5,1,3]. Then the 1st, 2nd, 4th, and 6th groups will be happy.
Example 2:

Input: batchSize = 4, groups = [1,3,2,5,2,2,1,6]
Output: 4


Constraints:

1 <= batchSize <= 9
1 <= groups.length <= 30
1 <= groups[i] <= 10^9

analysis:
for group which is batchSize factors, they are all happy groups automatically if we move them to the start
arrange a group of size n is same as size n % batchSize

method 1: bit mask; memory limit exceeds
dp[mask] means the maximum happy group by using group of mask
TC O(N * 2 ^ N)
SC O(2 ^ N)
 */
public class MaximumNumberOfGroupsGettingFreshDonuts {
    int batchSize;
    public int maxHappyGroups(int batchSize, int[] groups) {
        this.batchSize = batchSize;
        int res = 0;
        int[] cnt = new int[batchSize];
        for(int group : groups){
            int remainder = group % batchSize;
            cnt[remainder]++;
        }
        //System.out.println(Arrays.toString(cnt));
        //Remainder 0 groups are directly added to the result
        res += cnt[0];
        cnt[0] = 0;
        // pair groups
        for(int i = 1; i <= batchSize / 2; i++){
            if(i == batchSize - i){
                int pairs =cnt[i] / 2;
                cnt[i] -= pairs * 2;
                res += pairs;
            } else {
                int pairs = Math.min(cnt[i], cnt[batchSize - i]);
                cnt[i] -= pairs;
                cnt[batchSize - i] -= pairs;
                res += pairs;
            }
        }
        //System.out.println(res);
        //System.out.println(Arrays.toString(cnt));
        Map<String, Integer> mem = new HashMap<>();
        res += dfs(cnt, mem, 0);
        return res;
    }

    private int dfs(int[] cnt, Map<String, Integer> mem, int remain) {
        String key = Arrays.toString(cnt);
        if(mem.containsKey(key)){
            return mem.get(key);
        }
        int res = 0;
        if(remain > 0 && cnt[batchSize - remain] > 0){
            cnt[batchSize - remain]--;
            res = dfs(cnt, mem, 0);
            cnt[batchSize - remain]++;
        } else {
            for(int i = 1; i < batchSize; i++){
                if(cnt[i] > 0){
                    cnt[i]--;
                    res = Math.max(res, dfs(cnt, mem, (remain + i) % batchSize) + (remain == 0 ? 1 : 0));
                    cnt[i]++;
                }
            }
        }
        //System.out.println(key + ":" + res);
        mem.put(key, res);
        return res;
    }

    public int maxHappyGroupsII(int batchSize, int[] groups) {
        int len = groups.length;
        int[] dp = new int[1 << len];
        for(int state = 0; state < (1 << len); state++){
            int sum = 0;
            for(int i = 0; i < len; i++){
                if((state & (1 << i)) == (1 << i)){
                    sum = (sum + groups[i]) % batchSize;
                }
            }
            for(int i = 0; i < len; i++){
                if((state & (1 << i)) == 0){
                    int next = state | (1 << i);
                    int temp = dp[state];
                    if(sum == 0){
                        temp++;
                    }
                    dp[next] = Math.max(dp[next], temp);
                }
            }
        }
        return dp[(1 << len) - 1];
    }
}

