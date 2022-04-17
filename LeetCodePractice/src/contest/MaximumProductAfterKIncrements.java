package contest;

import java.util.PriorityQueue;

/*
You are given an array of non-negative integers nums and an integer k. In one operation, you may choose any element from nums and increment it by 1.

Return the maximum product of nums after at most k operations. Since the answer may be very large, return it modulo 109 + 7.



Example 1:

Input: nums = [0,4], k = 5
Output: 20
Explanation: Increment the first number 5 times.
Now nums = [5, 4], with a product of 5 * 4 = 20.
It can be shown that 20 is maximum product possible, so we return 20.
Note that there may be other ways to increment nums to have the maximum product.
Example 2:

Input: nums = [6,3,3,2], k = 2
Output: 216
Explanation: Increment the second number 1 time and increment the fourth number 1 time.
Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
It can be shown that 216 is maximum product possible, so we return 216.
Note that there may be other ways to increment nums to have the maximum product.


Constraints:

1 <= nums.length, k <= 10^5
0 <= nums[i] <= 10^6
 */
public class MaximumProductAfterKIncrements {
    int MOD = (int)(1e9 + 7);
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int n : nums){
            pq.offer(n);
        }
        while(k > 0){
            k--;
            int n = pq.poll();
            pq.offer(n + 1);
        }
        long res = 1;
        while(!pq.isEmpty()){
            res = (res * pq.poll()) % MOD;
        }
        return (int)res;
    }
}
