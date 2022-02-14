package contest;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
You are given a 0-indexed array nums consisting of n positive integers.

The array nums is called alternating if:

nums[i - 2] == nums[i], where 2 <= i <= n - 1.
nums[i - 1] != nums[i], where 1 <= i <= n - 1.
In one operation, you can choose an index i and change nums[i] into any positive integer.

Return the minimum number of operations required to make the array alternating.



Example 1:

Input: nums = [3,1,3,2,4,3]
Output: 3
Explanation:
One way to make the array alternating is by converting it to [3,1,3,1,3,1].
The number of operations required in this case is 3.
It can be proven that it is not possible to make the array alternating in less than 3 operations.
Example 2:

Input: nums = [1,2,2,2,2]
Output: 2
Explanation:
One way to make the array alternating is by converting it to [1,2,1,2,1].
The number of operations required in this case is 2.
Note that the array cannot be converted to [2,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.


Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5

hint:
1 Count the frequency of each element in odd positions in the array. Do the same for elements in even positions.
2 To minimize the number of operations we need to maximize the number of elements we keep from the original array.
3 What are the possible combinations of elements we can choose from odd indices and even indices so that the number of unchanged elements is maximized?
 */
public class MinimumOperationsToMakeTheArrayAlternating {
    public int minimumOperations(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        Map<Integer, Integer> oddCnt = new HashMap<>();
        Map<Integer, Integer> evenCnt = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                evenCnt.put(nums[i], evenCnt.getOrDefault(nums[i], 0) + 1);
            } else {
                oddCnt.put(nums[i], oddCnt.getOrDefault(nums[i], 0) + 1);
            }
        }
        PriorityQueue<Integer> oddQueue = new PriorityQueue<>((a, b) -> oddCnt.get(b) - oddCnt.get(a));
        for (int k : oddCnt.keySet()) {
            oddQueue.offer(k);
        }
        PriorityQueue<Integer> evenQueue = new PriorityQueue<>((a, b) -> evenCnt.get(b) - evenCnt.get(a));
        for (int k : evenCnt.keySet()) {
            evenQueue.offer(k);
        }
        if (oddQueue.peek() != evenQueue.peek()) {
            return len - oddCnt.get(oddQueue.peek()) - evenCnt.get(evenQueue.peek());
        } else {
            int cand = oddQueue.poll();
            evenQueue.poll();
            int res = Integer.MAX_VALUE;
            //odd uses cand
            res = Math.min(res, len - oddCnt.get(cand) - (evenQueue.peek() != null ? evenCnt.get(evenQueue.peek()) : 0));
            //even uses cand
            res = Math.min(res, len - evenCnt.get(cand) - (oddQueue.peek() != null ? oddCnt.get(oddQueue.peek()) : 0));
            return res;
        }
    }
}
