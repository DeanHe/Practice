package dp;
/*
roll n dices on the ground, show all the possible results probability.
return an array of double type, the ith element represents the probability of occuring a sum equals i.

Constraints:

1 <= n <= 11

analysis:
dp[i][j] means probability of getting sum j with i dices rolled.
 */
public class RollDiceResultProbability {
    public double[] dicesProbability(int n) {
        double[][] dp = new double[n + 1][6 * n + 1];
        //init
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1.0 / 6;
        }
        //transfer func
        for (int i = 2; i <= n; i++) {
            int most = 6 * i;
            for (int j = i; j <= most; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j - k > 0) {
                        dp[i][j] += dp[1][k] * dp[i - 1][j - k];
                    }
                }
            }
        }
        //aggregate result
        double[] res = new double[5 * n + 1];
        for (int i = n; i <= 6 * n; i++) {
            res[i - n] += dp[n][i];
        }
        return res;
    }
}

