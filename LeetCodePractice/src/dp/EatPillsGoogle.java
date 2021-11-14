package dp;

import java.util.Arrays;

/*
Given there is a bottle with 100 whole pills, you eat half pill per day.
return the probability of given whole and half pills occurrence.
 */
public class EatPillsGoogle {
    double[][] dp = new double[101][101];
    public EatPillsGoogle(){
        dp[100][0] = 1;
        for(int i = 99; i >= 0; i--){
            for(int j = 100 - i; j >= 0; j--){
                if(j - 1 >= 0){
                    dp[i][j] += (i + 1.0)/(i + j) * dp[i + 1][j - 1];
                }
                if(j + 1 <= 100){
                    dp[i][j] += (j + 1.0)/(i + j + 1.0) * dp[i][j + 1];
                }
            }
        }
    }

    public void print(){
        for(int i = 0; i <= 100; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
    }

}
