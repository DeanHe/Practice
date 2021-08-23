package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You are given an integer n representing the length of an unknown array that you are trying to recover. You are also given an array sums containing the values of all 2n subset sums of the unknown array (in no particular order).

Return the array ans of length n representing the unknown array. If multiple answers exist, return any of them.

An array sub is a subset of an array arr if sub can be obtained from arr by deleting some (possibly zero or all) elements of arr. The sum of the elements in sub is one possible subset sum of arr. The sum of an empty array is considered to be 0.

Note: Test cases are generated such that there will always be at least one correct answer.



Example 1:

Input: n = 3, sums = [-3,-2,-1,0,0,1,2,3]
Output: [1,2,-3]
Explanation: [1,2,-3] is able to achieve the given subset sums:
- []: sum is 0
- [1]: sum is 1
- [2]: sum is 2
- [1,2]: sum is 3
- [-3]: sum is -3
- [1,-3]: sum is -2
- [2,-3]: sum is -1
- [1,2,-3]: sum is 0
Note that any permutation of [1,2,-3] and also any permutation of [-1,-2,3] will also be accepted.
Example 2:

Input: n = 2, sums = [0,0,0,0]
Output: [0,0]
Explanation: The only correct answer is [0,0].
Example 3:

Input: n = 4, sums = [0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]
Output: [0,-1,4,5]
Explanation: [0,-1,4,5] is able to achieve the given subset sums.


Constraints:

1 <= n <= 15
sums.length == 2n
-10^4 <= sums[i] <= 10^4

hint:
What information do the two largest elements tell us?
Can we use recursion to check all possible states?
 */
public class FindArrayGivenSubsetSums {
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        int[] res = new int[n];
        dfs(res, sums, 0);
        return res;
    }

    private void dfs(int[] res, int[] sums, int idx) {
        if (idx == res.length) {
            return;
        }
        Map<Integer, Integer> sumCnt = new HashMap<>();
        for (int s : sums) {
            sumCnt.put(s, sumCnt.getOrDefault(s, 0) + 1);
        }
        int len = sums.length;
        int last = sums[len - 1];
        int secondLast = sums[len - 2];
        int candidate = last - secondLast;
        candidate = Math.abs(candidate);
        int[] smaller = new int[len / 2];
        int[] larger = new int[len / 2];
        int i = 0;
        int next;
        boolean positive = false;
        for (int s : sums) {
            if (!sumCnt.containsKey(s)) {
                continue;
            }
            smaller[i] = s;
            if (s == 0) {
                positive = true;
            }
            removeKey(sumCnt, s);
            next = candidate + s;
            larger[i] = next;
            i++;
            removeKey(sumCnt, next);
        }
        res[idx] = positive ? candidate : -candidate;
        int[] nextArr = positive ? smaller : larger;
        dfs(res, nextArr, idx + 1);

    }

    private void removeKey(Map<Integer, Integer> cnt, int n) {
        int freq = cnt.get(n) - 1;
        if (freq == 0) {
            cnt.remove(n);
        } else {
            cnt.put(n, freq);
        }
    }
}

