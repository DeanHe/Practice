package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.
The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table.
The favorite person of an employee is not themself.

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
Employee 2 cannot be invited because the two spots next to their favorite employee 0 are taken.
So the company leaves them out of the meeting.
The maximum number of employees that can be invited to the meeting is 4.

Constraints:
n == favorite.length
2 <= n <= 10^5
0 <= favorite[i] <= n - 1
favorite[i] != i

hint:
1 From the given array favorite, create a graph where for every index i, there is a directed edge from favorite[i] to i.
The graph will be a combination of cycles and chains of acyclic edges. Now, what are the ways in which we can choose employees to sit at the table?
2 The first way by which we can choose employees is by selecting a cycle of the graph. It can be proven that in this case,
the employees that do not lie in the cycle can never be seated at the table.
3 The second way is by combining acyclic chains. At most two chains can be combined by a cycle of length 2,
where each chain ends on one of the employees in the cycle.

analysis:
case 1: combine longest path from two interdependent nodes
case 2: longest cycle

To find a cycle length in the graph. start from any point and increment the steps along the path. The circle length is cnt - steps[j],
where j is the merge point of path and circle
If there are multiple path merged to the circle, mark the individual path. so that the circle is counted by only one path.
TC O(N)
SC O(N)
 */
public class MaximumEmployeesToBeInvitedToaMeeting {
    public int maximumInvitations(int[] favorite) {
        int len = favorite.length;
        int combinedAcyclic = 0, cycle = 0;
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < len; i++) {
            g.put(i, new ArrayList<>());
        }
        List<int[]> interFavor = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (i == favorite[favorite[i]]) {
                int[] pair = {i, favorite[i]};
                interFavor.add(pair);
            } else {
                g.get(favorite[i]).add(i);
            }
        }
        //case 1
        boolean[] visited = new boolean[len];
        for (int[] pair : interFavor) {
            combinedAcyclic += dfs(g, pair[0], visited) + dfs(g, pair[1], visited);
        }
        combinedAcyclic /= 2;
        //case 2
        int[] steps = new int[len];
        int[] trail = new int[len];
        int mark = 0;
        for (int i = 0; i < len; i++) {
            if (!visited[i] && steps[i] == 0) {
                int cnt = 1;
                int j = i;
                while (steps[j] == 0) {
                    steps[j] = cnt;
                    trail[j] = mark;
                    j = favorite[j];
                    cnt++;
                }
                if(trail[j] == mark){
                    cycle = Math.max(cycle, cnt - steps[j]);
                }
                mark++;
            }
        }
        return Math.max(combinedAcyclic, cycle);
    }

    private int dfs(Map<Integer, List<Integer>> g, int cur, boolean[] visited) {
        int res = 1;
        visited[cur] = true;
        for (int nb : g.get(cur)) {
            res = Math.max(res, dfs(g, nb, visited) + 1);
        }
        return res;
    }
}
