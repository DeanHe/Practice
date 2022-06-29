package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
Design a data structure that efficiently finds the majority element of a given subarray.

The majority element of a subarray is an element that occurs threshold times or more in the subarray.

Implementing the MajorityChecker class:

MajorityChecker(int[] arr) Initializes the instance of the class with the given array arr.
int query(int left, int right, int threshold) returns the element in the subarray arr[left...right] that occurs at least threshold times, or -1 if no such element exists.


Example 1:

Input
["MajorityChecker", "query", "query", "query"]
[[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
Output
[null, 1, -1, 2]

Explanation
MajorityChecker majorityChecker = new MajorityChecker([1, 1, 2, 2, 1, 1]);
majorityChecker.query(0, 5, 4); // return 1
majorityChecker.query(0, 3, 3); // return -1
majorityChecker.query(2, 3, 2); // return 2


Constraints:

1 <= arr.length <= 2 * 10^4
1 <= arr[i] <= 2 * 10^4
0 <= left <= right < arr.length
threshold <= right - left + 1
2 * threshold > right - left + 1
At most 10^4 calls will be made to query.

hint:
What's special about a majority element ?
A majority element appears more than half the length of the array number of times.
If we tried a random index of the array, what's the probability that this index has a majority element ?
It's more than 50% if that array has a majority element.
Try a random index for a proper number of times so that the probability of not finding the answer tends to zero.

analysis:
Random Pick:
As the majority occurs more than half in the interval [l, r], we will have the probability of more than 1/2 to find the "more than half" majority if we randomly pick a number in the interval.
Thus, if we randomly pick try_bound times, we will have the probability of (1-(1/2)) ** try_bound not to find the "more than half" majority.
The probability will be less than 1e-6 if we set try_bound as 20. If we find nothing in try_bound times, we can claim that there is no "more than half" majority.

pre-calculation: O(N)
query: O (try_bound * log(N))
*/
public class MajorityChecker {
    int maxTries = 20;
    int[] arr;
    Map<Integer, List<Integer>> idxMap;
    Random random;

    public MajorityChecker(int[] arr) {
        random = new Random();
        idxMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            idxMap.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }
        this.arr = arr;
    }

    public int query(int left, int right, int threshold) {
        for (int i = 0; i < maxTries; i++) {
            int idx = getRandom(left, right);
            int num = arr[idx];
            List<Integer> indexes = idxMap.get(num);
            if (indexes.size() < threshold) {
                continue;
            }
            int s = binarySearch(indexes, left);
            int e = binarySearch(indexes, right);
            //if s or e is negative, means not found, will return (-insert postion - 1);
            if (s < 0) s = -s - 1;
            if (e < 0) e = (-e - 1) - 1;//make e positive, then e--
            if (e - s + 1 >= threshold) {
                return num;
            }
        }
        return -1;
    }

    private int getRandom(int start, int end) {
        return start + random.nextInt(end - start + 1);
    }

    private int binarySearch(List<Integer> ls, int target) {
        int s = 0, e = ls.size() - 1;
        while (s <= e) {
            int mid = (s + e) >>> 1;
            if (ls.get(mid) == target) {
                return mid;
            } else if (ls.get(mid) < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return -(s + 1);
    }
}
/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
