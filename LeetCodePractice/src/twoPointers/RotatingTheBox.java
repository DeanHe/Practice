package twoPointers;

/*
You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:

A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.

It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.

Return an n x m matrix representing the box after the rotation described above.



Example 1:



Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]
Example 2:



Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]
Example 3:



Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]


Constraints:

m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.

hint:
Rotate the box using the relation rotatedBox[i][j] = box[m - 1 - j][i].
Start iterating from the bottom of the box and for each empty cell check if there is any stone above it with no obstacles between them.
 */
public class RotatingTheBox {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length, cols = box[0].length;
        char[][] res = new char[cols][rows];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                res[c][rows - 1 - r] = box[r][c];
            }
        }
        rows = res.length;
        cols = res[0].length;
        for(int c = 0; c < cols; c++){
            int i = rows - 1;
            for(int r = rows - 1; r >= 0; r--){
                if(res[r][c] == '*'){
                    i = r - 1;
                } else if(res[r][c] == '#'){
                    res[r][c] = '.';
                    res[i][c] = '#';
                    i--;
                }
            }
        }
        return res;
    }
}

