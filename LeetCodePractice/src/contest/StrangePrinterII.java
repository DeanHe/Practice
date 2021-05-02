package contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
There is a strange printer with the following two special requirements:

On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover up the existing colors in the rectangle.
Once the printer has used a color for the above operation, the same color cannot be used again.
You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.

Return true if it is possible to print the matrix targetGrid, otherwise, return false.



Example 1:



Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
Output: true
Example 2:



Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
Output: true
Example 3:

Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
Output: false
Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different turns.
Example 4:

Input: targetGrid = [[1,1,1],[3,1,3]]
Output: false


Constraints:

m == targetGrid.length
n == targetGrid[i].length
1 <= m, n <= 60
1 <= targetGrid[row][col] <= 60
 */
public class StrangePrinterII {

    public boolean isPrintable(int[][] targetGrid) {
        Map<Integer, int[]> colorArea = new HashMap<>();
        int rows = targetGrid.length;
        int cols = targetGrid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int color = targetGrid[r][c];
                int[] dimensions = colorArea.getOrDefault(color, new int[]{100, -100, 100, -100});
                dimensions[0] = Math.min(dimensions[0], r); //top
                dimensions[1] = Math.max(dimensions[1], r); //bottom
                dimensions[2] = Math.min(dimensions[2], c); //left
                dimensions[3] = Math.max(dimensions[3], c); //right
                colorArea.put(color, dimensions);
            }
        }
        Set<Integer> colors = new HashSet<>(colorArea.keySet());
        while (!colors.isEmpty()) {
            Set<Integer> toErase = new HashSet<>();
            for(int color : colors){
                if(!toErase.contains(color)){
                    if(!erase(color, targetGrid, colorArea.get(color))){
                        toErase.add(color);
                    }
                }
            }
            if(toErase.size() == colors.size()){
                return false;
            }
            colors = toErase;
        }
        return true;
    }

    private boolean erase(int color, int[][] targetGrid, int[] dimensions) {
        for (int r = dimensions[0]; r <= dimensions[1]; r++) {
            for (int c = dimensions[2]; c <= dimensions[3]; c++) {
                if (targetGrid[r][c] > 0 && targetGrid[r][c] != color) {
                    return false;
                }
            }
        }
        for (int r = dimensions[0]; r <= dimensions[1]; r++) {
            for (int c = dimensions[2]; c <= dimensions[3]; c++) {
                targetGrid[r][c] = 0;
            }
        }
        return true;
    }
}
