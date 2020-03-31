package SegmentTree;

/*
There are n cities on an axis, numbers from 0 ~ n - 1. John intends to do business in these n cities, He is interested in Armani's shipment. Each city has a price for these goods prices [i].
For city x, John can buy the goods from the city numbered from x - k to x + k, and sell them to city x. We want to know how much John can earn at most in each city?

Example
Example1

Input: prices = [1, 3, 2, 1, 5] and k = 2
Output: [0, 2, 1, 0, 4]
Explanation:
i = 0, John can go to the city 0 ~ 2. He can not make money because the prices in city 1 and city 2 are both higher than the price in city 0, that is, ans[0] = 0;
i = 1, John can go to the city 0~3. He can buy from city 0 or city 3 to earn the largest price difference. That is, ans[1] = 2.
i = 2, John can go to the city 0~4. Obviously, he can earn the largest price difference by buying from city 3. That is, ans[2] = 1.
i = 3, John can go to the city 1~4. He can not make money cause city 3 has the lowest price. That is, ans[3] = 0.
i = 4, John can go to the city 2~4. He can earn the largest price difference by buying from city 3. That is, ans[4] = 4.
Example2

Input: prices = [1, 1, 1, 1, 1] and k = 1
Output: [0, 0, 0, 0, 0]
Explanation:
All cities are the same price, so John can not make money, that is, all ans are 0.
Notice
prices.length range is [2, 100000], k <= 100000.
*/
public class JohnsBusiness {
    /**
     * @param A: The prices [i]
     * @param k:
     * @return: The ans array
     */
    public int[] business(int[] A, int k) {
        int len = A.length;
        int[] res = new int[len];
        SegmentTreeNode root = build(0, len - 1, A);
        for (int i = 0; i < len; i++) {
            int start = i - k;
            int end = i + k;
            if (start < 0) {
                start = 0;
            }
            if (end >= len) {
                end = len - 1;
            }
            res[i] = A[i] - query(root, start, end);
        }
        return res;
    }

    class SegmentTreeNode {
        int start, end, min;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
            this.left = this.right = null;
        }
    }

    private SegmentTreeNode build(int start, int end, int[] nums){
        SegmentTreeNode node = new SegmentTreeNode(start, end, nums[start]);
        if(start == end){
            return node;
        }
        int mid = (node.start + node.end) / 2;
        node.left = build(start, mid, nums);
        node.right =build(mid + 1, end, nums);
        node.min = Math.min(node.left.min, node.right.min);
        return node;
    }

    private int query(SegmentTreeNode node, int start, int end) {
        if (start <= node.start && node.end <= end) {
            return node.min;
        }
        int mid = (node.start + node.end) / 2;
        if (end <= mid) {
            return query(node.left, start, end);
        }
        if (mid < start) {
            return query(node.right, start, end);
        }
        int leftMin = query(node.left, start, mid);
        int rightMin = query(node.right, mid + 1, end);
        return Math.min(leftMin, rightMin);
    }
}
