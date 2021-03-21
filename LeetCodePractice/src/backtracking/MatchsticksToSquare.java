package backtracking;

import java.util.Arrays;

/*
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

time complexity: O(4^N)
NP complete problem
Naive DFS for small input size
 */
public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if(nums == null || nums.length < 4){
            return false;
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int side = sum / 4;
        return dfs(nums, new int[4], side, nums.length - 1);
    }

    private boolean dfs(int[] nums, int[] arr, int side, int pos) {
        for (int n : arr) {
            if (n > side) {
                return false;
            }
        }
        if (pos == -1) {
            return true;
        }
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            arr[i] += nums[pos];
            if (dfs(nums, arr, side, pos - 1)) {
                return true;
            }
            arr[i] -= nums[pos];
        }
        return false;
    }

}
