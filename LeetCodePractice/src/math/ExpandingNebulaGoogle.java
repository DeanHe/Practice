package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
You've escaped Commander Lambda's exploding space station along with numerous escape pods full of bunnies. But -- oh no! -- one of the escape pods has flown into a nearby nebula, causing you to lose track of it. You start monitoring the nebula, but unfortunately, just a moment too late to find where the pod went. However, you do find that the gas of the steadily expanding nebula follows a simple pattern, meaning that you should be able to determine the previous state of the gas and narrow down where you might find the pod.

From the scans of the nebula, you have found that it is very flat and distributed in distinct patches, so you can model it as a 2D grid. You find that the current existence of gas in a cell of the grid is determined exactly by its 4 nearby cells, specifically, (1) that cell, (2) the cell below it, (3) the cell to the right of it, and (4) the cell below and to the right of it. If, in the current state, exactly 1 of those 4 cells in the 2x2 block has gas, then it will also have gas in the next state. Otherwise, the cell will be empty in the next state.

For example, let's say the previous state of the grid (p) was:
.O..
..O.
...O
O...

To see how this grid will change to become the current grid (c) over the next time step, consider the 2x2 blocks of cells around each cell.  Of the 2x2 block of [p[0][0], p[0][1], p[1][0], p[1][1]], only p[0][1] has gas in it, which means this 2x2 block would become cell c[0][0] with gas in the next time step:
.O -> O
..

Likewise, in the next 2x2 block to the right consisting of [p[0][1], p[0][2], p[1][1], p[1][2]], two of the containing cells have gas, so in the next state of the grid, c[0][1] will NOT have gas:
O. -> .
.O

Following this pattern to its conclusion, from the previous state p, the current state of the grid c will be:
O.O
.O.
O.O

Note that the resulting output will have 1 fewer row and column, since the bottom and rightmost cells do not have a cell below and to the right of them, respectively.

Write a function solution(g) where g is an array of array of bools saying whether there is gas in each cell (the current scan of the nebula), and return an int with the number of possible previous states that could have resulted in that grid after 1 time step.  For instance, if the function were given the current state c above, it would deduce that the possible previous states were p (given above) as well as its horizontal and vertical reflections, and would return 4. The width of the grid will be between 3 and 50 inclusive, and the height of the grid will be between 3 and 9 inclusive.  The solution will always be less than one billion (10^9).

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

Input:
Solution.solution({{true, true, false, true, false, true, false, true, true, false}, {true, true, false, false, false, false, true, true, true, false}, {true, true, false, false, false, false, false, false, false, true}, {false, true, false, false, false, false, true, true, false, false}})
Output:
    11567

Input:
Solution.solution({{true, false, true}, {false, true, false}, {true, false, true}})
Output:
    4

Input:
Solution.solution({{true, false, true, false, false, true, true, true}, {true, false, true, false, false, false, true, false}, {true, true, true, false, false, false, true, false}, {true, false, true, false, false, false, true, false}, {true, false, true, false, false, true, true, true}}
Output:
    254

analysis:
Cellular Automaton
 */
public class ExpandingNebulaGoogle {
    public int solution(boolean[][] g) {
        g = transpose(g);
        int rows = g.length, cols = g[0].length;
        int[] nums = new int[rows];
        for (int r = 0; r < rows; r++) {
            nums[r] = binary(g[r]);
        }
        Map<Integer, Map<Integer, List<Integer>>> rules = build(nums, cols);
        Map<Integer, Integer> preImage = new HashMap<>();
        for (int n = 0; n < 1 << (cols + 1); n++) {
            preImage.put(n, 1);
        }
        for (int n : nums) {
            Map<Integer, Integer> next = new HashMap<>();
            for (int first : preImage.keySet()) {
                for (int second : rules.get(n).getOrDefault(first, new ArrayList<>())) {
                    next.put(second, next.getOrDefault(second, 0) + preImage.get(first));
                }
            }
            preImage = next;
        }
        int res = preImage.values().stream().mapToInt(n -> n).sum();
        return res;
    }

    // as height size is way smaller than width size, rotate the matrix by height and width
    private boolean[][] transpose(boolean[][] g) {
        int rows = g.length, cols = g[0].length;
        boolean[][] res = new boolean[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                res[c][r] = g[r][c];
            }
        }
        return res;
    }

    private int binary(boolean[] arr) {
        int len = arr.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i]) {
                res |= 1 << (len - 1 - i);
            }
        }
        return res;
    }

    private Map<Integer, Map<Integer, List<Integer>>> build(int[] nums, int len) {
        Set<Integer> numSet = new HashSet<>();
        Arrays.stream(nums).forEach(n -> numSet.add(n));
        Map<Integer, Map<Integer, List<Integer>>> map = new HashMap<>();
        for (int first = 0; first < 1 << (len + 1); first++) {
            for (int second = 0; second < 1 << (len + 1); second++) {
                int state = evolve(first, second, len);
                if (numSet.contains(state)) {
                    map.computeIfAbsent(state, x -> new HashMap<>())
                            .computeIfAbsent(first, x -> new ArrayList<>()).add(second);
                }
            }
        }
        return map;
    }

    private int evolve(int first, int second, int len) {
        int a = first & ~(1 << len);
        int b = second & ~(1 << len);
        int c = first >> 1;
        int d = second >> 1;
        return (a & ~b & ~c & ~d) | (~a & b & ~c & ~d) | (~a & ~b & c & ~d) | (~a & ~b & ~c & d);
    }
}

