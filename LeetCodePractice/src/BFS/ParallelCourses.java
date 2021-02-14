package BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
#1136

There are N courses, labelled from 1 to N.

We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.

In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.



Example 1:



Input: N = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation:
In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
Example 2:



Input: N = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation:
No course can be studied because they depend on each other.


Note:

1 <= N <= 5000
1 <= relations.length <= 5000
relations[i][0] != relations[i][1]
There are no repeated relations in the input.


Solution 1. Greedy Algorithm with BFS.

Since we can take as many courses as we want, taking all courses that do not have outstanding prerequisites each semester must be one valid answer that gives a minimum number of semesters needed.

1.  Construct a directed graph and in degree of each node(course).

2. Add all courses that do not have any prerequisites(in degree == 0) to a queue.

3. BFS on all neighboring nodes(courses) of the starting courses and decrease their indegrees by 1.  If a neighboring course's indegree becomes 0, it means all its prerequisites are met, add it to queue as this course can be taken in the next semester.

4. After all neighboring nodes of the courses that are taken in the current semester have been visited, increment semester count by 1. Repeat until the queue is empty.

5. When doing the BFS, keep a count of courses taken, compare this count with the total number of courses to determine if it is possible to study all courses.
 */
public class ParallelCourses {
    public int minimumSemesters(int N, int[][] relations) {
        int semester = 0;
        Map<Integer, List<Integer>> g = new HashMap<>();
        int[] indeg = new int[N];
        for (int[] pair : relations) {
            int from = pair[0] - 1;
            int to = pair[1] - 1;
            indeg[to]++;
            g.computeIfAbsent(from, i -> new ArrayList<>()).add(to);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int cur = q.poll();
                N--;
                for (int nb : g.get(cur)) {
                    indeg[nb]--;
                    if (indeg[nb] == 0) {
                        q.offer(nb);
                    }
                }
            }
            semester++;
        }
        return N == 0 ? semester : -1;
    }
}
