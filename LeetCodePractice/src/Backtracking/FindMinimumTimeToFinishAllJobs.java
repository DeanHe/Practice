package Backtracking;

import java.util.Arrays;

/*
You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.

There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.

Return the minimum possible maximum working time of any assignment.



Example 1:

Input: jobs = [3,2,3], k = 3
Output: 3
Explanation: By assigning each person one job, the maximum time is 3.
Example 2:

Input: jobs = [1,2,4,7,8], k = 2
Output: 11
Explanation: Assign the jobs the following way:
Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
Worker 2: 4, 7 (working time = 4 + 7 = 11)
The maximum working time is 11.


Constraints:

1 <= k <= jobs.length <= 12
1 <= jobs[i] <= 107

analysis:
branch cutting tricks:
1 it makes no difference if we assign job to any worker whose current workload are same.
2 reverse sort all the jobs so that we are more likely to exit earlier
3 if any sum >= res, exit

similar to partition-to-k-equal-sum-subsets
 */
public class FindMinimumTimeToFinishAllJobs {
    int res = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        int len = jobs.length;
        Arrays.sort(jobs);
        if (k >= len) {
            return jobs[len - 1];
        }
        dfs(jobs, new int[k], len - 1);
        return res;

    }

    private void dfs(int[] jobs, int[] arr, int pos) {
        if (pos == -1) {
            int maxSum = 0;
            for (int sum : arr) {
                maxSum = Math.max(maxSum, sum);
            }
            res = Math.min(res, maxSum);
            return;
        }
        for (int sum : arr) {
            if (sum > res) {
                return;
            }
        }
        boolean res = false;
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            arr[i] += jobs[pos];
            dfs(jobs, arr, pos - 1);
            arr[i] -= jobs[pos];
        }
    }

}
