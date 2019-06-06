package SegmentTree;
/*Give you an integer array (index from 0 to n-1, where n is the size of this array, data value from 0 to 10000) . For each element Ai in the array, count the number of element before this element Ai is smaller than it and return count number array.

Example
Example 1:

Input:
[1,2,7,8,5]
Output:
[0,1,2,3,2]
Example 2:

Input:
[7,8,2,1,3]
Output:
[0,1,0,0,2]
Clarification
Before you do this, you'd better complete the following three questions： Segment Tree Build， Segment Tree Query II，and Count of Smaller Number before itself I 。

Notice
We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number first.*/
import java.util.*;

public class CountOfSmallerNumberBeforeItself {
	/**
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
     class SegmentTreeNode {
        public int start, end;
        public int count;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int count) {
              this.start = start;
              this.end = end;
              this.count = count;
              this.left = this.right = null;
        }
     }
        SegmentTreeNode root;
        public SegmentTreeNode build(int start, int end) {
            if(start > end){
                return null;
            }
            SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
            if(start == end){
                node.count = 0; 
            } else {
                int mid = (start + end) / 2;
                node.left = build(start, mid);
                node.right = build(mid + 1, end);
            }
            return node;
        }
        public int querySegmentTree(SegmentTreeNode root, int start, int end) {
            if(start == root.start && end == root.end){
                return root.count;
            }
            int mid = (root.start + root.end) / 2;
            int leftCount = 0, rightCount = 0;
            //left range
            if(start <= mid) {
                if(end <= mid){ //contain
                    leftCount = querySegmentTree(root.left, start, end);
                } else { //right range
                    leftCount = querySegmentTree(root.left, start, mid);
                }
            } 
            //right range
            if(end >= mid + 1) {
                if(start >= mid + 1){ //contain
                    rightCount = querySegmentTree(root.right, start, end);
                } else { //split
                    rightCount = querySegmentTree(root.right, mid + 1, end);
                }
            }
            return leftCount + rightCount;
        }
        public void modifySegmentTree(SegmentTreeNode root, int index, int value) {
            if(root.start == index && root.end == index){
                root.count += value;
                return;
            }
            int mid = (root.start + root.end) / 2;   //query
            if(root.start <= index && index <= mid){
                modifySegmentTree(root.left, index, value);
            }
            if(index >= mid + 1 && index <= root.end){
                modifySegmentTree(root.right, index, value);
            }
            root.count = root.left.count + root.right.count;  //update
        }
    public List<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        root = build(0, 10000);
        List<Integer> res = new ArrayList<>();
        int count;
        for(int i = 0; i < A.length; i++){
            count = 0;
            if(A[i] > 0){
                count = querySegmentTree(root, 0, A[i] - 1);
            }
            modifySegmentTree(root, A[i], 1);
            res.add(count);
        }
        return res;
    }
}
