package bfs;

import java.util.*;

/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example
Given n = 2, prerequisites = [[1,0]]
Return [0,1]

Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
Return [0,1,2,3] or [0,2,1,3]
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
		List<List<Integer>> graph = new ArrayList<>(numCourses);
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		int[] inDegree = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			int pre = prerequisites[i][1];
			int post = prerequisites[i][0];
			graph.get(pre).add(post);
			inDegree[post]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		int count = 0;
		int[] res = new int[numCourses];
		while (!queue.isEmpty()) {
			int course = queue.poll();
			res[count] = course;
			count++;
			List<Integer> nbs = graph.get(course);
			for (int nb : nbs) {
				if (--inDegree[nb] == 0) {
					queue.offer(nb);
				}
			}
		}
		if (count == numCourses) {
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
