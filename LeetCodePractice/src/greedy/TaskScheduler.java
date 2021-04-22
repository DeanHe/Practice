package greedy;
/*
        Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
        Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
        However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
        You need to return the least number of intervals the CPU will take to finish all the given tasks.

        Example:
        Input: tasks = ["A","A","A","B","B","B"], n = 2
        Output: 8
        Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


        Constraints:
        The number of tasks is in the range [1, 10000].
        The integer n is in the range [0, 100].

        solution:
        https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
        partCount = count(A) - 1
        emptySlots = partCount * (n - (count of tasks with most frequency - 1))
        availableTasks = tasks.length - count(A) * count of tasks with most frequency
        idles = max(0, emptySlots - availableTasks)
        result = tasks.length + idles
*/
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        int maxCnt = 0; // task with max occurrence
        int maxDup = 0; // number of duplicate max occurrence
        for(char task : tasks){
            counter[task - 'A']++;
            if(maxCnt == counter[task - 'A']){
                maxDup++;
            } else if(maxCnt < counter[task - 'A']){
                maxCnt = counter[task - 'A'];
                maxDup = 1;
            }
        }
        int remainTasks = tasks.length - maxCnt * maxDup;
        int partitionCnt = maxCnt - 1;
        int emptySlots = partitionCnt * (n - (maxDup - 1));
        int idles = Math.max(0, emptySlots - remainTasks);
        return tasks.length + idles;

    }
}
