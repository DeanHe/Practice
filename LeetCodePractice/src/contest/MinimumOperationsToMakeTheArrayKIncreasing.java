package contest;

import java.util.ArrayList;
import java.util.List;

/*
You are given a 0-indexed array arr consisting of n positive integers, and a positive integer k.
The array arr is called K-increasing if arr[i-k] <= arr[i] holds for every index i, where k <= i <= n-1.

For example, arr = [4, 1, 5, 2, 6, 2] is K-increasing for k = 2 because:
arr[0] <= arr[2] (4 <= 5)
arr[1] <= arr[3] (1 <= 2)
arr[2] <= arr[4] (5 <= 6)
arr[3] <= arr[5] (2 <= 2)
However, the same arr is not K-increasing for k = 1 (because arr[0] > arr[1]) or k = 3 (because arr[0] > arr[3]).
In one operation, you can choose an index i and change arr[i] into any positive integer.

Return the minimum number of operations required to make the array K-increasing for the given k.

Example 1:
Input: arr = [5,4,3,2,1], k = 1
Output: 4
Explanation:
For k = 1, the resultant array has to be non-decreasing.
Some of the K-increasing arrays that can be formed are [5,6,7,8,9], [1,1,1,1,1], [2,2,3,4,4]. All of them require 4 operations.
It is suboptimal to change the array to, for example, [6,7,8,9,10] because it would take 5 operations.
It can be shown that we cannot make the array K-increasing in less than 4 operations.

Example 2:
Input: arr = [4,1,5,2,6,2], k = 2
Output: 0
Explanation:
This is the same example as the one in the problem description.
Here, for every index i where 2 <= i <= 5, arr[i-2] <= arr[i].
Since the given array is already K-increasing, we do not need to perform any operations.

Example 3:
Input: arr = [4,1,5,2,6,2], k = 3
Output: 2
Explanation:
Indices 3 and 5 are the only ones not satisfying arr[i-3] <= arr[i] for 3 <= i <= 5.
One of the ways we can make the array K-increasing is by changing arr[3] to 4 and arr[5] to 5.
The array will now be [4,1,5,4,6,5].
Note that there can be other ways to make the array K-increasing, but none of them require less than 2 operations.


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i], k <= arr.length

hint:
1 Can we divide the array into non-overlapping subsequences and simplify the problem?
2 In the final array, arr[i-k] ≤ arr[i] should hold. We can use this to divide the array into at most k non-overlapping sequences, where arr[i] will belong to the (i%k)th sequence.
3 Now our problem boils down to performing the minimum operations on each sequence such that it becomes non-decreasing. Our answer will be the sum of operations on each sequence.
4 Which indices of a sequence should we not change in order to count the minimum operations? Can finding the longest non-decreasing subsequence of the sequence help?

analysis:
similar to LIS

TC: O(K * N/K * log(N/K)) ~ O(N * logN)
We have total K new arr, each array have up to N/K elements.
We need O(M * logM) to find the longest non-increasing subsequence of an array length M.
SC: O(N)
 */
public class MinimumOperationsToMakeTheArrayKIncreasing {
    public int kIncreasing(int[] arr, int k) {
        int len = arr.length, res = 0;
        for(int i = 0; i < k; i++){
            List<Integer> ls = new ArrayList<>();
            for(int j = i; j < len; j += k){
                ls.add(arr[j]);
            }
            res += ls.size() - longestNonDecreasingSubsequence(ls);
        }
        return res;
    }

    private int longestNonDecreasingSubsequence(List<Integer> ls) {
        int res = 0, len = ls.size();
        for(int i = 0; i < len; i++){
            int idx = binarySearch(ls, 0, res - 1, ls.get(i));
            ls.set(idx, ls.get(i));
            if(idx == res){
                res++;
            }
        }
        return res;
    }
    private int binarySearch(List<Integer> nums, int s, int e, int target) {
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums.get(mid) <= target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return s;
    }
}
