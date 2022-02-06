package dp.backpack;

/*
Twitter is testing a new job processing service called Pigeon.

Pigeon processes any task in double the actual duration of the task and every task has a weight. Also, Pigeon can serve only for a limited duration(maximum runtime) in an hour.

Given the maximum runtime of the Pigon service, the list of tasks with their lengths and weights, determine the maximum total weight that the Pigeon service can achieve in an hour.

The input contains these parameters:

n: the number of tasks
weights: the weight for every task
tasks: the actual duration of every task
p: maximum runtime for Pigeon in an hour

1 <= n <= 10^3
1 <= weights[i] <= 10^3
1 <= tasks[i] <= 100
1 <= p <= 10^3

Every task can be processed only once.

Example 1
Input:
4
[2,4,4,5]
[2,2,3,4]
15
Output:
10
Explanation:
You can run No.0 No.1 and No.2 task. It will cost 2 * (2 + 2 + 3) = 14 minutes and get 2 + 4 + 4 = 10 weight.

Example 2
Input:
3
[3,2,2]
[3,2,2]
9
Output:
4
Explanation:
You can run No.1 and No.2 task. It will cost 2 * (2 + 2) = 8 minutes and get 2 + 2 = 4 weight.
 */
public class EfficientJobProcessingService {
    /**
     * @param n: the number of tasks
     * @param weights: the weight for every task
     * @param tasks: the actual duration of every task
     * @param p: maximum runtime for Pigeon in an hour
     * @return: the maximum total weight that the Pigeon service can achieve in an hour
     */
    public int maxWeight(int n, int[] weights, int[] tasks, int p) {
        int[][] dp = new int[n + 1][p / 2 + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= p / 2; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - tasks[i - 1] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - tasks[i - 1]] + weights[i - 1]);
                }
            }
        }
        return dp[n][p / 2];
    }
}

