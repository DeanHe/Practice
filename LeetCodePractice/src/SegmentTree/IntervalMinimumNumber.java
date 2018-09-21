import java.util.*;

public class IntervalMinimumNumber {
	class Interval {
		      int start, end;
		      Interval(int start, int end) {
		          this.start = start;
		          this.end = end;
		      }
		  }
	/**
     * @param A: An integer array
     * @param queries: An query list
     * @return: The result list
     */
    class SegmentTreeNode {
        int start, end, minVal;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end){
            this.start = start;
            this.end = end;
            left = null;
            right = null;
        }
    }
    SegmentTreeNode root;
    public SegmentTreeNode buildSegmentTree(int start, int end, int[] A){
        if(start > end){
            return null;
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        if(start == end){
            node.minVal = A[start];
        } else {
            int mid = (start + end) / 2;
            node.left = buildSegmentTree(start, mid, A);
            node.right = buildSegmentTree(mid + 1, end, A);
            node.minVal = Math.min(node.left.minVal, node.right.minVal);
        }
        return node;
    }
    
    public int querySegmentTree(SegmentTreeNode node, int start, int end){
        if(start > end){
            return Integer.MAX_VALUE;
        }
        if(start == node.start && node.end == end){
            return node.minVal;
        } 
        int mid = (node.start + node.end) / 2;
        int leftMin = Integer.MAX_VALUE, rightMin = Integer.MAX_VALUE;
        if(start <= mid){
            if(end <= mid){
                leftMin = querySegmentTree(node.left, start, end);
            } else {
                leftMin = querySegmentTree(node.left, start, mid);
            }
        }
        if(end >= mid + 1){
            if(start >= mid + 1){
                rightMin = querySegmentTree(node.right, start, end);
            } else {
                rightMin = querySegmentTree(node.right, mid + 1, end);
            }
        }
        return Math.min(leftMin, rightMin);
    }
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
        root = buildSegmentTree(0, A.length - 1, A);
        List<Integer> res = new ArrayList<>();
        for(Interval i : queries){
            int temp = querySegmentTree(root, i.start, i.end);
            res.add(temp);
        }
        return res;
    }
}
