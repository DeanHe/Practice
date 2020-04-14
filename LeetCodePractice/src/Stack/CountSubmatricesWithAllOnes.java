package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/*Given a m * n matrix of ones and zeros, return how many sub matrices have all ones.

        Example 1:

        Input: matrix =
        [
        [1,1,1],
        [1,1,1],
        [1,1,1]
        ]
        Output: 36
        Explanation: All the possible submatrices will have only 1s.
Since, there are 36 submatrices in total, ans = 36

        Example 2:

        Input: matrix =
        [
        [1,1,1],
        [1,0,1],
        [1,1,1]
        ]
        Output: 20

        Constraints:

        1 <= arr.length <= 300
        1 <= arr[0].length <= 300
        0 <= arr[i][j] <= 1

        similar to MaximalSquare
*/
public class CountSubmatricesWithAllOnes {
    public int countMatricies(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length, count = 0;
        int[] height = new int[cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(matrix[r][c] == 1){
                    height[c]++;
                } else {
                    height[c] = 0;
                }
            }
            count += helper(height);
        }
        return count;
    }

    public int helper(int[] heights){
        Stack<int[]> stack = new Stack<>(); // [height, idx]
        int count = 0; // count of rectangles for the given row
        int totalSum = 0;
        System.out.println(Arrays.toString(heights));
        for(int c = 0; c < heights.length; c++) {
            int biggerCnt = 0; // count of rectangles bigger than current index
            while(!stack.isEmpty() && stack.peek()[0] > heights[c]){
                int[] top = stack.pop();
                int preHeight = top[0];
                biggerCnt += top[1] + 1;
                totalSum -= (preHeight - heights[c])*(top[1]+1);
            }

            totalSum += heights[c]; // number of rectangles made by this height, using
            // height[c] as the base.
            // Suppose height 3, for some matrix[i][j], then using this as a base of rectangle of size (3 * 1)
            // how many rectangles you can make, it will be 3.

            // rectangles you can make with previous added rectangles
            // with each new rectangle you can make rectangles with previously added ones.
            // 3*1, 4*1, 5*1
            // for 3*1 = 3 rectangles
            // for 4*1 = 4 rectangles + 3 rectangles with 3*1
            // for 5*1 = 5 rectnagles + 4 rectangles with 4*1 + 3 rectangles with 3*1 = 5 rectangles + count(4*1)

            // Now suppose height 3*1, 4*1, 5*1, 3*1
            // for 3*1 = 3 rectangles
            // for 4*1 = 4 rectangles + 3 rectangles with 3*1
            // for 5*1 = 5 rectnagles + 4 rectangles with 4*1 + 3 rectangles with 3*1
            // for 3*1 = 3 rectangles + count(5*1) - (2 rectangles with 5*1) - (1 rectangles with 4*1);
            count += totalSum;

            stack.push(new int[] {heights[c], biggerCnt});
        }
        return count;
    }
}
