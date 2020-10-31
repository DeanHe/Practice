package BFS;

import java.util.*;
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example
Given n = 2, prerequisites = [[1,0]]
Return true

Given n = 2, prerequisites = [[1,0],[0,1]]
Return false
*/

public class CourseSchedule {
	/*
	 * @param numCourses: a total of n courses
	 * 
	 * @param prerequisites: a list of prerequisite pairs
	 * 
	 * @return: true if can finish all courses or false
	 */
	// BFS
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		// write your code here
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			graph.put(i, new ArrayList<>());
		}
		int[] indegree = new int[numCourses];

		for (int i = 0; i < prerequisites.length; i++) {
			int post = prerequisites[i][0];
			int pre = prerequisites[i][1];
			graph.get(pre).add(post);
			indegree[post]++;
		}

		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				// vertex without pre
				queue.offer(i);
			}
		}
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			count++;
			List<Integer> nbs = graph.get(cur);
			for (int nb : nbs) {
				if (--indegree[nb] == 0) {
					queue.offer(nb);
				}
			}
		}
		return count == numCourses;
	}

	// DFS
	public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<>(numCourses);
		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			int pre = prerequisites[i][1];
			int post = prerequisites[i][0];
			graph.get(pre).add(post);
		}
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i)) {
				return false;
			}
		}
		return true;
	}

	private boolean dfs(List<List<Integer>> graph, boolean[] visited, int cur) {
		// true- no cycle; false - with cycle
		if (visited[cur]) {
			return false;
		}
		visited[cur] = true;
		List<Integer> nbs = graph.get(cur);
		for (int nb : nbs) {
			if (!dfs(graph, visited, nb)) {
				return false;
			}
		}
		visited[cur] = false;
		return true;
	}
}
