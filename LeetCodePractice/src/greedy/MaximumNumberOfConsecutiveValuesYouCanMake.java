package greedy;

import java.util.Arrays;

/*
#1798

You are given an integer array coins of length n which represents the n coins that you own. The value of the ith coin is coins[i]. You can make some value x if you can choose some of your n coins such that their values sum up to x.

Return the maximum number of consecutive integer values that you can make with your coins starting from and including 0.

Note that you may have multiple coins of the same value.



Example 1:

Input: coins = [1,3]
Output: 2
Explanation: You can make the following values:
- 0: take []
- 1: take [1]
You can make 2 consecutive integer values starting from 0.
Example 2:

Input: coins = [1,1,1,4]
Output: 8
Explanation: You can make the following values:
- 0: take []
- 1: take [1]
- 2: take [1,1]
- 3: take [1,1,1]
- 4: take [4]
- 5: take [4,1]
- 6: take [4,1,1]
- 7: take [4,1,1,1]
You can make 8 consecutive integer values starting from 0.
Example 3:

Input: nums = [1,4,10,3,1]
Output: 20


Constraints:

coins.length == n
1 <= n <= 4 * 10^4
1 <= coins[i] <= 4 * 10^4

hint:
Let's note that if you can make the first x values then you can
and you have a value v ≤ x+1 then you can make all values ≤ v+x

The smaller v is the smaller the x you need so it's optimal to process elements in a sorted order
 */
public class MaximumNumberOfConsecutiveValuesYouCanMake {
    public int getMaximumConsecutive(int[] coins) {
        int most = 0;
        Arrays.sort(coins);
        for(int n : coins){
            if(n > most + 1){
                break;
            }
            most += n;
        }
        return most + 1;
    }
}
