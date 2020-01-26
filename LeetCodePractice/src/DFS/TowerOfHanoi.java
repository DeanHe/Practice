package DFS;

import java.util.ArrayList;
import java.util.List;

/*
        Tower of Hanoi problem, is a well-known problem. On the A, B, C three pillars, there are n disks of different sizes (radii 1-n), they are stacked in a start on A, your goal is to a minimum number of legal steps to move all the plates move from A to C tower tower.
        Each step in the rules of the game are as follows:

        Each step is only allowed to move a plate (from the top of one pillars to the top of another pillars)
        The process of moving, you must ensure that a large dish is not at the top of the small plates (small can be placed on top of a large, below the maximum plate size can not have any other dish)
        Diagram:
        hanoi

        Example
        Example 1:

        Input:n = 2
        Output: ["from A to B","from A to C","from B to C"]
        Example 2:

        Input:n = 3
        Output:["from A to C","from A to B","from C to B","from A to C","from B to A","from B to C","from A to C"]
*/
public class TowerOfHanoi {
    /**
     * @param n: the number of disks
     * @return: the order of moves
     */
    public List<String> towerOfHanoi(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, 'A', 'B', 'C', res);
        return res;
    }

    private void dfs(int n, char from, char buffer, char to, List<String> res) {
        if (n == 1) {
            res.add(moveTo(from, to));
            return;
        }
        dfs(n - 1, from, to, buffer, res);
        res.add(moveTo(from, to));
        dfs(n - 1, buffer, from, to, res);
    }

    private String moveTo(char from, char to) {
        return "from " + from + " to " + to;
    }
}
