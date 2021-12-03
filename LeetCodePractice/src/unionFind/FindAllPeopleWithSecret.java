package unionFind;

import java.util.*;

/*
You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.



Example 1:

Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.​​​​
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
Example 2:

Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
Output: [0,1,3]
Explanation:
At time 0, person 0 shares the secret with person 3.
At time 2, neither person 1 nor person 2 know the secret.
At time 3, person 3 shares the secret with person 0 and person 1.
Thus, people 0, 1, and 3 know the secret after all the meetings.
Example 3:

Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
Output: [0,1,2,3,4]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
Note that person 2 can share the secret at the same time as receiving it.
At time 2, person 3 shares the secret with person 4.
Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
Example 4:

Input: n = 6, meetings = [[0,2,1],[1,3,1],[4,5,1]], firstPerson = 1
Output: [0,1,2,3]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 0 shares the secret with person 2, and person 1 shares the secret with person 3.
Thus, people 0, 1, 2, and 3 know the secret after all the meetings.


Constraints:

2 <= n <= 10^5
1 <= meetings.length <= 10^5
meetings[i].length == 3
0 <= xi, yi <= n - 1
xi != yi
1 <= time_i <= 10^5
1 <= firstPerson <= n - 1

hint:
1 Could you model all the meetings happening at the same time as a graph?
2 What data structure can you use to efficiently share the secret?
3 You can use the union-find data structure to quickly determine who knows the secret and share the secret.

analysis:
need to reset the nodes not connect to 0 at each timestamp
 */
public class FindAllPeopleWithSecret {
    int[] parent;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        List<Integer> res = new ArrayList<>();
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        union(firstPerson, 0);
        TreeMap<Integer, List<int[]>> meetingsMap = new TreeMap<>();
        for (int[] meeting : meetings) {
            int a = meeting[0];
            int b = meeting[1];
            int time = meeting[2];
            List<int[]> g = meetingsMap.computeIfAbsent(time, x -> new ArrayList<>());
            g.add(new int[]{a, b});
        }
        for (Map.Entry<Integer, List<int[]>> tick : meetingsMap.entrySet()) {
            List<int[]> g = tick.getValue();
            List<Integer> visited = new ArrayList<>();
            for (int[] pair : g) {
                int a = pair[0];
                int b = pair[1];
                visited.add(a);
                visited.add(b);
                union(a, b);
            }
            // reset nodes not connected to 0
            for (int i : visited) {
                if (!isConnected(i, 0)) {
                    parent[i] = i;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (isConnected(i, 0)) {
                res.add(i);
            }
        }
        return res;
    }

    private int findRoot(int x) {
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (parent[x] != root) {
            int fa = parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }

    private boolean union(int a, int b) {
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if (root_a == root_b) {
            return false;
        }
        parent[root_a] = root_b;
        return true;
    }

    private boolean isConnected(int a, int b) {
        int root_a = findRoot(a);
        int root_b = findRoot(b);
        if (root_a == root_b) {
            return true;
        }
        return false;
    }
}
