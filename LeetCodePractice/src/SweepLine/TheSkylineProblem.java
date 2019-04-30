package SweepLine;

import java.util.*;
/*Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. Buildings may overlap if you see them from far away，find the outline of them。
An outline can be represented by a triple, (start, end, height), where start is the start position on x-axis of the outline, end is the end position on x-axis and height is the height of the outline.

Building Outline

Example
Given 3 buildings：

[
  [1, 3, 3],
  [2, 4, 4],
  [5, 6, 1]
]
The outlines are：

[
  [1, 2, 3],
  [2, 4, 4],
  [5, 6, 1]
]
Notice
Please merge the adjacent outlines if they have the same height and make sure different outlines cant overlap on x-axis.*/
public class TheSkylineProblem {
	private class Edge {
		int x, height;
		boolean isStart;
		public Edge(int x, int height, boolean isStart){
			this.x = x;
			this.height = height;
			this.isStart = isStart;
		}
	}
	
	public List<int[]> getSkyLine(int[][] buildings){
		List<int[]> res = new ArrayList<>();
		if(buildings == null || buildings.length == 0 || buildings[0].length == 0){
			return res;
		}
		List<Edge> edges = new ArrayList<>();
		// add all left/right edges
		for(int[] building : buildings){
			Edge startEdge = new Edge(building[0], building[2], true);
			edges.add(startEdge);
			Edge endEdge = new Edge(building[1], building[2], false);
			edges.add(endEdge);
		}
		// sort edges
		Collections.sort(edges, new Comparator<Edge>() {

			@Override
			public int compare(Edge a, Edge b) {
				if(a.x != b.x){
					return a.x - b.x;
				}
				if(a.isStart && b.isStart){
					return b.height - a.height;
				} else if(!a.isStart && !b.isStart){
					return a.height - b.height;
				} else {
					return a.isStart ? -1 : 1;
				}
			}
			
		});
		// process edges
		PriorityQueue<Integer> heightHeap = new PriorityQueue<>(Collections.reverseOrder());
		for(Edge edge : edges){
			if(edge.isStart){
				if(heightHeap.isEmpty() || edge.height > heightHeap.peek()){
					res.add(new int[]{edge.x, edge.height});
				}
				heightHeap.offer(edge.height);
			} else {
				heightHeap.remove(edge.height);
				if(heightHeap.isEmpty()){
					res.add(new int[]{edge.x, 0});
				} else if(edge.height > heightHeap.peek()){
					res.add(new int[]{edge.x, heightHeap.peek()});
				}
			}			
		}
		return res;
	}
}
