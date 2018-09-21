
public class SegmentTreeBuild {
	/**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start > end){
            return null;
        }
        if(start == end){
            return new SegmentTreeNode(start, end);
        }
        int mid = (start + end) / 2;
        SegmentTreeNode left = build(start, mid);
        SegmentTreeNode right = build(mid + 1, end);
        SegmentTreeNode node = new SegmentTreeNode(start, end); 
        node.left = left;
        node.right = right;
        return node;
    }
    
    class SegmentTreeNode {
    	public int start, end;
    	public SegmentTreeNode left, right;

    	public SegmentTreeNode(int start, int end) {
    		this.start = start;
    		this.end = end;
    		this.left = this.right = null;
    	}
    }
}