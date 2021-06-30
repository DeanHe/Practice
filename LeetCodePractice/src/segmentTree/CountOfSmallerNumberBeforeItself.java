package segmentTree;
/*
Give you an integer array (index from 0 to n-1, where n is the size of this array, data value from 0 to 10000) . For each element Ai in the array, count the number of element before this element Ai is smaller than it and return count number array.

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
We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number first.
*/

import java.util.*;

public class CountOfSmallerNumberBeforeItself {
    /**
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        SegmentTreeNode root = build(0, 10000);
        List<Integer> res = new ArrayList<>();
        int count;
        for (int i = 0; i < A.length; i++) {
            count = 0;
            if (A[i] > 0) {
                count = query(root, 0, A[i] - 1);
            }
            modify(root, A[i], 1);
            res.add(count);
        }
        return res;
    }

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

    private SegmentTreeNode build(int start, int end) {
        SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
        if (start == end) {
            node.count = 0;
        } else {
            int mid = (start + end) / 2;
            node.left = build(start, mid);
            node.right = build(mid + 1, end);
        }
        return node;
    }

    private int query(SegmentTreeNode node, int start, int end) {
        if (start <= node.start && node.end <= end) {
            return node.count;
        }
        int mid = (node.start + node.end) / 2;
        if(end <= mid){
            return query(node.left, start, end);
        }
        if(mid < start){
            return query(node.right, start, end);
        }
        int leftCount = query(node.left, start, mid);
        int rightCount = query(node.right, mid + 1, end);
        return leftCount + rightCount;
    }

    private void modify(SegmentTreeNode node, int index, int value) {
        if (node.start == index && node.end == index) {
            node.count += value;
            return;
        }
        int mid = (node.start + node.end) / 2;   //query
        if (node.start <= index && index <= mid) {
            modify(node.left, index, value);
        }
        if (index >= mid + 1 && index <= node.end) {
            modify(node.right, index, value);
        }
        node.count = node.left.count + node.right.count;  //update
    }
}
