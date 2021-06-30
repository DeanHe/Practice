package bfs;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ShortestPathInaGridWithObstaclesEliminationTest {
    private ShortestPathInaGridWithObstaclesElimination sp;

    @Before
    public void setup() {
        sp = new ShortestPathInaGridWithObstaclesElimination();
    }

    @Test
    public void testCase1() {
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        };
        List<int[]> path = sp.shortestPathII(grid, 1);
        for (int[] node : path) {
            System.out.println(Arrays.toString(node));
        }
    }

    @Test
    public void testCase2() {
        int[][] grid = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 0, 0}
        };
        List<int[]> path = sp.shortestPathII(grid, 1);
        for (int[] node : path) {
            System.out.println(Arrays.toString(node));
        }
    }
}
