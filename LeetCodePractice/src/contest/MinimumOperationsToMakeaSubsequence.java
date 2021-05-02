package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given an array target that consists of distinct integers and another integer array arr that can have duplicates.

In one operation, you can insert any integer at any position in arr. For example, if arr = [1,4,1,2], you can add 3 in the middle and make it [1,4,3,1,2]. Note that you can insert the integer at the very beginning or end of the array.

Return the minimum number of operations needed to make target a subsequence of arr.

A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.



Example 1:

Input: target = [5,1,3], arr = [9,4,2,3,4]
Output: 2
Explanation: You can add 5 and 1 in such a way that makes arr = [5,9,4,1,2,3,4], then target will be a subsequence of arr.
Example 2:

Input: target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
Output: 3


Constraints:

1 <= target.length, arr.length <= 105
1 <= target[i], arr[i] <= 109
target contains no duplicates.

analysis:
The problem can be reduced to computing Longest Common Subsequence between both arrays.

Since one of the arrays has distinct elements,
we can consider that these elements describe an arrangement of numbers,
and we can replace each element in the other array with the index it appeared at in the first array.

Then the problem is converted to finding Longest Increasing Subsequence in the second array, which can be done in O(n log n).
 */
public class MinimumOperationsToMakeaSubsequence {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            idxMap.put(target[i], i);
        }
        List<Integer> lis = new ArrayList<>();
        for (int n : arr) {
            if (idxMap.containsKey(n)) {
                int idx = idxMap.get(n);
                if (lis.isEmpty() || lis.get(lis.size() - 1) < idx) {
                    lis.add(idx);
                } else {
                    int i = binarySearch(lis, idx);
                    lis.set(i, idx);
                }
            }
        }
        return target.length - lis.size();
    }

    private int binarySearch(List<Integer> ls, int target) {
        int s = 0, e = ls.size() - 1;
        while (s + 1 < e) {
            int m = s + (e - s) / 2;
            if (ls.get(m) == target) {
                return m;
            } else if (ls.get(m) < target) {
                s = m;
            } else {
                e = m;
            }
        }
        return ls.get(s) < target ? e : s;
    }

}
