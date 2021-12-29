package bfs;

import java.util.*;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.

hint:
1 This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
2 Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
3 Topological sort could also be done via BFS.

analysis:
TC O(n)
*/

public class CourseScheduleII {
	/**
	 * @param numCourses
	 *            a total of n courses
	 * @param prerequisites
	 *            a list of prerequisite pairs
	 * @return the course order
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		int[] inDegree = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			int pre = prerequisites[i][1];
			int post = prerequisites[i][0];
			graph.computeIfAbsent(pre, x -> new ArrayList<>()).add(post);
			inDegree[post]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		int cnt = 0;
		int[] res = new int[numCourses];
		while (!queue.isEmpty()) {
			int course = queue.poll();
			res[cnt] = course;
			cnt++;
			List<Integer> nbs = graph.getOrDefault(course, new ArrayList<>());
			for (int nb : nbs) {
				if (--inDegree[nb] == 0) {
					queue.offer(nb);
				}
			}
		}
		if (cnt == numCourses) {
			return res;
		} else {
			return new int[0];
		}
	}

	public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<>(numCourses);
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			int pre = prerequisites[i][1];
			int post = prerequisites[i][0];
			graph.get(pre).add(post);
		}
		boolean[] visited = new boolean[numCourses];
		boolean[] verified = new boolean[numCourses];
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, res, visited, verified, i)) {
				return new int[0];
			}
		}
		int[] arr = new int[res.size()];
		for (int i = 0; i < res.size(); i++) {
			arr[i] = res.get(res.size() - 1 - i);
		}
		return arr;
	}

	private boolean dfs(List<List<Integer>> graph, List<Integer> res, boolean[] visited, boolean[] verified, int cur) {
		// true- no cycle; false - with cycle
		if (verified[cur]) {
			return true;
		}
		if (visited[cur]) {
			return false;
		}
		visited[cur] = true;
		List<Integer> nbs = graph.get(cur);
		for (int nb : nbs) {
			if (!dfs(graph, res, visited, verified, nb)) {
				return false;
			}
		}
		res.add(cur);
		verified[cur] = true;
		visited[cur] = false;
		return true;
	}
}
