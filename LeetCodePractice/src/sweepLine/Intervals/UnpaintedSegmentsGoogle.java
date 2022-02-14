package sweepLine.Intervals;

import segmentTree.SegmentTreeBuild;

import java.util.ArrayList;
import java.util.List;

/*
Calculate and output the length of unpainted segments inside [A, B] and then paints it.
Example input: N=4, [4, 10], [7, 13] [20, 30] [1, 40]
Output:
6, 3, 10, 20
Explain:
6 : 10-4
3 : [7, 10] already painted, [10, 13] is unpainted segment inside [7, 13], 13 -10 = 3
10: 30 - 20
20: [4, 13] and [20‍‌‌‍‌‌‌‌‍‍‌‍‍‍‌‌‌‍, 30] segments are already painted, [1, 4] [13, 20] [30, 40] are unpainted inside [1, 40],
(4-1) + (20-13) + (40-30) = 20

similar to insert interval
 */
public class UnpaintedSegmentsGoogle {
    public int[] unpaintedArea(int n, List<Interval> intervals){
        int[] res = new int[n];
        List<Interval> axis = new ArrayList<>();
        for(int i = 0; i < n; i++){
            axis = insert(axis, intervals.get(i), i, res);
        }
        return res;
    }

    private List<Interval> insert(List<Interval> intervals, Interval cur, int i, int[] res) {
        List<Interval> axis = new ArrayList<>();
        int insertPos = 0, sum = cur.end - cur.start;
        for(Interval it : intervals){
            if(it.end < cur.start){
                axis.add(it);
                insertPos++;
            } else if(cur.end < it.start){
                axis.add(it);
            } else {
                sum -= Math.min(it.end, cur.end) - Math.max(it.start, cur.start);
                cur.start = Math.min(it.start, cur.start);
                cur.end = Math.max(it.end, cur.end);
            }
        }
        axis.add(insertPos, cur);
        res[i] = sum;
        return axis;
    }

    // Segment tree TC O(NlogN)
    public int[] unpaintedArea2(int n, List<Interval> intervals){
        int[] res = new int[n];
        SNode root = build(0, 100);
        for(int i = 0; i < n; i++){
            Interval cur = intervals.get(i);
            int inner = query(root, cur.start, cur.end);
            res[i] = cur.end - cur.start - inner;
            for(int j = cur.start; j < cur.end; j++){
                modify(root, j, 1);
            }
        }
        return res;
    }

    private SNode build(int start, int end) {
        if(start > end){
            return null;
        }
        if(start == end){
            return new SNode(start, end, 0);
        }
        SNode node = new SNode(start, end, 0);
        int mid = (start + end) / 2;
        node.left = build(start, mid);
        node.right = build(mid + 1, end);
        return node;
    }

    private void modify(SNode root, int index, int value) {
        if (index < root.start || index > root.end) {
            return;
        }
        if (root.start == index && root.end == index) {
            root.count = value;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }
        root.count = root.left.count + root.right.count;
    }

    private int query(SNode root, int start, int end) {
        // write your code here
        if (start > end || root == null) {
            return 0;
        }
        if (start <= root.start && root.end <= end) {
            return root.count;
        }
        int mid = (root.start + root.end) / 2;
        if (end <= mid) {
            return query(root.left, start, end);
        } else if (mid < start) {
            return query(root.right, start, end);
        } else {
            return query(root.left, start, mid) + query(root.right, mid + 1, end);
        }
    }

    class SNode {
        public int start, end, count;
        public SNode left, right;

        public SNode(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
            this.left = this.right = null;
        }
    }
}
