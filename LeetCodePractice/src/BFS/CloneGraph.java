package BFS;

import java.util.*;

/*Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
How we serialize an undirected graph:http://www.lintcode.com/help/graph/
Nodes are labeled uniquely.
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.
The graph has a total of three nodes, and therefore contains three parts as separated by #.
First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

   1
  / \
 /   \
0 --- 2
     / \
     \_/
Example
return a deep copied graph.*/
public class CloneGraph {
	class UndirectedGraphNode {
		  int label;
		  ArrayList<UndirectedGraphNode> neighbors;
		  UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	 };
	/*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
    	if(node == null){
    		return null;
    	}
    	UndirectedGraphNode root_copy = new UndirectedGraphNode(node.label);
    	// map between original node and copied node
    	Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    	map.put(node, root_copy);
    	Queue<UndirectedGraphNode> queue = new LinkedList<>();
    	queue.offer(node);
    	while (!queue.isEmpty()) {
			UndirectedGraphNode cur = queue.poll();
			List<UndirectedGraphNode> cur_nbs = cur.neighbors;
			for(UndirectedGraphNode cur_nb : cur_nbs){
				UndirectedGraphNode cur_copy = map.get(cur);
				UndirectedGraphNode cur_nb_copy = null;
				if(!map.containsKey(cur_nb)){
					cur_nb_copy = new UndirectedGraphNode(cur_nb.label);
					map.put(cur_nb, cur_nb_copy);
					queue.offer(cur_nb);
				} else {
					cur_nb_copy = map.get(cur_nb);
				}
				cur_copy.neighbors.add(cur_nb_copy);
			}
		}
    	return root_copy;
    }
}
