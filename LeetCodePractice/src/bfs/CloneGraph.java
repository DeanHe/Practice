package bfs;

import java.util.*;

/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
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
return a deep copied graph.
*/
public class CloneGraph {
	class Node {
		  int val;
		  ArrayList<Node> neighbors;
		  Node(int x) { val = x; neighbors = new ArrayList<>(); }
	 };
	/*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public Node cloneGraph(Node node) {
        // write your code here
    	if(node == null){
    		return null;
    	}
    	Node head = new Node(node.val);
    	// map between original node and copied node
    	Map<Node, Node> map = new HashMap<>();
    	map.put(node, head);
    	Queue<Node> queue = new LinkedList<>();
    	queue.offer(node);
    	while (!queue.isEmpty()) {
			Node cur = queue.poll();
			Node copy = map.get(cur);
			for(Node nb : cur.neighbors){
				Node nb_copy = null;
				if(!map.containsKey(nb)){
					nb_copy = new Node(nb.val);
					map.put(nb, nb_copy);
					queue.offer(nb);
				} else {
					nb_copy = map.get(nb);
				}
				copy.neighbors.add(nb_copy);
			}
		}
    	return head;
    }
}
