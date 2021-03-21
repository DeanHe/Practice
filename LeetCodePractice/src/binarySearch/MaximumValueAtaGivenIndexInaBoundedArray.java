package binarySearch;
/*
You are given three positive integers n, index and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.



Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: The arrays [1,1,2,1] and [1,2,2,1] satisfy all the conditions. There are no other valid arrays with a larger value at the given index.
Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3


Constraints:

1 <= n <= maxSum <= 10^9
0 <= index < n

analysis:
binary search; math
 */
public class MaximumValueAtaGivenIndexInaBoundedArray {
    public int maxValue(int n, int index, int maxSum) {
        maxSum -= n;
        int s = 0, e = maxSum, mid;
        while(s + 1 < e){
            mid = s + (e - s) / 2;
            if(helper(mid, n, index, maxSum)){
                s = mid;
            } else {
                e = mid;
            }
        }
        if(helper(e, n, index, maxSum)){
            return 1 + e;
        }
        return 1 + s;
    }

    private boolean helper(int h, int n, int index, int maxSum) {
        long res = h;
        int leftLen = index;
        int rightLen = n - 1 - index;
        int leftEndHeight = Math.max(h - leftLen, 0);
        int rightEndHeight = Math.max(h - rightLen, 0);
        res += (long)(h - 1 + leftEndHeight) * (h - leftEndHeight) / 2;
        res += (long)(h - 1 + rightEndHeight) * (h - rightEndHeight) / 2;
        return res <= maxSum;
    }
}
