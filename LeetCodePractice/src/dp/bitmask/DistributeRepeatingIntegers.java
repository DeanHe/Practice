package dp.bitmask;

import java.util.HashMap;
import java.util.Map;

/*
You are given an array of n integers, nums, where there are at most 50 unique values in the array.
You are also given an array of m customer order quantities, quantity, where quantity[i] is the amount of integers the ith customer ordered.
Determine if it is possible to distribute nums such that:

The ith customer gets exactly quantity[i] integers,
The integers the ith customer gets are all equal, and
Every customer is satisfied.
Return true if it is possible to distribute nums according to the above conditions.

Example 1:
Input: nums = [1,2,3,4], quantity = [2]
Output: false
Explanation: The 0th customer cannot be given two different integers.

Example 2:
Input: nums = [1,2,3,3], quantity = [2]
Output: true
Explanation: The 0th customer is given [3,3]. The integers [1,2] are not used.

Example 3:
Input: nums = [1,1,2,2], quantity = [2,2]
Output: true
Explanation: The 0th customer is given [1,1], and the 1st customer is given [2,2].

Example 4:
Input: nums = [1,1,2,3], quantity = [2,2]
Output: false
Explanation: Although the 0th customer could be given [1,1], the 1st customer cannot be satisfied.
Example 5:

Input: nums = [1,1,1,1,1], quantity = [2,3]
Output: true
Explanation: The 0th customer is given [1,1], and the 1st customer is given [1,1,1].


Constraints:

n == nums.length
1 <= n <= 10^5
1 <= nums[i] <= 1000
m == quantity.length
1 <= m <= 10
1 <= quantity[i] <= 10^5
There are at most 50 unique values in nums.
 */
public class DistributeRepeatingIntegers {
    Boolean[][] mem;

    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int n : nums){
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }
        int i = 0;
        int[] freq = new int[freqMap.size()];
        for(int key : freqMap.keySet()){
            freq[i] = freqMap.get(key);
            i++;
        }
        mem = new Boolean[freq.length][1 << quantity.length];
        return dfs(quantity, freq, 0, 0);
    }

    private boolean dfs(int[] quantity, int[] freq, int pos, int state) {
        if(state == (1 << quantity.length) - 1){
            return true;
        }
        if(pos == freq.length){
            return false;
        }
        if(mem[pos][state] != null){
            return mem[pos][state];
        }
        // not use freq[pos]
        boolean res = dfs(quantity, freq, pos + 1, state);
        // use freq[pos] -> create a new state contains a customer, make sure the customer doesn't exist in old state
        for(int next = 0; next < (1 << quantity.length); next++){
            if(state == (state & next)){
                int sum = 0;
                for(int i = 0; i < quantity.length; i++){
                    if((state & (1 << i)) == 0 && (next & (1 << i)) == (1 << i)){
                        sum += quantity[i];
                    }
                }
                if(sum <= freq[pos]){
                    res |= dfs(quantity, freq, pos + 1, next);
                }
                if(res){
                    break;
                }
            }
        }
        return mem[pos][state] = res;
    }
}
