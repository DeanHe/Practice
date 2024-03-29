package bfs;
/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]

Return

[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Notice
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

import java.util.*;

public class WordLadderII {
	/**
	 * @param beginWord,
	 *            a string
	 * @param endWord,
	 *            a string
	 * @param wordList,
	 *            a set of string
	 * @return a list of lists of string
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<>();
		// key: the to string, value: the from strings
		Map<String, List<String>> graph = new HashMap<>();
		// the smallest step from start to current node
		Map<String, Integer> distance = new HashMap<>();
		Set<String> dict = new HashSet<>(wordList);
		bfs(beginWord, dict, graph, distance);
		List<String> path = new ArrayList<>();
		path.add(endWord);
		dfs(endWord, beginWord, graph, distance, res, path);
		return res;
	}

	private void bfs(String start, Set<String> dict, Map<String, List<String>> graph, Map<String, Integer> distance) {
		// initial neighborsMap
		for (String temp : dict) {
			graph.put(temp, new ArrayList<>());
		}
		distance.put(start, 0);

		Queue<String> queue = new LinkedList<>();
		queue.offer(start);
		//
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				List<String> neighbors = findNeighbors(cur, dict);
				for (String nb : neighbors) {
					graph.get(nb).add(cur);
					if (!distance.containsKey(nb)) {
						distance.put(nb, distance.get(cur) + 1);
						queue.offer(nb);
					}
				}
			}
		}
	}

	private void dfs(String current, String target, Map<String, List<String>> graph, Map<String, Integer> distance,
			List<List<String>> res, List<String> path) {
		if (current.equals(target)) {
			Collections.reverse(path);
			res.add(new ArrayList<>(path));
			Collections.reverse(path);
			return;
		}
		List<String> neighbors = graph.getOrDefault(current, new ArrayList<>());
		for (String nb : neighbors) {
			if (distance.containsKey(nb) && distance.get(current) == distance.get(nb) + 1) {
				path.add(nb);
				dfs(nb, target, graph, distance, res, path);
				path.remove(path.size() - 1);
			}
		}
	}

	private List<String> findNeighbors(String s, Set<String> dict) {
		List<String> res = new ArrayList<>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				String temp = s.substring(0, i) + c + s.substring(i + 1, len);
				if (dict.contains(temp)) {
					res.add(temp);
				}
			}
		}
		return res;
	}
}
