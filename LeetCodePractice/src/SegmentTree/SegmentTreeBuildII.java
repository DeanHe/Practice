public class SegmentTreeBuildII {
	class SegmentTreeNode {
		public int start, end, max;
		public SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end, int max) {
		          this.start = start;
		          this.end = end;
		          this.max = max;
		          this.left = this.right = null;
		      }
	}
	/**
     * @param A: a list of integer
     * @return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        // write your code here
        return buildHelper(0, A.length - 1, A);
    }
    
    private SegmentTreeNode buildHelper(int start, int end, int[] A){
        if(start > end){
            return null;
        }
        if(start == end){
            return new SegmentTreeNode(start, end, A[start]);
        }
        int mid = (start  + end) / 2;
        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
        node.left = buildHelper(start, mid, A);
        node.right = buildHelper(mid + 1, end, A);
        if(node.left != null){
            node.max = Math.max(node.max, node.left.max);
        }
        if(node.right != null){
            node.max = Math.max(node.max, node.right.max);
        }
        return node;
    }
}
