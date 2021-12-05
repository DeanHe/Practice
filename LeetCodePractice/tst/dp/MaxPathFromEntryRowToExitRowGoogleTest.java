package dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
eg matrix:

"#.#..#",
"#.#..#",
"#.##.#",
"#..#.#",
"#..#.#",
"#..#.#"
 */
public class MaxPathFromEntryRowToExitRowGoogleTest {
    private MaxPathFromEntryRowToExitRowGoogle mp;

    @Before
    public void setup() {
        mp = new MaxPathFromEntryRowToExitRowGoogle();
    }

    @Test
    public void testCase1() {
        char[][] matrix = {
                {'#', '.', '#', '.', '.', '#'},
                {'#', '.', '#', '.', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '#', '.', '#'},
                {'#', '.', '.', '#', '.', '#'},
                {'#', '.', '.', '#', '.', '#'},
        };
        int res = mp.maxPath(matrix);
        Assert.assertEquals(9, res);
    }
}
