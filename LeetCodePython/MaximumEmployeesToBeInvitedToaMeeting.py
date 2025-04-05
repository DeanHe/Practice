"""
A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.

The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.

Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.

Example 1:
Input: favorite = [2,2,1,2]
Output: 3
Explanation:
The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
The maximum number of employees that can be invited to the meeting is 3.

Example 2:
Input: favorite = [1,2,0]
Output: 3
Explanation:
Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
The seating arrangement will be the same as that in the figure given in example 1:
- Employee 0 will sit between employees 2 and 1.
- Employee 1 will sit between employees 0 and 2.
- Employee 2 will sit between employees 1 and 0.
The maximum number of employees that can be invited to the meeting is 3.

Example 3:
Input: favorite = [3,0,1,4,1]
Output: 4
Explanation:
The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
So the company leaves them out of the meeting.
The maximum number of employees that can be invited to the meeting is 4.

Constraints:
n == favorite.length
2 <= n <= 10^5
0 <= favorite[i] <= n - 1
favorite[i] != i

hints:
1 From the given array favorite, create a graph where for every index i, there is a directed edge from favorite[i] to i. The graph will be a combination of cycles and chains of acyclic edges. Now, what are the ways in which we can choose employees to sit at the table?
2 The first way by which we can choose employees is by selecting a cycle of the graph. It can be proven that in this case, the employees that do not lie in the cycle can never be seated at the table.
3 The second way is by combining acyclic chains. At most two chains can be combined by a cycle of length 2, where each chain ends on one of the employees in the cycle.

Analysis:
case 1: combine longest path from two interdependent nodes
case 2: longest cycle

Topological Sort to Reduce Non-Cyclic Nodes
The idea is to first process nodes in topological order to remove non-cycle nodes and focus on the cycles that need further examination.

TC O(N)
SC O(N)
"""
from collections import deque
from typing import List


class MaximumEmployeesToBeInvitedToaMeeting:
    def maximumInvitations(self, favorite: List[int]) -> int:
        n = len(favorite)
        in_deg = [0] * n
        for i in range(n):
            in_deg[favorite[i]] += 1

        # Topological sorting to remove non-cycle nodes
        q = deque()
        # Depth of each node
        depth = [1] * n
        for i in range(n):
            if in_deg[i] == 0:
                q.append(i)
        while q:
            cur = q.popleft()
            nb = favorite[cur]
            depth[nb] = max(depth[nb], depth[cur] + 1)
            in_deg[nb] -= 1
            if in_deg[nb] == 0:
                q.append(nb)

        longest_cycle = 0
        two_acyclic_connected = 0

        # Detect cycles
        for i in range(n):
            if in_deg[i] != 0:  # must be on cycle
                cycle_len = 0
                cur = i
                while in_deg[cur] != 0:
                    # Mark as visited
                    in_deg[cur] = 0
                    cycle_len += 1
                    cur = favorite[cur]
                if cycle_len == 2:  # pair favorite
                    two_acyclic_connected += depth[i] + depth[favorite[i]]
                else:
                    longest_cycle = max(longest_cycle, cycle_len)
        return max(longest_cycle, two_acyclic_connected)


