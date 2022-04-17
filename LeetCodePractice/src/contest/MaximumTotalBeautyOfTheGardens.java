package contest;

import java.util.Arrays;

/*
Alice is a caretaker of n gardens and she wants to plant flowers to maximize the total beauty of all her gardens.

You are given a 0-indexed integer array flowers of size n, where flowers[i] is the number of flowers already planted in the ith garden. Flowers that are already planted cannot be removed. You are then given another integer newFlowers, which is the maximum number of flowers that Alice can additionally plant. You are also given the integers target, full, and partial.

A garden is considered complete if it has at least target flowers. The total beauty of the gardens is then determined as the sum of the following:

The number of complete gardens multiplied by full.
The minimum number of flowers in any of the incomplete gardens multiplied by partial. If there are no incomplete gardens, then this value will be 0.
Return the maximum total beauty that Alice can obtain after planting at most newFlowers flowers.

Example 1:
Input: flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
Output: 14
Explanation: Alice can plant
- 2 flowers in the 0th garden
- 3 flowers in the 1st garden
- 1 flower in the 2nd garden
- 1 flower in the 3rd garden
The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7 flowers.
There is 1 garden that is complete.
The minimum number of flowers in the incomplete gardens is 2.
Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
No other way of planting flowers can obtain a total beauty higher than 14.

Example 2:
Input: flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
Output: 30
Explanation: Alice can plant
- 3 flowers in the 0th garden
- 0 flowers in the 1st garden
- 0 flowers in the 2nd garden
- 2 flowers in the 3rd garden
The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5 flowers.
There are 3 gardens that are complete.
The minimum number of flowers in the incomplete gardens is 4.
Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
No other way of planting flowers can obtain a total beauty higher than 30.
Note that Alice could make all the gardens complete but in this case, she would obtain a lower total beauty.


Constraints:
1 <= flowers.length <= 10^5
1 <= flowers[i], target <= 10^5
1 <= newFlowers <= 10^10
1 <= full, partial <= 10^5

hint:
1 Say we choose k gardens to be complete, is there an optimal way of choosing which gardens to plant more flowers to achieve this?
2 For a given k, we should greedily fill-up the k gardens with the most flowers planted already. This gives us the most remaining flowers to fill up the other gardens.
3 After sorting flowers, we can thus try every possible k and what is left is to find the highest minimum flowers we can obtain by planting the remaining flowers in the other gardens.
4 To find the highest minimum in the other gardens, we can use binary search to find the most optimal way of planting.
 */
public class MaximumTotalBeautyOfTheGardens {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int len = flowers.length;
        Arrays.sort(flowers);
        int completed = 0, idx = len;
        for (int i = len - 1; i >= 0; i--) {
            if (target <= flowers[i]) {
                completed++;
                idx = i;
            } else {
                break;
            }
        }
        long[] cost = new long[idx];
        for (int i = 1; i < idx; i++) {
            cost[i] = cost[i - 1] + (long) (flowers[i] - flowers[i - 1]) * i;
        }
        long minIncomplete = flowers[0] < target ? flowers[0] : 0;
        long res = (long) full * completed + minIncomplete * partial;
        for (int i = completed; i <= len; i++) {
            minIncomplete = getMin(flowers, cost, idx, target, newFlowers);
            res = Math.max(res, (long) full * i + minIncomplete * partial);
            idx--;
            if(idx >= 0 && target - flowers[idx] < newFlowers){
                newFlowers -= target - flowers[idx];
                flowers[idx] = target;
            } else {
                break;
            }
        }
        return res;
    }

    private long getMin(int[] flowers, long[] cost, int idx, int target, long newFlowers) {
        if(idx == 0){
            return 0;
        }
        long least = 0;
        int find = Arrays.binarySearch(cost, 0, idx, newFlowers);
        if(find > 0){
            least = flowers[find];
        } else {
            find = -(find + 1);
            least = flowers[find - 1] + (newFlowers - cost[find - 1]) / find;
        }
        return least < target ? least : target - 1;
    }
}
