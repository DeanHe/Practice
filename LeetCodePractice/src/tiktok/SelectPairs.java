package tiktok;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Give a list of positive integer. Each turn, you need to select two numbers x and у out and
do a calculation. The result will be abs(x - у) and the number will be put back to the list if it
is not 0.The result will be abs(x - у) and the number will be put back to the list if it
is not 0.
At the end, there is at most 1 number left.Return the smallest possible number left.
(It is 0 if no number left.)
Input:
2
7
4
1
8
1
The array will be [2,7,4,1,8,1], which will be the input of the function you need to write,
the first 6 means that there will be 6 elements in the array
Output:
1
Explanation:
Combining 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
Combining 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
Combining 2 and 1 to get 1 so the array converts to [1,1,1] then,
Combining 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 */
public class SelectPairs {
    public int calculate(int[] arr){
        int len = arr.length;
        int total = 0, maxOfSmall = 0;
        for(int stone : arr){
            total += stone;
        }
        boolean[][] dp = new boolean[total + 1][len + 1];
        for(int i = 0; i <= len; i++){
            dp[0][i] = true;
        }
        for(int s_small = 0; s_small <= total / 2; s_small++){
            for(int i = 1; i <= len; i++){
                if(dp[s_small][i - 1] || (s_small >= arr[i - 1] && dp[s_small - arr[i - 1]][i - 1])){
                    dp[s_small][i] = true;
                    maxOfSmall = Math.max(maxOfSmall, s_small);
                }
            }
        }
        return total - 2 * maxOfSmall;
    }
}
