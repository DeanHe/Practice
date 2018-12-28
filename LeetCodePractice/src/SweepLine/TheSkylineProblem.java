package SweepLine;

import java.util.*;

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
		PriorityQueue<Integer> heightHeap = new PriorityQueue<>(10, Collections.reverseOrder());
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
