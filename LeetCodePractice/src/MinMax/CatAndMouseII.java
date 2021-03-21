package MinMax;

/*
 game is played by a cat and a mouse named Cat and Mouse.

The environment is represented by a grid of size rows x cols, where each element is a wall, floor, player (Cat, Mouse), or food.

Players are represented by the characters 'C'(Cat),'M'(Mouse).
Floors are represented by the character '.' and can be walked on.
Walls are represented by the character '#' and cannot be walked on.
Food is represented by the character 'F' and can be walked on.
There is only one of each character 'C', 'M', and 'F' in grid.
Mouse and Cat play according to the following rules:

Mouse moves first, then they take turns to move.
During each turn, Cat and Mouse can jump in one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of the grid.
catJump, mouseJump are the maximum lengths Cat and Mouse can jump at a time, respectively. Cat and Mouse can jump less than the maximum length.
Staying in the same position is allowed.
Mouse can jump over Cat.
The game can end in 4 ways:

If Cat occupies the same position as Mouse, Cat wins.
If Cat reaches the food first, Cat wins.
If Mouse reaches the food first, Mouse wins.
If Mouse cannot get to the food within 1000 turns, Cat wins.
Given a rows x cols matrix grid and two integers catJump and mouseJump, return true if Mouse can win the game if both Cat and Mouse play optimally, otherwise return false.



Example 1:



Input: grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
Output: true
Explanation: Cat cannot catch Mouse on its turn nor can it get the food before Mouse.
Example 2:



Input: grid = ["M.C...F"], catJump = 1, mouseJump = 4
Output: true
Example 3:

Input: grid = ["M.C...F"], catJump = 1, mouseJump = 3
Output: false
Example 4:

Input: grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
Output: false
Example 5:

Input: grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
Output: true


Constraints:

rows == grid.length
cols = grid[i].length
1 <= rows, cols <= 8
grid[i][j] consist only of characters 'C', 'M', 'F', '.', and '#'.
There is only one of each character 'C', 'M', and 'F' in grid.
1 <= catJump, mouseJump <= 8

analysis:
dfs + memo
win function identify this is a winning position for either side
 */
public class CatAndMouseII {
    int rows, cols;
    int[] dirs = {0, 1, 0, -1, 0};
    char[][] g;
    Boolean[][][][][] mem;
    int maxStep = 127;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        rows = grid.length;
        cols = grid[0].length();
        g = new char[rows][cols];
        mem = new Boolean[rows][cols][rows][cols][maxStep + 1];
        int mr = -1, mc = -1, cr = -1, cc = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g[r][c] = grid[r].charAt(c);
                if (g[r][c] == 'M') {
                    mr = r;
                    mc = c;
                } else if (g[r][c] == 'C') {
                    cr = r;
                    cc = c;
                }
            }
        }
        return win(mr, mc, cr, cc, 0, catJump, mouseJump);
    }

    private boolean win(int mr, int mc, int cr, int cc, int step, int catJump, int mouseJump) {
        if (step % 2 == 0) { // mouse
            if(mr == cr && mc == cc){
                return false;
            }
            if (step >= maxStep) {
                return false;
            }
            if(g[mr][mc] == 'F'){
                return true;
            }
            if(g[cr][cc] == 'F'){
                return false;
            }
        } else { // cat
            if(mr == cr && mc == cc){
                return true;
            }
            if (step >= maxStep) {
                return true;
            }
            if(g[mr][mc] == 'F'){
                return false;
            }
            if(g[cr][cc] == 'F'){
                return true;
            }
        }
        if (mem[mr][mc][cr][cc][step] != null) {
            return mem[mr][mc][cr][cc][step];
        }
        if (step % 2 == 0) { // mouse
            for (int i = 0; i < dirs.length - 1; i++) {
                for (int d = 0; d <= mouseJump; d++) {
                    int nb_r = mr + d * dirs[i];
                    int nb_c = mc + d * dirs[i + 1];
                    if (isValid(nb_r, nb_c)) {
                        if (!win(nb_r, nb_c, cr, cc, step + 1, catJump, mouseJump)) {
                            return mem[mr][mc][cr][cc][step] = true;
                        }
                    } else {
                        break;
                    }
                }
            }
            return mem[mr][mc][cr][cc][step] = false;
        } else { // cat, all choices must fail in order for the mouse to win
            for (int i = 0; i < dirs.length - 1; i++) {
                for (int d = 0; d <= catJump; d++) {
                    int nb_r = cr + d * dirs[i];
                    int nb_c = cc + d * dirs[i + 1];
                    if (isValid(nb_r, nb_c)) {
                        if (!win(mr, mc, nb_r, nb_c, step + 1, catJump, mouseJump)) {
                            return mem[mr][mc][cr][cc][step] = true;
                        }
                    } else {
                        break;
                    }
                }
            }
            return mem[mr][mc][cr][cc][step] = false;
        }
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols && g[r][c] != '#';
    }
}
