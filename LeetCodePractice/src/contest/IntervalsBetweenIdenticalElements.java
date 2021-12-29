package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a 0-indexed array of n integers arr.
The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.
Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].
Note: |x| is the absolute value of x.

Example 1:
Input: arr = [2,1,3,1,2,3,3]
Output: [4,2,7,2,4,4,5]
Explanation:
- Index 0: Another 2 is found at index 4. |0 - 4| = 4
- Index 1: Another 1 is found at index 3. |1 - 3| = 2
- Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
- Index 3: Another 1 is found at index 1. |3 - 1| = 2
- Index 4: Another 2 is found at index 0. |4 - 0| = 4
- Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
- Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5

Example 2:
Input: arr = [10,5,10,10]
Output: [5,0,3,4]
Explanation:
- Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
- Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
- Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
- Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4

Constraints:
n == arr.length
1 <= n <= 10^5
1 <= arr[i] <= 10^5

hint:
1 For each unique value found in the array, store a sorted list of indices of elements that have this value in the array.
2 One way of doing this is to use a HashMap that maps the values to their list of indices. Update this mapping as you iterate through the array.
3 Process each list of indices separately and get the sum of intervals for the elements of that value by utilizing prefix sums.
4 For each element, keep track of the sum of indices of the identical elements that have come before and that will come after respectively.
Use this to calculate the sum of intervals for that element to the rest of the elements with identical values.
 */
public class IntervalsBetweenIdenticalElements {
    public long[] getDistances(int[] arr) {
        int len = arr.length;
        long[] res = new long[len];
        Map<Integer, Long> preSum = new HashMap<>();
        Map<Integer, Integer> preCnt = new HashMap<>();
        Map<Integer, Integer> preIdx = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int n = arr[i];
            long sum = preSum.getOrDefault(n, 0L);
            int cnt = preCnt.getOrDefault(n, 0);
            int last = preIdx.getOrDefault(n, i);
            sum += cnt * (i - last);
            res[i] += sum;
            preSum.put(n, sum);
            preCnt.put(n, cnt + 1);
            preIdx.put(n, i);
        }
        preSum.clear();
        preCnt.clear();
        preIdx.clear();
        for (int i = len - 1; i >= 0; i--) {
            int n = arr[i];
            long sum = preSum.getOrDefault(n, 0L);
            int cnt = preCnt.getOrDefault(n, 0);
            int last = preIdx.getOrDefault(n, i);
            sum += cnt * (last - i);
            res[i] += sum;
            preSum.put(n, sum);
            preCnt.put(n, cnt + 1);
            preIdx.put(n, i);
        }
        return res;
    }
}
