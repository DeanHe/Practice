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
		int a = solution.greateCommonDivisor(2, 3);
		int x = 1;
	}
	private int greateCommonDivisor(int A, int B) {
		if(B == 0){
			return A;
		} else {
			return greateCommonDivisor(B, A % B);
		}
	}
}
