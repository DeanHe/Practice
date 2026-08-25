"""
You are given two integer arrays tasks and shifts.

tasks[i] represents the time required to complete the ith task.
shifts[j] represents the amount of time available during the jth shift.
The tasks must be processed in order from left to right.

Carry-over: If a task is not completed during a shift, processing continues from the same point in that task during the next shift.
Restart: If all tasks are completed during a shift, the shift ends immediately. Any unused time in that shift is discarded, and the next shift begins again from task 0.
A task is unfinished if it has not been fully completed. This includes a task that is currently in progress.

Return an integer array ans where ans[j] is the number of unfinished tasks immediately after the jth shift.


Example 1:

Input: tasks = [1,4,4], shifts = [9,1,4]

Output: [0,2,1]

Explanation:

Shift 0: The tasks require 1 + 4 + 4 = 9 units of time, so all tasks are completed. There are 0 unfinished tasks.
Shift 1: Processing restarts from task 0. The shift has time 1, so task 0 is completed. There are 2 unfinished tasks.
Shift 2: Processing continues from task 1. The shift has time 4, so task 1 is completed. There is 1 unfinished task.
Example 2:

Input: tasks = [2,3,4], shifts = [20,4,5]

Output: [0,2,0]

Explanation:

Shift 0: The tasks require 2 + 3 + 4 = 9 units of time, so all tasks are completed. The remaining time in this shift is ignored. There are 0 unfinished tasks.
Shift 1: Processing restarts from task 0. The shift has time 4, so task 0 is completed and task 1 is partially completed. There are 2 unfinished tasks.
Shift 2: Processing continues from task 1. The remaining time needed is 1 + 4 = 5, so all tasks are completed. There are 0 unfinished tasks.

Example 3:
Input: tasks = [4,2], shifts = [3,6,1]
Output: [2,0,2]

Explanation:
Shift 0: The shift has time 3, so task 0 is partially completed with 1 unit of work remaining. There are 2 unfinished tasks.
Shift 1: Processing continues from task 0. The remaining time needed is 1 + 2 = 3, so all tasks are completed. There are 0 unfinished tasks.
Shift 2: Processing restarts from task 0. The shift has time 1, so task 0 is partially completed. There are 2 unfinished tasks.

Constraints:
1 <= tasks.length <= 10^5
1 <= shifts.length <= 10^5
1 <= tasks[i] <= 10^9
1 <= shifts[i] <= 10^9

analysis:
Binary Search
TC:O(shifts_len * log(tasks_len))
SC:O(shifts_len)
"""
import bisect
from typing import List


class CountOfUnfinishedTasksAfterEachShift:
    def countTasks(self, tasks: List[int], shifts: List[int]) -> List[int]:
        res = []
        tasks_len = len(tasks)
        tasks_pre_sum = []
        for task in tasks:
            if not tasks_pre_sum:
                tasks_pre_sum.append(task)
            else:
                tasks_pre_sum.append(task + tasks_pre_sum[-1])
        finished = 0    # finished accumulated per tasks loop
        for shift in shifts:
            finished += shift
            i = bisect.bisect_right(tasks_pre_sum, finished)
            if i == tasks_len:
                res.append(0)
                finished = 0
            else:
                res.append(tasks_len - i)
        return res
