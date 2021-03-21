package bst;
/*

If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

*/
public class PathSumIV {
	public int pathSum(int[] nums) {
        // process from back to front to count how many times each number appear in the sum. 
		// It is O(n^2) but can be optimized easily to O(nlogn). The code is very short.
        int n = nums.length;
        int[] count = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            if (count[i] == 0) {
                int level = nums[i] / 100;
                int pos = (nums[i] / 10) % 10;
                count[i] = 1;
                while (level > 1) {
                    level--;
                    pos = (pos + 1) / 2;
                    int val = level * 10 + pos;
                    for (int j = i - 1; j >= 0; --j) {
                        if (nums[j] / 10 == val) {
                            count[j]++;
                            break;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += count[i] * (nums[i] % 10);
        }
        return sum;
    }
}
