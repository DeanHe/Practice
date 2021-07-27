package stack.monotonicStack;

import java.util.Stack;

/*
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.

Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.

Example 2:

Input: arr = [11,81,94,43,3]
Output: 444

Constraints:

1 <= arr.length <= 3 * 10^4
1 <= arr[i] <= 3 * 10^4

analysis:
approach 1:
stack: Increasing stack, store the index
dp[i]: Sum of minimum of all subarrays which end in idx i, A[:i]

approach 2:
use left[i] to track left side number of sub arrays with min element idx as A[i]
use right[i] to track right side number of sub arrays with min element idx as A[i]
use monotonic leftStack and rightStack to get the first less element idx
res = SUM(A[i] * left[i] + 1 * right[i] + 1)
*/

public class SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        int MOD = (int) (1e9 + 7);
        int res = 0, len = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                dp[i] = dp[stack.peek()] + (i - stack.peek()) * arr[i];
            } else {
                dp[i] = arr[i] * (i + 1);
            }
            stack.push(i);
            res += dp[i];
            res %= MOD;
        }
        return res;
    }

    public int sumSubarrayMinsII(int[] arr) {
        int MOD = (int) (1e9 + 7);
        int res = 0, len = arr.length;
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int[] left = new int[len];
        int[] right = new int[len];
        for (int i = 0; i < len; i++) {
            while (!leftStack.isEmpty() && arr[i] < arr[leftStack.peek()]) {
                leftStack.pop();
            }
            if (!leftStack.isEmpty()) {
                left[i] = i - leftStack.peek() - 1;
            } else {
                left[i] = i;
            }
            leftStack.push(i);
        }
        for (int i = len - 1; i >= 0; i--) {
            while (!rightStack.isEmpty() && arr[i] <= arr[rightStack.peek()]) {
                rightStack.pop();
            }
            if (!rightStack.isEmpty()) {
                right[i] = rightStack.peek() - i - 1;
            } else {
                right[i] = len - 1 - i;
            }
            rightStack.push(i);
        }
        for (int i = 0; i < len; i++) {
            res += arr[i] * (left[i] + 1) * (right[i] + 1);
            res %= MOD;
        }
        return res;
    }
}
