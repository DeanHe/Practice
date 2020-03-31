package SegmentTree;

import java.util.*;

/*
        Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller than the given integer.
        We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

        Have you met this question in a real interview?

        Example
        Example 1:
        Input: array =[1,2,7,8,5] queries =[1,8,5]
        Output:[0,4,2]

        Example 2:
        Input: array =[3,4,5,8] queries =[2,4]
        Output:[0,1]
*/
public class CountOfSmallerNumber {
    /**
     * @param A:       An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        SegmentTreeNode root = build(0, 10000);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            modify(root, A[i], 1);
        }
        for (int i = 0; i < queries.length; i++) {
            int count = query(root, 0, queries[i] - 1);
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

    public SegmentTreeNode build(int start, int end) {
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
