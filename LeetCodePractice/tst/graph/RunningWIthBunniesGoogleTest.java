package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/*

 */
public class RunningWIthBunniesGoogleTest {
    private RunningWithBunniesGoogle rw;

    @Before
    public void setup() {
        rw = new RunningWithBunniesGoogle();
    }

    @Test
    public void testCase1() {
        int[][] times = {{0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0}};
        int time_limit = 1;
        int[] res= rw.solution(times, time_limit);
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testCase2() {
        int[][] times = {{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}};
        int time_limit = 3;
        int[] res= rw.solution(times, time_limit);
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testCase3() {
        int[][] times = {{0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0}};
        int time_limit = 1;
        int[] res= rw.solution(times, time_limit);
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testCase4() {
        rw.V = 7;
        List<List<Integer>> paths = rw.pathGenerate(3);
        System.out.println(paths);
    }
}
