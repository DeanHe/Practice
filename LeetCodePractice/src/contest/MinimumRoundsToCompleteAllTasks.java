package contest;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the same difficulty level.

Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.

Example 1:
Input: tasks = [2,2,3,3,2,4,4,4,4,4]
Output: 4
Explanation: To complete all the tasks, a possible plan is:
- In the first round, you complete 3 tasks of difficulty level 2.
- In the second round, you complete 2 tasks of difficulty level 3.
- In the third round, you complete 3 tasks of difficulty level 4.
- In the fourth round, you complete 2 tasks of difficulty level 4.
It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.

Example 2:
Input: tasks = [2,3,3]
Output: -1
Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.

Constraints:
1 <= tasks.length <= 10^5
1 <= tasks[i] <= 10^9

hint:

1 Which data structure can you use to store the number of tasks of each difficulty level?
2 For any particular difficulty level, what can be the optimal strategy to complete the tasks using minimum rounds?
3 When can we not complete all tasks of a difficulty level?

analysis:
If freq = 1, not possible, return -1
If freq = 2, needs one 2-tasks
If freq = 3, needs one 3-tasks
If freq = 3k, freq = 3 * k, needs k batches.
If freq = 3k + 1, freq = 3 * (k - 1) + 2 + 2, needs k + 1 batches.
If freq = 3k + 2, freq = 3 * k + 2, needs k + 1 batches.
 */
public class MinimumRoundsToCompleteAllTasks {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        int res = 0;
        for (int t : tasks) {
            cntMap.put(t, cntMap.getOrDefault(t, 0) + 1);
        }
        for (int cnt : cntMap.values()) {
            if (cnt == 1) {
                return -1;
            }
            res += (cnt + 2) / 3;
        }
        return res;
    }
}
