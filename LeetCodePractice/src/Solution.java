import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import SweepLine.Interval;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
/*		Map<Integer, List<Integer>> graph = new HashMap<>();
		graph.put(1, Arrays.asList(2, 3));
		graph.put(2, Arrays.asList(1, 6));
		graph.put(3, Arrays.asList(1, 4, 9));
		graph.put(4, Arrays.asList(3, 5, 7));
		graph.put(5, Arrays.asList(4, 6));
		graph.put(6, Arrays.asList(2, 5, 7));
		graph.put(7, Arrays.asList(4, 6));
		graph.put(9, Arrays.asList(3));
		List<Integer> path = solution.getShortestPath(graph, 1, 6);
		System.out.println(Arrays.toString(path.toArray()));*/
		Map<String, Character> map = new HashMap<>();
		map.put(".-", 'a');
		map.put("-.", 'b');
		map.put(".-.", 'c');
		String a = solution.translate(".-.-.-.", map);
		int x = 1;
	}
	public String translate(String code, Map<String, Character> map){
		StringBuilder sb = new StringBuilder();
		dfs(code, map, sb);
		return sb.toString();
	}
	private boolean dfs(String code, Map<String, Character> map, StringBuilder sb){
		if(code.length() == 0){
			return true;
		}
		int len = code.length();
		for(int i = 1; i <= len; i++){
			String sub = code.substring(0, i);
			if(map.containsKey(sub)){
				sb.append(map.get(sub));
				if(dfs(code.substring(i), map, sb)){
					return true;
				}
				sb.delete(sb.length() - sub.length() + 1, sb.length());
			}
		}
		return false;
	}
}
