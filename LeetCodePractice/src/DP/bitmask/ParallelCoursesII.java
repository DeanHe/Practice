package DP.bitmask;

/*
Given the integer n representing the number of courses at some university labeled from 1 to n, and the array dependencies where dependencies[i] = [xi, yi]  represents a prerequisite relationship, that is, the course xi must be taken before the course yi.  Also, you are given the integer k.

        In one semester you can take at most k courses as long as you have taken all the prerequisites for the courses you are taking.

        Return the minimum number of semesters to take all courses. It is guaranteed that you can take all courses in some way.



        Example 1:



        Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
        Output: 3
        Explanation: The figure above represents the given graph. In this case we can take courses 2 and 3 in the first semester, then take course 1 in the second semester and finally take course 4 in the third semester.
        Example 2:



        Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
        Output: 4
        Explanation: The figure above represents the given graph. In this case one optimal way to take all courses is: take courses 2 and 3 in the first semester and take course 4 in the second semester, then take course 1 in the third semester and finally take course 5 in the fourth semester.
        Example 3:

        Input: n = 11, dependencies = [], k = 2
        Output: 6


        Constraints:

        1 <= n <= 15
        1 <= k <= n
        0 <= dependencies.length <= n * (n-1) / 2
        dependencies[i].length == 2
        1 <= xi, yi <= n
        xi != yi
        All prerequisite relationships are distinct, that is, dependencies[i] != dependencies[j].
        The given graph is a directed acyclic graph.
*/

import java.util.Arrays;

public class ParallelCoursesII {
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] pre = new int[n]; // pre[i]: the bit representation of all dependencies of course i, convert to 0 based
        for (int[] dependency : dependencies) {
            int from = dependency[0] - 1;
            int to = dependency[1] - 1;
            pre[to] |= 1 << from;
        }
        // i is the bit representation of a combination of courses.
        // dp[i] is the minimum days to complete courses combination i
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        return dfs(pre, dp, n, k, 0);
    }

    private int dfs(int[] pre, int[] dp, int n, int k, int state) {
        if (state == ((1 << n) - 1)) {
            return 0;
        }
        if (dp[state] != Integer.MAX_VALUE) {
            return dp[state];
        }
        for (int i = 0; i < n; i++) {
            int cur = state;
            int nextCourse = i;
            int cnt = 0;
            while (cnt < k && nextCourse < n) { // Take at most k courses from the current index i.
                // If the course is not taken yet and all its prerequisites have been taken.
                if ((cur & (1 << nextCourse)) == 0 && ((pre[nextCourse] & state) == pre[nextCourse])) {
                    cur |= 1 << nextCourse;
                    cnt++;
                }
                nextCourse++;
            }
            if (cur != state) { // find any next course this semester
                int val = dfs(pre, dp, n, k, cur);
                if (val != Integer.MAX_VALUE) {
                    dp[state] = Math.min(dp[state], val + 1);
                }
            }
        }
        return dp[state];
    }
}
