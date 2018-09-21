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
            // 左子区
            if(start <= mid) {
                if(end <= mid){ // 包含
                    leftCount = querySegmentTree(root.left, start, end);
                } else { // 分裂
                    leftCount = querySegmentTree(root.left, start, mid);
                }
            } 
            // 右子区
            if(end >= mid + 1) {
                if(start >= mid + 1){ // 包含
                    rightCount = querySegmentTree(root.right, start, end);
                } else { // 分裂
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
            int mid = (root.start + root.end) / 2;   // 查询
            if(root.start <= index && index <= mid){
                modifySegmentTree(root.left, index, value);
            }
            if(index >= mid + 1 && index <= root.end){
                modifySegmentTree(root.right, index, value);
            }
            root.count = root.left.count + root.right.count;  //更新
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
