package binaryIndexedTree;
/*
You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].

A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and nums2.
In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of the value v in nums2,
then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.

Return the total number of good triplets.


Example 1:
Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
Output: 1
Explanation:
There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.

Example 2:
Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
Output: 4
Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).

Constraints:
n == nums1.length == nums2.length
3 <= n <= 10^5
0 <= nums1[i], nums2[i] <= n - 1
nums1 and nums2 are permutations of [0, 1, ..., n - 1].

hint:
1 For every value y, how can you find the number of values x (0 ≤ x, y ≤ n - 1) such that x appears before y in both of the arrays?
2 Similarly, for every value y, try finding the number of values z (0 ≤ y, z ≤ n - 1) such that z appears after y in both of the arrays.
3 Now, for every value y, count the number of good triplets that can be formed if y is considered as the middle element.

Analysis:
for each y, the total combinations count is left smaller positions * right larger positions
TC: O(NlogN)
 */
public class CountGoodTripletsInAnArray {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }
        long[] left = new long[n];
        long[] right = new long[n];
        BinaryIndexTree tree1 = new BinaryIndexTree(n + 1);
        for (int i = 0; i < n; i++) {
            int idx = pos[nums1[i]];
            left[i] = tree1.preSum(idx - 1);
            System.out.println("nums1:" + nums1[i] + " idx:" + idx + "left:" + left[i]);
            tree1.update(idx);
        }
        BinaryIndexTree tree2 = new BinaryIndexTree(n + 1);
        for (int i = n - 1; i >= 0; i--) {
            int idx = pos[nums1[i]];
            right[i] = tree2.preSum(n) - tree2.preSum(idx - 1);
            System.out.println("nums1:" + nums1[i] + " idx:" + idx + "right:" + right[i]);
            tree2.update(idx);
        }
        long res = 0;
        for(int i = 0; i < n; i++){
            res += left[i] * right[i];
        }
        return res;
    }

    private class BinaryIndexTree {
        int[] bit;

        public BinaryIndexTree(int n){
            bit = new int[n + 1];
        }

        public void update(int idx){
            for(int i = idx + 1; i < bit.length; i += lowbit(i)){
                bit[i]++;
            }
        }

        public int preSum(int idx){
            int sum = 0;
            for(int i = idx + 1; i > 0; i -= lowbit(i)){
                sum += bit[i];
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & -x;
        }
    }
}
