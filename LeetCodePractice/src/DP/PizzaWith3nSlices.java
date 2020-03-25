package DP;
/*
        There is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:
        You will pick any pizza slice.
        Your friend Alice will pick next slice in anti clockwise direction of your pick.
        Your friend Bob will pick next slice in clockwise direction of your pick.
        Repeat until there are no more slices of pizzas.
        Sizes of Pizza slices is represented by circular array slices in clockwise direction.

        Return the maximum possible sum of slice sizes which you can have.

        Example 1:
        Input: slices = [1,2,3,4,5,6]
        Output: 10
        Explanation: Pick pizza slice of size 4, Alice and Bob will pick slices with size 3 and 5 respectively. Then Pick slices with size 6, finally Alice and Bob will pick slice of size 2 and 1 respectively. Total = 4 + 6.

        Example 2:
        Input: slices = [8,9,8,6,1,1]
        Output: 16
        Output: Pick pizza slice of size 8 in each turn. If you pick slice with size 9 your partners will pick slices of size 8.

        Example 3:
        Input: slices = [4,1,2,5,8,3,1,9,7]
        Output: 21

        Example 4:
        Input: slices = [3,1,2]
        Output: 3

        Constraints:
        1 <= slices.length <= 500
        slices.length % 3 == 0
        1 <= slices[i] <= 1000

        By studying the pattern of the operations, we can find out that the problem is equivalent to: Given an integer array with size 3N, select N integers with maximum sum and any selected integers are not next to each other in the array.
        The first one in the array is considered next to the last one in the array. Use Dynamic Programming to solve it.
*/
public class PizzaWith3nSlices {
    public int maxSizeSlices(int[] slices) {
        int len = slices.length;
        return Math.max(helper(slices, len / 3, 0,len  - 2), helper(slices, len / 3,1, len - 1));
    }

    private int helper(int[] slices, int rounds, int start, int end){
        int most = 0;
        int[][][] dp = new int[end + 1][rounds + 1][2]; // dp[i][j][k] means max value of pick j slices from [0:i] with last slice taken 0, not taken 1
        dp[start][1][0] = slices[start];
        for(int i = start + 1; i <= end; i++){
            for(int j = 1; j <= rounds; j++){
                dp[i][j][0] = dp[i - 1][j - 1][1] + slices[i];
                dp[i][j][1] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]);
                if(j == rounds){
                    most = Math.max(most, dp[i][j][0]);
                    most = Math.max(most, dp[i][j][1]);
                }
            }
        }
        return most;
    }
}
