package contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
Given the array nums consisting of n positive integers. You computed the sum of all non-empty continous subarrays from the array and then sort them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 10^9 + 7.



Example 1:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
Output: 13
Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13.
Example 2:

Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
Output: 6
Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.
Example 3:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
Output: 50


Constraints:

1 <= nums.length <= 10^3
nums.length == n
1 <= nums[i] <= 100
1 <= left <= right <= n * (n + 1) / 2

analysis:
Compute all sums and save it in array.
Then just go from LEFT to RIGHT index and calculate answer modulo 1e9 + 7.
 */
public class RangeSumOfSortedSubarraySums {
    class Pair {
        int sum, idx;
        public Pair(int sum, int idx){
            this.sum = sum;
            this.idx = idx;
        }
    }
    public int rangeSum(int[] nums, int n, int left, int right) {
        int MOD = (int) (1e9 + 7);
        long res = 0L;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) ->
                a.sum - b.sum
        );
        for (int i = 0; i < n; i++) {
            pq.offer(new Pair(nums[i], i));
        }
        for (int i = 1; i <= right; i++) {
            Pair cur = pq.poll();
            int sum = cur.sum;
            int idx = cur.idx;
            if (i >= left) {
                res += sum;
            }
            if (idx + 1 < n) {
                pq.offer(new Pair(sum + nums[idx + 1], idx + 1));
            }
        }
        res = res % MOD;
        return (int) res;
    }
    //Brutal Force
    public int rangeSumBF(int[] nums, int n, int left, int right) {
        int MOD = (int)(1e9 + 7);
        long res = 0L;
        List<Long> ls = new ArrayList<>();
        for(int i = 0; i < n; i++){
            long sum = 0;
            for(int j = i; j < n; j++){
                sum += nums[j];
                ls.add(sum);
            }
        }
        Collections.sort(ls);
        for(int i = left - 1; i <= right - 1; i++){
            res += ls.get(i);
        }
        res = res % MOD;
        return (int) res;
    }
}
