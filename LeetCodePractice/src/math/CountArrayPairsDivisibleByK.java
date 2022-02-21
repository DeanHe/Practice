package math;

import java.util.HashMap;
import java.util.Map;

/*
Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) such that:

0 <= i < j <= n - 1 and
nums[i] * nums[j] is divisible by k.

Example 1:
Input: nums = [1,2,3,4,5], k = 2
Output: 7
Explanation:
The 7 pairs of indices whose corresponding products are divisible by 2 are
(0, 1), (0, 3), (1, 2), (1, 3), (1, 4), (2, 3), and (3, 4).
Their products are 2, 4, 6, 8, 10, 12, and 20 respectively.
Other pairs such as (0, 2) and (2, 4) have products 3 and 15 respectively, which are not divisible by 2.

Example 2:
Input: nums = [1,2,3,4], k = 5
Output: 0
Explanation: There does not exist any pair of indices whose corresponding product is divisible by 5.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i], k <= 10^5

hint:
1 For any element in the array, what is the smallest number it should be multiplied with such that the product is divisible by k?
2 The smallest number which should be multiplied with nums[i] so that the product is divisible by k is k / gcd(k, nums[i]).
Now think about how you can store and update the count of such numbers present in the array efficiently.

analysis:
if gcd(num[i], k) * gcd(num[j], k) is divisible by k , num[i] * num[j] should also be divisible by k.
TC: O(N^2)
 */
public class CountArrayPairsDivisibleByK {
    public long coutPairs(int[] nums, int k) {
        long res = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int gcd = gcd(nums[i], k);
            for(int n : cnt.keySet()){
                if((long)gcd * n % k == 0){
                    res += cnt.get(n);
                }
            }
            cnt.put(gcd, cnt.getOrDefault(gcd, 0) + 1);
        }
        return res;
    }

    private int gcd(int a, int b){
        if(a == 0){
            return b;
        }
        return gcd(b % a, a);
    }
}
