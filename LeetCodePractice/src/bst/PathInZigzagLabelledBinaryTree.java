package bst;

import java.util.*;

/*
In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.



Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

 

Example 1:

Input: label = 14
Output: [1,3,4,14]
Example 2:

Input: label = 26
Output: [1,2,6,10,26]
*/
public class PathInZigzagLabelledBinaryTree {
	public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList<>();
        int height = getHeight(label);
        while(label > 0){
            res.addFirst(label);
            int parentLvlUpperBound = (1 << height) - 1;
            int parentLvlLowerBound = 1 <<(height - 1);
            // The pre level is sorted in different order than current one.
            //So the new label is adjusted accordingly
            label = parentLvlLowerBound + parentLvlUpperBound - label / 2;
            height--;
        }
        return res;
    }
	private int getHeight(int val){
		int height = 0;
		while(val / 2 > 0){
			val /= 2;
			height++;
		}
		return height;
	}
}
