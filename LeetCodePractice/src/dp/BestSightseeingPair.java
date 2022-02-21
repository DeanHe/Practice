package dp;
/*
You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.

The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

Example 1:
Input: values = [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11

Example 2:
Input: values = [1,2]
Output: 2

Constraints:
2 <= values.length <= 5 * 104
1 <= values[i] <= 1000

hint:
1 Can you tell the best sightseeing spot in one pass (ie. as you iterate over the input?) What should we store or keep track of as we iterate to do this?
 */
public class BestSightseeingPair {
    public int maxScoreSightseeingPair(int[] values) {
        int res = Integer.MIN_VALUE;
        int len = values.length;
        int maxRight = Integer.MIN_VALUE;
        for(int i = len - 2; i >= 0; i--){
            maxRight = Math.max(maxRight, values[i + 1] - (i + 1));
            res = Math.max(res, values[i] + i + maxRight);
        }
        return res;
    }
}
