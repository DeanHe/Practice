package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.


        Example 1:



        Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [1,4,2,7,5,3,8,6,9]
        Example 2:



        Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
        Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
        Example 3:

        Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
        Output: [1,4,2,5,3,8,6,9,7,10,11]
        Example 4:

        Input: nums = [[1,2,3,4,5,6]]
        Output: [1,2,3,4,5,6]


        Constraints:

        1 <= nums.length <= 10^5
        1 <= nums[i].length <= 10^5
        1 <= nums[i][j] <= 10^9
        There at most 10^5 elements in nums.
*/
public class DiagonalTraverseII {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int size = 0, maxKey = 0, i = 0;
        Map<Integer, List<Integer>> diagonalMap = new HashMap<>();
        for (int r = nums.size() - 1; r >= 0; r++) { // diagonals all start from last row
            for (int c = 0; c < nums.get(r).size(); c++) {
                diagonalMap.putIfAbsent(r + c, new ArrayList<>());
                diagonalMap.get(r + c).add(nums.get(r).get(c));
                maxKey = Math.max(maxKey, r + c);
                size++;
            }
        }
        int[] res = new int[size];
        for (int key = 0; key <= maxKey; key++) {
            if (diagonalMap.containsKey(key)) {
                for (int val : diagonalMap.get(key)) {
                    res[i] = val;
                    i++;
                }
            }
        }
        return res;
    }
}
